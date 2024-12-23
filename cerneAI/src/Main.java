package src;

import org.neo4j.driver.*;

import javafx.application.Application;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;

import src.Praticas;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Main application class for the JavaFX application that interacts with a Neo4j database
 * to add, remove, and search for words. It also allows the user to select tags and display
 * practices associated with those tags.
 */
public class Main extends Application {
    // Inicializa os Painéis da interface
    private VBox controlPanel;
    private VBox subMenuPanel;
    // Declare the 'state' variable at the class level
    private boolean state = true;  // This will enable buttons by default
    // Inicializa a lista de solicitações de serviços do Maker
    private ArrayList<Maker> pecas;
    // Inicializa a lista de TAG's para identificação de práticas do Cerne
    private static List<String> selectedTags = new ArrayList<>();
    // Inicializa a lista de práticas do Cerne
    private List<Praticas> searchKeyPractices(List<String> selectedTags) {
        // Chama o método findByTags da classe Praticas para encontrar as práticas associadas às tags selecionadas
        return Praticas.findByTags(selectedTags);
    }
    /**
     * Neo4j Database Connector Class
     * This class manages the connection to the Neo4j database.
     */
    static class Neo4jConnector {
        private final Driver driver;

        /**
         * Constructor to initialize the connection to the Neo4j database.
         *
         * @param uri  URI for the Neo4j database connection (e.g., bolt://localhost:7687).
         * @param user The username for the Neo4j database authentication.
         */
        public Neo4jConnector(String uri, String user) {
            // Retrieve the Neo4j password from the environment variable
            String password = System.getenv("NEO4J_PASSWORD");
            if (password == null) {
                throw new IllegalStateException("NEO4J_PASSWORD environment variable is not set!");
            }
            // Create the driver to connect to Neo4j
            this.driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
        }

        /**
         * Returns a session for interacting with the Neo4j database.
         *
         * @return A session to interact with the database.
         */
        public Session getSession() {
            return driver.session();
        }

        /**
         * Closes the connection to the Neo4j database.
         */
        public void close() {
            driver.close();
        }    
    }

