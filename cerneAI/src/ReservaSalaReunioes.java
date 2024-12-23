package src;

import java.time.LocalDateTime;

public class ReservaSalaReunioes extends ReservaSala {
    private int numeroPessoas;
    private boolean necessitaTransmissao;

    // Lista estática para armazenar reservas de salas de reuniões
    private static List<ReservaSalaReunioes> reservasSalaReunioes = new ArrayList<>();

    public ReservaSalaReunioes(String solicitante, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim, String descricaoReserva, int numeroPessoas, boolean necessitaTransmissao) {
        super(solicitante, dataHoraInicio, dataHoraFim, descricaoReserva);
        this.numeroPessoas = numeroPessoas;
        this.necessitaTransmissao = necessitaTransmissao;
    }

    public int getNumeroPessoas() {
        return numeroPessoas;
    }

    public boolean isNecessitaTransmissao() {
        return necessitaTransmissao;
    }

    // Método para obter todas as reservas de salas de reuniões
    public static List<ReservaSalaReunioes> getReservasSalaReunioes() {
        return reservasSalaReunioes;
    }

    @Override
    public void exibirDetalhesReserva() {
        // Lógica para exibir detalhes da reserva de sala de reuniões
        System.out.println("Reserva de Sala de Reuniões: " + getDescricaoReserva() + " - Número de Pessoas: " + numeroPessoas + " - Necessita Transmissão: " + necessitaTransmissao);
    }
}
