package control;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI {
    private JFrame frame;

    public static void main(String[] args) {
        // Inicializa a interface gráfica
        EventQueue.invokeLater(() -> {
            try {
                MainGUI window = new MainGUI();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // Construtor da janela principal
    public MainGUI() {
        initialize();
    }

    // Inicializa os componentes da interface gráfica
    private void initialize() {
        // Cria a janela principal
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(0, 1)); // Layout de 1 coluna

        // Cria botões para o menu principal
        addMenuButton("Cadastrar Projeto", () -> openCadastroProjeto());
        addMenuButton("Cadastrar Evento", () -> openCadastroEvento());
        addMenuButton("Cadastrar Monitoramento", () -> openCadastroMonitoramento());
        addMenuButton("Cadastrar Gestão Estratégica", () -> openCadastroGestaoEstrategica());
        addMenuButton("Cadastrar Ambiente de Ideação", () -> openCadastroAmbienteIdeacao());
        addMenuButton("Cadastrar Gestão Ambiental", () -> openCadastroGestaoAmbiental());
        addMenuButton("Cadastrar Responsabilidade Social", () -> openCadastroResponsabilidadeSocial());
        addMenuButton("Cadastrar Internacionalização", () -> openCadastroInternacionalizacao());
        addMenuButton("Cadastrar Reserva", () -> openCadastroReserva());
    }

    // Adiciona um botão ao menu principal
    private void addMenuButton(String label, Runnable action) {
        JButton button = new JButton(label);
        button.addActionListener(e -> action.run());
        frame.getContentPane().add(button);
    }

    // Métodos para abrir janelas de cadastro específicas
    private void openCadastroProjeto() {
        JFrame projetoFrame = new JFrame("Cadastrar Projeto");
        projetoFrame.setBounds(100, 100, 400, 200);
        projetoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        projetoFrame.getContentPane().setLayout(new GridLayout(4, 2));

        // Campos para cadastro de projeto
        JLabel nomeLabel = new JLabel("Nome do Projeto:");
        JTextField nomeField = new JTextField();
        JLabel descricaoLabel = new JLabel("Descrição:");
        JTextField descricaoField = new JTextField();
        JLabel dataInicioLabel = new JLabel("Data de Início:");
        JTextField dataInicioField = new JTextField();
        JButton salvarButton = new JButton("Salvar");

        // Ação de salvar o projeto
        salvarButton.addActionListener(e -> {
            String nomeProjeto = nomeField.getText();
            String descricaoProjeto = descricaoField.getText();
            String dataInicioProjeto = dataInicioField.getText();
            JOptionPane.showMessageDialog(projetoFrame, "Projeto cadastrado: " + nomeProjeto);
            projetoFrame.dispose();
        });

        // Adiciona componentes à janela
        projetoFrame.getContentPane().add(nomeLabel);
        projetoFrame.getContentPane().add(nomeField);
        projetoFrame.getContentPane().add(descricaoLabel);
        projetoFrame.getContentPane().add(descricaoField);
        projetoFrame.getContentPane().add(dataInicioLabel);
        projetoFrame.getContentPane().add(dataInicioField);
        projetoFrame.getContentPane().add(salvarButton);

        projetoFrame.setVisible(true);
    }

    private void openCadastroEvento() {
        JFrame eventoFrame = new JFrame("Cadastrar Evento");
        eventoFrame.setBounds(100, 100, 400, 300);
        eventoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        eventoFrame.getContentPane().setLayout(new GridLayout(6, 2));

        // Campos para cadastro de evento
        JLabel nomeLabel = new JLabel("Nome do Evento:");
        JTextField nomeField = new JTextField();
        JLabel detalhesLabel = new JLabel("Detalhes:");
        JTextField detalhesField = new JTextField();
        JLabel dataLabel = new JLabel("Data (yyyy-MM-dd):");
        JTextField dataField = new JTextField();
        JLabel horaInicioLabel = new JLabel("Hora Início (HH:mm):");
        JTextField horaInicioField = new JTextField();
        JLabel horaFimLabel = new JLabel("Hora Fim (HH:mm):");
        JTextField horaFimField = new JTextField();
        JButton salvarButton = new JButton("Salvar");

        // Ação de salvar o evento
        salvarButton.addActionListener(e -> {
            String nomeEvento = nomeField.getText();
            String detalhesEvento = detalhesField.getText();
            String dataEvento = dataField.getText();
            String horaInicioEvento = horaInicioField.getText();
            String horaFimEvento = horaFimField.getText();
            JOptionPane.showMessageDialog(eventoFrame, "Evento cadastrado: " + nomeEvento);
            eventoFrame.dispose();
        });

        // Adiciona componentes à janela
        eventoFrame.getContentPane().add(nomeLabel);
        eventoFrame.getContentPane().add(nomeField);
        eventoFrame.getContentPane().add(detalhesLabel);
        eventoFrame.getContentPane().add(detalhesField);
        eventoFrame.getContentPane().add(dataLabel);
        eventoFrame.getContentPane().add(dataField);
        eventoFrame.getContentPane().add(horaInicioLabel);
        eventoFrame.getContentPane().add(horaInicioField);
        eventoFrame.getContentPane().add(horaFimLabel);
        eventoFrame.getContentPane().add(horaFimField);
        eventoFrame.getContentPane().add(salvarButton);

        eventoFrame.setVisible(true);
    }

    // Implementar outros métodos de cadastro com base no mesmo padrão
    // Exemplo: openCadastroMonitoramento(), openCadastroGestaoEstrategica(), etc.

    private void openCadastroMonitoramento() {
        // Implementação semelhante ao openCadastroProjeto
    }

    private void openCadastroGestaoEstrategica() {
        // Implementação semelhante ao openCadastroProjeto
    }

    private void openCadastroAmbienteIdeacao() {
        // Implementação semelhante ao openCadastroProjeto
    }

    private void openCadastroGestaoAmbiental() {
        // Implementação semelhante ao openCadastroProjeto
    }

    private void openCadastroResponsabilidadeSocial() {
        // Implementação semelhante ao openCadastroProjeto
    }

    private void openCadastroInternacionalizacao() {
        // Implementação semelhante ao openCadastroProjeto
    }

    private void openCadastroReserva() {
        // Implementação semelhante ao openCadastroProjeto
    }
}
