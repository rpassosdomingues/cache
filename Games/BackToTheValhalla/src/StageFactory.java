package src;

import javafx.scene.paint.Color;

public class StageFactory {
    public static Scenario createStage(String stageName) {
        switch (stageName) {
            case "Stage 1":
                return new Stage1(); // Carrega Stage 1
            case "Stage 2":
                return new Stage2(); // Carrega Stage 2
            case "Stage 3":
                return new Stage3(); // Carrega Stage 3
            default:
                return new Stage1(); // Fallback para Stage 1
        }
    }
}
