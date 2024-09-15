package gui;

import control.Main;
import control.Projeto;
import control.Reserva;
import control.SolicitacaoPecas;

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

        if (nomeProjeto.isPresent()) {
            dialog.setHeaderText("Informe a descrição do projeto:");
            Optional<String> descricaoProjeto = dialog.showAndWait();

            if (descricaoProjeto.isPresent()) {
                dialog.setHeaderText("Informe a data de início do projeto (yyyy-mm-dd):");
                Optional<String> dataInicioProjeto = dialog.showAndWait();

                if (dataInicioProjeto.isPresent()) {
                    mainController.cadastrarProjeto(new Projeto(nomeProjeto.get(), descricaoProjeto.get(), dataInicioProjeto.get()));
                }
            }
        }
    }

    private void cadastrarEvento() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Cadastrar Evento");
        dialog.setHeaderText("Informe o nome do evento:");
        Optional<String> nomeEvento = dialog.showAndWait();

        if (nomeEvento.isPresent()) {
            dialog.setHeaderText("Informe os detalhes do evento:");
            Optional<String> detalhesEvento = dialog.showAndWait();

            if (detalhesEvento.isPresent()) {
                dialog.setHeaderText("Informe a data do evento (yyyy-mm-dd):");
                Optional<String> dataEvento = dialog.showAndWait();

                if (dataEvento.isPresent()) {
                    dialog.setHeaderText("Informe a hora de início do evento (hh:mm):");
                    Optional<String> horaInicioEvento = dialog.showAndWait();

                    if (horaInicioEvento.isPresent()) {
                        dialog.setHeaderText("Informe a hora de fim do evento (hh:mm):");
                        Optional<String> horaFimEvento = dialog.showAndWait();

                        if (horaFimEvento.isPresent()) {
                            dialog.setHeaderText("Informe o local do evento:");
                            Optional<String> localEvento = dialog.showAndWait();

                            if (localEvento.isPresent()) {
                                dialog.setHeaderText("Informe o número de participantes:");
                                Optional<String> numeroParticipantes = dialog.showAndWait();

                                if (numeroParticipantes.isPresent()) {
                                    dialog.setHeaderText("Informe o responsável pelo evento:");
                                    Optional<String> responsavelEvento = dialog.showAndWait();

                                    if (responsavelEvento.isPresent()) {
                                        mainController.cadastrarEvento(new Evento(
                                                nomeEvento.get(),
                                                detalhesEvento.get(),
                                                dataEvento.get(),
                                                horaInicioEvento.get(),
                                                horaFimEvento.get(),
                                                localEvento.get(),
                                                numeroParticipantes.get(),
                                                responsavelEvento.get()
                                        ));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void cadastrarReserva() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Cadastrar Reserva");
        dialog.setHeaderText("Informe o nome do solicitante:");
        Optional<String> solicitante = dialog.showAndWait();

        if (solicitante.isPresent()) {
            dialog.setHeaderText("Informe a data e hora de início da reserva (yyyy-mm-dd hh:mm):");
            Optional<String> dataHoraInicioInput = dialog.showAndWait();

            if (dataHoraInicioInput.isPresent()) {
                LocalDateTime dataHoraInicio = LocalDateTime.parse(dataHoraInicioInput.get().replace(" ", "T"));

                dialog.setHeaderText("Informe a data e hora de fim da reserva (yyyy-mm-dd hh:mm):");
                Optional<String> dataHoraFimInput = dialog.showAndWait();

                if (dataHoraFimInput.isPresent()) {
                    LocalDateTime dataHoraFim = LocalDateTime.parse(dataHoraFimInput.get().replace(" ", "T"));

                    dialog.setHeaderText("Escolha o tipo de reserva (1 - Sala de Reuniões, 2 - Coworking):");
                    Optional<String> tipoReservaInput = dialog.showAndWait();

                    if (tipoReservaInput.isPresent()) {
                        int tipoReservaEscolha = Integer.parseInt(tipoReservaInput.get());

                        if (tipoReservaEscolha == 1) {
                            dialog.setHeaderText("Informe a descrição da reserva:");
                            Optional<String> descricaoReserva = dialog.showAndWait();

                            if (descricaoReserva.isPresent()) {
                                mainController.cadastrarReserva(new ReservaSalaReunioes(
                                        solicitante.get(),
                                        dataHoraInicio,
                                        dataHoraFim,
                                        descricaoReserva.get()
                                ));
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

                                    if (descricaoReserva.isPresent()) {
                                        mainController.cadastrarReserva(new ReservaCoworking(
                                                solicitante.get(),
                                                dataHoraInicio,
                                                dataHoraFim,
                                                descricaoReserva.get(),
                                                necessitaProjetor,
                                                necessitaComputadores
                                        ));
                                    }
                                }
                            }
                        } else {
                            System.out.println("Opção inválida. Tente novamente.");
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
