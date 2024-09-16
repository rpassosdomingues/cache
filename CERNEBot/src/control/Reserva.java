package control;

import java.time.LocalDateTime;

public abstract class Reserva {
    protected String solicitante;
    protected LocalDateTime dataHoraInicio;
    protected LocalDateTime dataHoraFim;
    protected String descricaoReserva;

    public Reserva(String solicitante, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim, String descricaoReserva) {
        this.solicitante = solicitante;
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
        this.descricaoReserva = descricaoReserva;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }

    public LocalDateTime getDataHoraFim() {
        return dataHoraFim;
    }

    public String getDescricaoReserva() {
        return descricaoReserva;
    }
}
