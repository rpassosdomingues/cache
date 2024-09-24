package src;

import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.CubicCurveTo;

public class Stage1 extends Scenario {
    private final int waveCount = 3;
    private final double wavelength = 100; // Example value
    private final double amplitude = 20;    // Example value

    @Override
    public void setup() {
        createGrass();
        createWavePath();
        addPortals();
    }

    private void createGrass() {
        Rectangle grass = new Rectangle(0, 0, root.getWidth(), root.getHeight());
        grass.setFill(Color.GREEN);
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

    private void addPortals() {
        Portal startPortal = new Portal(30, root.getHeight() * 0.8, 30, true);
        Portal endPortal = new Portal(root.getWidth() - 30, root.getHeight() * 0.8, 30, false);
        root.getChildren().addAll(startPortal, endPortal);
    }
}
