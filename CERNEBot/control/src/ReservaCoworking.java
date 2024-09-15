package control;

import java.time.LocalDateTime; // Importando LocalDateTime
import java.time.LocalTime; // Importando LocalTime

public class ReservaCoworking extends Reserva {
    private int computadores; // Declarar atributo para número de computadores
    private boolean projetor; // Declarar atributo para projetor

    // Construtor
    public ReservaCoworking(String solicitante, LocalDateTime dataHoraInicio, LocalTime horaInicio, LocalTime horaFim, String status, String descricaoReserva, boolean necessitaProjetor, int computadores) {
        super(solicitante, dataHoraInicio, horaInicio, horaFim, status, descricaoReserva);
        this.projetor = necessitaProjetor;
        this.computadores = computadores;
    }

    @Override
    public String getTipoReserva() {
        return "Coworking";
    }

    // Getters e Setters
    public int getComputadores() {
        return computadores;
    }

    public void setComputadores(int computadores) {
        this.computadores = computadores;
    }

    public boolean isProjetor() {
        return projetor;
    }

    public void setProjetor(boolean projetor) {
        this.projetor = projetor;
    }

    // Método para exibir informações da reserva
    public void exibirInformacoes() {
        System.out.println("Dados da Reserva de Coworking:");
        System.out.println("Data da Reserva: " + getDataHoraInicio()); // Use o método da classe pai
        System.out.println("Hora de Início: " + getHoraInicio());
        System.out.println("Hora de Fim: " + getHoraFim());
        System.out.println("Solicitante: " + getSolicitante());
        System.out.println("Status: " + status);
        System.out.println("Número de Computadores Necessários: " + computadores);
        System.out.println("Necessita Projetor: " + projetor);
    }
}
