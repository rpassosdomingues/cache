package src;

import javafx.scene.paint.Color;

public class StageFactory {
    public static Scenario createStage(int stageNumber, Enemy.Difficulty difficulty) {
        switch (stageNumber) {
            case 1:
                return new Stage(Color.BLUE, Color.GREEN, 5, 100, difficulty); // Passa a dificuldade
            case 2:
                return new Stage(Color.GREEN, Color.YELLOW, 7, 150, difficulty);
            case 3:
                return new Stage(Color.RED, Color.BLACK, 10, 200, difficulty);
            default:
                throw new IllegalArgumentException("Stage number not recognized.");
        }
    }
}
