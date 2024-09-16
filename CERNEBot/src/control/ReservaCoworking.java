package control;

import java.time.LocalDateTime;

public class ReservaCoworking extends Reserva {
    private boolean necessitaTransmissao;

    public ReservaCoworking(String solicitante, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim, String descricaoReserva, boolean necessitaTransmissao) {
        super(solicitante, dataHoraInicio, dataHoraFim, descricaoReserva);
        this.necessitaTransmissao = necessitaTransmissao;
    }

    public boolean isNecessitaTransmissao() {
        return necessitaTransmissao;
    }
}
