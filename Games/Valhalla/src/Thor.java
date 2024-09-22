package src;

import javafx.scene.paint.Color;

public class Thor extends Hero {
    public Thor() {
        super("Thor", 100, 20, Color.YELLOW, 0.2, 0.1);
    }

    @Override
    public void useAbility() {
        // Implementação da habilidade especial de Thor
        System.out.println(name + " usa sua habilidade: Martelo de Thor!");
    }
}
