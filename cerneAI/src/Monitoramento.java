package src;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class Monitoramento {

    private VBox subMenuPanel;

    // Construtor
    public Monitoramento(VBox subMenuPanel) {
        this.subMenuPanel = subMenuPanel;
    }

    // Método para exibir o formulário de monitoramento
    public void exibirFormularioMonitoramento() {
        subMenuPanel.getChildren().clear();
        subMenuPanel.setStyle("-fx-border-color: lightgray; -fx-padding: 10;");

        Label titleLabel = new Label("Formulário de Monitoramento");
        titleLabel.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");
        subMenuPanel.getChildren().add(titleLabel);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15));
        grid.setVgap(10);
        grid.setHgap(10);

        // Campos do formulário
        Label lblData = new Label("Data de Realização:");
        DatePicker dataPicker = new DatePicker();
        grid.add(lblData, 0, 0);
        grid.add(dataPicker, 1, 0);

        Label lblResponsavel = new Label("Responsável pelo Monitoramento:");
        TextField txtResponsavel = new TextField();
        grid.add(lblResponsavel, 0, 1);
        grid.add(txtResponsavel, 1, 1);

        Label lblSocios = new Label("Sócios:");
        TextField txtSocios = new TextField();
        grid.add(lblSocios, 0, 2);
        grid.add(txtSocios, 1, 2);

        // Estágio de Desenvolvimento da Solução
        Label lblEstagio = new Label("Estágio de Desenvolvimento da Solução:");
        ComboBox<String> cbEstagio = new ComboBox<>();
        cbEstagio.getItems().addAll("1: MVP não oferecido amplamente", "2: Produto concluído, mas sem clientes pagantes", "3: Produto concluído com clientes pagantes");
        grid.add(lblEstagio, 0, 3);
        grid.add(cbEstagio, 1, 3);

        // Análise de Diferenciais Competitivos
        Label lblDiferenciais = new Label("Análise de Diferenciais Competitivos:");
        ComboBox<String> cbDiferenciais = new ComboBox<>();
        cbDiferenciais.getItems().addAll("1: Não há análise", "2: Análise desatualizada", "3: Análise atualizada");
        grid.add(lblDiferenciais, 0, 4);
        grid.add(cbDiferenciais, 1, 4);

        // Desenvolvimento de Portfólio
        Label lblPortifolio = new Label("Desenvolvimento de Portfólio e/ou Melhorias:");
        ComboBox<String> cbPortifolio = new ComboBox<>();
        cbPortifolio.getItems().addAll("1: Não há novos produtos", "2: Novos produtos definidos", "3: Produtos em desenvolvimento", "4: Produtos e melhorias concluídas");
        grid.add(lblPortifolio, 0, 5);
        grid.add(cbPortifolio, 1, 5);

        // Conhecimentos sobre Propriedade Intelectual
        Label lblPropriedadeIntelectual = new Label("Conhecimento sobre Propriedade Intelectual:");
        ComboBox<String> cbPropriedadeIntelectual = new ComboBox<>();
        cbPropriedadeIntelectual.getItems().addAll("1: Não possui conhecimento", "2: Conhecimento superficial", "3: Conhecimento adquirido em cursos", "4: Registro iniciado");
        grid.add(lblPropriedadeIntelectual, 0, 6);
        grid.add(cbPropriedadeIntelectual, 1, 6);

        // Adequação do Processo Produtivo
        Label lblProcessoProdutivo = new Label("Adequação do Processo Produtivo:");
        ComboBox<String> cbProcessoProdutivo = new ComboBox<>();
        cbProcessoProdutivo.getItems().addAll("1: Não possui equipamentos", "2: Equipamentos insuficientes", "3: Equipamentos adequados", "4: Equipamentos com conformidade legal");
        grid.add(lblProcessoProdutivo, 0, 7);
        grid.add(cbProcessoProdutivo, 1, 7);

        // Mercado
        Label lblIdentidadeVisual = new Label("Identidade Visual:");
        ComboBox<String> cbIdentidadeVisual = new ComboBox<>();
        cbIdentidadeVisual.getItems().addAll("1: Não possui logomarca", "2: Logomarca precisa ser redefinida", "3: Logomarca definida, mas sem embalagens", "4: Logomarca e embalagens definidas");
        grid.add(lblIdentidadeVisual, 0, 8);
        grid.add(cbIdentidadeVisual, 1, 8);

        // Adicionando um botão para salvar os dados
        Button btnSalvar = new Button("Salvar Monitoramento");
        btnSalvar.setOnAction(e -> {
            // Aqui você pode salvar os dados e realizar ações necessárias
            Label successLabel = new Label("Monitoramento registrado com sucesso!");
            successLabel.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
            subMenuPanel.getChildren().add(successLabel);
        });

        grid.add(btnSalvar, 1, 9);

        subMenuPanel.getChildren().add(grid);
    }
}
