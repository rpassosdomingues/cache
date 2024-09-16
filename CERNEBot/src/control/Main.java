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
    private List<Evento> eventos;  
    private List<Monitoramento> monitoramentos;
    private List<GestaoEstrategica> gestoesEstrategicas;
    private List<AmbienteIdeacao> ambientesIdeacao;
    private List<GestaoAmbiental> gestoesAmbientais;
    private List<ResponsabilidadeSocial> responsabilidadesSociais;
    private List<Internacionalizacao> internacionalizacoes;
    private List<Reserva> reservas; 

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
        reservas = new ArrayList<>(); 
    }

    // Método principal
    public static void main(String[] args) {
        Main main = new Main();
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\nMenu Principal:");
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
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    main.cadastrarProjeto(scanner); // Chama o metodo de instância diretamente
                    break;
                case 2:
                    main.cadastrarEvento(scanner);
                    break;
                case 3:
                    main.cadastrarMonitoramento(scanner);
                    break;
                case 4:
                    main.cadastrarGestaoEstrategica(scanner);
                    break;
                case 5:
                    main.cadastrarAmbienteIdeacao(scanner);
                    break;
                case 6:
                    main.cadastrarGestaoAmbiental(scanner);
                    break;
                case 7:
                    main.cadastrarResponsabilidadeSocial(scanner);
                    break;
                case 8:
                    main.cadastrarInternacionalizacao(scanner);
                    break;
                case 9:
                    main.cadastrarReserva(scanner);
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

    // Metodos de cadastro
    // Método para cadastrar um projeto
    private void cadastrarProjeto(Scanner scanner) {
        System.out.print("Informe o nome do projeto: ");
        String nomeProjeto = scanner.nextLine();
        System.out.print("Informe a descrição do projeto: ");
        String descricaoProjeto = scanner.nextLine();
        System.out.print("Informe a data de início do projeto (yyyy-mm-dd): ");
        String dataInicioProjeto = scanner.nextLine();

        // Criação do objeto Projeto
        Projeto projeto = new Projeto(nomeProjeto, descricaoProjeto, dataInicioProjeto);

        // Chamar o método estático para cadastrar o projeto
        Projeto.cadastrarProjeto(projeto);
    }

    private void cadastrarEvento(Scanner scanner) {
        System.out.print("Informe o nome do evento: ");
        String nomeEvento = scanner.nextLine(); // Usando a entrada do usuário

        System.out.print("Informe os detalhes do evento: ");
        String detalhesEvento = scanner.nextLine(); // Detalhes do evento

        System.out.print("Informe a data do evento (yyyy-MM-dd): ");
        String dataEvento = scanner.nextLine(); // Data do evento

        System.out.print("Informe a hora de início do evento (HH:mm): ");
        String horaInicioEvento = scanner.nextLine(); // Hora de início

        System.out.print("Informe a hora de fim do evento (HH:mm): ");
        String horaFimEvento = scanner.nextLine(); // Hora de fim

        System.out.print("Informe o local do evento: ");
        String localEvento = scanner.nextLine(); // Local do evento

        System.out.print("Informe o número de participantes: ");
        int numeroParticipantes = scanner.nextInt(); // Número de participantes
        scanner.nextLine(); // Consumir a nova linha

        System.out.print("Informe o responsável pelo evento: ");
        String responsavelEvento = scanner.nextLine(); // Responsável pelo evento

        // Conversão de dados para tipos adequados
        LocalDate dia = LocalDate.parse(dataEvento); // Data do evento
        LocalTime horarioInicio = LocalTime.parse(horaInicioEvento); // Hora de início
        LocalTime horarioTermino = LocalTime.parse(horaFimEvento); // Hora de término

        // Inicialização de outras variáveis que não precisam ser fixas
        String nomeProjeto = "Projeto Exemplo"; // Ou pode ser uma entrada do usuário
        String acaoExecutada = "Execução do evento"; // Ou outra lógica que você desejar
        String assunto = detalhesEvento; // Usando detalhes como assunto

        // Criar o evento
        Evento evento = new Evento(nomeProjeto, acaoExecutada, nomeEvento, localEvento, dia, horarioInicio, horarioTermino, assunto);
        eventos.add(evento);
        System.out.println("Evento cadastrado com sucesso!");
    }

    private void cadastrarMonitoramento(Scanner scanner) {
        System.out.print("Escolha a empresa incubada (1 - Polygon, 2 - ICRO Digital, 3 - SMARTComerci, 4 - iLegis): ");
        int escolhaEmpresa = scanner.nextInt();
        scanner.nextLine(); 
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
                return; // Retorna ao início do método
        }

        System.out.print("Informe a data e hora do monitoramento (yyyy-mm-dd hh:mm): ");
        String dataHoraInput = scanner.nextLine();
        LocalDateTime dataHora = LocalDateTime.parse(dataHoraInput.replace(" ", "T"));

        System.out.print("Informe os documentos pendentes (separados por vírgula): ");
        String documentosInput = scanner.nextLine();
        List<String> documentosPendentes = List.of(documentosInput.split(","));

        System.out.print("Informe o caminho do arquivo Excel: ");
        String arquivoExcel = scanner.nextLine();

        Monitoramento monitoramento = new Monitoramento(empresaSelecionada, dataHora, documentosPendentes, arquivoExcel);
        monitoramentos.add(monitoramento);
        System.out.println("Monitoramento cadastrado com sucesso!");
    }

    private void cadastrarGestaoEstrategica(Scanner scanner) {
        System.out.print("Informe o objetivo da gestão estratégica: ");
        String objetivoGestao = scanner.nextLine();
        GestaoEstrategica gestaoEstrategica = new GestaoEstrategica(objetivoGestao);
        gestoesEstrategicas.add(gestaoEstrategica);
        System.out.println("Gestão Estratégica cadastrada com sucesso!");
    }

    private void cadastrarAmbienteIdeacao(Scanner scanner) {
        System.out.print("Informe o nome do ambiente de ideação: ");
        String nomeAmbiente = scanner.nextLine();
        AmbienteIdeacao ambienteIdeacao = new AmbienteIdeacao(nomeAmbiente);
        ambientesIdeacao.add(ambienteIdeacao);
        System.out.println("Ambiente de Ideação cadastrado com sucesso!");
    }

    private void cadastrarGestaoAmbiental(Scanner scanner) {
        System.out.print("Informe a ação da gestão ambiental: ");
        String acaoGestao = scanner.nextLine();
        System.out.print("Informe o impacto da gestão ambiental: ");
        String impactoGestao = scanner.nextLine();
        GestaoAmbiental gestaoAmbiental = new GestaoAmbiental(acaoGestao, impactoGestao);
        gestoesAmbientais.add(gestaoAmbiental);
        System.out.println("Gestão Ambiental cadastrada com sucesso!");
    }

    private void cadastrarResponsabilidadeSocial(Scanner scanner) {
        System.out.print("Informe o projeto social: ");
        String projetoSocial = scanner.nextLine();
        System.out.print("Informe o impacto do projeto social: ");
        String impactoSocial = scanner.nextLine();
        ResponsabilidadeSocial responsabilidadeSocial = new ResponsabilidadeSocial(projetoSocial, impactoSocial);
        responsabilidadesSociais.add(responsabilidadeSocial);
        System.out.println("Responsabilidade Social cadastrada com sucesso!");
    }

    private void cadastrarInternacionalizacao(Scanner scanner) {
        System.out.print("Informe o país: ");
        String pais = scanner.nextLine();
        System.out.print("Informe o projeto internacional: ");
        String projetoInternacional = scanner.nextLine();
        Internacionalizacao internacionalizacao = new Internacionalizacao(pais, projetoInternacional);
        internacionalizacoes.add(internacionalizacao);
        System.out.println("Internacionalização cadastrada com sucesso!");
    }

    private void cadastrarReserva(Scanner scanner) {
        System.out.print("Escolha o tipo de reserva (1 - Sala de Reuniões, 2 - Coworking): ");
        int tipoReservaEscolha = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Informe o nome do solicitante: ");
        String solicitante = scanner.nextLine();

        System.out.print("Informe a data e hora de início (yyyy-MM-dd HH:mm): ");
        String inicioInput = scanner.nextLine();
        LocalDateTime dataHoraInicio = LocalDateTime.parse(inicioInput.replace(" ", "T"));

        System.out.print("Informe a data e hora de fim (yyyy-MM-dd HH:mm): ");
        String fimInput = scanner.nextLine();
        LocalDateTime dataHoraFim = LocalDateTime.parse(fimInput.replace(" ", "T"));

        switch (tipoReservaEscolha) {
            case 1:
                cadastrarReservaSalaReunioes(scanner, solicitante, dataHoraInicio, dataHoraFim);
                break;
            case 2:
                cadastrarReservaCoworking(scanner, solicitante, dataHoraInicio, dataHoraFim);
                break;
            default:
                System.out.println("Tipo de reserva inválido.");
        }
    }

    private void cadastrarReservaSalaReunioes(Scanner scanner, String solicitante, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim) {
        System.out.print("Informe o número de pessoas: ");
        int numeroPessoas = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Necessita transmissão? (true/false): ");
        boolean necessitaTransmissao = scanner.nextBoolean();
        scanner.nextLine();

        reservas.add(new ReservaSalaReunioes(solicitante, dataHoraInicio, dataHoraFim, "Reserva de sala de reuniões", numeroPessoas, necessitaTransmissao));
        System.out.println("Reserva de sala de reuniões cadastrada com sucesso!");
    }

    private void cadastrarReservaCoworking(Scanner scanner, String solicitante, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim) {
        System.out.print("Informe o número de mesas: ");
        int numeroMesas = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Necessita projetor? (true/false): ");
        boolean necessitaProjetor = scanner.nextBoolean();
        scanner.nextLine();

        // Passa a descrição correta para o construtor
        String descricaoReserva = "Reserva de coworking para " + numeroMesas + " mesas";

        reservas.add(new ReservaCoworking(solicitante, dataHoraInicio, dataHoraFim, descricaoReserva, necessitaProjetor));
        System.out.println("Reserva de coworking cadastrada com sucesso!");
    }
}
