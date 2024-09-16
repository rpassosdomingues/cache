package control;

import java.time.LocalDateTime;

public class ReservaSalaReunioes extends Reserva {
    private int numeroPessoas;
    private boolean necessitaTransmissao;

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
}
