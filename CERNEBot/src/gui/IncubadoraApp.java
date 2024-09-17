package gui;

import control.Main;
import control.Projeto;
import control.Evento;
import control.ReservaSalaReunioes;
import control.ReservaCoworking;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.util.Optional;

public class IncubadoraApp extends Application {
    private Main mainController; // Controlador principal

    @Override
    public void start(Stage primaryStage) {
        mainController = new Main(); // Instanciando a classe Main

        primaryStage.setTitle("Sistema de Gerenciamento de Incubadora");

        // Menu principal
        Button cadastrarProjetoButton = new Button("Cadastrar Projeto");
        Button cadastrarEventoButton = new Button("Cadastrar Evento");
        Button cadastrarReservaButton = new Button("Cadastrar Reserva");
        Button sairButton = new Button("Sair");

        // Ações dos botões
        cadastrarProjetoButton.setOnAction(e -> cadastrarProjeto());
        cadastrarEventoButton.setOnAction(e -> cadastrarEventoComDialog());
        cadastrarReservaButton.setOnAction(e -> cadastrarReserva());
        sairButton.setOnAction(e -> primaryStage.close());

        VBox vbox = new VBox(10, cadastrarProjetoButton, cadastrarEventoButton, cadastrarReservaButton, sairButton);
        Scene scene = new Scene(vbox, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void cadastrarProjeto() {
        Optional<String> nomeProjeto = showInputDialog("Cadastrar Projeto", "Informe o nome do projeto:");

        if (nomeProjeto.isPresent() && !nomeProjeto.get().isEmpty()) {
            Optional<String> descricaoProjeto = showInputDialog("Cadastrar Projeto", "Informe a descrição do projeto:");

            if (descricaoProjeto.isPresent() && !descricaoProjeto.get().isEmpty()) {
                Optional<String> dataInicioProjeto = showInputDialog("Cadastrar Projeto", "Informe a data de início do projeto (yyyy-mm-dd):");

                if (dataInicioProjeto.isPresent() && !dataInicioProjeto.get().isEmpty()) {
                    Projeto projeto = new Projeto(nomeProjeto.get(), descricaoProjeto.get(), dataInicioProjeto.get());
                    System.out.println("Projeto cadastrado com sucesso: " + projeto.getNomeProjeto());
                } else {
                    showAlert("Data de início não pode ser vazia.");
                }
            } else {
                showAlert("Descrição do projeto não pode ser vazia.");
            }
        } else {
            showAlert("Nome do projeto não pode ser vazio.");
        }
    }

    private void cadastrarEventoComDialog() {
        Optional<String> nomeEvento = showInputDialog("Cadastrar Evento", "Informe o nome do evento:");

        if (nomeEvento.isPresent()) {
            Optional<String> detalhesEvento = showInputDialog("Cadastrar Evento", "Informe os detalhes do evento:");

            if (detalhesEvento.isPresent()) {
                Optional<String> dataEvento = showInputDialog("Cadastrar Evento", "Informe a data do evento (yyyy-mm-dd):");

                if (dataEvento.isPresent()) {
                    Optional<String> horaInicioEvento = showInputDialog("Cadastrar Evento", "Informe a hora de início do evento (hh:mm):");

                    if (horaInicioEvento.isPresent()) {
                        Optional<String> horaFimEvento = showInputDialog("Cadastrar Evento", "Informe a hora de fim do evento (hh:mm):");

                        if (horaFimEvento.isPresent()) {
                            Optional<String> localEvento = showInputDialog("Cadastrar Evento", "Informe o local do evento:");

                            if (localEvento.isPresent()) {
                                Optional<String> numeroParticipantes = showInputDialog("Cadastrar Evento", "Informe o número de participantes:");

                                if (numeroParticipantes.isPresent()) {
                                    Optional<String> responsavelEvento = showInputDialog("Cadastrar Evento", "Informe o responsável pelo evento:");

                                    if (responsavelEvento.isPresent()) {
                                        try {
                                            // Converte as entradas para os tipos adequados
                                            LocalDateTime dataHoraInicio = LocalDateTime.parse(dataEvento.get() + "T" + horaInicioEvento.get());
                                            LocalDateTime dataHoraFim = LocalDateTime.parse(dataEvento.get() + "T" + horaFimEvento.get());

                                            mainController.cadastrarEvento(new Evento(
                                                    nomeEvento.get(),
                                                    detalhesEvento.get(),
                                                    dataHoraInicio.toLocalDate(),
                                                    dataHoraInicio.toLocalTime(),
                                                    dataHoraFim.toLocalTime(),
                                                    localEvento.get(),
                                                    Integer.parseInt(numeroParticipantes.get()),
                                                    responsavelEvento.get()
                                            ));
                                            showAlert("Evento cadastrado com sucesso!");
                                        } catch (Exception e) {
                                            showAlert("Erro ao cadastrar o evento. Verifique os dados inseridos.");
                                        }
                                    } else {
                                        showAlert("Responsável não pode ser vazio.");
                                    }
                                } else {
                                    showAlert("Número de participantes não pode ser vazio.");
                                }
                            } else {
                                showAlert("Local do evento não pode ser vazio.");
                            }
                        } else {
                            showAlert("Hora de fim não pode ser vazia.");
                        }
                    } else {
                        showAlert("Hora de início não pode ser vazia.");
                    }
                } else {
                    showAlert("Data do evento não pode ser vazia.");
                }
            } else {
                showAlert("Detalhes do evento não podem ser vazios.");
            }
        } else {
            showAlert("Nome do evento não pode ser vazio.");
        }
    }

    private Optional<String> showInputDialog(String title, String headerText) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(title);
        dialog.setHeaderText(headerText);
        return dialog.showAndWait();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void cadastrarReserva() {
        Optional<String> solicitante = showInputDialog("Cadastrar Reserva", "Informe o nome do solicitante:");

        if (solicitante.isPresent() && !solicitante.get().isEmpty()) {
            Optional<String> dataHoraInicioInput = showInputDialog("Cadastrar Reserva", "Informe a data e hora de início da reserva (yyyy-mm-dd hh:mm):");

            if (dataHoraInicioInput.isPresent() && !dataHoraInicioInput.get().isEmpty()) {
                try {
                    LocalDateTime dataHoraInicio = LocalDateTime.parse(dataHoraInicioInput.get().replace(" ", "T"));

                    Optional<String> dataHoraFimInput = showInputDialog("Cadastrar Reserva", "Informe a data e hora de fim da reserva (yyyy-mm-dd hh:mm):");

                    if (dataHoraFimInput.isPresent() && !dataHoraFimInput.get().isEmpty()) {
                        LocalDateTime dataHoraFim = LocalDateTime.parse(dataHoraFimInput.get().replace(" ", "T"));

                        Optional<String> tipoReservaInput = showInputDialog("Cadastrar Reserva", "Escolha o tipo de reserva (1 - Sala de Reuniões, 2 - Coworking):");

                        if (tipoReservaInput.isPresent() && !tipoReservaInput.get().isEmpty()) {
                            int tipoReservaEscolha = Integer.parseInt(tipoReservaInput.get());

                            if (tipoReservaEscolha == 1) {
                                Optional<String> descricaoReserva = showInputDialog("Cadastrar Reserva", "Informe a descrição da reserva:");

                                if (descricaoReserva.isPresent() && !descricaoReserva.get().isEmpty()) {
                                    mainController.cadastrarReserva(new ReservaSalaReunioes(
                                            solicitante.get(),
                                            dataHoraInicio,
                                            dataHoraFim,
                                            descricaoReserva.get()
                                    ));
                                    showAlert("Reserva de sala de reuniões cadastrada com sucesso!");
                                } else {
                                    showAlert("Descrição da reserva não pode ser vazia.");
                                }
                            } else if (tipoReservaEscolha == 2) {
                                Optional<String> necessitaProjetorInput = showInputDialog("Cadastrar Reserva", "Informe se necessita projetor (true/false):");

                                if (necessitaProjetorInput.isPresent()) {
                                    boolean necessitaProjetor = Boolean.parseBoolean(necessitaProjetorInput.get());

                                    Optional<String> necessitaComputadoresInput = showInputDialog("Cadastrar Reserva", "Informe se necessita computadores (true/false):");

                                    if (necessitaComputadoresInput.isPresent()) {
                                        boolean necessitaComputadores = Boolean.parseBoolean(necessitaComputadoresInput.get());

                                        Optional<String> descricaoReserva = showInputDialog("Cadastrar Reserva", "Informe a descrição da reserva:");

                                        if (descricaoReserva.isPresent() && !descricaoReserva.get().isEmpty()) {
                                            mainController.cadastrarReserva(new ReservaCoworking(
                                                    solicitante.get(),
                                                    dataHoraInicio,
                                                    dataHoraFim,
                                                    necessitaProjetor,
                                                    necessitaComputadores,
                                                    descricaoReserva.get()
                                            ));
                                            showAlert("Reserva de coworking cadastrada com sucesso!");
                                        } else {
                                            showAlert("Descrição da reserva não pode ser vazia.");
                                        }
                                    }
                                }
                            } else {
                                showAlert("Tipo de reserva inválido.");
                            }
                        }
                    }
                } catch (Exception e) {
                    showAlert("Erro ao cadastrar a reserva. Verifique os dados inseridos.");
                }
            } else {
                showAlert("Data e hora de início não podem ser vazias.");
            }
        } else {
            showAlert("Nome do solicitante não pode ser vazio.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
