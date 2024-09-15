package control;

import java.time.LocalDateTime; // Importando LocalDateTime
import java.time.LocalTime; // Importando LocalTime

public abstract class Reserva {
    protected String solicitante;
    protected LocalDateTime dataHoraInicio;
    protected LocalTime horaInicio;
    protected LocalTime horaFim;
    protected String status;
    protected String descricaoReserva;

    public abstract String getTipoReserva();

    // Método para obter a descrição da reserva
    public String getDescricao() {
        return descricaoReserva;
    }

    // Getters e Setters
    public String getSolicitante() {
        return solicitante;
    }

    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }

    public LocalDateTime getDataHoraFim() {
        return dataHoraInicio.with(horaFim);
    }
}
