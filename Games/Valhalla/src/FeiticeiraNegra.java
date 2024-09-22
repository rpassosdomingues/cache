package src;

import javafx.scene.paint.Color;

public class FeiticeiraNegra extends Hero {
    public FeiticeiraNegra() {
        super("Feiticeira Negra", 100, 25, Color.BLACK, 0.1, 0.5);
    }

    @Override
    public void useAbility() {
        // Implementação da habilidade especial da Feiticeira Negra
        System.out.println(name + " usa sua habilidade: Magia Negra!");
    }
}
