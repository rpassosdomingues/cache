package src;

import src.Praticas;
import org.neo4j.driver.*;

import javafx.application.Application;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.SplitPane;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe principal da aplicação JavaFX que interage com um banco de dados Neo4j
 * para adicionar, remover e buscar palavras. Ela também permite ao usuário selecionar tags 
 * e exibir práticas associadas a essas tags.
 */
public class Main extends Application {
    
    // Declarando os painéis de controle e submenus da interface gráfica
    private VBox controlPanel;
    private VBox subMenuPanel;

    // Variável de estado para controle do fluxo de botões
    private boolean state = true;

    // Lista de peças (Maker), para futura utilização
    private ArrayList<Maker> pecas;

    // Lista de tags selecionadas para busca de práticas
    private static List<String> selectedTags = new ArrayList<>();

    /**
     * Conecta ao banco de dados Neo4j, permitindo buscar práticas baseadas nas tags selecionadas.
     */
    private List<Praticas> searchKeyPractices(List<String> selectedTags) {
        return Praticas.findByTags(selectedTags);
    }

    /**
     * Conector do banco de dados Neo4j, gerenciando as sessões de interação com o banco.
     */
    static class Neo4jConnector {
        private final Driver driver;

        // Construtor para inicializar a conexão com o banco de dados Neo4j
        public Neo4jConnector(String uri, String user) {
            // Recupera a senha de autenticação do Neo4j a partir de variável de ambiente
            String password = System.getenv("NEO4J_PASSWORD");
            if (password == null) {
                throw new IllegalStateException("NEO4J_PASSWORD environment variable is not set!");
            }
            this.driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
        }

        // Retorna uma nova sessão para interações com o banco de dados
        public Session getSession() {
            return driver.session();
        }

        // Fecha a conexão com o banco de dados
        public void close() {
            driver.close();
        }
    }

    /**
     * Método para adicionar uma palavra ao banco de dados Neo4j
     */
    private void insertNode(Neo4jConnector neo4jConnector, String word, Label resultLabel) {
        try (Session session = neo4jConnector.getSession()) {
            word = word.toLowerCase();  // Garante que a busca seja insensível a maiúsculas/minúsculas

            // Verifica se a palavra já existe no banco
            String queryCheck = "MATCH (w:Word {name: $name}) RETURN w";
            Result resultCheck = session.run(queryCheck, Values.parameters("name", word));

            // Se a palavra já existir, avisa o usuário, senão cria um novo nó
            if (resultCheck.hasNext()) {
                resultLabel.setText("The word '" + word + "' already exists.");
            } else {
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
     * Método para remover uma palavra do banco de dados Neo4j
     */
    private void removeNode(Neo4jConnector neo4jConnector, String word, Label resultLabel) {
        try (Session session = neo4jConnector.getSession()) {
            word = word.toLowerCase();

            // Verifica se a palavra existe no banco
            String queryCheck = "MATCH (w:Word {name: $name}) RETURN w";
            Result resultCheck = session.run(queryCheck, Values.parameters("name", word));

            // Se a palavra existir, remove o nó, senão avisa que não foi encontrada
            if (resultCheck.hasNext()) {
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
     * Método para buscar uma palavra no banco de dados Neo4j
     */
    private void searchNode(Neo4jConnector neo4jConnector, String word, Label resultLabel) {
        try (Session session = neo4jConnector.getSession()) {
            word = word.toLowerCase();

            // Executa a consulta para encontrar a palavra
            String query = "MATCH (w:Word {name: $name}) RETURN w";
            Result result = session.run(query, Values.parameters("name", word));

            // Verifica se a palavra foi encontrada e atualiza a interface
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
     * Inicializa e configura a interface gráfica do JavaFX
     */
    @Override
    public void start(Stage primaryStage) {
        // Conecta ao banco de dados Neo4j
        Neo4jConnector neo4jConnector = new Neo4jConnector("bolt://localhost:7687", "neo4j");

        primaryStage.setTitle("CERNEBot");

        // Divisória de tela
        SplitPane splitPane = new SplitPane();
        splitPane.setDividerPositions(0.35);  // Configura a proporção da divisória

        // Painéis de controle e submenu
        controlPanel = new VBox(10);
        controlPanel.setPadding(new Insets(15));
        subMenuPanel = new VBox();
        subMenuPanel.setPadding(new Insets(15));
        subMenuPanel.setSpacing(10);

        // Criação dos botões
        criaBotoes(neo4jConnector);

        // Configura a estrutura dividida (painéis à esquerda e direita)
        splitPane.getItems().addAll(controlPanel, subMenuPanel);

        // Instancia as classes para configurar as interfaces
        Projeto cadastraProjeto = new Projeto(subMenuPanel);
        Evento agendaEvento = new Evento(subMenuPanel);
        Monitoramento registraRodadaMonitoramento = new Monitoramento(subMenuPanel);
        ReservaSala reservaSala = new ReservaSala(subMenuPanel);
        Maker solicitaServicosMaker = new Maker(subMenuPanel);
        Cerne registraPraticaCerne = new Cerne(subMenuPanel);

        // Chama os métodos de configuração das interfaces incorporadas
        cadastraProjeto.cadastraProjeto();
        agendaEvento.agendaEvento();
        registraRodadaMonitoramento.registraRodadaMonitoramento();
        reservaSala.reservaSala();
        solicitaServicosMaker.solicitaServicosMaker();
        registraPraticaCerne.registraPraticaCerne();

        // Configura a cena e a janela
        Scene scene = new Scene(splitPane, 1920, 1200);  // Defina o tamanho da janela conforme necessário
        primaryStage.setScene(scene);
        primaryStage.show();  // Exibe a janela
    }

    /**
     * Método para adicionar botões ao painel de controle
     */
    private void criaBotoes(Neo4jConnector neo4jConnector) {
        adicionaBotao("Cadastrar Projeto", e -> {
            Projeto projeto = new Projeto(subMenuPanel);
            projeto.cadastraProjeto();
        });
        adicionaBotao("Agendar Evento", e -> {
            Evento evento = new Evento(subMenuPanel);
            evento.agendaEvento();
        });
        adicionaBotao("Registrar Rodada de Monitoramento", e -> {
            Monitoramento monitoramento = new Monitoramento(subMenuPanel);
            monitoramento.registraRodadaMonitoramento();
        });
        adicionaBotao("Reservar Sala", e -> {
            ReservaSala reservaSala = new ReservaSala(subMenuPanel);
            reservaSala.reservaSala();
        });
        adicionaBotao("Solicitar Fabricação de Peça", e -> {
            Maker maker = new Maker(subMenuPanel);
            maker.solicitaServicosMaker();
        });
        adicionaBotao("Registrar Prática Cerne", e -> {
            Cerne cerne = new Cerne(subMenuPanel);
            cerne.registraPraticaCerne();
        });
        adicionaBotao("Remover Prática Cerne", e -> removeNode(neo4jConnector, "word", new Label()));
        adicionaBotao("Busca Prática Cerne", e -> searchNode(neo4jConnector, "word", new Label()));
        adicionaBotao("Sair", e -> System.exit(0));
    }

    /**
     * Método auxiliar para adicionar um botão à interface
     */
    private void adicionaBotao(String texto, EventHandler<ActionEvent> evento) {
        Button button = new Button(texto);
        button.setOnAction(evento);
        HBox hBox = new HBox(button);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(10));
        controlPanel.getChildren().add(hBox);
    }

    /**
     * Método principal para rodar a aplicação
     */
    public static void main(String[] args) {
        launch(args);
    }
}
