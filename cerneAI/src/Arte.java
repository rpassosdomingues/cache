package src;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Arte extends DICOM {
    private String instagram;
    private String linkedin;
    private boolean artePronta;
    private boolean legendaPronta;
    private String legenda;
    private List<String> arteAnexos;
    private VBox subMenuPanel;

    // Construtor
    public Arte(VBox subMenuPanel) {
        super(subMenuPanel);
        this.subMenuPanel = subMenuPanel;
        this.arteAnexos = new ArrayList<>();
    }

    // Getters e Setters
    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public boolean isArtePronta() {
        return artePronta;
    }

    public void setArtePronta(boolean artePronta) {
        this.artePronta = artePronta;
    }

    public boolean isLegendaPronta() {
        return legendaPronta;
    }

    public void setLegendaPronta(boolean legendaPronta) {
        this.legendaPronta = legendaPronta;
    }

    public String getLegenda() {
        return legenda;
    }

    public void setLegenda(String legenda) {
        this.legenda = legenda;
    }

    public List<String> getArteAnexos() {
        return arteAnexos;
    }

    public void addArteAnexo(String path) {
        arteAnexos.add(path);
    }

    // Interface gráfica para gerenciamento da arte
    public VBox gerenciaArte() {
        subMenuPanel.getChildren().clear();
        subMenuPanel.setStyle("-fx-border-color: lightgray; -fx-padding: 10;");
        subMenuPanel.setAlignment(Pos.TOP_CENTER);

        // Título
        Label titleLabel = new Label("Gerenciar Arte de Divulgação");
        titleLabel.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");
        subMenuPanel.getChildren().add(titleLabel);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15));
        grid.setVgap(10);
        grid.setHgap(10);

        // Campos para Instagram e LinkedIn
        Label lblInstagram = new Label("Perfil Instagram:");
        TextField txtInstagram = new TextField(instagram);
        Label lblLinkedin = new Label("Perfil LinkedIn:");
        TextField txtLinkedin = new TextField(linkedin);

        // Checkbox para arte e legenda prontas
        CheckBox chkArtePronta = new CheckBox("Arte pronta");
        chkArtePronta.setSelected(artePronta);

        CheckBox chkLegendaPronta = new CheckBox("Legenda pronta");
        chkLegendaPronta.setSelected(legendaPronta);

        // Campo de legenda
        Label lblLegenda = new Label("Legenda:");
        TextArea txtLegenda = new TextArea(legenda);
        txtLegenda.setPrefHeight(100);

        // Botão para anexar arte
        Button btnAnexarArte = new Button("Anexar Arte");
        btnAnexarArte.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Selecionar Arte");
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imagens", "*.png", "*.jpg", "*.jpeg")
            );
            String selectedFile = fileChooser.showOpenDialog(subMenuPanel.getScene().getWindow()) != null
                ? fileChooser.showOpenDialog(subMenuPanel.getScene().getWindow()).getAbsolutePath()
                : null;
            if (selectedFile != null) {
                addArteAnexo(selectedFile);
                Label successLabel = new Label("Arte anexada: " + selectedFile);
                successLabel.setStyle("-fx-text-fill: green;");
                subMenuPanel.getChildren().add(successLabel);
            }
        });

        // Botão para salvar alterações
        Button btnSalvar = new Button("Salvar Alterações");
        btnSalvar.setOnAction(e -> {
            instagram = txtInstagram.getText();
            linkedin = txtLinkedin.getText();
            artePronta = chkArtePronta.isSelected();
            legendaPronta = chkLegendaPronta.isSelected();
            legenda = txtLegenda.getText();

            Label successLabel = new Label("Dados salvos com sucesso!");
            successLabel.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
            subMenuPanel.getChildren().add(successLabel);
        });

        // Adicionando componentes ao GridPane
        grid.add(lblInstagram, 0, 0);
        grid.add(txtInstagram, 1, 0);
        grid.add(lblLinkedin, 0, 1);
        grid.add(txtLinkedin, 1, 1);
        grid.add(chkArtePronta, 0, 2);
        grid.add(chkLegendaPronta, 1, 2);
        grid.add(lblLegenda, 0, 3);
        grid.add(txtLegenda, 1, 3, 2, 1);
        grid.add(btnAnexarArte, 0, 4);
        grid.add(btnSalvar, 1, 4);

        subMenuPanel.getChildren().add(grid);
        return subMenuPanel;
    }

    // Método para processar imagem
    public Image processarImagem(String path, int largura, int altura) {
        return new Image("file:" + path, largura, altura, false, true);
    }
}
