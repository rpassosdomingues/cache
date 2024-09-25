package src;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import java.util.ArrayList;
import java.util.List;

public abstract class Scenario {
    protected Pane root;
    protected List<Enemy> enemies;
    protected Hero hero;
    protected int heroHealth;
    private Color backgroundColor; // Cor de fundo
    private Color waveColor; // Cor da onda

    // Definição das constantes para o cenário
    private static final int STAGE_WAVE_COUNT = 5; // Número de ondas
    private static final double STAGE_WAVELENGTH = 10; // Comprimento da onda

    public Scenario(Color backgroundColor, Color waveColor) {
        this.root = new Pane();
        this.enemies = new ArrayList<>();
        this.heroHealth = 100; // Saúde inicial do herói
        this.backgroundColor = backgroundColor;
        this.waveColor = waveColor;

        // Define a cor de fundo do cenário
        root.setStyle("-fx-background-color: " + toHexString(backgroundColor) + ";");
    }

    public Pane getRoot() {
        return root;
    }

    public List<Enemy> getEnemies() {
        return new ArrayList<>(enemies); // Retorna uma cópia da lista de inimigos
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
        initializeHero();
    }

    public void createStage(Enemy.Difficulty difficulty) {
        createWavePath(STAGE_WAVE_COUNT, STAGE_WAVELENGTH); // Chama o método com argumentos corretos
        initializeHero();
        initializeEnemies(difficulty); // Passa a dificuldade recebida
        gameLoop();
    }

    private void createPlace(Color placeColor) {
        // Cria um retângulo para representar o local
        Rectangle place = new Rectangle(0, 0, root.getWidth(), root.getHeight());
        place.setFill(placeColor); // Usando a cor recebida
        root.getChildren().add(place); // Adiciona o lugar ao painel
    }

    public void createWavePath(int waveCount, double wavelength) {
        Path wavePath = new Path();
        wavePath.setStroke(waveColor); // Usando a cor da onda definida na classe Scenario
        wavePath.setStrokeWidth(30);
        wavePath.getStyleClass().add("wave-path");

        double yPosition = root.getHeight() * 0.8; // Calcula a posição Y

        // Define o ponto inicial
        double startX = 0;
        wavePath.getElements().add(new MoveTo(startX, yPosition));

        for (int i = 1; i <= waveCount; i++) {
            double midX = startX + (i * (wavelength / 2)); // O ponto médio entre cada onda
            double peakY = yPosition - 20; // O pico da onda (valor fixo)
            double valleyY = yPosition + 20; // O vale da onda (valor fixo)

            // Adiciona uma curva cúbica representando a onda
            wavePath.getElements().add(new CubicCurveTo(midX, peakY, midX, valleyY, startX + (i * wavelength), yPosition));
        }

        root.getChildren().add(wavePath);

        // Adiciona os portais no início e no fim
        addPortals();
    }

    // Inicialização do herói (pode ser herdado por todos os estágios)
    protected void initializeHero() {
        if (hero == null) {
            hero = HeroFactory.createHero("Thor");  // Use Thor instead of Hero
        }
        root.getChildren().add(hero.getNode());
        hero.setPosition(0, 200);  // Define a posição inicial do herói
    }    

    // Método genérico para inicializar os inimigos
    protected void initializeEnemies(Enemy.Difficulty difficulty) {
        for (int i = 0; i < 5; i++) {  // Exemplo: inicializar 5 inimigos
            Enemy enemy = new Enemy("Inimigo" + (i + 1), difficulty, Color.RED, 750, 100 + (i * 50));
            enemies.add(enemy);
            root.getChildren().add(enemy.getShape());
        }
    }

    private void addPortals() {
        // Posição inicial constante
        double startX = 0;
        
        // Posição final constante
        double endX = 500;

        // Definindo a posição Y dos portais
        double yPosition = root.getHeight() * 0.8;

        // Adiciona o portal de início com gradiente de vermelho para azul
        Portal startPortal = new Portal(startX, yPosition, 15, Color.RED, Color.BLUE); // 15 é o raio do portal
        root.getChildren().add(startPortal);

        // Adiciona o portal de fim com gradiente de azul para vermelho
        Portal endPortal = new Portal(endX, yPosition, 15, Color.BLUE, Color.RED); // 15 é o raio do portal
        root.getChildren().add(endPortal);
    }

    // Loop principal do jogo
    private void gameLoop() {
        while (heroHealth > 0) {
            moveEnemies();
            checkCollisions();
        }
    }

    // Método auxiliar para converter cor em string hexadecimal
    private String toHexString(Color color) {
        return String.format("#%02X%02X%02X",
        (int) (color.getRed() * 255),
        (int) (color.getGreen() * 255),
        (int) (color.getBlue() * 255));
    }

    private void moveEnemies() {
        // Move cada inimigo em direção ao herói
        for (Enemy enemy : enemies) {
            enemy.moveTowards(hero.getX(), hero.getY());
        }
    }

    private void checkCollisions() {
        for (Enemy enemy : enemies) {
            if (isColliding(hero, enemy)) {
                resolveCollision(hero, enemy);
            }
        }
    }

    // Verifica se o herói colidiu com um inimigo
    private boolean isColliding(Hero hero, Enemy enemy) {
        double deltaX = Math.abs(hero.getX() - enemy.getX());
        double deltaY = Math.abs(hero.getY() - enemy.getY());
        return deltaX < 20 && deltaY < 20; // Tolerância para a colisão
    }

    // Resolve a colisão entre o herói e um inimigo
    private void resolveCollision(Hero hero, Enemy enemy) {
        if (enemy.getMomentum() > hero.getMomentum()) {
            heroHealth -= 5; // Diminui a saúde do herói
            if (heroHealth <= 0) {
                gameOver();
            }
        } else {
            enemy.takeDamage(25); // Diminui a saúde do inimigo
        }
    }

    // Método para calcular a distância entre dois pontos
    private double calculateDistance(double x1, double y1, double x2, double y2) {
        double deltaX = x2 - x1;
        double deltaY = y2 - y1;
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    // Método para calcular a direção oposta a um inimigo
    private double[] calculateDirection(double[] pointA, double[] pointB) {
        double deltaX = pointA[0] - pointB[0];
        double deltaY = pointA[1] - pointB[1];
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        // Evita divisão por zero
        if (distance > 0) {
            return new double[]{deltaX / distance, deltaY / distance}; // Normaliza o vetor
        }
        return null; // Retorna nulo se a distância for zero
    }

    private void gameOver() {
        root.getChildren().clear();
        System.out.println("Game Over");
    }

    public abstract void setup(); // Cada cenário terá sua configuração específica.
}
