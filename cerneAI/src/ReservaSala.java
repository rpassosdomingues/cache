package src;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.time.*;
import java.util.*;

import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class ReservaSala extends Application {

    private static final List<Reserva> reservasSalaReuniao = new ArrayList<>();
    private static final List<Reserva> reservasCoworking = new ArrayList<>();
    private LocalDate currentMonth = LocalDate.now(); // Mês atual
    private VBox subMenuPanel; // Definido como variável de instância

    public ReservaSala(VBox subMenuPanel) {
        this.subMenuPanel = subMenuPanel; // Atribui o painel passado ao campo
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        reservaSala();
    }

    private void reservaSala() {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Sistema de Reservas de Salas");

        // Layout principal
        VBox layoutPrincipal = new VBox(10);
        layoutPrincipal.setPadding(new javafx.geometry.Insets(15));

        // Botões para navegar entre os meses
        HBox header = new HBox(10);
        header.setAlignment(Pos.CENTER);
        Button btnAnterior = new Button("<");
        Button btnProximo = new Button(">");

        // Adicionando ação aos botões
        btnAnterior.setOnAction(e -> {
            currentMonth = currentMonth.minusMonths(1);
            atualizarCalendario(layoutPrincipal);
        });

        btnProximo.setOnAction(e -> {
            currentMonth = currentMonth.plusMonths(1);
            atualizarCalendario(layoutPrincipal);
        });

        header.getChildren().addAll(btnAnterior, new Label(getMesAno(currentMonth)), btnProximo);

        // Calendário
        VBox calendario = criarCalendario();

        // Campos de informações
        TextField solicitanteField = new TextField();
        solicitanteField.setPromptText("Nome do Solicitante");

        // Botão de confirmação da reserva
        Button btnReservar = new Button("Confirmar Reserva");

        // Adicionando componentes ao layout
        layoutPrincipal.getChildren().addAll(header, calendario, new Label("Solicitante:"), solicitanteField, btnReservar);

        // Configuração da cena
        Scene scene = new Scene(layoutPrincipal, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Método para obter o mês e ano em formato legível
    private String getMesAno(LocalDate data) {
        return data.getMonth().toString() + " " + data.getYear();
    }

    // Criação do calendário
    private VBox criarCalendario() {
        VBox calendario = new VBox(10);
        GridPane gridDias = new GridPane();
        gridDias.setHgap(10);
        gridDias.setVgap(10);

        LocalDate primeiroDiaDoMes = currentMonth.withDayOfMonth(1);
        LocalDate ultimoDiaDoMes = currentMonth.withDayOfMonth(currentMonth.lengthOfMonth());

        // Preenche os dias
        int diaSemana = primeiroDiaDoMes.getDayOfWeek().getValue(); // 1 para segunda, 7 para domingo
        int totalDias = ultimoDiaDoMes.getDayOfMonth();

        for (int i = 1; i <= totalDias; i++) {
            final int diaAtual = i; // Criamos uma variável final para armazenar o valor de i
            Button diaButton = new Button(String.valueOf(i));
            diaButton.setPrefSize(40, 40);
            diaButton.setStyle("-fx-background-color: lightblue; -fx-font-weight: bold;");
            // Verificar se o dia tem reserva e mudar a cor
            if (temReservaNoDia(primeiroDiaDoMes.withDayOfMonth(i))) {
                diaButton.setStyle("-fx-background-color: lightgreen; -fx-font-weight: bold;");
            }

            diaButton.setOnMouseClicked(e -> selecionarDia(e, diaAtual)); // Usamos a variável final

            gridDias.add(diaButton, (diaSemana - 1) % 7, (diaSemana - 1) / 7);
            diaSemana++;
        }

        calendario.getChildren().add(gridDias);
        return calendario;
    }

    // Alteração na assinatura do método atualizarCalendario
    public void atualizarCalendario(VBox layoutPrincipal) {
        // Limpa o layout anterior
        layoutPrincipal.getChildren().clear();

        // Define o mês atual a partir da variável currentMonth
        LocalDate primeiroDiaDoMes = currentMonth.withDayOfMonth(1);
        LocalDate ultimoDiaDoMes = currentMonth.withDayOfMonth(currentMonth.lengthOfMonth());

        // Adiciona o nome do mês e o ano ao topo
        Text mesAno = new Text(primeiroDiaDoMes.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault()) + " " + currentMonth.getYear());
        layoutPrincipal.getChildren().add(mesAno);  // Adiciona a linha de mes e ano

        // Adiciona os nomes dos dias da semana
        String[] diasDaSemana = {"Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sáb"};
        HBox diasSemana = new HBox();
        for (String dia : diasDaSemana) {
            Label diaSemanaLabel = new Label(dia);
            diasSemana.getChildren().add(diaSemanaLabel);
        }
        layoutPrincipal.getChildren().add(diasSemana); // Linha de dias da semana

        // Criação do calendário (grid de dias)
        GridPane gridDias = new GridPane();
        gridDias.setHgap(10);
        gridDias.setVgap(10);

        // Preenche os dias do mês
        int diaSemanaIniciar = primeiroDiaDoMes.getDayOfWeek().getValue() % 7; // Posição para o primeiro dia
        int diaDoMes = 1;

        for (int linha = 2; linha < 7; linha++) {
            for (int coluna = 0; coluna < 7; coluna++) {
                if ((linha == 2 && coluna >= diaSemanaIniciar) || (linha > 2)) {
                    if (diaDoMes <= ultimoDiaDoMes.getDayOfMonth()) {
                        // Torna o valor de diaDoMes final para uso dentro da lambda
                        final int diaFinal = diaDoMes;  // Torna diaDoMes final

                        // Adiciona o botão para o dia
                        Button diaButton = new Button(String.valueOf(diaDoMes));
                        diaButton.setOnAction(e -> {
                            // Ação para selecionar o dia (pode ser usada para reservar ou exibir detalhes)
                            System.out.println("Dia selecionado: " + diaFinal);  // Usando diaFinal que é final
                        });
                        gridDias.add(diaButton, coluna, linha);
                        diaDoMes++;
                    }
                }
            }
        }

        layoutPrincipal.getChildren().add(gridDias);  // Adiciona o grid com os dias
    }

    // Verificar se há reservas para um dia específico
    private boolean temReservaNoDia(LocalDate dia) {
        // Verifica se existe alguma reserva no dia
        for (Reserva reserva : reservasSalaReuniao) {
            if (reserva.getDataHoraInicio().toLocalDate().equals(dia)) {
                return true;
            }
        }
        return false;
    }

    // Método de ação quando um dia é clicado
    private void selecionarDia(MouseEvent event, int dia) {
        // Abrir janela de seleção de horário
        System.out.println("Dia selecionado: " + dia);
        mostrarHoraDeReserva(dia);
    }

    // Método para selecionar hora e duração da reserva
    private void mostrarHoraDeReserva(int dia) {
        Stage popup = new Stage();
        popup.setTitle("Selecione o horário para reserva no dia " + dia);

        VBox layoutPopup = new VBox(10);
        layoutPopup.setPadding(new javafx.geometry.Insets(15));

        Label labelHoraInicio = new Label("Hora de Início (HH:mm):");
        TextField horaInicioField = new TextField();
        horaInicioField.setPromptText("Ex: 10:00");

        Label labelDuracao = new Label("Duração (em horas):");
        TextField duracaoField = new TextField();
        duracaoField.setPromptText("Ex: 2");

        // Pergunta específica para o tipo de sala
        Label labelTipoReserva = new Label("Tipo de Sala:");
        ComboBox<String> tipoReservaComboBox = new ComboBox<>();
        tipoReservaComboBox.getItems().addAll("Coworking", "Sala de Reuniões");
        
        // Quando o tipo de reserva for escolhido
        tipoReservaComboBox.setOnAction(e -> {
            if ("Coworking".equals(tipoReservaComboBox.getValue())) {
                Label labelProjetor = new Label("Vai precisar de projetor?");
                layoutPopup.getChildren().add(labelProjetor);
            } else if ("Sala de Reuniões".equals(tipoReservaComboBox.getValue())) {
                Label labelTransmissao = new Label("Vai realizar transmissão ao vivo?");
                layoutPopup.getChildren().add(labelTransmissao);
            }
        });

        Button btnConfirmar = new Button("Confirmar Reserva");
        btnConfirmar.setOnAction(e -> {
            // Lógica para confirmar a reserva
            confirmarReserva(dia, horaInicioField.getText(), duracaoField.getText());
            popup.close();
        });

        layoutPopup.getChildren().addAll(labelHoraInicio, horaInicioField, labelDuracao, duracaoField, labelTipoReserva, tipoReservaComboBox, btnConfirmar);

        Scene scenePopup = new Scene(layoutPopup, 300, 300);
        popup.setScene(scenePopup);
        popup.show();
    }

    // Método para confirmar a reserva
    private void confirmarReserva(int dia, String horaInicio, String duracao) {
        LocalDateTime dataHoraInicio = LocalDate.parse("2024-12-" + dia).atTime(LocalTime.parse(horaInicio));
        LocalDateTime dataHoraFim = dataHoraInicio.plusHours(Integer.parseInt(duracao));

        reservasSalaReuniao.add(new Reserva("Solicitante", dataHoraInicio, dataHoraFim, "Sala de Reuniões", false));

        // Atualiza o calendário
        System.out.println("Reserva confirmada para o dia " + dia + " às " + horaInicio + " com duração de " + duracao + " horas.");
    }
}

// Classe Reserva para representar os dados de uma reserva
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
}
