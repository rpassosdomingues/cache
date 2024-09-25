package src;

import javafx.scene.paint.Color;

public class Stage2 extends Scenario {
    
    // Definindo os valores específicos para o estágio 1
    private static final Color BACKGROUND_COLOR = Color.RED;
    private static final Color STAGE_WAVE_COLOR = Color.LIGHTGRAY;
    private static final int STAGE_WAVE_COUNT = 1;
    private static final double STAGE_WAVELENGTH = 50.0;

    public Stage2() {
        super(BACKGROUND_COLOR, STAGE_WAVE_COLOR); // Chama o construtor da classe pai com a cor de fundo e a cor da onda
    }

    @Override
    public void setup() {
        createStage(Enemy.Difficulty.EASY); // Configuração do estágio usando dificuldade fácil
    }

    @Override
    public void createStage(Enemy.Difficulty difficulty) {
        // Chama o método createStage da classe pai, passando a dificuldade e os parâmetros específicos para o estágio 1
        createWavePath(STAGE_WAVE_COUNT, STAGE_WAVELENGTH);
        super.createStage(difficulty);
    }
}
