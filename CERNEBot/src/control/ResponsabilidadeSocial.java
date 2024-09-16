package control;

public class ResponsabilidadeSocial {
    private String projetoSocial;
    private String impacto;

    // Construtor
    public ResponsabilidadeSocial(String projetoSocial, String impacto) {
        this.projetoSocial = projetoSocial;
        this.impacto = impacto;
    }

    // Método para obter o projeto social
    public String getProjetoSocial() {
        return projetoSocial;
    }

    // Método para obter o impacto
    public String getImpacto() {
        return impacto;
    }

    public void agendarAcaoSocial(String data) {
        System.out.println("Ação social '" + projetoSocial + "' agendada para " + data);
    }
}
