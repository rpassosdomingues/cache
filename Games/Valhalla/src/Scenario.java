// Scenario.java
package src;

import javafx.scene.layout.Pane;
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

    private void initializeHero() {
        if (hero != null) {
            root.getChildren().add(hero.getNode());
            hero.setPosition(0, 200); // Posição inicial do herói
        }
    }

    public void startStage() {
        initializeEnemies(Enemy.Difficulty.MEDIUM);
        moveEnemies();
        checkCollisions();
    }

    protected abstract void initializeEnemies(Enemy.Difficulty difficulty);

    private void moveEnemies() {
        enemies.forEach(enemy -> enemy.moveTowards(hero.getX(), hero.getY()));
    }

    private void checkCollisions() {
        for (Enemy enemy : enemies) {
            if (isColliding(hero, enemy)) {
                resolveCollision(hero, enemy);
            }
        }
    }

    private boolean isColliding(Hero hero, Enemy enemy) {
        double deltaX = Math.abs(hero.getX() - enemy.getX());
        double deltaY = Math.abs(hero.getY() - enemy.getY());
        return deltaX < 20 && deltaY < 20;
    }

    private void resolveCollision(Hero hero, Enemy enemy) {
        if (enemy.getMomentum() > hero.getMomentum()) {
            heroHealth -= 5;
            if (heroHealth <= 0) {
                gameOver();
            }
        } else {
            enemy.takeDamage(25);
        }
    }

    private void gameOver() {
        root.getChildren().clear();
        System.out.println("Game Over");
    }

    public abstract void setup(); // Cada cenário terá sua configuração específica.
}
