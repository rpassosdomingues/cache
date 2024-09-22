package src;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Path;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.CubicCurveTo;

public class Stage2 extends Scenario {
    private final int waveCount = 5; // Número de ondas no cenário
    private double wavelength = 30; // Largura da onda
    private double amplitude = 20; // Amplitude da onda

    @Override
    public void setup() {
        createGrass();
        createWavePath();
        addEnemies();
        addPortals();
    }

    private void createGrass() {
        Rectangle grass = new Rectangle(0, 0, root.getWidth(), root.getHeight());
        grass.setFill(Color.RED);
        root.getChildren().add(grass);
    }

    private void createWavePath() {
        Path wavePath = new Path();
        wavePath.setStroke(Color.LIGHTGRAY);
        wavePath.setStrokeWidth(30);
        wavePath.getStyleClass().add("wave-path");

        wavePath.getElements().add(new MoveTo(0, root.getHeight() * 0.8));

        for (int i = 1; i <= waveCount; i++) {
            double startX = (i - 1) * wavelength;
            double endX = i * wavelength;
            double midX = (startX + endX) / 2;
            double peakY = root.getHeight() * 0.8 - amplitude;
            double valleyY = root.getHeight() * 0.8 + amplitude;

            wavePath.getElements().add(new CubicCurveTo(midX, peakY, midX, valleyY, endX, root.getHeight() * 0.8));
        }

        root.getChildren().add(wavePath);
    }

    private void addEnemies() {
        for (int i = 0; i < 10; i++) {
            Enemy enemy = new Enemy("Inimigo" + (i + 1), Enemy.Difficulty.MEDIUM, Color.RED, 750, 100 + (i * 50));
            enemies.add(enemy);
            root.getChildren().add(enemy.getShape());
        }
    }

    private void addPortals() {
        Portal startPortal = new Portal(30, root.getHeight() * 0.8, 30, true);
        Portal endPortal = new Portal(root.getWidth() - 30, root.getHeight() * 0.8, 30, false);
        root.getChildren().addAll(startPortal, endPortal);
    }

    @Override
    protected void initializeEnemies(Enemy.Difficulty difficulty) {
        // Inicializa inimigos com base na dificuldade
        for (int i = 0; i < 8; i++) {
            Enemy enemy = new Enemy("Inimigo" + (i + 1), difficulty, Color.RED, 750, 100 + (i * 50));
            enemies.add(enemy);
            root.getChildren().add(enemy.getShape());
        }
    }
}
