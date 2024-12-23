package src;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

public abstract class ReservaSala {
    protected String solicitante;
    protected LocalDateTime dataHoraInicio;
    protected LocalDateTime dataHoraFim;
    protected String descricaoReserva;

    // Lista privada para armazenar as reservas
    private static List<ReservaSala> reservas = new ArrayList<>();

    public ReservaSala(String solicitante, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim, String descricaoReserva) {
        this.solicitante = solicitante;
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
        this.descricaoReserva = descricaoReserva;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }

    public LocalDateTime getDataHoraFim() {
        return dataHoraFim;
    }

    public String getDescricaoReserva() {
        return descricaoReserva;
    }

    // Método para obter todas as reservas
    public static List<ReservaSala> getReservas() {
        return reservas;
    }

    // Método para adicionar reserva à lista
    public static void adicionarReserva(ReservaSala reserva) {
        reservas.add(reserva);
    }

    // Método abstrato para ser implementado pelas subclasses
    public abstract void exibirDetalhesReserva();

    // Janela de Reserva de Sala
    private void reservaSala() {
        subMenuPanel.getChildren().clear(); // Limpa a área de detalhes
        subMenuPanel.setStyle("-fx-border-color: lightgray; -fx-padding: 10;"); // Adiciona uma borda e padding

        Label titleLabel = new Label("Reserva de Sala");
        titleLabel.setStyle("-fx-font-size: 18; -fx-font-weight: bold;"); // Estilo do título
        subMenuPanel.getChildren().add(titleLabel); // Adiciona o título ao painel

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(15));

        // Botão para reservar sala de reuniões
        Button btnReuniao = new Button("Sala de Reuniões");
        btnReuniao.setOnAction(e -> reservaSalaReunioes()); // Chama o método para reserva de salas de reuniões

        // Botão para reservar coworking
        Button btnCoworking = new Button("Coworking");
        btnCoworking.setOnAction(e -> reservaCoworking()); // Chama o método para reserva de coworking

        // Adiciona os botões à VBox
        vbox.getChildren().addAll(btnReuniao, btnCoworking);

        // Adiciona o vbox à área de subMenu
        subMenuPanel.getChildren().add(vbox);
    }
}
