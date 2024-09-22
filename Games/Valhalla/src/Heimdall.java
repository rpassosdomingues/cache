package src;

import javafx.scene.paint.Color;

public class Heimdall extends Hero {
    public Heimdall() {
        super("Heimdall", 100, 50, Color.YELLOW, 1.0, 0.5);
    }

    @Override
    public void useAbility() {
        // Implementação da habilidade especial de Heimdall
        System.out.println(name + " usa sua habilidade: Visão do Futuro!");
    }
}
