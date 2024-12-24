package src;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.util.ArrayList;
import java.util.List;

public class Monitoramento {

    // A lista que irá armazenar os dados coletados durante o monitoramento
    private List<String> dadosMonitoramento;

    // Referência ao painel principal onde as telas serão exibidas
    private VBox subMenuPanel;

    // Enum para as empresas incubadas
    public enum EmpresaIncubada {
        POLYGON, ICRO_DIGITAL, SMARTCOMERCI, ILEGIS
    }

    // Construtor
    public Monitoramento(VBox subMenuPanel) {
        this.subMenuPanel = subMenuPanel;
        dadosMonitoramento = new ArrayList<>();  // Inicializa a lista de dados
    }

    // Método para exibir o formulário de monitoramento
    public void registraRodadaMonitoramento() {
        subMenuPanel.getChildren().clear(); // Limpa o painel
        subMenuPanel.setStyle("-fx-border-color: lightgray; -fx-padding: 10;");

        // Tela 1: Selecione empresa incubada e dados iniciais
        // Criando os componentes da primeira tela
        Label titleLabel = new Label("Formulário de Monitoramento");
        titleLabel.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");
        subMenuPanel.getChildren().add(titleLabel);

        // Criação de um layout para a tela
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15));
        grid.setVgap(10);
        grid.setHgap(10);

        // Seleção da empresa incubada
        Label lblEmpresa = new Label("Selecione a Empresa Incubada:");
        ComboBox<EmpresaIncubada> cbEmpresa = new ComboBox<>();
        cbEmpresa.getItems().addAll(EmpresaIncubada.values());
        grid.add(lblEmpresa, 0, 0);
        grid.add(cbEmpresa, 1, 0);

        // Data de realização
        Label lblData = new Label("Data de Realização:");
        DatePicker dataPicker = new DatePicker();
        grid.add(lblData, 0, 1);
        grid.add(dataPicker, 1, 1);

        // Responsável pelo monitoramento
        Label lblResponsavel = new Label("Responsável pelo Monitoramento:");
        TextField txtResponsavel = new TextField();
        grid.add(lblResponsavel, 0, 2);
        grid.add(txtResponsavel, 1, 2);

        // Sócios
        Label lblSocios = new Label("Sócios:");
        TextField txtSocios = new TextField();
        grid.add(lblSocios, 0, 3);
        grid.add(txtSocios, 1, 3);

        // Botão para avançar para a próxima tela
        Button btnProximaTela = new Button("Próxima Tela");
        btnProximaTela.setOnAction(e -> {
            // Armazenar os dados da primeira tela
            dadosMonitoramento.add("Empresa: " + cbEmpresa.getValue().toString());
            dadosMonitoramento.add("Data de Realização: " + dataPicker.getValue().toString());
            dadosMonitoramento.add("Responsável: " + txtResponsavel.getText());
            dadosMonitoramento.add("Sócios: " + txtSocios.getText());

            // Exibir a tela 2
            exibirTela2();
        });

        grid.add(btnProximaTela, 1, 4);
        subMenuPanel.getChildren().add(grid);
    }

    // Tela 2: Dados financeiros
    private void exibirTela2() {
        subMenuPanel.getChildren().clear(); // Limpa o painel
        subMenuPanel.setStyle("-fx-border-color: lightgray; -fx-padding: 10;");

        // Criando os componentes da tela 2
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15));
        grid.setVgap(10);
        grid.setHgap(10);

        // Número de empregos
        Label lblEmpregos = new Label("Número de Empregos:");
        TextField txtEmpregos = new TextField();
        grid.add(lblEmpregos, 0, 0);
        grid.add(txtEmpregos, 1, 0);

        // Investimentos
        Label lblInvestimentos = new Label("Investimentos:");
        TextField txtInvestimentos = new TextField();
        grid.add(lblInvestimentos, 0, 1);
        grid.add(txtInvestimentos, 1, 1);

        // Faturamento acumulado anual
        Label lblFaturamento = new Label("Faturamento Acumulado Anual:");
        TextField txtFaturamento = new TextField();
        grid.add(lblFaturamento, 0, 2);
        grid.add(txtFaturamento, 1, 2);

        // Impostos
        Label lblImpostos = new Label("Impostos:");
        TextField txtImpostos = new TextField();
        grid.add(lblImpostos, 0, 3);
        grid.add(txtImpostos, 1, 3);

        // Botão para avançar para a próxima tela
        Button btnProximaTela = new Button("Próxima Tela");
        btnProximaTela.setOnAction(e -> {
            // Armazenar os dados da segunda tela
            dadosMonitoramento.add("Empregos: " + txtEmpregos.getText());
            dadosMonitoramento.add("Investimentos: " + txtInvestimentos.getText());
            dadosMonitoramento.add("Faturamento: " + txtFaturamento.getText());
            dadosMonitoramento.add("Impostos: " + txtImpostos.getText());

            // Exibir a tela 3
            exibirTela3();
        });

        grid.add(btnProximaTela, 1, 4);
        subMenuPanel.getChildren().add(grid);
    }

    // Tela 3: Eixo Tecnologia - Seleção dos critérios
    private void exibirTela3() {
        subMenuPanel.getChildren().clear(); // Limpa o painel
        subMenuPanel.setStyle("-fx-border-color: lightgray; -fx-padding: 10;");

        // Criando os componentes da tela 3
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15));
        grid.setVgap(10);
        grid.setHgap(10);

        // Criando ComboBoxes para cada critério de tecnologia
        Label lblEstagio = new Label("Estágio de Desenvolvimento da Solução:");
        ComboBox<String> cbEstagio = new ComboBox<>();
        cbEstagio.getItems().addAll("MVP", "Produto concluído sem clientes pagantes", "Produto com clientes pagantes");
        grid.add(lblEstagio, 0, 0);
        grid.add(cbEstagio, 1, 0);

        Label lblDiferenciais = new Label("Análise de Diferenciais Competitivos:");
        ComboBox<String> cbDiferenciais = new ComboBox<>();
        cbDiferenciais.getItems().addAll("Não há análise", "Análise desatualizada", "Análise atualizada");
        grid.add(lblDiferenciais, 0, 1);
        grid.add(cbDiferenciais, 1, 1);

        Label lblPortifolio = new Label("Desenvolvimento de Portfólio e/ou Melhorias:");
        ComboBox<String> cbPortifolio = new ComboBox<>();
        cbPortifolio.getItems().addAll("Sem novos produtos", "Novos produtos definidos", "Produtos em desenvolvimento", "Produtos concluídos");
        grid.add(lblPortifolio, 0, 2);
        grid.add(cbPortifolio, 1, 2);

        // Botão de salvar e concluir
        Button btnSalvar = new Button("Salvar Monitoramento");
        btnSalvar.setOnAction(e -> {
            // Armazenar os dados da terceira tela
            dadosMonitoramento.add("Estágio: " + cbEstagio.getValue());
            dadosMonitoramento.add("Diferenciais: " + cbDiferenciais.getValue());
            dadosMonitoramento.add("Portfólio: " + cbPortifolio.getValue());

            // Exibir sucesso e finalizar
            Label successLabel = new Label("Monitoramento registrado com sucesso!");
            successLabel.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
            subMenuPanel.getChildren().add(successLabel);

            // Aqui você pode salvar os dados no banco de dados ou realizar outra ação
            System.out.println(dadosMonitoramento);
        });

        grid.add(btnSalvar, 1, 3);
        subMenuPanel.getChildren().add(grid);
    }
}
