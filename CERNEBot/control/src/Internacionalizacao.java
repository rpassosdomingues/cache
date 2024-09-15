package control;

public class Internacionalizacao {
    private String pais;
    private String projetoInternacional;

    public Internacionalizacao(String pais, String projetoInternacional) {
        this.pais = pais;
        this.projetoInternacional = projetoInternacional;
    }

    // Método para obter o país
    public String getPais() {
        return pais;
    }

    // Método para obter o projeto internacional
    public String getProjetoInternacional() {
        return projetoInternacional;
    }

    public void planejarColaboracao(String data) {
        System.out.println("Colaboração internacional com o país '" + pais + "' planejada para " + data);
    }
}
