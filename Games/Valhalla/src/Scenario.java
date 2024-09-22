// Scenario.java
package src;

import javafx.scene.layout.Pane;

public abstract class Scenario {
    protected Pane root;
    protected int width = 500;
    protected int height = 400;

    // Parâmetros para controlar a onda
    protected double amplitude;
    protected double wavelength;
    protected int waveCount;

    public Scenario(double amplitude, double wavelength, int waveCount) {
        root = new Pane();
        this.amplitude = amplitude;
        this.wavelength = wavelength;
        this.waveCount = waveCount;
    }

    public Pane getRoot() {
        return root;
    }

    public abstract void setup(); // Cada cenário terá sua configuração específica.
}
