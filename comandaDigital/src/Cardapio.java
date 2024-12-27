package src;

import java.util.Arrays;
import java.util.List;

import java.util.Arrays;
import java.util.List;

// Cardápio com categorias para Bebidas e Comidas
public enum Cardapio {

    // Bebidas
    CERVEJA("Cerveja", Categoria.BEBIDA, new Bebida("Cerveja", 7.00)),
    CAIPIRINHA("Caipirinha", Categoria.BEBIDA, new Bebida("Caipirinha", 15.00)),
    CAMPARI("Campari", Categoria.BEBIDA, new Bebida("Campari", 20.00)),
    CERVEJA_LITRAO("Cerveja Litrão", Categoria.BEBIDA, new Bebida("Cerveja Litrão", 25.00)),

    // Espetos (Comidas)
    ESPETO_CARNE_BOVINA("Espeto Carne Bovina", Categoria.COMIDA, new Espeto("Carne Bovina", 30.00)),
    ESPETO_PANCETA("Espeto Panceta", Categoria.COMIDA, new Espeto("Panceta", 35.00)),
    ESPETO_CORACAOZINHO("Espeto Coraçãozinho", Categoria.COMIDA, new Espeto("Coraçãozinho", 25.00)),
    ESPETO_FRANGO("Espeto Frango", Categoria.COMIDA, new Espeto("Frango", 28.00)),
    ESPETO_QUEIJO_NOZINHO("Espeto Queijo Nozinho", Categoria.COMIDA, new Espeto("Queijo Nozinho", 20.00)),
    ESPETO_QUEIJO_COALHO("Espeto Queijo Coalho", Categoria.COMIDA, new Espeto("Queijo Coalho", 18.00)),

    // Porções (Comidas)
    PORCAO_BATATA_FRITA("Porção Batata Frita", Categoria.COMIDA, new Porcao("Batata Frita", 12.00)),
    PORCAO_BATATA_ISADORA("Porção Batata Isadora", Categoria.COMIDA, new Porcao("Batata Isadora", 15.00)),
    PORCAO_CALABRESA_ACEBOLADA("Porção Calabresa Acebolada", Categoria.COMIDA, new Porcao("Calabresa Acebolada", 20.00)),
    PORCAO_FRIOS("Porção Frios", Categoria.COMIDA, new Porcao("Frios", 25.00));

    private final String nome;
    private final Categoria categoria;
    private final ItemCardapio item;

    Cardapio(String nome, Categoria categoria, ItemCardapio item) {
        this.nome = nome;
        this.categoria = categoria;
        this.item = item;
    }

    public String getNome() {
        return nome;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public ItemCardapio getItem() {
        return item;
    }

    // Método para retornar as bebidas
    public static List<Cardapio> getBebidas() {
        return Arrays.asList(CERVEJA, CAIPIRINHA, CAMPARI, CERVEJA_LITRAO);
    }

    // Método para retornar os espetos
    public static List<Cardapio> getEspetos() {
        return Arrays.asList(
            ESPETO_CARNE_BOVINA, 
            ESPETO_PANCETA, 
            ESPETO_CORACAOZINHO, 
            ESPETO_FRANGO, 
            ESPETO_QUEIJO_NOZINHO, 
            ESPETO_QUEIJO_COALHO
        );
    }

    // Método para retornar as porções
    public static List<Cardapio> getPorcoes() {
        return Arrays.asList(
            PORCAO_BATATA_FRITA,
            PORCAO_BATATA_ISADORA,
            PORCAO_CALABRESA_ACEBOLADA,
            PORCAO_FRIOS
        );
    }

    // Método para retornar todas as comidas (espeto + porção)
    public static List<Cardapio> getComidas() {
        return Arrays.asList(
            ESPETO_CARNE_BOVINA, 
            ESPETO_PANCETA, 
            ESPETO_CORACAOZINHO, 
            ESPETO_FRANGO, 
            ESPETO_QUEIJO_NOZINHO, 
            ESPETO_QUEIJO_COALHO,
            PORCAO_BATATA_FRITA,
            PORCAO_BATATA_ISADORA,
            PORCAO_CALABRESA_ACEBOLADA,
            PORCAO_FRIOS
        );
    }

    // Enum Categoria com as opções BEBIDA e COMIDA
    public enum Categoria {
        BEBIDA, COMIDA;
    }
    
    // Classe base ItemCardapio
    public interface ItemCardapio {
        String getNome();
        double getPreco();
    }

    // Classe para itens de bebida
    public static class Bebida implements ItemCardapio {
        private final String nome;
        private final double preco;

        public Bebida(String nome, double preco) {
            this.nome = nome;
            this.preco = preco;
        }

        @Override
        public String getNome() {
            return nome;
        }

        @Override
        public double getPreco() {
            return preco;
        }
    }

    // Classe para itens de espeto
    public static class Espeto implements ItemCardapio {
        private final String nome;
        private final double preco;

        public Espeto(String nome, double preco) {
            this.nome = nome;
            this.preco = preco;
        }

        @Override
        public String getNome() {
            return nome;
        }

        @Override
        public double getPreco() {
            return preco;
        }
    }

    // Classe para itens de porção
    public static class Porcao implements ItemCardapio {
        private final String nome;
        private final double preco;

        public Porcao(String nome, double preco) {
            this.nome = nome;
            this.preco = preco;
        }

        @Override
        public String getNome() {
            return nome;
        }

        @Override
        public double getPreco() {
            return preco;
        }
    }
}
