package src;

import javafx.scene.paint.Color;

public class Odin extends Hero {
    public Odin() {
        super("Odin", 100, 30, Color.GRAY, 1.0, 1.0);
    }

    @Override
    public void useAbility() {
        // Implementação da habilidade especial de Odin
        System.out.println(name + " usa sua habilidade: Sabedoria Eterna!");
    }
}
