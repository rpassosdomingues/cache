package src;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Projeto {
    private String nomeProjeto;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataTermino;
    private static List<Projeto> projetos = new ArrayList<>();
    private VBox subMenuPanel;

    // Construtor
    public Projeto(VBox subMenuPanel) {
        this.subMenuPanel = subMenuPanel;
    }

    // Método para cadastrar um novo projeto via interface gráfica
    public void cadastraProjeto() {
        subMenuPanel.getChildren().clear(); // Limpa a área de detalhes
        subMenuPanel.setStyle("-fx-border-color: lightgray; -fx-padding: 10;"); // Adiciona uma borda e padding

        Label titleLabel = new Label("Cadastrar Projeto");
        titleLabel.setStyle("-fx-font-size: 18; -fx-font-weight: bold;"); // Estilo do título
        subMenuPanel.getChildren().add(titleLabel); // Adiciona o título ao painel

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15));
        grid.setVgap(10);
        grid.setHgap(10);

        // Campo para nome do projeto
        Label lblNomeProjeto = new Label("Nome do Projeto:");
        TextField txtNomeProjeto = new TextField();

        // Campo para descrição do projeto
        Label lblDescricao = new Label("Descrição do Projeto:");
        TextField txtDescricao = new TextField();

        // Campo para data de início do projeto
        Label lblDataInicio = new Label("Data de Início:");
        DatePicker txtDataInicio = new DatePicker();

        // Campo para data de término do projeto
        Label lblDataTermino = new Label("Data de Término:");
        DatePicker txtDataTermino = new DatePicker();

        // ComboBox para selecionar o projeto CAEX
        Label lblProjetoCAEX = new Label("Selecionar Projeto CAEX:");
        ComboBox<CAEX> comboBoxCAEX = new ComboBox<>();
        comboBoxCAEX.getItems().setAll(CAEX.values()); // Adiciona todos os projetos CAEX à lista
        comboBoxCAEX.setPromptText("Selecione um Projeto CAEX");

        // Campo para cadastrar um novo projeto se a opção "Cadastrar Novo Projeto" for selecionada
        Label lblNovoProjeto = new Label("Ou cadastre um novo projeto:");
        TextField txtNovoProjeto = new TextField();
        txtNovoProjeto.setDisable(true); // Inicialmente desabilitado

        // Quando o ComboBox seleciona "Cadastrar Novo Projeto", habilita o campo de texto
        comboBoxCAEX.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null || newValue == CAEX.values()[CAEX.values().length - 1]) {
                txtNovoProjeto.setDisable(false);
            } else {
                txtNovoProjeto.setDisable(true);
            }
        });

        // Botão para salvar o projeto
        Button btnSalvar = new Button("Salvar");
        btnSalvar.setOnAction(e -> {
            String nomeProjeto = txtNomeProjeto.getText();
            String descricao = txtDescricao.getText();
            LocalDate dataInicio = txtDataInicio.getValue();
            LocalDate dataTermino = txtDataTermino.getValue();
            CAEX projetoCAEX = comboBoxCAEX.getValue();
            String novoProjeto = txtNovoProjeto.getText();

            // Se "Cadastrar Novo Projeto" foi selecionado, use o nome do novo projeto
            if (projetoCAEX == null || projetoCAEX == CAEX.values()[CAEX.values().length - 1]) {
                projetoCAEX = CAEX.valueOf(novoProjeto.toUpperCase().replace(" ", "_"));
            }

            // Verifica se os campos obrigatórios foram preenchidos
            if (nomeProjeto.isEmpty() || descricao.isEmpty() || dataInicio == null || dataTermino == null || projetoCAEX == null) {
                Label errorLabel = new Label("Todos os campos são obrigatórios!");
                errorLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
                subMenuPanel.getChildren().add(errorLabel);
            } else {
                // Cria o projeto e adiciona à lista
                Projeto novoProjetoObj = new Projeto(subMenuPanel);
                novoProjetoObj.nomeProjeto = nomeProjeto;
                novoProjetoObj.descricao = descricao;
                novoProjetoObj.dataInicio = dataInicio;
                novoProjetoObj.dataTermino = dataTermino;
                projetos.add(novoProjetoObj); // Adiciona à lista de projetos

                // Exibe a mensagem de sucesso
                Label successLabel = new Label("Projeto cadastrado com sucesso!");
                successLabel.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
                subMenuPanel.getChildren().add(successLabel);
            }
        });

        // Adiciona os elementos ao grid
        grid.add(lblNomeProjeto, 0, 0);
        grid.add(txtNomeProjeto, 1, 0);
        grid.add(lblDescricao, 0, 1);
        grid.add(txtDescricao, 1, 1);
        grid.add(lblDataInicio, 0, 2);
        grid.add(txtDataInicio, 1, 2);
        grid.add(lblDataTermino, 0, 3);
        grid.add(txtDataTermino, 1, 3);
        grid.add(lblProjetoCAEX, 0, 4);
        grid.add(comboBoxCAEX, 1, 4);
        grid.add(lblNovoProjeto, 0, 5);
        grid.add(txtNovoProjeto, 1, 5);
        grid.add(btnSalvar, 1, 6);

        subMenuPanel.getChildren().add(grid); // Adiciona o grid à área de subMenu
    }

    // Métodos de acesso aos dados
    public String getNomeProjeto() {
        return nomeProjeto;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }

    public static List<Projeto> listarProjetos() {
        return projetos;
    }
}
