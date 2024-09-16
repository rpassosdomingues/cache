package control;

public class Projeto {
    private String nomeProjeto;
    private String descricao;
    private String dataInicio;

    // Construtor com nome, descrição e data de início
    public Projeto(String nomeProjeto, String descricao, String dataInicio) {
        this.nomeProjeto = nomeProjeto;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
    }

    public String getNomeProjeto() {
        return nomeProjeto;
    }

    public void cadastrarProjeto(Projeto projeto) {
        projetos.add(projeto);
        System.out.println("Projeto cadastrado com sucesso!");
    }

    public void agendarReuniao(String data, String horario) {
        System.out.println("Reunião agendada para o projeto '" + nomeProjeto + "' na data " + data + " às " + horario);
    }

    public void reservarSala(String data, String horarioInicio, String horarioTermino) {
        System.out.println("Sala reservada para o projeto '" + nomeProjeto + "' na data " + data + ", das " + horarioInicio + " às " + horarioTermino);
    }
}
