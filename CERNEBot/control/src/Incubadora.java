package control;

import java.util.ArrayList;
import java.util.List;

public class Incubadora {
    private List<Projeto> projetos;
    private List<SolicitacaoPecas> solicitacoes; // Adicionei uma lista para as solicitações
    private List<Reserva> reservas; // Adicionei uma lista para as reservas

    public Incubadora() {
        this.projetos = new ArrayList<>();
        this.solicitacoes = new ArrayList<>();
        this.reservas = new ArrayList<>();
    }

    // Métodos para cadastrar e consultar

    public void cadastrarProjeto(Projeto projeto) {
        projetos.add(projeto);
    }

    public Projeto consultarProjeto(String nome) {
        for (Projeto projeto : projetos) {
            if (projeto.getNomeProjeto().equalsIgnoreCase(nome)) {
                return projeto;
            }
        }
        return null;
    }

    public void cadastrarSolicitacao(SolicitacaoPecas solicitacao) {
        solicitacoes.add(solicitacao);
    }

    public List<SolicitacaoPecas> consultarSolicitacoes() {
        return solicitacoes;
    }

    public void cadastrarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    public List<Reserva> consultarReservas() {
        return reservas;
    }

    public void gerarRelatorio() {
        System.out.println("Relatório de todas as áreas:");

        // Relatório de projetos
        for (Projeto projeto : projetos) {
            System.out.println("Projeto: " + projeto.getNomeProjeto());
        }

        // Relatório de solicitações
        for (SolicitacaoPecas solicitacao : solicitacoes) {
            System.out.println("Solicitação: " + solicitacao.getDescricao());
        }

        // Relatório de reservas
        for (Reserva reserva : reservas) {
            System.out.println("Reserva: " + reserva.getDescricao());
        }
    }
}
