package src;

import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.CubicCurveTo;

public class Stage3 extends Scenario {
    private final int waveCount = 6; // Number of waves in the stage
    private double wavelength = 25; // Wave width
    private double amplitude = 15; // Wave amplitude

    @Override
    public void setup() {
        createGrass();
        createWavePath();
        addEnemies();
        addPortals();
    }

    private void createGrass() {
        Rectangle grass = new Rectangle(0, 0, root.getWidth(), root.getHeight());
        grass.setFill(Color.SKYBLUE);
        root.getChildren().add(grass);
    }

    private void createWavePath() {
        Path wavePath = new Path();
        wavePath.setStroke(Color.WHITE);
        wavePath.setStrokeWidth(20);
        wavePath.getStyleClass().add("wave-path");

        wavePath.getElements().add(new MoveTo(0, root.getHeight() * 0.75));

        for (int i = 1; i <= waveCount; i++) {
            double startX = (i - 1) * wavelength;
            double endX = i * wavelength;
            double midX = (startX + endX) / 2;
            double peakY = root.getHeight() * 0.75 - amplitude;
            double valleyY = root.getHeight() * 0.75 + amplitude;

            wavePath.getElements().add(new CubicCurveTo(midX, peakY, midX, valleyY, endX, root.getHeight() * 0.75));
        }

        root.getChildren().add(wavePath);
    }

    private void addEnemies() {
        for (int i = 0; i < 12; i++) {
            Enemy enemy = new Enemy("Inimigo" + (i + 1), Enemy.Difficulty.HARD, Color.RED, 800, 100 + (i * 40));
            enemies.add(enemy);
            root.getChildren().add(enemy.getShape());
        }
    }

    private void addPortals() {
        Portal startPortal = new Portal(30, root.getHeight() * 0.75, 30, true);
        Portal endPortal = new Portal(root.getWidth() - 30, root.getHeight() * 0.75, 30, false);
        root.getChildren().addAll(startPortal, endPortal);
    }
}
