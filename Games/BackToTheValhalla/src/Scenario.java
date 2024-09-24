// Scenario.java
package src;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

public abstract class Scenario {
    protected Pane root;
    protected List<Enemy> enemies;
    protected Hero hero;
    protected int heroHealth;

    public Scenario() {
        this.root = new Pane();
        this.enemies = new ArrayList<>();
        this.heroHealth = 100; // Saúde inicial do herói
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

    public void startStage() {
        initializeHero();
        initializeEnemies(Enemy.Difficulty.MEDIUM);
        gameLoop();
    }

    // Inicialização do herói (pode ser herdado por todos os estágios)
    protected void initializeHero() {
        if (hero == null) {
            Hero thor = HeroFactory.createHero("Thor");  // Use Thor instead of Hero
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

    // Loop principal do jogo
    private void gameLoop() {
        while (heroHealth > 0) {
            moveEnemies();
            moveHeroAwayFromEnemies();
            checkCollisions();
        }
    }

    // Método para movimentar os inimigos na direção do herói
    private void moveEnemies() {
        enemies.forEach(enemy -> enemy.moveTowards(hero.getX(), hero.getY()));
    }

    // Método para movimentar o herói na direção oposta aos inimigos mais próximos
    private void moveHeroAwayFromEnemies() {
        Enemy closestEnemy = getClosestEnemy();
        if (closestEnemy != null) {
            double heroX = hero.getX();
            double heroY = hero.getY();

            // Calcula a direção oposta ao inimigo mais próximo
            double deltaX = heroX - closestEnemy.getX();
            double deltaY = heroY - closestEnemy.getY();

            double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

            if (distance > 0) {
                double moveSpeed = 5;  // Velocidade do herói
                heroX += (deltaX / distance) * moveSpeed;
                heroY += (deltaY / distance) * moveSpeed;

                hero.setPosition(heroX, heroY);
            }
        }
    }

    // Método para encontrar o inimigo mais próximo do herói
    private Enemy getClosestEnemy() {
        Enemy closestEnemy = null;
        double minDistance = Double.MAX_VALUE;

        for (Enemy enemy : enemies) {
            double distance = calculateDistance(hero.getX(), hero.getY(), enemy.getX(), enemy.getY());
            if (distance < minDistance) {
                minDistance = distance;
                closestEnemy = enemy;
            }
        }
        return closestEnemy;
    }

    // Calcula a distância entre duas coordenadas (herói e inimigo)
    private double calculateDistance(double x1, double y1, double x2, double y2) {
        double deltaX = x1 - x2;
        double deltaY = y1 - y2;
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    // Método para checar colisões entre herói e inimigos
    private void checkCollisions() {
        for (Enemy enemy : enemies) {
            if (isColliding(hero, enemy)) {
                resolveCollision(hero, enemy);
            }
        }
    }

    // Verifica se o herói colidiu com o inimigo
    private boolean isColliding(Hero hero, Enemy enemy) {
        double deltaX = Math.abs(hero.getX() - enemy.getX());
        double deltaY = Math.abs(hero.getY() - enemy.getY());
        return deltaX < 20 && deltaY < 20;
    }

    // Resolve a colisão entre o herói e o inimigo
    private void resolveCollision(Hero hero, Enemy enemy) {
        if (enemy.getMomentum() > hero.getMomentum()) {
            heroHealth -= 5;  // Reduz a saúde do herói em 5
            if (heroHealth <= 0) {
                gameOver();
            }
        } else {
            enemy.takeDamage(25);  // Inimigo sofre dano se o momento do herói for maior
        }
    }

    // Método chamado quando o jogo termina
    private void gameOver() {
        root.getChildren().clear();  // Limpa a tela
        System.out.println("Game Over");
    }

    // Cada cenário terá sua configuração específica
    public abstract void setup();
}
