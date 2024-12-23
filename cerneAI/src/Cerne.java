package src;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;

import java.util.List;
import java.util.ArrayList;

public class Cerne {

    // Lista para armazenar as práticas-chave registradas
    private final List<String> listaPraticasChave = new ArrayList<>();
    
    private VBox subMenuPanel;

    // Construtor para inicializar o painel
    public Cerne(VBox subMenuPanel) {
        this.subMenuPanel = subMenuPanel;
    }

    // Método para identificar prática chave
    public void registraPraticaCerne() {
        subMenuPanel.getChildren().clear(); // Limpa a área de detalhes
        subMenuPanel.setStyle("-fx-border-color: lightgray; -fx-padding: 10;"); // Adiciona uma borda e padding

        Label titleLabel = new Label("Registrar Prática Cerne");
        titleLabel.setStyle("-fx-font-size: 18; -fx-font-weight: bold;"); // Estilo do título
        subMenuPanel.getChildren().add(titleLabel); // Adiciona o título ao painel

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(15));

        // Campos de texto
        TextField campoAcao = new TextField();
        campoAcao.setPromptText("Digite a ação...");

        TextField campoAbordagem = new TextField();
        campoAbordagem.setPromptText("O que foi abordado...");

        TextField campoParticipantes = new TextField();
        campoParticipantes.setPromptText("Número de participantes...");

        // Label para mensagens de sucesso ou erro
        Label successLabel = new Label();

        // Botão de Identificar Prática Chave
        Button btnIdentificarPraticaChave = new Button("Identificar Prática Chave");
        btnIdentificarPraticaChave.setOnAction(e -> {
            // Valida se os campos foram preenchidos
            if (campoAcao.getText().isEmpty() || campoAbordagem.getText().isEmpty() || campoParticipantes.getText().isEmpty()) {
                successLabel.setText("Preencha todos os campos!");
                successLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
            } else {
                // Salva os dados temporariamente
                String acao = campoAcao.getText();
                String abordagem = campoAbordagem.getText();
                String participantes = campoParticipantes.getText();

                // Transfere para a tela de seleção de tags
                abrePainelSelecaoTags(titleLabel, acao, abordagem, participantes);

                // Limpa os campos de texto
                campoAcao.clear();
                campoAbordagem.clear();
                campoParticipantes.clear();
                successLabel.setText("");
            }
        });

        // Adiciona os componentes ao layout
        vbox.getChildren().addAll(
            new Label("Ação:"), campoAcao,
            new Label("O que foi abordado:"), campoAbordagem,
            new Label("Número de Participantes:"), campoParticipantes,
            btnIdentificarPraticaChave,
            successLabel
        );

        subMenuPanel.getChildren().add(vbox); // Adiciona o vbox à área de subMenu
    }

    // Método auxiliar para abrir a tela de seleção de tags
    private void abrePainelSelecaoTags(Label titleLabel, String acao, String abordagem, String participantes) {
        VBox painelSelecaoTags = SearchKeyPractices.createTagSelectionPanel();

        // Adiciona a funcionalidade de salvar as informações na lista final
        Button btnRegistrarPraticaCerne = new Button("Registrar Prática-Chave");
        btnRegistrarPraticaCerne.setOnAction(e -> {
            // Aqui você pode implementar a lógica para acumular as práticas-chave e concatenar as listas
            Label successLabel = new Label("Prática-Chave registrada com sucesso!");
            successLabel.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
            subMenuPanel.getChildren().add(successLabel);
        });

        painelSelecaoTags.getChildren().add(btnRegistrarPraticaCerne);

        subMenuPanel.getChildren().clear(); // Limpa o painel principal
        subMenuPanel.getChildren().addAll(titleLabel, painelSelecaoTags); // Adiciona o título e o painel de seleção de tags
    }
}
