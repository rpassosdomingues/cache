public class Projeto {
    private String nomeProjeto;
    private String descricao;
    private String responsavel;

    public Projeto(String nomeProjeto, String descricao, String responsavel) {
        this.nomeProjeto = nomeProjeto;
        this.descricao = descricao;
        this.responsavel = responsavel;
    }

    public String getNomeProjeto() {
        return nomeProjeto;
    }

    public void agendarReuniao(String data, String horario) {
        System.out.println("Reunião agendada para o projeto '" + nomeProjeto + "' na data " + data + " às " + horario);
    }

    public void reservarSala(String data, String horarioInicio, String horarioTermino) {
        System.out.println("Sala reservada para o projeto '" + nomeProjeto + "' na data " + data + ", das " + horarioInicio + " às " + horarioTermino);
    }
}
