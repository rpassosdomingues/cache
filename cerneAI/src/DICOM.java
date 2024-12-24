package src;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class DICOM {
    private String nomeCompleto;
    private String unidadeAcademica;
    private String setorCurso;
    private String email;
    private String telefone;
    private String ramal;
    private String areaVinculada;
    private String secaoJornal;
    private String acao;
    private String agente;
    private LocalDate data;
    private LocalTime horarioInicio;
    private LocalTime horarioTermino;
    private String lugar;
    private String modo;
    private String motivo;
    private String depoimento;
    private List<String> anexos;

    // Painel do submenu
    private VBox subMenuPanel;

    // Construtor
    public DICOM(VBox subMenuPanel) {
        this.subMenuPanel = subMenuPanel;
        this.anexos = new ArrayList<>();
    }

    // Método escreveMateria para exibir a interface gráfica
    public VBox escreveMateria() {
        subMenuPanel.getChildren().clear();
        subMenuPanel.setStyle("-fx-border-color: lightgray; -fx-padding: 10;");
        subMenuPanel.setAlignment(Pos.CENTER);

        // Título
        Label titleLabel = new Label("Escrever Matéria para o Jornal UNIFAL-MG");
        titleLabel.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");
        subMenuPanel.getChildren().add(titleLabel);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15));
        grid.setVgap(10);
        grid.setHgap(10);

        // Campos de entrada
        Label lblNomeCompleto = new Label("Nome Completo:");
        TextField txtNomeCompleto = new TextField();

        Label lblUnidade = new Label("Unidade Acadêmica/Administrativa:");
        TextField txtUnidade = new TextField();

        Label lblSetorCurso = new Label("Setor/Curso:");
        TextField txtSetorCurso = new TextField();

        Label lblEmail = new Label("E-mail:");
        TextField txtEmail = new TextField();

        Label lblTelefone = new Label("WhatsApp/Telefone:");
        TextField txtTelefone = new TextField();

        Label lblRamal = new Label("Ramal:");
        TextField txtRamal = new TextField();

        Label lblAreaVinculada = new Label("Área Vinculada:");
        ComboBox<String> comboArea = new ComboBox<>();
        comboArea.getItems().addAll(
            "Administração/Gestão Universitária",
            "Ensino",
            "Extensão",
            "Pesquisa",
            "Outras"
        );

        Label lblSecaoJornal = new Label("Seção do Jornal:");
        ComboBox<String> comboSecao = new ComboBox<>();
        comboSecao.getItems().addAll(
            "Artigos",
            "Autores UNIFAL-MG",
            "Giro Acadêmico",
            "Notícias",
            "UNIFAL-MG Indica",
            "UNIFAL-MG em Podcast",
            "UNIFAL-MG na Mídia"
        );

        Label lblAcao = new Label("Ação (Título da Atividade ou Projeto):");
        TextField txtAcao = new TextField();

        Label lblAgente = new Label("Agente(s):");
        TextField txtAgente = new TextField();

        Label lblData = new Label("Data:");
        DatePicker datePicker = new DatePicker();

        Label lblHorarioInicio = new Label("Horário de Início:");
        ComboBox<Integer> comboHoraInicio = new ComboBox<>();
        for (int i = 0; i < 24; i++) {
            comboHoraInicio.getItems().add(i);
        }
        ComboBox<Integer> comboMinutoInicio = new ComboBox<>();
        for (int i = 0; i < 60; i += 5) {
            comboMinutoInicio.getItems().add(i);
        }

        Label lblHorarioTermino = new Label("Horário de Término:");
        ComboBox<Integer> comboHoraTermino = new ComboBox<>();
        for (int i = 0; i < 24; i++) {
            comboHoraTermino.getItems().add(i);
        }
        ComboBox<Integer> comboMinutoTermino = new ComboBox<>();
        for (int i = 0; i < 60; i += 5) {
            comboMinutoTermino.getItems().add(i);
        }

        Label lblLugar = new Label("Lugar:");
        TextField txtLugar = new TextField();

        Label lblModo = new Label("Modo:");
        TextField txtModo = new TextField();

        Label lblMotivo = new Label("Motivo:");
        TextField txtMotivo = new TextField();

        Label lblDepoimento = new Label("Depoimento:");
        TextField txtDepoimento = new TextField();

        Button btnSalvar = new Button("Salvar");
        btnSalvar.setOnAction(e -> {
            // Captura os dados e exibe uma mensagem de confirmação
            nomeCompleto = txtNomeCompleto.getText();
            unidadeAcademica = txtUnidade.getText();
            setorCurso = txtSetorCurso.getText();
            email = txtEmail.getText();
            telefone = txtTelefone.getText();
            ramal = txtRamal.getText();
            areaVinculada = comboArea.getValue();
            secaoJornal = comboSecao.getValue();
            acao = txtAcao.getText();
            agente = txtAgente.getText();
            data = datePicker.getValue();
            horarioInicio = LocalTime.of(comboHoraInicio.getValue(), comboMinutoInicio.getValue());
            horarioTermino = LocalTime.of(comboHoraTermino.getValue(), comboMinutoTermino.getValue());
            lugar = txtLugar.getText();
            modo = txtModo.getText();
            motivo = txtMotivo.getText();
            depoimento = txtDepoimento.getText();

            Label successLabel = new Label("Matéria salva com sucesso!");
            successLabel.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
            subMenuPanel.getChildren().add(successLabel);
        });

        // Adicionando componentes ao grid
        grid.add(lblNomeCompleto, 0, 0);
        grid.add(txtNomeCompleto, 1, 0);
        grid.add(lblUnidade, 0, 1);
        grid.add(txtUnidade, 1, 1);
        grid.add(lblSetorCurso, 0, 2);
        grid.add(txtSetorCurso, 1, 2);
        grid.add(lblEmail, 0, 3);
        grid.add(txtEmail, 1, 3);
        grid.add(lblTelefone, 0, 4);
        grid.add(txtTelefone, 1, 4);
        grid.add(lblRamal, 0, 5);
        grid.add(txtRamal, 1, 5);
        grid.add(lblAreaVinculada, 0, 6);
        grid.add(comboArea, 1, 6);
        grid.add(lblSecaoJornal, 0, 7);
        grid.add(comboSecao, 1, 7);
        grid.add(lblAcao, 0, 8);
        grid.add(txtAcao, 1, 8);
        grid.add(lblAgente, 0, 9);
        grid.add(txtAgente, 1, 9);
        grid.add(lblData, 0, 10);
        grid.add(datePicker, 1, 10);
        grid.add(lblHorarioInicio, 0, 11);
        grid.add(comboHoraInicio, 1, 11);
        grid.add(comboMinutoInicio, 2, 11);
        grid.add(lblHorarioTermino, 0, 12);
        grid.add(comboHoraTermino, 1, 12);
        grid.add(comboMinutoTermino, 2, 12);
        grid.add(lblLugar, 0, 13);
        grid.add(txtLugar, 1, 13);
        grid.add(lblModo, 0, 14);
        grid.add(txtModo, 1, 14);
        grid.add(lblMotivo, 0, 15);
        grid.add(txtMotivo, 1, 15);
        grid.add(lblDepoimento, 0, 16);
        grid.add(txtDepoimento, 1, 16);
        grid.add(btnSalvar, 1, 17);

        subMenuPanel.getChildren().add(grid);
        return subMenuPanel;
    }
}
