package src;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

public class Acao {

    private VBox subMenuPanel;

    // Atributos da ação
    private String nomeAcao;
    private String descricao;
    private String local;
    private Integer participantes;
    private LocalDate dia;
    private String horarioInicio;
    private String horarioTermino;
    private String assunto;
    private CAEX projetoVinculado;

    // Construtor
    public Acao(VBox subMenuPanel) {
        this.subMenuPanel = subMenuPanel;
    }

    // Método para cadastrar uma nova ação via interface gráfica
    public void registraAcao() {
        subMenuPanel.getChildren().clear(); // Limpa a área de detalhes
        subMenuPanel.setStyle("-fx-border-color: lightgray; -fx-padding: 10;"); // Estilo de borda e padding

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

        Label lblParticipantes = new Label("Número de Participantes:");
        TextField txtParticipantes = new TextField();

        // Validação para aceitar apenas números inteiros
        txtParticipantes.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (!Character.isDigit(event.getCharacter().charAt(0))) {
                event.consume();
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
        comboBoxProjeto.getItems().setAll(CAEX.values());
        comboBoxProjeto.setPromptText("Selecione um Projeto");

        // Botão para salvar a ação
        Button btnSalvar = new Button("Salvar");
        btnSalvar.setOnAction(e -> {
            // Captura e valida os dados inseridos pelo usuário
            nomeAcao = txtNomeAcao.getText();
            descricao = txtDescricao.getText();
            local = txtLocal.getText();

            try {
                participantes = Integer.parseInt(txtParticipantes.getText());
            } catch (NumberFormatException ex) {
                exibirMensagemErro("O número de participantes deve ser um valor inteiro válido!");
                return;
            }

            dia = txtDia.getValue();
            horarioInicio = txtHorarioInicio.getText();
            horarioTermino = txtHorarioTermino.getText();
            assunto = txtAssunto.getText();
            projetoVinculado = comboBoxProjeto.getValue();

            // Verifica se todos os campos obrigatórios foram preenchidos
            if (nomeAcao.isEmpty() || descricao.isEmpty() || local.isEmpty() || dia == null || horarioInicio.isEmpty() ||
                horarioTermino.isEmpty() || assunto.isEmpty() || projetoVinculado == null) {
                exibirMensagemErro("Todos os campos são obrigatórios!");
            } else {
                // Exibe mensagem de sucesso
                exibirMensagemSucesso("Ação cadastrada com sucesso!");

                // Aqui você pode implementar a lógica adicional, como salvar em um banco de dados ou exibir a ação cadastrada.
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

    // Exibe uma mensagem de erro
    private void exibirMensagemErro(String mensagem) {
        Label errorLabel = new Label(mensagem);
        errorLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
        subMenuPanel.getChildren().add(errorLabel);
    }

    // Exibe uma mensagem de sucesso
    private void exibirMensagemSucesso(String mensagem) {
        Label successLabel = new Label(mensagem);
        successLabel.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
        subMenuPanel.getChildren().add(successLabel);
    }
}
