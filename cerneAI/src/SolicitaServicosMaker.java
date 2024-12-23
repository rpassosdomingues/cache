package src;

import java.time.LocalDateTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import java.util.ArrayList;
import java.util.List;

public class Maker {
    private String solicitante;
    private String campus;
    private String areaOrigem;
    private String material;
    private String equipamento;
    private LocalDateTime dataSolicitacao;
    private String status;
    private String andamento;
    private String conclusao;

    // Construtor
    public Maker(String solicitante, String campus, String areaOrigem, String material,
                            String equipamento, LocalDateTime dataSolicitacao, String status,
                            String andamento, String conclusao) {
        this.solicitante = solicitante;
        this.campus = campus;
        this.areaOrigem = areaOrigem;
        this.material = material;
        this.equipamento = equipamento;
        this.dataSolicitacao = dataSolicitacao;
        this.status = status;
        this.andamento = andamento;
        this.conclusao = conclusao;
    }

    // Getters e Setters
    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getAreaOrigem() {
        return areaOrigem;
    }

    public void setAreaOrigem(String areaOrigem) {
        this.areaOrigem = areaOrigem;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(String equipamento) {
        this.equipamento = equipamento;
    }

    public LocalDateTime getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(LocalDateTime dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAndamento() {
        return andamento;
    }

    public void setAndamento(String andamento) {
        this.andamento = andamento;
    }

    public String getConclusao() {
        return conclusao;
    }

    public void setConclusao(String conclusao) {
        this.conclusao = conclusao;
    }

    // Método para exibir informações da solicitação
    public void exibirInformacoes() {
        System.out.println("Solicitação de Peças:");
        System.out.println("Solicitante: " + solicitante);
        System.out.println("Campus: " + campus);
        System.out.println("Área de Origem: " + areaOrigem);
        System.out.println("Material: " + material);
        System.out.println("Equipamento: " + equipamento);
        System.out.println("Data da Solicitação: " + dataSolicitacao);
        System.out.println("Status: " + status);
        System.out.println("Andamento: " + andamento);
        System.out.println("Conclusão: " + conclusao);
    }

    // Método para obter uma descrição resumida da solicitação
    public String getDescricao() {
        return "Solicitação de " + material + " para " + equipamento + " - Solicitante: " + solicitante;
    }

    // Janela de Solicitação de Fabricação de Peça
    private void solicitaServicosMaker() {
        subMenuPanel.getChildren().clear(); // Limpa a área de detalhes
        subMenuPanel.setStyle("-fx-border-color: lightgray; -fx-padding: 10;"); // Adiciona uma borda e padding

        Label titleLabel = new Label("Solicitar Fabricação de Peça");
        titleLabel.setStyle("-fx-font-size: 18; -fx-font-weight: bold;"); // Estilo do título
        subMenuPanel.getChildren().add(titleLabel); // Adiciona o título ao painel

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15));
        grid.setVgap(10);
        grid.setHgap(10);

        Label lblSolicitante = new Label("Solicitante:");
        TextField txtSolicitante = new TextField();

        Label lblCampus = new Label("Campus:");
        ComboBox<String> cmbCampus = new ComboBox<>();
        cmbCampus.getItems().addAll("Alfenas", "Poços de Caldas", "Varginha"); // Adiciona opções de campus

        Label lblAreaOrigem = new Label("Área de Origem:");
        ComboBox<Departamento> cmbAreaOrigem = new ComboBox<>();
        cmbAreaOrigem.getItems().addAll(Departamento.values()); // Adiciona todos os departamentos

        Label lblMaterial = new Label("Material:");
        ComboBox<String> cmbMaterial = new ComboBox<>();
        cmbMaterial.getItems().addAll("ABS", "PLA", "MDF"); // Adiciona opções de material

        Label lblEquipamento = new Label("Equipamento:");
        ComboBox<String> cmbEquipamento = new ComboBox<>();
        cmbEquipamento.getItems().addAll("Impressora 3D", "Cortadora a Laser"); // Adiciona opções de equipamento

        Button btnEnviar = new Button("Enviar Solicitação");
        btnEnviar.setOnAction(e -> {
            String solicitante = txtSolicitante.getText();
            String campus = cmbCampus.getValue(); // Pega o valor selecionado no ComboBox
            Departamento areaOrigem = cmbAreaOrigem.getValue(); // Pega o departamento selecionado
            String material = cmbMaterial.getValue(); // Pega o material selecionado
            String equipamento = cmbEquipamento.getValue(); // Pega o equipamento selecionado
            LocalDateTime dataSolicitacao = LocalDateTime.now(); // Captura a data/hora da solicitação

            // Criação da solicitação de peça
            Maker solicitacao = new Maker(solicitante, campus, areaOrigem.name(), material, equipamento, dataSolicitacao, "Aprovado", "Em Andamento", "Concluído");
            pecas.add(solicitacao); // Adiciona à lista de solicitações

            // Exibindo confirmação com detalhes da solicitação
            Label successLabel = new Label("Solicitação enviada com sucesso!\n" +
                    "Solicitante: " + solicitante + "\n" +
                    "Campus: " + campus + "\n" +
                    "Área de Origem: " + areaOrigem.name() + "\n" +
                    "Material: " + material + "\n" +
                    "Equipamento: " + equipamento + "\n" +
                    "Data da Solicitação: " + dataSolicitacao.toLocalDate() + " " + dataSolicitacao.toLocalTime());
            successLabel.setStyle("-fx-text-fill: green; -fx-font-weight: bold;"); // Estilo opcional para destacar a mensagem
            subMenuPanel.getChildren().add(successLabel);
        });

        grid.add(lblSolicitante, 0, 0);
        grid.add(txtSolicitante, 1, 0);
        grid.add(lblCampus, 0, 1);
        grid.add(cmbCampus, 1, 1); // Adiciona ComboBox de campus ao grid
        grid.add(lblAreaOrigem, 0, 2);
        grid.add(cmbAreaOrigem, 1, 2); // Adiciona ComboBox de área de origem ao grid
        grid.add(lblMaterial, 0, 3);
        grid.add(cmbMaterial, 1, 3); // Adiciona ComboBox de material ao grid
        grid.add(lblEquipamento, 0, 4);
        grid.add(cmbEquipamento, 1, 4); // Adiciona ComboBox de equipamento ao grid
        grid.add(btnEnviar, 1, 5);

        subMenuPanel.getChildren().add(grid); // Adiciona o grid à área de subMenu
    }
}
