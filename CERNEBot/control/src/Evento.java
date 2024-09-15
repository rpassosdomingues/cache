package control;

public class Evento {
    private String nomeProjeto;
    private String acaoExecutada;
    private String nomeEvento;
    private String local;
    private String dia;
    private String horarioInicio;
    private String horarioTermino;
    private String assunto;

    public Evento(String nomeProjeto, String acaoExecutada, String nomeEvento, String local,
                  String dia, String horarioInicio, String horarioTermino, String assunto) {
        this.nomeProjeto = nomeProjeto;
        this.acaoExecutada = acaoExecutada;
        this.nomeEvento = nomeEvento;
        this.local = local;
        this.dia = dia;
        this.horarioInicio = horarioInicio;
        this.horarioTermino = horarioTermino;
        this.assunto = assunto;
    }

    // Getters e Setters
    public String getNomeEvento() {
        return nomeEvento;
    }

    // Adicione outros getters e setters conforme necessário
}
