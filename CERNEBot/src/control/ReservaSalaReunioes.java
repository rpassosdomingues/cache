package control;

import java.time.LocalDateTime; // Importação necessária
import java.time.LocalTime;

public class ReservaSalaReunioes extends Reserva {

    private int numeroPessoas;
    private boolean necessitaTransmissao; // Alterado para necessitaTransmissao

    // Construtor
    public ReservaSalaReunioes(String solicitante, LocalDateTime dataReserva, LocalTime horaInicio,
                               LocalTime horaFim, String status, String descricao, int numeroPessoas, boolean necessitaTransmissao) {
        super(solicitante, dataReserva, horaInicio, horaFim, status, descricao); // Chama o construtor da superclasse
        this.numeroPessoas = numeroPessoas;
        this.necessitaTransmissao = necessitaTransmissao;
    }

    // Sobrescrevendo o método getTipoReserva para especificar o tipo
    @Override
    public String getTipoReserva() {
        return "Sala de Reuniões";
    }

    // Getters e Setters
    public int getNumeroPessoas() {
        return numeroPessoas;
    }

    public void setNumeroPessoas(int numeroPessoas) {
        this.numeroPessoas = numeroPessoas;
    }

    public boolean isNecessitaTransmissao() {
        return necessitaTransmissao;
    }

    public void setNecessitaTransmissao(boolean necessitaTransmissao) {
        this.necessitaTransmissao = necessitaTransmissao;
    }

    // Método para exibir informações da reserva
    public void exibirInformacoes() {
        System.out.println("Dados da Reserva de Sala de Reuniões:");
        System.out.println("Data da Reserva: " + getDataHoraInicio()); // Supondo que este método exista
        System.out.println("Hora de Início: " + getHoraInicio()); // Supondo que este método exista
        System.out.println("Hora de Fim: " + getHoraFim()); // Supondo que este método exista
        System.out.println("Solicitante: " + getSolicitante());
        System.out.println("Status: " + getStatus());
        System.out.println("Descrição: " + getDescricaoReserva()); // Supondo que este método exista
        System.out.println("Número de Pessoas: " + numeroPessoas);
        System.out.println("Necessita Transmissão: " + necessitaTransmissao);
    }
}
