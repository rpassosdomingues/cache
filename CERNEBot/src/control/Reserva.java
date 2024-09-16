package control;

import java.time.LocalDateTime; // Importando LocalDateTime
import java.time.LocalTime; // Importando LocalTime

public class Reserva {
    private String solicitante;
    private LocalDateTime dataHoraInicio;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    protected String status;
    private String descricaoReserva;

    // Construtor
    public Reserva(String solicitante, LocalDateTime dataHoraInicio, LocalTime horaInicio, LocalTime horaFim, String status, String descricaoReserva) {
        this.solicitante = solicitante;
        this.dataHoraInicio = dataHoraInicio;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.status = status;
        this.descricaoReserva = descricaoReserva;
    }

    // Getters
    public String getSolicitante() {
        return solicitante;
    }

    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public LocalTime getHoraFim() {
        return horaFim;
    }

    public String getDescricaoReserva() {
        return descricaoReserva;
    }

    public String getStatus() {
        return status;
    }

    // Método que pode ser sobrescrito pelas subclasses para especificar o tipo de reserva
    public String getTipoReserva() {
        return "Reserva";
    }
}
