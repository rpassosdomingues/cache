package src;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.input.KeyEvent;

import java.time.LocalDate;

public class Acao {

    private VBox subMenuPanel;

    // Construtor
    public Acao(VBox subMenuPanel) {
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

        Label lblParticipantes = new Label("Número de Participantes da Ação:");
        TextField txtParticipantes = new TextField();

        // Adiciona validação para aceitar apenas números inteiros
        txtParticipantes.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (!Character.isDigit(event.getCharacter().charAt(0))) {
                event.consume(); // Impede a entrada de caracteres não numéricos
            }
        });

        Label lblDia = new Label("Dia da Ação:");
        DatePicker txtDia = new DatePicker();

        Label lblHorarioInicio = new Label("Horário de Início:");
        TextField txtHorarioInicio = new TextField();

        Label lblHorarioTermino = new Label("Horário de Término:");
        TextField txtHorarioTermino = new TextField();

        Label lblAssunto = new Label("Assunto:");
        TextField txtAssunto = new TextField();

        // ComboBox para selecionar o projeto vinculado à ação
        Label lblProjetoVinculado = new Label("Selecionar Projeto:");
        ComboBox<CAEX> comboBoxProjeto = new ComboBox<>();
        comboBoxProjeto.getItems().setAll(CAEX.values()); // Adiciona todos os projetos CAEX à lista
        comboBoxProjeto.setPromptText("Selecione um Projeto");

        // Botão para salvar a ação
        Button btnSalvar = new Button("Salvar");
        btnSalvar.setOnAction(e -> {
            String nomeAcao = txtNomeAcao.getText();
            String descricao = txtDescricao.getText();
            String local = txtLocal.getText();
            Integer participantes = null;

            // Verifica se o campo de participantes foi preenchido com um número válido
            try {
                participantes = Integer.parseInt(txtParticipantes.getText());
            } catch (NumberFormatException ex) {
                // Caso o número não seja válido, exibe uma mensagem de erro
                Label errorLabel = new Label("O número de participantes deve ser um valor inteiro válido!");
                errorLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
                subMenuPanel.getChildren().add(errorLabel);
                return; // Interrompe o cadastro se o número for inválido
            }

            LocalDate dia = txtDia.getValue();
            String horarioInicio = txtHorarioInicio.getText();
            String horarioTermino = txtHorarioTermino.getText();
            String assunto = txtAssunto.getText();
            CAEX projetoVinculado = comboBoxProjeto.getValue();

            // Verifica se todos os campos foram preenchidos
            if (nomeAcao.isEmpty() || descricao.isEmpty() || local.isEmpty() || dia == null || horarioInicio.isEmpty() || 
                horarioTermino.isEmpty() || assunto.isEmpty() || projetoVinculado == null) {
                Label errorLabel = new Label("Todos os campos são obrigatórios!");
                errorLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
                subMenuPanel.getChildren().add(errorLabel);
            } else {
                // Cria a ação e vincula ao projeto selecionado
                Acao novaAcao = new Acao(subMenuPanel);
                novaAcao.setNomeAcao(nomeAcao);
                novaAcao.setDescricao(descricao);
                novaAcao.setLocal(local);
                novaAcao.setDia(dia.toString());
                novaAcao.setHorarioInicio(horarioInicio);
                novaAcao.setHorarioTermino(horarioTermino);
                novaAcao.setAssunto(assunto);
                novaAcao.setProjetoVinculado(projetoVinculado);

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
        grid.add(lblParticipantes, 0, 3);
        grid.add(txtParticipantes, 1, 3);
        grid.add(lblDia, 0, 4);
        grid.add(txtDia, 1, 4);
        grid.add(lblHorarioInicio, 0, 5);
        grid.add(txtHorarioInicio, 1, 5);
        grid.add(lblHorarioTermino, 0, 6);
        grid.add(txtHorarioTermino, 1, 6);
        grid.add(lblAssunto, 0, 7);
        grid.add(txtAssunto, 1, 7);
        grid.add(lblProjetoVinculado, 0, 8);
        grid.add(comboBoxProjeto, 1, 8);
        grid.add(btnSalvar, 1, 9);

        subMenuPanel.getChildren().add(grid); // Adiciona o grid à área de subMenu
    }
}
