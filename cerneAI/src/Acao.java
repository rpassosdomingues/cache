package src;

import java.time.LocalDate;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

// Classe para representar uma Ação
public class Acao {
    private String nomeAcao;
    private String descricao;
    private String local;
    private LocalDate dia;
    private String horarioInicio;
    private String horarioTermino;
    private String assunto;
    private CAEX projetoVinculado;  // Campo para vincular um projeto

    // Construtor da classe Acao
    public Acao(String nomeAcao, String descricao, String local, String diaInput,
                String horarioInicio, String horarioTermino, String assunto, CAEX projetoVinculado) {
        this.nomeAcao = nomeAcao;
        this.descricao = descricao;
        this.local = local;
        this.dia = LocalDate.parse(diaInput); // Converte a string para LocalDate
        this.horarioInicio = horarioInicio;
        this.horarioTermino = horarioTermino;
        this.assunto = assunto;
        this.projetoVinculado = projetoVinculado;  // Atribuindo o projeto
    }

    // Getters e Setters
    public String getNomeAcao() {
        return nomeAcao;
    }

    public void setNomeAcao(String nomeAcao) {
        this.nomeAcao = nomeAcao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public LocalDate getDia() {
        return dia;
    }

    public void setDia(String diaInput) {
        this.dia = LocalDate.parse(diaInput); // Converte para LocalDate
    }

    public String getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(String horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public String getHorarioTermino() {
        return horarioTermino;
    }

    public void setHorarioTermino(String horarioTermino) {
        this.horarioTermino = horarioTermino;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public CAEX getProjetoVinculado() {
        return projetoVinculado;
    }

    public void setProjetoVinculado(CAEX projetoVinculado) {
        this.projetoVinculado = projetoVinculado;
    }
}

// Classe para o cadastro da ação via interface gráfica
public class CadastroAcao {
    private VBox subMenuPanel;

    // Construtor
    public CadastroAcao(VBox subMenuPanel) {
        this.subMenuPanel = subMenuPanel;
    }

    // Método para cadastrar uma nova ação via interface gráfica
    public void cadastraAcao() {
        subMenuPanel.getChildren().clear(); // Limpa a área de detalhes
        subMenuPanel.setStyle("-fx-border-color: lightgray; -fx-padding: 10;"); // Adiciona uma borda e padding

        Label titleLabel = new Label("Cadastrar Ação");
        titleLabel.setStyle("-fx-font-size: 18; -fx-font-weight: bold;"); // Estilo do título
        subMenuPanel.getChildren().add(titleLabel); // Adiciona o título ao painel

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15));
        grid.setVgap(10);
        grid.setHgap(10);

        // Campos para dados da ação
        Label lblNomeAcao = new Label("Nome da Ação:");
        TextField txtNomeAcao = new TextField();

        Label lblDescricao = new Label("Descrição da Ação:");
        TextField txtDescricao = new TextField();

        Label lblLocal = new Label("Local da Ação:");
        TextField txtLocal = new TextField();

        Label lblDia = new Label("Dia da Ação:");
        TextField txtDia = new TextField();

        Label lblHorarioInicio = new Label("Horário de Início:");
        TextField txtHorarioInicio = new TextField();

        Label lblHorarioTermino = new Label("Horário de Término:");
        TextField txtHorarioTermino = new TextField();

        Label lblAssunto = new Label("Assunto:");
        TextField txtAssunto = new TextField();

        // ComboBox para selecionar o projeto vinculado à ação
        Label lblProjetoVinculado = new Label("Selecionar Projeto:");
        ComboBox<CAEX> comboBoxProjeto = new ComboBox<>();
        comboBoxProjeto.getItems().setAll(CAEX.values()); // Adiciona todos os projetos à lista
        comboBoxProjeto.setPromptText("Selecione um Projeto");

        // Botão para salvar a ação
        Button btnSalvar = new Button("Salvar");
        btnSalvar.setOnAction(e -> {
            String nomeAcao = txtNomeAcao.getText();
            String descricao = txtDescricao.getText();
            String local = txtLocal.getText();
            String dia = txtDia.getText();
            String horarioInicio = txtHorarioInicio.getText();
            String horarioTermino = txtHorarioTermino.getText();
            String assunto = txtAssunto.getText();
            CAEX projetoVinculado = comboBoxProjeto.getValue();

            // Verifica se todos os campos foram preenchidos
            if (nomeAcao.isEmpty() || descricao.isEmpty() || local.isEmpty() || dia.isEmpty() || horarioInicio.isEmpty() || 
                horarioTermino.isEmpty() || assunto.isEmpty() || projetoVinculado == null) {
                Label errorLabel = new Label("Todos os campos são obrigatórios!");
                errorLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
                subMenuPanel.getChildren().add(errorLabel);
            } else {
                // Cria a ação e vincula ao projeto selecionado
                Acao novaAcao = new Acao(nomeAcao, descricao, local, dia, horarioInicio, horarioTermino, assunto, projetoVinculado);

                // Exibe a mensagem de sucesso
                Label successLabel = new Label("Ação cadastrada com sucesso!");
                successLabel.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
                subMenuPanel.getChildren().add(successLabel);
            }
        });

        // Adiciona os elementos ao grid
        grid.add(lblNomeAcao, 0, 0);
        grid.add(txtNomeAcao, 1, 0);
        grid.add(lblDescricao, 0, 1);
        grid.add(txtDescricao, 1, 1);
        grid.add(lblLocal, 0, 2);
        grid.add(txtLocal, 1, 2);
        grid.add(lblDia, 0, 3);
        grid.add(txtDia, 1, 3);
        grid.add(lblHorarioInicio, 0, 4);
        grid.add(txtHorarioInicio, 1, 4);
        grid.add(lblHorarioTermino, 0, 5);
        grid.add(txtHorarioTermino, 1, 5);
        grid.add(lblAssunto, 0, 6);
        grid.add(txtAssunto, 1, 6);
        grid.add(lblProjetoVinculado, 0, 7);
        grid.add(comboBoxProjeto, 1, 7);
        grid.add(btnSalvar, 1, 8);

        subMenuPanel.getChildren().add(grid); // Adiciona o grid à área de subMenu
    }
}
