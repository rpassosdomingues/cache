package src;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class Evento {
    private String nomeProjeto;
    private String acaoExecutada;
    private String nomeEvento;
    private String local;
    private LocalDate dia; // Data do evento
    private LocalTime horarioInicio; // Horário de início do evento
    private LocalTime horarioTermino; // Horário de término do evento
    private String assunto;

    // Lista estática para armazenar todos os eventos
    private static List<Evento> eventos = new ArrayList<>();

    // Painel do submenu (declarado como variável de instância)
    private VBox subMenuPanel;

    // Construtor
    public Evento(String nomeProjeto, String acaoExecutada, String nomeEvento, String local,
                  LocalDate dia, LocalTime horarioInicio, LocalTime horarioTermino, String assunto) {
        // Validação: o horário de término deve ser após o horário de início
        if (horarioTermino.isBefore(horarioInicio)) {
            throw new IllegalArgumentException("O horário de término deve ser após o horário de início.");
        }
        this.nomeProjeto = nomeProjeto;
        this.acaoExecutada = acaoExecutada;
        this.nomeEvento = nomeEvento;
        this.local = local;
        this.dia = dia;
        this.horarioInicio = horarioInicio;
        this.horarioTermino = horarioTermino;
        this.assunto = assunto;

        // Inicializa o painel de submenu
        this.subMenuPanel = new VBox();
    }

    // Getters e Setters
    public String getNomeProjeto() {
        return nomeProjeto;
    }

    public void setNomeProjeto(String nomeProjeto) {
        this.nomeProjeto = nomeProjeto;
    }

    public String getAcaoExecutada() {
        return acaoExecutada;
    }

    public void setAcaoExecutada(String acaoExecutada) {
        this.acaoExecutada = acaoExecutada;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
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

    public void setDia(LocalDate dia) {
        this.dia = dia;
    }

    public LocalTime getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(LocalTime horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public LocalTime getHorarioTermino() {
        return horarioTermino;
    }

    public void setHorarioTermino(LocalTime horarioTermino) {
        this.horarioTermino = horarioTermino;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    // Janela de Agendamento de Evento
    public VBox agendaEvento() {
        subMenuPanel.getChildren().clear(); // Limpa a área de detalhes
        subMenuPanel.setStyle("-fx-border-color: lightgray; -fx-padding: 10;"); // Adiciona uma borda e padding
        
        Label titleLabel = new Label("Agendar Evento");
        titleLabel.setStyle("-fx-font-size: 18; -fx-font-weight: bold;"); // Estilo do título
        subMenuPanel.getChildren().add(titleLabel); // Adiciona o título ao painel

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15));
        grid.setVgap(10);
        grid.setHgap(10);

        Label lblNome = new Label("Nome do Evento:");
        TextField txtNome = new TextField();

        Label lblDetalhes = new Label("Detalhes do Evento:");
        TextField txtDetalhes = new TextField();

        Label lblData = new Label("Data do Evento:");
        DatePicker dataEvento = new DatePicker();

        Label lblHoraInicio = new Label("Hora de Início:");
        ComboBox<Integer> comboHoraInicio = new ComboBox<>();
        for (int i = 0; i < 24; i++) {
            comboHoraInicio.getItems().add(i);
        }

        ComboBox<Integer> comboMinutoInicio = new ComboBox<>();
        for (int i = 0; i < 60; i += 5) {
            comboMinutoInicio.getItems().add(i);
        }

        Label lblHoraFim = new Label("Hora de Fim:");
        ComboBox<Integer> comboHoraFim = new ComboBox<>();
        for (int i = 0; i < 24; i++) {
            comboHoraFim.getItems().add(i);
        }

        ComboBox<Integer> comboMinutoFim = new ComboBox<>();
        for (int i = 0; i < 60; i += 5) {
            comboMinutoFim.getItems().add(i);
        }

        Label lblLocal = new Label("Local do Evento:");
        TextField txtLocal = new TextField();

        Button btnSalvar = new Button("Salvar");
        btnSalvar.setOnAction(e -> {
            // Capturando as informações do evento
            String nomeEvento = txtNome.getText();
            String detalhesEvento = txtDetalhes.getText();
            LocalDate data = dataEvento.getValue();

            Integer horaInicio = comboHoraInicio.getValue();
            Integer minutoInicio = comboMinutoInicio.getValue();
            String horarioInicioStr = String.format("%02d:%02d", horaInicio, minutoInicio);

            Integer horaFim = comboHoraFim.getValue();
            Integer minutoFim = comboMinutoFim.getValue();
            String horarioFimStr = String.format("%02d:%02d", horaFim, minutoFim);

            String localEvento = txtLocal.getText();

            // Exibindo confirmação
            Label successLabel = new Label("Evento cadastrado com sucesso!\n" +
                            "Nome: " + nomeEvento + "\n" +
                            "Detalhes: " + detalhesEvento + "\n" +
                            "Data: " + data + "\n" +
                            "Início: " + horarioInicioStr + "\n" +
                            "Fim: " + horarioFimStr + "\n" +
                            "Local: " + localEvento);
            successLabel.setStyle("-fx-text-fill: green; -fx-font-weight: bold;"); // Estilo opcional para destacar a mensagem
            subMenuPanel.getChildren().add(successLabel);
        });

        // Adicionando os componentes ao GridPane
        grid.add(lblNome, 0, 0);
        grid.add(txtNome, 1, 0);
        grid.add(lblDetalhes, 0, 1);
        grid.add(txtDetalhes, 1, 1);
        grid.add(lblData, 0, 2);
        grid.add(dataEvento, 1, 2);
        grid.add(lblHoraInicio, 0, 3);
        grid.add(comboHoraInicio, 1, 3);
        grid.add(comboMinutoInicio, 2, 3);
        grid.add(lblHoraFim, 0, 4);
        grid.add(comboHoraFim, 1, 4);
        grid.add(comboMinutoFim, 2, 4);
        grid.add(lblLocal, 0, 5);
        grid.add(txtLocal, 1, 5);
        grid.add(btnSalvar, 1, 6);

        subMenuPanel.getChildren().add(grid); // Adiciona o grid à área de subMenu
        return subMenuPanel; // Retorna o painel
    }
}
