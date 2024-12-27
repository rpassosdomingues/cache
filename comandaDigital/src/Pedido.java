package src;

class Pedido {
    private final Bebida bebida;
    private final Espeto espeto;

    public Pedido(Bebida bebida, Espeto espeto) {
        this.bebida = bebida;
        this.espeto = espeto;
    }

    public Bebida getBebida() {
        return bebida;
    }

    public Espeto getEspeto() {
        return espeto;
    }

    public double calcularTotal() {
        return bebida.getPreco() + espeto.getPreco();
    }

    @Override
    public String toString() {
        return "Pedido: " + bebida.toString() + " + " + espeto.toString() + " - Total: R$ " + calcularTotal();
    }
}
