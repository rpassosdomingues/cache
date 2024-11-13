package src;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;
import org.neo4j.driver.Transaction;
import org.neo4j.driver.exceptions.Neo4jException;

/**
 * Manages directory creation, file organization, and Neo4j graph storage for Practices.
 * Uses Neo4j to store directories and files as nodes, supporting advanced reference and training data generation.
 * Enforces atomic operations and ensures directory uniqueness.
 */
public class Evidence {

    private static final String BASE_DIRECTORY_PATH = "PracticeDirectories";
    private final Map<String, File> practiceDirectories = new HashMap<>();
    private final Driver neo4jDriver;

    /**
     * Initializes the Neo4j driver and base directory.
     * @param uri Neo4j database URI.
     * @param user Neo4j username.
     * @param password Neo4j password.
     * @throws IOException if base directory initialization fails.
     */
    public Evidence(String uri, String user, String password) throws IOException {
        this.neo4jDriver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
        initializeBaseDirectory();
    }

    /**
     * Initializes the base directory for practices if it does not exist.
     * @throws IOException if directory creation fails.
     */
    private void initializeBaseDirectory() throws IOException {
        File baseDir = new File(BASE_DIRECTORY_PATH);
        if (!baseDir.exists()) {
            Files.createDirectory(baseDir.toPath());
        }
    }

    /**
     * Creates directories based on selected practices, avoiding duplicates.
     * Registers each directory as a node in Neo4j.
     *
     * @param selectedTags List of tags to determine directory creation.
     * @throws IOException if directory creation fails.
     */
    public void createPracticeDirectories(List<String> selectedTags) throws IOException {
        List<Practices> practices = Practices.findByTags(selectedTags);

        try (Session session = neo4jDriver.session()) {
            for (Practices practice : practices) {
                String practiceName = practice.getPracticeName();
                String practicePath = BASE_DIRECTORY_PATH + "/" + practiceName;

                if (!practiceDirectories.containsKey(practiceName)) {
                    File practiceDir = new File(practicePath);
                    if (!practiceDir.exists()) {
                        Files.createDirectory(practiceDir.toPath());
                    }
                    practiceDirectories.put(practiceName, practiceDir);
                    createPracticeNode(session, practiceName, selectedTags);
                }
            }
        }
    }

    /**
     * Registers a practice as a node in Neo4j, tagged with selected attributes.
     *
     * @param session Neo4j session.
     * @param practiceName Name of the practice.
     * @param tags Tags associated with the practice.
     */
    private void createPracticeNode(Session session, String practiceName, List<String> tags) {
        try (Transaction tx = session.beginTransaction()) {
            tx.run("MERGE (p:Practice {name: $name, tags: $tags})", 
                   Map.of("name", practiceName, "tags", tags));
            tx.commit();
        } catch (Neo4jException e) {
            System.err.println("Neo4j error during node creation: " + e.getMessage());
        }
    }

    /**
     * Moves a specified file to directories matching selected practices.
     * Allows copying the same file across different practice directories and registers file relationships in Neo4j.
     *
     * @param filePath Path of the file to be moved.
     * @param selectedTags List of tags identifying target directories.
     * @param context Context for the file.
     * @param peopleCount Number of people involved.
     * @throws IOException if file copying fails.
     */
    public void moveFileToDirectories(Path filePath, List<String> selectedTags, String context, int peopleCount) throws IOException {
        List<Practices> practices = Practices.findByTags(selectedTags);

        try (Session session = neo4jDriver.session()) {
            for (Practices practice : practices) {
                File practiceDir = practiceDirectories.get(practice.getPracticeName());

                if (practiceDir != null) {
                    Path targetPath = Paths.get(practiceDir.getPath(), filePath.getFileName().toString());

                    if (!Files.exists(targetPath)) {
                        Files.copy(filePath, targetPath);
                        createFileNode(session, filePath.getFileName().toString(), context, peopleCount, practice.getPracticeName());
                        System.out.println("File moved to: " + targetPath);
                    }
                }
            }
        }
    }

    /**
     * Registers a file as a node in Neo4j and links it to its practice node.
     *
     * @param session Neo4j session.
     * @param fileName Name of the file.
     * @param context Context description.
     * @param peopleCount Number of people involved.
     * @param practiceName Name of the related practice.
     */
    private void createFileNode(Session session, String fileName, String context, int peopleCount, String practiceName) {
        try (Transaction tx = session.beginTransaction()) {
            tx.run("MERGE (f:File {name: $name, context: $context, peopleCount: $peopleCount}) " +
                   "WITH f " +
                   "MATCH (p:Practice {name: $practiceName}) " +
                   "MERGE (f)-[:BELONGS_TO]->(p)",
                   Map.of("name", fileName, "context", context, "peopleCount", peopleCount, "practiceName", practiceName));
            tx.commit();
        } catch (Neo4jException e) {
            System.err.println("Neo4j error during file-node creation: " + e.getMessage());
        }
    }

    /**
     * Verifies the presence of files in corresponding directories.
     *
     * @param filePath Path of the file to verify.
     * @throws IOException if an error occurs during verification.
     */
    public void validateFileStructure(Path filePath) throws IOException {
        for (File dir : practiceDirectories.values()) {
            Path targetPath = Paths.get(dir.getPath(), filePath.getFileName().toString());
            if (Files.exists(targetPath)) {
                System.out.println("Validation passed for file in directory: " + dir.getName());
            } else {
                System.out.println("Validation failed for file in directory: " + dir.getName());
            }
        }
    }

    /**
     * Prepares AI training data by organizing practice directories and files into Neo4j nodes and relationships.
     */
    public void prepareAITrainingData() {
        // Placeholder for future AI training data integration.
        // This method will interact with Neo4j to further structure data for AI processing.
    }

    /**
     * Closes the Neo4j driver connection.
     */
    public void close() {
        neo4jDriver.close();
    }

    public static void main(String[] args) {
        List<String> tags = Arrays.asList("Event", "Promotion");
        try (Evidence manager = new Evidence("bolt://localhost:7687", "neo4j", "password")) {
            manager.createPracticeDirectories(tags);

            Path sampleFile = Paths.get("sample-evidence.txt");
            manager.moveFileToDirectories(sampleFile, tags, "Sample context", 10);

            manager.validateFileStructure(sampleFile);
            manager.prepareAITrainingData();
        } catch (IOException e) {
            System.err.println("IO Error: " + e.getMessage());
        } catch (Neo4jException e) {
            System.err.println("Neo4j Error: " + e.getMessage());
        }
    }
}
