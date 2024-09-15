package control;

import java.time.LocalDateTime; // Importação necessária
import java.time.LocalTime;

public class ReservaSalaReunioes extends Reserva {
    @Override
    public String getTipoReserva() {
        return "Sala de Reuniões";
    }
    private int numeroPessoas;
    private boolean necessitaProjetor;

    // Construtor
    public ReservaSalaReunioes(String solicitante, LocalDateTime dataReserva, LocalTime horaInicio,
                               LocalTime horaFim, String status, String descricao, int numeroPessoas, boolean necessitaProjetor) {
        super(solicitante, dataReserva, horaInicio, horaFim, status, descricao); // Chama o construtor da superclasse
        this.numeroPessoas = numeroPessoas;
        this.necessitaProjetor = necessitaProjetor;
    }

    // Getters e Setters
    public int getNumeroPessoas() {
        return numeroPessoas;
    }

    public void setNumeroPessoas(int numeroPessoas) {
        this.numeroPessoas = numeroPessoas;
    }

    public boolean isNecessitaProjetor() {
        return necessitaProjetor;
    }

    public void setNecessitaProjetor(boolean necessitaProjetor) {
        this.necessitaProjetor = necessitaProjetor;
    }

    // Método para exibir informações da reserva
    public void exibirInformacoes() {
        System.out.println("Dados da Reserva de Sala de Reuniões:");
        System.out.println("Data da Reserva: " + getDataReserva());
        System.out.println("Hora de Início: " + getHoraInicio());
        System.out.println("Hora de Fim: " + getHoraFim());
        System.out.println("Solicitante: " + getSolicitante());
        System.out.println("Status: " + getStatus());
        System.out.println("Descrição: " + getDescricao()); // Adicionando a descrição
        System.out.println("Número de Pessoas: " + numeroPessoas);
        System.out.println("Necessita Projetor: " + necessitaProjetor);
    }
}
