package src;

import javafx.scene.paint.Color;

public class Loki extends Hero {
    public Loki() {
        super("Loki", 100, 15, Color.GREEN, 0.1, 0.2);
    }

    @Override
    public void useAbility() {
        // Implementação da habilidade especial de Loki
        System.out.println(name + " usa sua habilidade: Ilusão!");
    }
}
