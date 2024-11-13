package src;

import org.neo4j.driver.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;

import src.Comanda;

/**
 * Classe Main da aplicação JavaFX que interage com o banco de dados Neo4j
 * para adicionar, remover e pesquisar clientes. Também permite que o usuário
 * selecione tags associadas aos clientes e exibe as práticas relacionadas.
 */
public class Main extends Application {
    // Variável 'estado' que controla se os botões estão habilitados ou desabilitados
    private boolean estado = true;  // Inicia com os botões habilitados por padrão

    /**
     * Classe que gerencia a conexão com o banco de dados Neo4j.
     * Esta classe estabelece a conexão e gerencia a sessão.
     */
    static class ConectorNeo4j {
        private final Driver driver;

        /**
         * Construtor que inicializa a conexão com o banco de dados Neo4j.
         *
         * @param uri  URI de conexão com o banco Neo4j (por exemplo, bolt://localhost:7687).
         * @param usuario Nome de usuário para autenticação no banco Neo4j.
         */
        public ConectorNeo4j(String uri, String usuario) {
            // Recupera a senha do banco de dados a partir de uma variável de ambiente
            String senha = System.getenv("NEO4J_PASSWORD");
            if (senha == null) {
                throw new IllegalStateException("A variável de ambiente NEO4J_PASSWORD não está definida!");
            }
            // Cria o driver para conectar ao Neo4j
            this.driver = GraphDatabase.driver(uri, AuthTokens.basic(usuario, senha));
        }

        /**
         * Retorna uma sessão para interagir com o banco de dados Neo4j.
         *
         * @return Uma sessão para executar consultas no banco de dados.
         */
        public Session obterSessao() {
            return driver.session();
        }

        /**
         * Fecha a conexão com o banco de dados Neo4j.
         */
        public void fechar() {
            driver.close();
        }
    }

