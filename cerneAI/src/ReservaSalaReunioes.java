package src;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservaSalaReunioes extends Reserva {
    private int numeroPessoas;
    private boolean necessitaTransmissao;

    // Lista estática para armazenar reservas de salas de reuniões
    private static List<ReservaSalaReunioes> reservasSalaReunioes = new ArrayList<>();

    public ReservaSalaReunioes(String solicitante, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim, String descricaoReserva, int numeroPessoas, boolean necessitaTransmissao) {
        super(solicitante, dataHoraInicio, dataHoraFim, descricaoReserva);
        this.numeroPessoas = numeroPessoas;
        this.necessitaTransmissao = necessitaTransmissao;
    }

    public int getNumeroPessoas() {
        return numeroPessoas;
    }

    public boolean isNecessitaTransmissao() {
        return necessitaTransmissao;
    }

    // Método para obter todas as reservas de salas de reuniões
    public static List<ReservaSalaReunioes> getReservasSalaReunioes() {
        return reservasSalaReunioes;
    }

    // Método para abrir a janela de Reserva de Sala de Reuniões
    private void reservaSalaReunioes() {
        subMenuPanel.getChildren().clear(); // Limpa a área de detalhes
        subMenuPanel.setStyle("-fx-border-color: lightgray; -fx-padding: 10;"); // Adiciona uma borda e padding

        Label titleLabel = new Label("Reservar Sala de Reuniões");
        titleLabel.setStyle("-fx-font-size: 18; -fx-font-weight: bold;"); // Estilo do título
        subMenuPanel.getChildren().add(titleLabel); // Adiciona o título ao painel

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15));
        grid.setVgap(10);
        grid.setHgap(10);

        Label lblDataHoraInicio = new Label("Data e Hora de Início:");
        DatePicker dataHoraInicio = new DatePicker();

        Label lblDataHoraFim = new Label("Data e Hora de Fim:");
        DatePicker dataHoraFim = new DatePicker();

        Label lblTransmissao = new Label("Haverá transmissão ao vivo?");
        CheckBox checkTransmissao = new CheckBox();

        Button btnSalvar = new Button("Salvar");
        btnSalvar.setOnAction(e -> {
            // Exibindo confirmação
            Label successLabel = new Label("Reserva da Sala de Reuniões feita com sucesso!");
            successLabel.setStyle("-fx-text-fill: green; -fx-font-weight: bold;"); // Estilo opcional para destacar a mensagem
            subMenuPanel.getChildren().add(successLabel);
        });

        grid.add(lblDataHoraInicio, 0, 0);
        grid.add(dataHoraInicio, 1, 0);
        grid.add(lblDataHoraFim, 0, 1);
        grid.add(dataHoraFim, 1, 1);
        grid.add(lblTransmissao, 0, 2);
        grid.add(checkTransmissao, 1, 2);
        grid.add(btnSalvar, 1, 3);

        subMenuPanel.getChildren().add(grid); // Adiciona o grid à área de subMenu
    }
}
