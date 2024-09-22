// Stage2.java
package src;

import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;

public class Stage2 extends Scenario {

    public Stage2(double amplitude, double wavelength, int waveCount) {
        super(amplitude, wavelength, waveCount);
    }

    @Override
    public void setup() {
        // Lava vermelha
        Rectangle lava = new Rectangle(0, 0, width, height);
        lava.setFill(Color.RED);
        root.getChildren().add(lava);

        // Caminho amarelo
        Path wavePath = new Path();
        wavePath.setStroke(Color.YELLOW);
        wavePath.setStrokeWidth(30);
        wavePath.getStyleClass().add("wave-path");

        // Iniciar o caminho
        wavePath.getElements().add(new MoveTo(0, height * 0.8));

        // Gerar curvas baseadas nas variáveis da onda
        for (int i = 1; i <= waveCount; i++) {
            double startX = (i - 1) * wavelength;
            double endX = i * wavelength;
            double midX = (startX + endX) / 2;
            double peakY = height * 0.8 - amplitude;
            double valleyY = height * 0.8 + amplitude;

            wavePath.getElements().add(new CubicCurveTo(midX, peakY, midX, valleyY, endX, height * 0.8));
        }

        // Adiciona o caminho ao cenário
        root.getChildren().add(wavePath);

        // Adicionar portais
        Portal startPortal = new Portal(30, height * 0.8, 30, true);
        Portal endPortal = new Portal(width - 30, height * 0.8, 30, false);
        root.getChildren().addAll(startPortal, endPortal);
    }
}
