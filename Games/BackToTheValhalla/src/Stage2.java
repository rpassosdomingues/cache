package src;

import javafx.scene.paint.Color;

public class Stage2 extends Scenario {
    
    private int waveCount;
    private double waveLength;

    public Stage2(Color backgroundColor, Color pathColor, int waveCount, double waveLength) {
        super(backgroundColor, pathColor, waveCount, waveLength); // Passa todos os parâmetros ao construtor da classe pai
        this.waveCount = waveCount; // Inicializa o número de ondas
        this.waveLength = waveLength; // Inicializa o comprimento da onda
        setup(); // Chama o método de configuração
    }

    @Override
    public void setup() {
        // Chama o método para criar as ondas
        createWavePath(waveCount, waveLength); // Cria o caminho da onda
        initializeHero(); // Inicializa o herói
        initializeEnemies(Enemy.Difficulty.MEDIUM); // Inicializa os inimigos com uma dificuldade específica
    }
}
