package src;

// Classe Espeto
class Espeto {
    private final String tipo;
    private final double preco;

    public Espeto(String tipo, double preco) {
        this.tipo = tipo;
        this.preco = preco;
    }

    public String getTipo() {
        return tipo;
    }

    public double getPreco() {
        return preco;
    }

    @Override
    public String toString() {
        return tipo + " - R$ " + preco;
    }
}
