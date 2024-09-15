package control;

public class GestaoAmbiental {
    private String acao;     // Declare the acao variable
    private String impacto;  // Declare the impacto variable

    // Constructor
    public GestaoAmbiental(String acao, String impacto) {
        this.acao = acao;
        this.impacto = impacto;
    }

    // Method to obtain the action
    public String getAcao() {
        return acao;
    }

    // Method to obtain the impact
    public String getImpacto() {
        return impacto;
    }

    public void agendarAcao(String data) {
        System.out.println("Ação ambiental '" + acao + "' agendada para " + data);
    }
}
