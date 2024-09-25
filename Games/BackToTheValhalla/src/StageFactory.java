package src;

import javafx.scene.paint.Color;

public class StageFactory {
    public static Scenario createStage(String stageName) {
        switch (stageName) {
            case "Stage 1":
                return new Stage1(Color.GREEN, Color.BROWN, 1, 10); // Carrega Stage 1 com parâmetros
            case "Stage 2":
                return new Stage2(Color.RED, Color.LIGHTGRAY, 2, 5.0); // Carrega Stage 2 com parâmetros
            case "Stage 3":
                return new Stage3(Color.SKYBLUE, Color.WHITE, 2, 1.0); // Carrega Stage 3 com parâmetros
            default:
                return new Stage1(Color.GREEN, Color.BROWN, 1, 50.0); // Fallback para Stage 1
        }
    }
}