    /**
     * Método que adiciona um cliente ao banco de dados Neo4j.
     *
     * @param conectorNeo4j Instância do ConectorNeo4j para interagir com o banco de dados.
     * @param cliente       O cliente a ser adicionado.
     * @param rotuloResultado Rótulo onde a mensagem de resultado será exibida.
     */
    private void adicionarCliente(ConectorNeo4j conectorNeo4j, String cliente, Label rotuloResultado) {
        try (Session sessao = conectorNeo4j.obterSessao()) {
            cliente = cliente.toLowerCase();  // Garante que a busca seja insensível a maiúsculas/minúsculas

            // Consulta para verificar se o cliente já existe no banco de dados
            String consultaVerificar = "MATCH (c:Cliente {nome: $nome}) RETURN c";
            Result resultadoVerificar = sessao.run(consultaVerificar, Values.parameters("nome", cliente));

            if (resultadoVerificar.hasNext()) {
                rotuloResultado.setText("O cliente '" + cliente + "' já existe.");
            } else {
                // Adiciona o cliente se ele não existir
                String consultaAdicionar = "CREATE (c:Cliente {nome: $nome})";
                sessao.run(consultaAdicionar, Values.parameters("nome", cliente));
                rotuloResultado.setText("Cliente '" + cliente + "' adicionado.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            rotuloResultado.setText("Erro ao adicionar o cliente.");
        }
    }

    /**
     * Método que remove um cliente do banco de dados Neo4j.
     *
     * @param conectorNeo4j Instância do ConectorNeo4j para interagir com o banco de dados.
     * @param cliente       O cliente a ser removido.
     * @param rotuloResultado Rótulo onde a mensagem de resultado será exibida.
     */
    private void removerCliente(ConectorNeo4j conectorNeo4j, String cliente, Label rotuloResultado) {
        try (Session sessao = conectorNeo4j.obterSessao()) {
            cliente = cliente.toLowerCase();  // Garante que a busca seja insensível a maiúsculas/minúsculas

            // Consulta para verificar se o cliente existe no banco de dados
            String consultaVerificar = "MATCH (c:Cliente {nome: $nome}) RETURN c";
            Result resultadoVerificar = sessao.run(consultaVerificar, Values.parameters("nome", cliente));

            if (resultadoVerificar.hasNext()) {
                // Remove o cliente se ele existir
                String consultaRemover = "MATCH (c:Cliente {nome: $nome}) DELETE c";
                sessao.run(consultaRemover, Values.parameters("nome", cliente));
                rotuloResultado.setText("Cliente '" + cliente + "' removido.");
            } else {
                rotuloResultado.setText("Cliente '" + cliente + "' não encontrado para remoção.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            rotuloResultado.setText("Erro ao remover o cliente.");
        }
    }

    /**
     * Método que pesquisa um cliente no banco de dados Neo4j.
     *
     * @param conectorNeo4j Instância do ConectorNeo4j para interagir com o banco de dados.
     * @param cliente       O cliente a ser pesquisado.
     * @param rotuloResultado Rótulo onde a mensagem de resultado será exibida.
     */
    private void pesquisarCliente(ConectorNeo4j conectorNeo4j, String cliente, Label rotuloResultado) {
        try (Session sessao = conectorNeo4j.obterSessao()) {
            cliente = cliente.toLowerCase();  // Garante que a busca seja insensível a maiúsculas/minúsculas

            // Consulta para verificar se o cliente existe no banco de dados
            String consulta = "MATCH (c:Cliente {nome: $nome}) RETURN c";
            Result resultado = sessao.run(consulta, Values.parameters("nome", cliente));

            // Exibe a mensagem de resultado com base na pesquisa
            if (resultado.hasNext()) {
                rotuloResultado.setText("Cliente '" + cliente + "' encontrado.");
            } else {
                rotuloResultado.setText("Cliente '" + cliente + "' não encontrado.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            rotuloResultado.setText("Erro ao pesquisar o cliente.");
        }
    }

    /**
    * Cria o painel de seleção de comidas e bebidas, permitindo registrar a comanda de um cliente.
    *
    * @return VBox com os controles de seleção do cardápio e o botão para anotar o pedido.
    */
    private VBox criarPainelComanda() {
        VBox painelComanda = new VBox(10);
        painelComanda.setAlignment(Pos.TOP_LEFT);

        // Cadastro do cliente e mesa
        TextField campoNomeCliente = new TextField();
        campoNomeCliente.setPromptText("Nome do Cliente");

        TextField campoMesa = new TextField();
        campoMesa.setPromptText("Número da Mesa");

        // Lista de comidas e bebidas disponíveis no cardápio
        List<String> cardapio = Comanda.getCardapioDisponivel();

        // GridPane para organizar os checkboxes em colunas
        GridPane grid = new GridPane();
        grid.setHgap(10);  // Espaçamento horizontal entre as colunas
        grid.setVgap(10);  // Espaçamento vertical entre as linhas

        // Criação dos checkboxes para cada item do cardápio
        int linha = 0, coluna = 0;
        for (String item : cardapio) {
            CheckBox checkBox = new CheckBox(item);
            grid.add(checkBox, coluna, linha);

            // Muda para a próxima coluna após 3 checkboxes
            coluna++;
            if (coluna > 2) {
                coluna = 0;
                linha++;
            }
        }

        // Lista para armazenar os itens selecionados
        List<String> itensSelecionados = new ArrayList<>();

        // Botão para registrar o pedido
        Button botaoRegistrarPedido = new Button("Registrar Pedido");
        botaoRegistrarPedido.setOnAction(e -> {
            String nomeCliente = campoNomeCliente.getText();
            String numeroMesa = campoMesa.getText();
            
            // Verificar se o nome e número da mesa estão preenchidos
            if (nomeCliente.isEmpty() || numeroMesa.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Nome do cliente e número da mesa são obrigatórios!");
                alert.showAndWait();
                return;
            }

            // Adicionar os itens selecionados à lista de pedido
            for (String item : cardapio) {
                if (grid.getChildren().stream().filter(node -> node instanceof CheckBox && ((CheckBox) node).isSelected()).anyMatch(checkBox -> ((CheckBox) checkBox).getText().equals(item))) {
                    itensSelecionados.add(item);
                }
            }

            // Registrar o pedido e exibir os itens escolhidos
            if (!itensSelecionados.isEmpty()) {
                System.out.println("Pedido de " + nomeCliente + " na mesa " + numeroMesa + ": " + String.join(", ", itensSelecionados));
            } else {
                System.out.println("Nenhum item selecionado para o pedido.");
            }
        });

        painelComanda.getChildren().addAll(campoNomeCliente, campoMesa, grid, botaoRegistrarPedido);
        return painelComanda;
    }

    /**
     * Método principal de inicialização da interface gráfica e execução da aplicação.
     */
    @Override
    public void start(Stage primaryStage) {
        // Configurações iniciais da interface gráfica
        primaryStage.setTitle("Gestão de Clientes");

        // Criar os botões de adicionar, remover, pesquisar
        Button botaoAdicionar = new Button("Adicionar Cliente");
        Button botaoRemover = new Button("Remover Cliente");
        Button botaoPesquisar = new Button("Pesquisar Cliente");

        // Rótulo para mostrar os resultados
        Label rotuloResultado = new Label();

        // Criar o conector Neo4j
        ConectorNeo4j conectorNeo4j = new ConectorNeo4j("bolt://localhost:7687", "neo4j");

        // Lógica de eventos para os botões
        botaoAdicionar.setOnAction(e -> {
            adicionarCliente(conectorNeo4j, "Cliente Exemplo", rotuloResultado);
        });

        botaoRemover.setOnAction(e -> {
            removerCliente(conectorNeo4j, "Cliente Exemplo", rotuloResultado);
        });

        botaoPesquisar.setOnAction(e -> {
            pesquisarCliente(conectorNeo4j, "Cliente Exemplo", rotuloResultado);
        });

        // Layout da interface gráfica
        VBox layout = new VBox(10);
        layout.getChildren().addAll(botaoAdicionar, botaoRemover, botaoPesquisar, rotuloResultado);

        Scene cena = new Scene(layout, 400, 300);
        primaryStage.setScene(cena);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
