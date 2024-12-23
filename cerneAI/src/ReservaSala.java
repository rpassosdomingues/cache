package src;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class ReservaSala {
    protected String solicitante;
    protected LocalDateTime dataHoraInicio;
    protected LocalDateTime dataHoraFim;
    protected String descricaoReserva;

    // Lista privada para armazenar as reservas
    private static List<Reserva> reservas = new ArrayList<>();

    public Reserva(String solicitante, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim, String descricaoReserva) {
        this.solicitante = solicitante;
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
        this.descricaoReserva = descricaoReserva;
    }

    // Método para cadastrar a reserva
    public void cadastrarReserva() {
        reservas.add(this); // Adiciona a instância atual à lista de reservas
        System.out.println("Reserva cadastrada com sucesso!");
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

    // Metodo para obter todas as reservas
    public static List<Reserva> getReservas() {
        return reservas;
    }

    // Janela de Reserva de Sala
    private void reservarSala() {
        subMenuPanel.getChildren().clear(); // Limpa a área de detalhes
        subMenuPanel.setStyle("-fx-border-color: lightgray; -fx-padding: 10;"); // Adiciona uma borda e padding

        Label titleLabel = new Label("Reserva de Sala");
        titleLabel.setStyle("-fx-font-size: 18; -fx-font-weight: bold;"); // Estilo do título
        subMenuPanel.getChildren().add(titleLabel); // Adiciona o título ao painel

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(15));

        Button btnReuniao = new Button("Sala de Reuniões");
        btnReuniao.setOnAction(e -> abrirSalaReunioes());

        Button btnCoworking = new Button("Coworking");
        btnCoworking.setOnAction(e -> abrirCoworking());

        vbox.getChildren().addAll(btnReuniao, btnCoworking);

        subMenuPanel.getChildren().add(vbox); // Adiciona o vbox à área de subMenu
    }
}
