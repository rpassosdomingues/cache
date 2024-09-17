package control;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class MainGUI extends JFrame {
    private JPanel mainPanel;
    private ArrayList<SolicitacaoPecas> pecas; // Inicializando a lista de solicitações

    public MainGUI() {
        pecas = new ArrayList<>(); // Inicializando a lista
        setTitle("Sistema de Gestão");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(6, 1));

        JButton btnProjeto = new JButton("Cadastrar Projeto");
        btnProjeto.addActionListener(e -> abrirCadastroProjeto());

        JButton btnEvento = new JButton("Agendar Evento");
        btnEvento.addActionListener(e -> abrirAgendarEvento());

        JButton btnMonitoramento = new JButton("Registrar Rodada de Monitoramento");
        btnMonitoramento.addActionListener(e -> abrirMonitoramento());

        JButton btnReserva = new JButton("Reservar Sala");
        btnReserva.addActionListener(e -> abrirReservaSala());

        JButton btnPeca = new JButton("Solicitar Fabricação de Peça");
        btnPeca.addActionListener(e -> abrirSolicitacaoPeca());

        JButton btnSair = new JButton("Sair");
        btnSair.addActionListener(e -> System.exit(0));

        mainPanel.add(btnProjeto);
        mainPanel.add(btnEvento);
        mainPanel.add(btnMonitoramento);
        mainPanel.add(btnReserva);
        mainPanel.add(btnPeca);
        mainPanel.add(btnSair);

        add(mainPanel);
        setLocationRelativeTo(null); // Centraliza a janela
    }

    // Janela de Cadastro de Projeto
    private void abrirCadastroProjeto() {
        JFrame frame = new JFrame("Cadastrar Projeto");
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(4, 2));

        JLabel lblNome = new JLabel("Nome do Projeto:");
        JTextField txtNome = new JTextField();

        JLabel lblDescricao = new JLabel("Descrição do Projeto:");
        JTextField txtDescricao = new JTextField();

        JLabel lblDataInicio = new JLabel("Data de Início (yyyy-MM-dd):");
        JTextField txtDataInicio = new JTextField();

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> {
            // Lógica de salvar o projeto
            String nome = txtNome.getText();
            String descricao = txtDescricao.getText();
            String dataInicio = txtDataInicio.getText();
            // Adicione o código para salvar o projeto
            JOptionPane.showMessageDialog(frame, "Projeto cadastrado com sucesso!");
            frame.dispose();
        });

        frame.add(lblNome);
        frame.add(txtNome);
        frame.add(lblDescricao);
        frame.add(txtDescricao);
        frame.add(lblDataInicio);
        frame.add(txtDataInicio);
        frame.add(new JLabel());
        frame.add(btnSalvar);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Janela de Agendamento de Evento
    private void abrirAgendarEvento() {
        JFrame frame = new JFrame("Agendar Evento");
        frame.setSize(400, 400);
        frame.setLayout(new GridLayout(7, 2));

        JLabel lblNome = new JLabel("Nome do Evento:");
        JTextField txtNome = new JTextField();

        JLabel lblDetalhes = new JLabel("Detalhes do Evento:");
        JTextField txtDetalhes = new JTextField();

        JLabel lblData = new JLabel("Data do Evento:");
        JSpinner dataEvento = new JSpinner(new SpinnerDateModel());

        JLabel lblHoraInicio = new JLabel("Hora de Início:");
        JSpinner horaInicio = new JSpinner(new SpinnerDateModel());

        JLabel lblHoraFim = new JLabel("Hora de Fim:");
        JSpinner horaFim = new JSpinner(new SpinnerDateModel());

        JLabel lblLocal = new JLabel("Local do Evento:");
        JTextField txtLocal = new JTextField();

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> {
            // Lógica de salvar o evento
            JOptionPane.showMessageDialog(frame, "Evento cadastrado com sucesso!");
            frame.dispose();
        });

        frame.add(lblNome);
        frame.add(txtNome);
        frame.add(lblDetalhes);
        frame.add(txtDetalhes);
        frame.add(lblData);
        frame.add(dataEvento);
        frame.add(lblHoraInicio);
        frame.add(horaInicio);
        frame.add(lblHoraFim);
        frame.add(horaFim);
        frame.add(lblLocal);
        frame.add(txtLocal);
        frame.add(new JLabel());
        frame.add(btnSalvar);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Janela de Registro de Monitoramento
    private void abrirMonitoramento() {
        JFrame frame = new JFrame("Registrar Rodada de Monitoramento");
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(5, 1));

        JLabel lblEmpresa = new JLabel("Escolha a empresa:");
        JButton btnPolygon = new JButton("Polygon");
        JButton btnICRO = new JButton("ICRO Digital");
        JButton btnSMART = new JButton("SMARTComerci");
        JButton btnILegis = new JButton("iLegis");

        frame.add(lblEmpresa);
        frame.add(btnPolygon);
        frame.add(btnICRO);
        frame.add(btnSMART);
        frame.add(btnILegis);

        btnPolygon.addActionListener(e -> abrirDetalhesMonitoramento(frame, "Polygon"));
        btnICRO.addActionListener(e -> abrirDetalhesMonitoramento(frame, "ICRO Digital"));
        btnSMART.addActionListener(e -> abrirDetalhesMonitoramento(frame, "SMARTComerci"));
        btnILegis.addActionListener(e -> abrirDetalhesMonitoramento(frame, "iLegis"));

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Submenu de Detalhes de Monitoramento
    private void abrirDetalhesMonitoramento(JFrame parent, String empresa) {
        parent.dispose();
        JFrame frame = new JFrame("Detalhes do Monitoramento para " + empresa);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(4, 2));

        JLabel lblDataHora = new JLabel("Data e Hora:");
        JSpinner dataHora = new JSpinner(new SpinnerDateModel());

        JLabel lblDocumento = new JLabel("Upload de Documento:");
        JButton btnUpload = new JButton("Upload PDF");

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> {
            // Lógica de salvar o monitoramento
            JOptionPane.showMessageDialog(frame, "Monitoramento para " + empresa + " registrado com sucesso!");
            frame.dispose();
        });

        frame.add(lblDataHora);
        frame.add(dataHora);
        frame.add(lblDocumento);
        frame.add(btnUpload);
        frame.add(new JLabel());
        frame.add(btnSalvar);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Janela de Reserva de Sala
    private void abrirReservaSala() {
        JFrame frame = new JFrame("Reservar Sala");
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(3, 1));

        JButton btnReuniao = new JButton("Sala de Reuniões");
        btnReuniao.addActionListener(e -> abrirSalaReunioes());

        JButton btnCoworking = new JButton("Coworking");
        btnCoworking.addActionListener(e -> abrirCoworking());

        frame.add(btnReuniao);
        frame.add(btnCoworking);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void abrirSalaReunioes() {
        JFrame frame = new JFrame("Reserva de Sala de Reuniões");
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(5, 2));

        JLabel lblDataHoraInicio = new JLabel("Data e Hora de Início:");
        JSpinner dataHoraInicio = new JSpinner(new SpinnerDateModel());

        JLabel lblDataHoraFim = new JLabel("Data e Hora de Fim:");
        JSpinner dataHoraFim = new JSpinner(new SpinnerDateModel());

        JLabel lblTransmissao = new JLabel("Necessita Transmissão?");
        JCheckBox cbTransmissao = new JCheckBox();

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> {
            // Lógica de salvar a reserva
            JOptionPane.showMessageDialog(frame, "Reserva de Sala de Reuniões realizada com sucesso!");
            frame.dispose();
        });

        frame.add(lblDataHoraInicio);
        frame.add(dataHoraInicio);
        frame.add(lblDataHoraFim);
        frame.add(dataHoraFim);
        frame.add(lblTransmissao);
        frame.add(cbTransmissao);
        frame.add(new JLabel());
        frame.add(btnSalvar);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void abrirCoworking() {
        JFrame frame = new JFrame("Reserva de Coworking");
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(5, 2));

        JLabel lblDataHoraInicio = new JLabel("Data e Hora de Início:");
        JSpinner dataHoraInicio = new JSpinner(new SpinnerDateModel());

        JLabel lblDataHoraFim = new JLabel("Data e Hora de Fim:");
        JSpinner dataHoraFim = new JSpinner(new SpinnerDateModel());

        JLabel lblProjetor = new JLabel("Necessita Projetor?");
        JCheckBox cbProjetor = new JCheckBox();

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> {
            // Lógica de salvar a reserva
            JOptionPane.showMessageDialog(frame, "Reserva de Coworking realizada com sucesso!");
            frame.dispose();
        });

        frame.add(lblDataHoraInicio);
        frame.add(dataHoraInicio);
        frame.add(lblDataHoraFim);
        frame.add(dataHoraFim);
        frame.add(lblProjetor);
        frame.add(cbProjetor);
        frame.add(new JLabel());
        frame.add(btnSalvar);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Janela de Solicitação de Fabricação de Peças Maker
    private void abrirSolicitacaoPeca() {
        JFrame frame = new JFrame("Solicitação de Fabricação de Peça");
        frame.setSize(400, 400);
        frame.setLayout(new GridLayout(8, 2));

        JLabel lblSolicitante = new JLabel("Solicitante:");
        JTextField txtSolicitante = new JTextField();

        JLabel lblCampus = new JLabel("Campus:");
        JTextField txtCampus = new JTextField();

        JLabel lblAreaOrigem = new JLabel("Área de Origem:");
        JTextField txtAreaOrigem = new JTextField();

        JLabel lblMaterial = new JLabel("Material:");
        JTextField txtMaterial = new JTextField();

        JLabel lblEquipamento = new JLabel("Equipamento:");
        JTextField txtEquipamento = new JTextField();

        JLabel lblStatus = new JLabel("Status:");
        JTextField txtStatus = new JTextField();

        JLabel lblAndamento = new JLabel("Andamento:");
        JTextField txtAndamento = new JTextField();

        JButton btnSolicitar = new JButton("Solicitar");
        btnSolicitar.addActionListener(e -> {
            // Lógica para solicitar a peça
            String solicitante = txtSolicitante.getText();
            String campus = txtCampus.getText();
            String areaOrigem = txtAreaOrigem.getText();
            String material = txtMaterial.getText();
            String equipamento = txtEquipamento.getText();
            String status = txtStatus.getText();
            String andamento = txtAndamento.getText();
            LocalDateTime dataSolicitacao = LocalDateTime.now();  // Data atual
            String conclusao = "Em aberto";

            SolicitacaoPecas solicitacao = new SolicitacaoPecas(solicitante, campus, areaOrigem, material, equipamento, dataSolicitacao, status, andamento, conclusao);
            pecas.add(solicitacao);  // Adicionando a solicitação à lista

            JOptionPane.showMessageDialog(frame, "Solicitação de peça cadastrada com sucesso!");
            frame.dispose();
        });

        // Adicionando os componentes à janela
        frame.add(lblSolicitante);
        frame.add(txtSolicitante);
        frame.add(lblCampus);
        frame.add(txtCampus);
        frame.add(lblAreaOrigem);
        frame.add(txtAreaOrigem);
        frame.add(lblMaterial);
        frame.add(txtMaterial);
        frame.add(lblEquipamento);
        frame.add(txtEquipamento);
        frame.add(lblStatus);
        frame.add(txtStatus);
        frame.add(lblAndamento);
        frame.add(txtAndamento);
        frame.add(new JLabel()); // Espaço vazio
        frame.add(btnSolicitar);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainGUI gui = new MainGUI();
            gui.setVisible(true);
        });
    }
}
