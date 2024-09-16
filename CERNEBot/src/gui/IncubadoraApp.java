package gui;

import control.Main;
import control.Projeto;
import control.Evento;
import control.Reserva;
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

        // Ação do botão 'Cadastrar Projeto'
        cadastrarProjetoButton.setOnAction(e -> cadastrarProjeto());

        // Ação do botão 'Cadastrar Evento'
        cadastrarEventoButton.setOnAction(e -> cadastrarEvento());

        // Ação do botão 'Cadastrar Reserva'
        cadastrarReservaButton.setOnAction(e -> cadastrarReserva());

        // Ação do botão 'Sair'
        sairButton.setOnAction(e -> primaryStage.close());

        VBox vbox = new VBox(10, cadastrarProjetoButton, cadastrarEventoButton, cadastrarReservaButton, sairButton);
        Scene scene = new Scene(vbox, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void cadastrarProjeto() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Cadastrar Projeto");
        dialog.setHeaderText("Informe o nome do projeto:");
        Optional<String> nomeProjeto = dialog.showAndWait();

        if (nomeProjeto.isPresent() && !nomeProjeto.get().isEmpty()) {
            dialog.setHeaderText("Informe a descrição do projeto:");
            Optional<String> descricaoProjeto = dialog.showAndWait();

            if (descricaoProjeto.isPresent() && !descricaoProjeto.get().isEmpty()) {
                dialog.setHeaderText("Informe a data de início do projeto (yyyy-mm-dd):");
                Optional<String> dataInicioProjeto = dialog.showAndWait();

                if (dataInicioProjeto.isPresent() && !dataInicioProjeto.get().isEmpty()) {
                    mainController.cadastrarProjeto(new Projeto(nomeProjeto.get(), descricaoProjeto.get(), dataInicioProjeto.get()));
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

    private void cadastrarEvento() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Cadastrar Evento");
        dialog.setHeaderText("Informe o nome do evento:");
        Optional<String> nomeEvento = dialog.showAndWait();

        if (nomeEvento.isPresent() && !nomeEvento.get().isEmpty()) {
            dialog.setHeaderText("Informe os detalhes do evento:");
            Optional<String> detalhesEvento = dialog.showAndWait();

            if (detalhesEvento.isPresent() && !detalhesEvento.get().isEmpty()) {
                dialog.setHeaderText("Informe a data do evento (yyyy-mm-dd):");
                Optional<String> dataEvento = dialog.showAndWait();

                if (dataEvento.isPresent() && !dataEvento.get().isEmpty()) {
                    dialog.setHeaderText("Informe a hora de início do evento (hh:mm):");
                    Optional<String> horaInicioEvento = dialog.showAndWait();

                    if (horaInicioEvento.isPresent() && !horaInicioEvento.get().isEmpty()) {
                        dialog.setHeaderText("Informe a hora de fim do evento (hh:mm):");
                        Optional<String> horaFimEvento = dialog.showAndWait();

                        if (horaFimEvento.isPresent() && !horaFimEvento.get().isEmpty()) {
                            dialog.setHeaderText("Informe o local do evento:");
                            Optional<String> localEvento = dialog.showAndWait();

                            if (localEvento.isPresent() && !localEvento.get().isEmpty()) {
                                dialog.setHeaderText("Informe o número de participantes:");
                                Optional<String> numeroParticipantes = dialog.showAndWait();

                                if (numeroParticipantes.isPresent() && !numeroParticipantes.get().isEmpty()) {
                                    dialog.setHeaderText("Informe o responsável pelo evento:");
                                    Optional<String> responsavelEvento = dialog.showAndWait();

                                    if (responsavelEvento.isPresent() && !responsavelEvento.get().isEmpty()) {
                                        // Converter data e horas para os tipos adequados
                                        try {
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

    private void cadastrarReserva() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Cadastrar Reserva");
        dialog.setHeaderText("Informe o nome do solicitante:");
        Optional<String> solicitante = dialog.showAndWait();

        if (solicitante.isPresent() && !solicitante.get().isEmpty()) {
            dialog.setHeaderText("Informe a data e hora de início da reserva (yyyy-mm-dd hh:mm):");
            Optional<String> dataHoraInicioInput = dialog.showAndWait();

            if (dataHoraInicioInput.isPresent() && !dataHoraInicioInput.get().isEmpty()) {
                try {
                    LocalDateTime dataHoraInicio = LocalDateTime.parse(dataHoraInicioInput.get().replace(" ", "T"));

                    dialog.setHeaderText("Informe a data e hora de fim da reserva (yyyy-mm-dd hh:mm):");
                    Optional<String> dataHoraFimInput = dialog.showAndWait();

                    if (dataHoraFimInput.isPresent() && !dataHoraFimInput.get().isEmpty()) {
                        LocalDateTime dataHoraFim = LocalDateTime.parse(dataHoraFimInput.get().replace(" ", "T"));

                        dialog.setHeaderText("Escolha o tipo de reserva (1 - Sala de Reuniões, 2 - Coworking):");
                        Optional<String> tipoReservaInput = dialog.showAndWait();

                        if (tipoReservaInput.isPresent() && !tipoReservaInput.get().isEmpty()) {
                            int tipoReservaEscolha = Integer.parseInt(tipoReservaInput.get());

                            if (tipoReservaEscolha == 1) {
                                dialog.setHeaderText("Informe a descrição da reserva:");
                                Optional<String> descricaoReserva = dialog.showAndWait();

                                if (descricaoReserva.isPresent() && !descricaoReserva.get().isEmpty()) {
                                    mainController.cadastrarReserva(new ReservaSalaReunioes(
                                            solicitante.get(),
                                            dataHoraInicio,
                                            dataHoraFim,
                                            descricaoReserva.get()
                                    ));
                                } else {
                                    showAlert("Descrição da reserva não pode ser vazia.");
                                }
                            } else if (tipoReservaEscolha == 2) {
                                dialog.setHeaderText("Informe se necessita projetor (true/false):");
                                Optional<String> necessitaProjetorInput = dialog.showAndWait();

                                if (necessitaProjetorInput.isPresent()) {
                                    boolean necessitaProjetor = Boolean.parseBoolean(necessitaProjetorInput.get());

                                    dialog.setHeaderText("Informe se necessita computadores (true/false):");
                                    Optional<String> necessitaComputadoresInput = dialog.showAndWait();

                                    if (necessitaComputadoresInput.isPresent()) {
                                        boolean necessitaComputadores = Boolean.parseBoolean(necessitaComputadoresInput.get());

                                        dialog.setHeaderText("Informe a descrição da reserva:");
                                        Optional<String> descricaoReserva = dialog.showAndWait();

                                        if (descricaoReserva.isPresent() && !descricaoReserva.get().isEmpty()) {
                                            mainController.cadastrarReserva(new ReservaCoworking(
                                                    solicitante.get(),
                                                    dataHoraInicio,
                                                    dataHoraFim,
                                                    necessitaProjetor,
                                                    necessitaComputadores,
                                                    descricaoReserva.get()
                                            ));
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

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Atenção");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
