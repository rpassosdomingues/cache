package src;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservaCoworking extends Reserva {
    private boolean necessitaProjetor;

    // Lista estática para armazenar reservas de coworking
    private static List<ReservaCoworking> reservasCoworking = new ArrayList<>();

    public ReservaCoworking(String solicitante, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim, String descricaoReserva, boolean necessitaProjetor) {
        super(solicitante, dataHoraInicio, dataHoraFim, descricaoReserva);
        this.necessitaProjetor = necessitaProjetor;
    }

    public boolean isNecessitaProjetor() {
        return necessitaProjetor;
    }

    // Metodo para cadastrar a reserva de coworking
    public void cadastrarReservaCoworking() {
        reservasCoworking.add(this); // Adiciona a instância atual à lista de reservas de coworking
        System.out.println("Reserva de coworking cadastrada com sucesso!");
    }

    // Metodo para obter todas as reservas de coworking
    public static List<ReservaCoworking> getReservasCoworking() {
        return reservasCoworking;
    }

    // Método para abrir a janela de Reserva de Coworking
    private void reservaCoworking() {
        subMenuPanel.getChildren().clear(); // Limpa a área de detalhes
        subMenuPanel.setStyle("-fx-border-color: lightgray; -fx-padding: 10;"); // Adiciona uma borda e padding

        Label titleLabel = new Label("Reservar Coworking");
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

        Label lblProjetor = new Label("Vai precisar do Projetor?");
        CheckBox checkProjetor = new CheckBox();

        Button btnSalvar = new Button("Salvar");
        btnSalvar.setOnAction(e -> {
            // Exibindo confirmação
            Label successLabel = new Label("Reserva do Coworking feita com sucesso!");
            successLabel.setStyle("-fx-text-fill: green; -fx-font-weight: bold;"); // Estilo opcional para destacar a mensagem
            subMenuPanel.getChildren().add(successLabel);
        });

        grid.add(lblDataHoraInicio, 0, 0);
        grid.add(dataHoraInicio, 1, 0);
        grid.add(lblDataHoraFim, 0, 1);
        grid.add(dataHoraFim, 1, 1);
        grid.add(lblProjetor, 0, 2);
        grid.add(checkProjetor, 1, 2);
        grid.add(btnSalvar, 1, 3); // Corrigido para adicionar na linha correta

        subMenuPanel.getChildren().add(grid); // Adiciona o grid à área de subMenu
    }
}
