package src;

// Classe base para Bebida
abstract class Bebida {
    private final String nome;
    private final String tipo;
    private final double preco;

    public Bebida(String nome, String tipo, double preco) {
        this.nome = nome;
        this.tipo = tipo;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public double getPreco() {
        return preco;
    }

    public abstract String getDescricao();

    @Override
    public String toString() {
        return nome + " (" + tipo + ") - R$ " + preco;
    }
}

// Classe Dose, que pode armazenar e retornar uma bebida
class Dose {
    private final Bebida bebida;
    private final double quantidade;

    public Dose(Bebida bebida, double quantidade) {
        this.bebida = bebida;
        this.quantidade = quantidade;
    }

    public Bebida getBebida() {
        return bebida;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public double calcularPrecoTotal() {
        return bebida.getPreco() * quantidade;
    }

    @Override
    public String toString() {
        return quantidade + " doses de " + bebida.getNome() + " (" + bebida.getTipo() + ")";
    }
}

// Classe Litrinho, que herda de Bebida e implementa o método getDescricao
class Litrinho extends Bebida {

    public Litrinho(String nome, double preco) {
        super(nome, "Litrinho", preco);
    }

    @Override
    public String getDescricao() {
        return "Litrinho de " + getNome() + ": uma bebida refrescante!";
    }
}

// Classe Litrao, que herda de Bebida e implementa o método getDescricao
class Litrao extends Bebida {

    public Litrao(String nome, double preco) {
        super(nome, "Litrao", preco);
    }

    @Override
    public String getDescricao() {
        return "Litrao de " + getNome() + ": uma bebida para grandes momentos!";
    }
}
