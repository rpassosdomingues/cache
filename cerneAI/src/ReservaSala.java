package src;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import java.time.*;
import java.time.format.TextStyle;
import java.util.*;

public class ReservaSala {

    private static final List<Reserva> reservasSalaReuniao = new ArrayList<>();
    private static final List<Reserva> reservasCoworking = new ArrayList<>();
    private LocalDate currentMonth = LocalDate.now(); // Mês atual
    private VBox subMenuPanel;

    public ReservaSala(VBox subMenuPanel) {
        this.subMenuPanel = subMenuPanel;
    }

    public void reservaSala() {
        subMenuPanel.getChildren().clear();
        subMenuPanel.setStyle("-fx-border-color: lightgray; -fx-padding: 10;");

        Label titleLabel = new Label("Reservar Sala");
        titleLabel.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");
        subMenuPanel.getChildren().add(titleLabel);

        HBox header = new HBox(10);
        header.setAlignment(Pos.CENTER);
        Button btnAnterior = new Button("<");
        Button btnProximo = new Button(">");

        btnAnterior.setOnAction(e -> {
            currentMonth = currentMonth.minusMonths(1);
            atualizarCalendario();
        });

        btnProximo.setOnAction(e -> {
            currentMonth = currentMonth.plusMonths(1);
            atualizarCalendario();
        });

        Label mesAnoLabel = new Label(getMesAno(currentMonth));
        header.getChildren().addAll(btnAnterior, mesAnoLabel, btnProximo);

        VBox calendario = criarCalendario(mesAnoLabel);

        TextField solicitanteField = new TextField();
        solicitanteField.setPromptText("Nome do Solicitante");

        Button btnReservar = new Button("Confirmar Reserva");
        btnReservar.setOnAction(e -> confirmarReserva(solicitanteField.getText()));

        subMenuPanel.getChildren().addAll(header, calendario, new Label("Solicitante:"), solicitanteField, btnReservar);
    }

    private String getMesAno(LocalDate data) {
        return data.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault()) + " " + data.getYear();
    }

    private VBox criarCalendario(Label mesAnoLabel) {
        VBox calendario = new VBox(10);
        GridPane gridDias = new GridPane();
        gridDias.setHgap(10);
        gridDias.setVgap(10);

        LocalDate primeiroDiaDoMes = currentMonth.withDayOfMonth(1);
        int diaSemana = primeiroDiaDoMes.getDayOfWeek().getValue() % 7;
        int totalDias = currentMonth.lengthOfMonth();

        for (int i = 1; i <= totalDias; i++) {
            final int diaAtual = i;
            Button diaButton = new Button(String.valueOf(i));
            diaButton.setPrefSize(40, 40);

            if (temReservaNoDia(primeiroDiaDoMes.withDayOfMonth(i))) {
                diaButton.setStyle("-fx-background-color: lightgreen; -fx-font-weight: bold;");
            } else {
                diaButton.setStyle("-fx-background-color: lightblue; -fx-font-weight: bold;");
            }

            diaButton.setOnMouseClicked(e -> selecionarDia(e, diaAtual));
            gridDias.add(diaButton, (diaSemana) % 7, (diaSemana) / 7);
            diaSemana++;
        }

        calendario.getChildren().add(gridDias);
        mesAnoLabel.setText(getMesAno(currentMonth));
        return calendario;
    }

    private void atualizarCalendario() {
        reservaSala(); // Atualiza todo o layout com o calendário ajustado
    }

    private boolean temReservaNoDia(LocalDate dia) {
        for (Reserva reserva : reservasSalaReuniao) {
            if (reserva.getDataHoraInicio().toLocalDate().equals(dia)) {
                return true;
            }
        }
        return false;
    }

    private void selecionarDia(MouseEvent event, int dia) {
        mostrarHoraDeReserva(dia);
    }

    private void mostrarHoraDeReserva(int dia) {
        // Cria um painel de reserva diretamente no subMenuPanel
        VBox reservaPanel = new VBox(10);
        reservaPanel.setPadding(new Insets(15));

        Label labelHoraInicio = new Label("Hora de Início (HH:mm):");
        TextField horaInicioField = new TextField();
        horaInicioField.setPromptText("Ex: 10:00");

        Label labelDuracao = new Label("Duração (em horas):");
        TextField duracaoField = new TextField();
        duracaoField.setPromptText("Ex: 2");

        Label labelTipoReserva = new Label("Tipo de Sala:");
        ComboBox<String> tipoReservaComboBox = new ComboBox<>();
        tipoReservaComboBox.getItems().addAll("Coworking", "Sala de Reuniões");

        Button btnConfirmar = new Button("Confirmar Reserva");
        btnConfirmar.setOnAction(e -> {
            confirmarReserva(dia, horaInicioField.getText(), duracaoField.getText(), tipoReservaComboBox.getValue());
            reservaPanel.getChildren().clear(); // Limpa o painel após a reserva
        });

        reservaPanel.getChildren().addAll(labelHoraInicio, horaInicioField, labelDuracao, duracaoField, labelTipoReserva, tipoReservaComboBox, btnConfirmar);

        // Adiciona o painel de reserva à subMenuPanel
        subMenuPanel.getChildren().add(reservaPanel);
    }

    private void confirmarReserva(int dia, String horaInicio, String duracao, String tipoReserva) {
        try {
            LocalDateTime dataHoraInicio = LocalDate.of(currentMonth.getYear(), currentMonth.getMonth(), dia).atTime(LocalTime.parse(horaInicio));
            LocalDateTime dataHoraFim = dataHoraInicio.plusHours(Long.parseLong(duracao));

            // Adiciona a reserva conforme o tipo
            if (tipoReserva.equals("Sala de Reuniões")) {
                reservasSalaReuniao.add(new Reserva("Solicitante", dataHoraInicio, dataHoraFim, tipoReserva, false));
            } else {
                reservasCoworking.add(new Reserva("Solicitante", dataHoraInicio, dataHoraFim, tipoReserva, false));
            }

            System.out.println("Reserva confirmada para o dia " + dia + " às " + horaInicio + " com duração de " + duracao + " horas.");
            atualizarCalendario();
        } catch (Exception e) {
            System.err.println("Erro ao confirmar reserva: " + e.getMessage());
        }
    }

    private void confirmarReserva(String solicitanteNome) {
        System.out.println("Reserva confirmada para " + solicitanteNome);
    }
}

class Reserva {
    private String solicitante;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private String tipoReserva;
    private boolean especificacao;

    public Reserva(String solicitante, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim, String tipoReserva, boolean especificacao) {
        this.solicitante = solicitante;
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
        this.tipoReserva = tipoReserva;
        this.especificacao = especificacao;
    }

    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }

    public LocalDateTime getDataHoraFim() {
        return dataHoraFim;
    }

    public String getTipoReserva() {
        return tipoReserva;
    }

    public boolean isEspecificacao() {
        return especificacao;
    }
}
