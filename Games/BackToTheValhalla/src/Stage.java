package src;

import javafx.scene.paint.Color;

public class Stage extends Scenario {
    private Enemy.Difficulty difficulty; // Armazena o nível de dificuldade

    public Stage(Color backgroundColor, Color pathColor, int waveCount, double waveLength, Enemy.Difficulty difficulty) {
        super(backgroundColor, pathColor, waveCount, waveLength); // Passa todos os parâmetros ao construtor da classe pai
        this.difficulty = difficulty; // Inicializa a dificuldade
        setup(); // Chama o método de configuração
    }

    @Override
    public void setup() {
        createWavePath(waveCount, waveLength); // Cria o caminho da onda
        initializeHero(); // Inicializa o herói
        initializeEnemies(difficulty); // Inicializa os inimigos com a dificuldade especificada
        // addPortals(); // Adiciona portais ao cenário, se necessário
    }
}
