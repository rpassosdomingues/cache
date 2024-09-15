public class ReservaSalaReunioes extends Reserva {
    private int numeroPessoas;
    private boolean necessitaProjetor;

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
}