    /**
     * Adds a word to the Neo4j database.
     *
     * @param neo4jConnector The Neo4jConnector instance to interact with the database.
     * @param word           The word to add to the database.
     * @param resultLabel    The label where the result message will be displayed.
     */
    private void insertNode(Neo4jConnector neo4jConnector, String word, Label resultLabel) {
        try (Session session = neo4jConnector.getSession()) {
            word = word.toLowerCase();  // Ensure case-insensitivity for word comparison

            // Query to check if the word already exists in the database
            String queryCheck = "MATCH (w:Word {name: $name}) RETURN w";
            Result resultCheck = session.run(queryCheck, Values.parameters("name", word));

            if (resultCheck.hasNext()) {
                resultLabel.setText("The word '" + word + "' already exists.");
            } else {
                // Add the word if it doesn't already exist
                String queryAdd = "CREATE (w:Word {name: $name})";
                session.run(queryAdd, Values.parameters("name", word));
                resultLabel.setText("Word '" + word + "' added.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultLabel.setText("Error adding the word.");
        }
    }

    /**
     * Removes a word from the Neo4j database.
     *
     * @param neo4jConnector The Neo4jConnector instance to interact with the database.
     * @param word           The word to remove from the database.
     * @param resultLabel    The label where the result message will be displayed.
     */
    private void removeNode(Neo4jConnector neo4jConnector, String word, Label resultLabel) {
        try (Session session = neo4jConnector.getSession()) {
            word = word.toLowerCase();  // Ensure case-insensitivity for word comparison

            // Query to check if the word exists
            String queryCheck = "MATCH (w:Word {name: $name}) RETURN w";
            Result resultCheck = session.run(queryCheck, Values.parameters("name", word));

            if (resultCheck.hasNext()) {
                // Remove the word if it exists in the database
                String queryDelete = "MATCH (w:Word {name: $name}) DELETE w";
                session.run(queryDelete, Values.parameters("name", word));
                resultLabel.setText("Word '" + word + "' removed.");
            } else {
                resultLabel.setText("Word '" + word + "' not found for removal.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultLabel.setText("Error removing the word.");
        }
    }

    /**
     * Searches for a word in the Neo4j database.
     *
     * @param neo4jConnector The Neo4jConnector instance to interact with the database.
     * @param word           The word to search for in the database.
     * @param resultLabel    The label where the result message will be displayed.
     */
    private void searchNode(Neo4jConnector neo4jConnector, String word, Label resultLabel) {
        try (Session session = neo4jConnector.getSession()) {
            word = word.toLowerCase();  // Ensure case-insensitivity for search

            // Query to check if the word exists
            String query = "MATCH (w:Word {name: $name}) RETURN w";
            Result result = session.run(query, Values.parameters("name", word));

            // Display the result message based on whether the word is found
            if (result.hasNext()) {
                resultLabel.setText("Word '" + word + "' found.");
            } else {
                resultLabel.setText("Word '" + word + "' not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultLabel.setText("Error searching for the word.");
        }
    }

    /**
     * Initializes and starts the JavaFX application window.
     *
     * @param primaryStage The primary stage for the JavaFX application.
     */
    @Override
    public void start(Stage primaryStage) {
        // Criando a conexão com o Neo4j
        Neo4jConnector neo4jConnector = new Neo4jConnector("bolt://localhost:7687", "neo4j");

        // Criando os botões de ação
        Button inserePraticaCerne = new Button("Registrar Prática");
        Button removePraticaCerne = new Button("Remover Prática");
        Button buscaPraticaCerne = new Button("Buscar Prática");

        // Criando o campo de texto para entrada da palavra
        TextField wordInput = new TextField();
        wordInput.setPromptText("Enter word");
        wordInput.setVisible(false);  // Inicialmente invisível

        // Rótulo para exibir os resultados das operações
        Label resultLabel = new Label();

        // Layout principal: HBox organiza os componentes horizontalmente
        HBox layout = new HBox(20);  // Espaçamento de 20 entre os elementos

        // Painel à esquerda com os botões de ação
        VBox leftPanel = new VBox(10, inserePraticaCerne, removePraticaCerne, buscaPraticaCerne);
        leftPanel.setAlignment(Pos.CENTER);  // Centraliza os botões na coluna

        // Painel à direita com o campo de texto e o rótulo de resultados
        VBox rightPanel = new VBox(10, wordInput, resultLabel);  // Add the word input field and result label here
        rightPanel.setAlignment(Pos.CENTER);  // Center the input field and label vertically

        // Variável para armazenar a operação atual (add, remove, search)
        final String[] operation = {""};

        pecas = new ArrayList<>(); // Inicializando a lista
            primaryStage.setTitle("CERNEBot");

            SplitPane splitPane = new SplitPane(); // Usando SplitPane para dividir a janela
            // Definindo a posição do divisor (0.35 significa 35% da largura para controlPanel)
            splitPane.setDividerPositions(0.35);

            controlPanel = new VBox(10);
            controlPanel.setPadding(new Insets(15));

            subMenuPanel = new VBox(); // Área para exibir os detalhes
            subMenuPanel.setPadding(new Insets(15));
            subMenuPanel.setSpacing(10);

            // Criando os botões
            criaBotoes();

            splitPane.getItems().addAll(controlPanel, subMenuPanel);

            // Adicionando o SplitPane à cena
            Scene scene = new Scene(splitPane, 1920, 1200); // Defina o tamanho da janela conforme necessário
            primaryStage.setScene(scene);
            primaryStage.show(); // Mostra a janela
        }

        private void criaBotoes() {
            // Definindo os botões e adicionando ao painel de controle
            adicionaBotao("Cadastrar Projeto", e -> Projeto.cadastraProjeto());
            adicionaBotao("Agendar Evento", e -> Evento.agendaEvento());
            adicionaBotao("Registrar Rodada de Monitoramento", e -> Monitoramento.registraRodadaMonitoramento());
            adicionaBotao("Reservar Sala", e -> ReservaSala.reservaSala());
            adicionaBotao("Solicitar Fabricação de Peça", e -> Maker.solicitaServicosMaker());
            adicionaBotao("Registrar Prática Cerne", e -> Cerne.registraPraticaCerne());
            adicionaBotao("Sair", e -> System.exit(0));
        }

        private void adicionaBotao(String texto, EventHandler<ActionEvent> evento) {
            Button button = new Button(texto);
            button.setOnAction(evento);
            HBox hBox = new HBox(button);
            hBox.setAlignment(Pos.CENTER);
            hBox.setPadding(new Insets(10));
            controlPanel.getChildren().add(hBox);
        }

        // Definindo ação para o botão de adicionar nó
        inserePraticaCerne.setOnAction(e -> {
            wordInput.setVisible(true);  // Torna o campo de texto visível para a entrada de palavras
            resultLabel.setText("");     // Limpa o rótulo de resultados
            operation[0] = "add";       // Define a operação como "add"
            toggleButtons(false, inserePraticaCerne, removePraticaCerne, buscaPraticaCerne);  // Desativa os outros botões
        });

        // Definindo ação para o botão de remover nó
        removePraticaCerne.setOnAction(e -> {
            wordInput.setVisible(true);
            resultLabel.setText("");   
            operation[0] = "remove";
            toggleButtons(false, inserePraticaCerne, removePraticaCerne, buscaPraticaCerne);
        });

        // Definindo ação para o botão de buscar nó
        buscaPraticaCerne.setOnAction(e -> {
            wordInput.setVisible(true);
            resultLabel.setText("");   
            operation[0] = "search";
            toggleButtons(false, inserePraticaCerne, removePraticaCerne, buscaPraticaCerne);
        });

        // Usando SplitPane para dividir a janela
        SplitPane splitPane = new SplitPane();
        // Definindo a posição do divisor (0.35 significa 35% da largura para controlPanel)
        splitPane.setDividerPositions(0.35);
        splitPane.getItems().addAll(leftPanel, rightPanel);

        // Instancia as classes, passando o painel 'subMenuPanel' para manipulação do painel de controle
        Projeto cadastraProjeto = new Projeto(root);
        Evento agendaEvento = new Evento(root);
        Monitoramento registraRodadaMonitoramento = new Monitoramento(root);
        ReservaSala reservaSala = new ReservaSala(root);
        Maker solicitaServicosMaker = new Maker(root);
        Cerne registraPraticaCerne = new Cerne(root);
        
        // Chama os métodos de configuração das interfaces incorporadas na janela à direita
        cadastraProjeto.cadastraProjeto();
        agendaEvento.agendaEvento();
        registraRodadaMonitoramento.registraRodadaMonitoramento();
        reservaSala.reservaSala();
        solicitaServicosMaker.solicitaServicosMaker();
        registraPraticaCerne.registraPraticaCerne();

        // Adicionando o SplitPane à cena
        Scene scene = new Scene(splitPane, 1920, 1200); // Defina o tamanho da janela conforme necessário
        primaryStage.setScene(scene);
        primaryStage.show(); // Mostra a janela
    }

    /**
     * Main method to launch the application.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
