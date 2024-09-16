package control;

import control.Evento;
import control.Reserva;
import control.ReservaSalaReunioes;
import control.ReservaCoworking;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

public class Main {
    private List<Projeto> projetos;
    private List<Evento> eventos;  // Correção: tipo deve ser Evento
    private List<Monitoramento> monitoramentos;
    private List<GestaoEstrategica> gestoesEstrategicas;
    private List<AmbienteIdeacao> ambientesIdeacao;
    private List<GestaoAmbiental> gestoesAmbientais;
    private List<ResponsabilidadeSocial> responsabilidadesSociais;
    private List<Internacionalizacao> internacionalizacoes;
    private List<Reserva> reservas; // Correção: reservas deve ser uma lista

    // Construtor
    public Main() {
        projetos = new ArrayList<>();
        eventos = new ArrayList<>();
        monitoramentos = new ArrayList<>();
        gestoesEstrategicas = new ArrayList<>();
        ambientesIdeacao = new ArrayList<>();
        gestoesAmbientais = new ArrayList<>();
        responsabilidadesSociais = new ArrayList<>();
        internacionalizacoes = new ArrayList<>();
        reservas = new ArrayList<>(); // Inicialização correta
    }

    // Método principal
    public static void main(String[] args) {
        Main main = new Main();
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\nMenu:");
            System.out.println("1. Cadastrar Projeto");
            System.out.println("2. Cadastrar Evento");
            System.out.println("3. Cadastrar Monitoramento");
            System.out.println("4. Cadastrar Gestão Estratégica");
            System.out.println("5. Cadastrar Ambiente de Ideação");
            System.out.println("6. Cadastrar Gestão Ambiental");
            System.out.println("7. Cadastrar Responsabilidade Social");
            System.out.println("8. Cadastrar Internacionalização");
            System.out.println("9. Cadastrar Reserva");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            switch (opcao) {
                case 1:
                    System.out.print("Informe o nome do projeto: ");
                    String nomeProjeto = scanner.nextLine();
                    System.out.print("Informe a descrição do projeto: ");
                    String descricaoProjeto = scanner.nextLine();
                    System.out.print("Informe a data de início do projeto (yyyy-mm-dd): ");
                    String dataInicioProjeto = scanner.nextLine();

                    main.cadastrarProjeto(new Projeto(nomeProjeto, descricaoProjeto, dataInicioProjeto));
                    break;
                case 2:
                    System.out.print("Informe o nome do evento: ");
                    String nomeEvento = scanner.nextLine();
                    System.out.print("Informe os detalhes do evento: ");
                    String detalhesEvento = scanner.nextLine();
                    System.out.print("Informe a data do evento (yyyy-mm-dd): ");
                    String dataEvento = scanner.nextLine();
                    System.out.print("Informe a hora de início do evento (hh:mm): ");
                    String horaInicioEvento = scanner.nextLine();
                    System.out.print("Informe a hora de fim do evento (hh:mm): ");
                    String horaFimEvento = scanner.nextLine();
                    System.out.print("Informe o local do evento: ");
                    String localEvento = scanner.nextLine();
                    System.out.print("Informe o número de participantes: ");
                    int numeroParticipantes = scanner.nextInt();
                    scanner.nextLine(); // Limpa o buffer
                    System.out.print("Informe o responsável pelo evento: ");
                    String responsavelEvento = scanner.nextLine();

                    // Converter data e horas para os tipos adequados
                    LocalDate dia = LocalDate.parse(dataEvento);
                    LocalTime horarioInicio = LocalTime.parse(horaInicioEvento);
                    LocalTime horarioTermino = LocalTime.parse(horaFimEvento);

                    // Criar o objeto Evento com todos os parâmetros necessários
                    Evento evento = new Evento(nomeEvento, detalhesEvento, dia, horarioInicio, horarioTermino, localEvento, numeroParticipantes, responsavelEvento);

                    // Aqui você pode cadastrar o evento, por exemplo, em uma lista ou banco de dados
                    cadastrarEvento(evento);
                    break;
                case 3:
                    System.out.print("Escolha a empresa incubada (1 - Polygon, 2 - ICRO Digital, 3 - SMARTComerci, 4 - iLegis): ");
                    int escolhaEmpresa = scanner.nextInt();
                    scanner.nextLine(); // Limpa o buffer
                    EmpresaIncubada empresaSelecionada = null;

                    switch (escolhaEmpresa) {
                        case 1:
                            empresaSelecionada = EmpresaIncubada.POLYGON;
                            break;
                        case 2:
                            empresaSelecionada = EmpresaIncubada.ICRO_DIGITAL;
                            break;
                        case 3:
                            empresaSelecionada = EmpresaIncubada.SMARTCOMERCI;
                            break;
                        case 4:
                            empresaSelecionada = EmpresaIncubada.ILEGIS;
                            break;
                        default:
                            System.out.println("Opção inválida. Tente novamente.");
                            continue; // Retorna ao início do loop
                    }

                    System.out.print("Informe a data e hora do monitoramento (yyyy-mm-dd hh:mm): ");
                    String dataHoraInput = scanner.nextLine();
                    LocalDateTime dataHora = LocalDateTime.parse(dataHoraInput.replace(" ", "T")); // Formato correto para LocalDateTime

                    System.out.print("Informe os documentos pendentes (separados por vírgula): ");
                    String documentosInput = scanner.nextLine();
                    List<String> documentosPendentes = List.of(documentosInput.split(",")); // Converte para lista

                    System.out.print("Informe o caminho do arquivo Excel: ");
                    String arquivoExcel = scanner.nextLine();

                    // Cadastrar o monitoramento
                    main.cadastrarMonitoramento(new Monitoramento(empresaSelecionada, dataHora, documentosPendentes, arquivoExcel));
                    break;
                case 4:
                    System.out.print("Informe o objetivo da gestão estratégica: ");
                    String objetivoGestao = scanner.nextLine();
                    main.cadastrarGestaoEstrategica(new GestaoEstrategica(objetivoGestao));
                    break;
                case 5:
                    System.out.print("Informe o nome do ambiente de ideação: ");
                    String nomeAmbiente = scanner.nextLine();
                    main.cadastrarAmbienteIdeacao(new AmbienteIdeacao(nomeAmbiente));
                    break;
                case 6:
                    System.out.print("Informe a ação da gestão ambiental: ");
                    String acaoGestao = scanner.nextLine();
                    System.out.print("Informe o impacto da gestão ambiental: ");
                    String impactoGestao = scanner.nextLine();
                    main.cadastrarGestaoAmbiental(new GestaoAmbiental(acaoGestao, impactoGestao));
                    break;
                case 7:
                    System.out.print("Informe o projeto social: ");
                    String projetoSocial = scanner.nextLine();
                    System.out.print("Informe o impacto do projeto social: ");
                    String impactoSocial = scanner.nextLine();
                    main.cadastrarResponsabilidadeSocial(new ResponsabilidadeSocial(projetoSocial, impactoSocial));
                    break;
                case 8:
                    System.out.print("Informe o país: ");
                    String pais = scanner.nextLine();
                    System.out.print("Informe o projeto internacional: ");
                    String projetoInternacional = scanner.nextLine();
                    main.cadastrarInternacionalizacao(new Internacionalizacao(pais, projetoInternacional));
                    break;
                case 9:
                    System.out.print("Escolha o tipo de reserva (1 - Sala de Reuniões, 2 - Coworking): ");
                    int tipoReservaEscolha = scanner.nextInt();
                    scanner.nextLine(); // Limpa o buffer

                    System.out.print("Informe o nome do solicitante: ");
                    String solicitante = scanner.nextLine();
                    System.out.print("Informe a data e hora de início da reserva (yyyy-mm-dd hh:mm): ");
                    String dataHoraInicioInput = scanner.nextLine();
                    LocalDateTime dataHoraInicio = LocalDateTime.parse(dataHoraInicioInput.replace(" ", "T"));
                    System.out.print("Informe a data e hora de fim da reserva (yyyy-mm-dd hh:mm): ");
                    String dataHoraFimInput = scanner.nextLine();
                    LocalDateTime dataHoraFim = LocalDateTime.parse(dataHoraFimInput.replace(" ", "T"));

                    if (tipoReservaEscolha == 1) {
                        System.out.print("Informe a descrição da reserva: ");
                        String descricaoReserva = scanner.nextLine();
                        System.out.print("Informe o número de pessoas: ");
                        int numeroPessoas = scanner.nextInt();
                        scanner.nextLine(); // Limpa o buffer
                        System.out.print("Necessita de transmissão (true/false): ");
                        boolean necessitaTransmissao = scanner.nextBoolean();
                        scanner.nextLine(); // Limpa o buffer

                        ReservaSalaReunioes reservaSalaReunioes = new ReservaSalaReunioes(solicitante, dataHoraInicio, dataHoraFim, descricaoReserva, numeroPessoas, necessitaTransmissao);
                        main.cadastrarReserva(reservaSalaReunioes);
                    } else if (tipoReservaEscolha == 2) {
                        // Para o coworking, você pode adicionar mais parâmetros, se necessário.
                        System.out.print("Informe a descrição da reserva: ");
                        String descricaoReserva = scanner.nextLine();
                        System.out.print("Necessita de transmissão (true/false): ");
                        boolean necessitaTransmissao = scanner.nextBoolean();
                        scanner.nextLine(); // Limpa o buffer

                        ReservaCoworking reservaCoworking = new ReservaCoworking(solicitante, dataHoraInicio, dataHoraFim, descricaoReserva, necessitaTransmissao);
                        main.cadastrarReserva(reservaCoworking);
                    } else {
                        System.out.println("Tipo de reserva inválido.");
                    }
                    break;
                case 0:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }

    public void cadastrarProjeto(Projeto projeto) {
        projetos.add(projeto);
        System.out.println("Projeto cadastrado com sucesso!");
    }

    public void cadastrarEvento(Evento evento) {
        eventos.add(evento);
        System.out.println("Evento cadastrado com sucesso!");
    }

    public void cadastrarMonitoramento(Monitoramento monitoramento) {
        monitoramentos.add(monitoramento);
        System.out.println("Monitoramento cadastrado com sucesso!");
    }

    public void cadastrarGestaoEstrategica(GestaoEstrategica gestaoEstrategica) {
        gestoesEstrategicas.add(gestaoEstrategica);
        System.out.println("Gestão Estratégica cadastrada com sucesso!");
    }

    public void cadastrarAmbienteIdeacao(AmbienteIdeacao ambienteIdeacao) {
        ambientesIdeacao.add(ambienteIdeacao);
        System.out.println("Ambiente de Ideação cadastrado com sucesso!");
    }

    public void cadastrarGestaoAmbiental(GestaoAmbiental gestaoAmbiental) {
        gestoesAmbientais.add(gestaoAmbiental);
        System.out.println("Gestão Ambiental cadastrada com sucesso!");
    }

    public void cadastrarResponsabilidadeSocial(ResponsabilidadeSocial responsabilidadeSocial) {
        responsabilidadesSociais.add(responsabilidadeSocial);
        System.out.println("Responsabilidade Social cadastrada com sucesso!");
    }

    public void cadastrarInternacionalizacao(Internacionalizacao internacionalizacao) {
        internacionalizacoes.add(internacionalizacao);
        System.out.println("Internacionalização cadastrada com sucesso!");
    }

    public void cadastrarReserva(Reserva reserva) {
        reservas.add(reserva);
        System.out.println("Reserva cadastrada com sucesso!");
    }
}
