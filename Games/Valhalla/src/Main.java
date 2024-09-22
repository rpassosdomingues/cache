package src;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    private Hero hero; // Referência ao herói selecionado
    private List<Enemy> enemies; // Lista de inimigos
    private Pane gameArea; // Área do jogo
    private double heroX = 50; // Posição inicial do herói
    private double heroY = 300; // Posição inicial do herói
    private double speedMultiplier = 2; // Fator de multiplicação da velocidade
    private VBox controlPanel; // Painel de controle

    @Override
    public void start(Stage primaryStage) {
        enemies = new ArrayList<>();

        // Cria a estrutura do layout
        BorderPane layout = new BorderPane();
        gameArea = new Pane();
        gameArea.setPrefSize(500, 400);
        gameArea.setStyle("-fx-background-color: lightblue;"); // Cor de fundo da área do jogo

        layout.setCenter(gameArea);
        layout.setRight(createControlPanel()); // Painel de controle à direita

        Scene scene = new Scene(layout, 800, 600);
        primaryStage.setTitle("Hero Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createControlPanel() {
        controlPanel = new VBox(10); // Espaçamento entre os botões
        controlPanel.setPrefWidth(200);
        controlPanel.setStyle("-fx-padding: 10; -fx-background-color: #eee;");

        // Botões de controle
        Button newGameButton = new Button("New Game");
        Button optionsButton = new Button("Options");
        Button exitButton = new Button("Exit");

        newGameButton.setOnAction(e -> showNewGameMenu());
        optionsButton.setOnAction(e -> showOptionsMenu());
        exitButton.setOnAction(e -> exitGame());

        controlPanel.getChildren().addAll(newGameButton, optionsButton, exitButton);
        return controlPanel;
    }

    private void showNewGameMenu() {
        // Limpa a área de jogo
        gameArea.getChildren().clear();
        enemies.clear();

        // ComboBox para seleção do estágio
        ComboBox<String> stageSelection = createStageSelection();
        stageSelection.setOnAction(e -> selectStage(stageSelection.getValue()));

        // ComboBox para seleção do herói
        ComboBox<String> heroSelection = createHeroSelection();
        heroSelection.setOnAction(e -> selectHero(heroSelection.getValue()));

        // Botão de início
        Button startButton = new Button("Start");
        startButton.setOnAction(e -> startGame());

        // Adiciona ao painel de controle
        controlPanel.getChildren().addAll(stageSelection, heroSelection, startButton);
    }

    private ComboBox<String> createStageSelection() {
        ComboBox<String> stageSelection = new ComboBox<>();
        stageSelection.getItems().addAll("Stage 1", "Stage 2", "Stage 3"); // Exemplo de estágios
        stageSelection.setValue("Stage 1"); // Valor padrão
        return stageSelection;
    }

    private ComboBox<String> createHeroSelection() {
        ComboBox<String> heroSelection = new ComboBox<>();
        heroSelection.getItems().addAll("Thor", "Loki", "Heimdall", "Feiticeira Negra", "Odin");
        heroSelection.setValue("Thor"); // Valor padrão
        return heroSelection;
    }

    private void selectHero(String heroName) {
        if (hero != null) {
            gameArea.getChildren().remove(hero.getShape());
        }

        hero = HeroFactory.createHero(heroName);
        if (hero != null) {
            positionHero();
            gameArea.getChildren().add(hero.getShape());
        }
    }

    private void selectStage(String stage) {
        // Aqui você pode implementar a lógica para carregar diferentes estágios
        System.out.println("Selected stage: " + stage);
        // Exibir estágios no jogo, se necessário
    }

    private void positionHero() {
        hero.getShape().setTranslateX(heroX);
        hero.getShape().setTranslateY(heroY);
    }

    public void startGame() {
        spawnEnemies(); // Cria inimigos

        // Animação dos inimigos e do herói
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateGame();
            }
        };
        gameLoop.start();
    }

    private void spawnEnemies() {
        enemies.clear(); // Limpa a lista de inimigos
        for (int i = 0; i < 5; i++) {
            Enemy enemy = new Enemy("Inimigo" + (i + 1), Enemy.Difficulty.MEDIUM, Color.RED, 750, 100 + (i * 50));
            enemies.add(enemy);
            gameArea.getChildren().add(enemy.getShape());
        }
    }

    private void updateGame() {
        // Atualiza a posição do herói
        if (hero != null) {
            heroX += speedMultiplier; // Move o herói
            hero.getShape().setTranslateX(heroX);
        }

        // Atualiza a posição dos inimigos
        for (Enemy enemy : enemies) {
            enemy.moveTowards(heroX, heroY); // Move os inimigos em direção ao herói
            checkCollisions(hero, enemy);
        }
    }

    private void checkCollisions(Hero hero, Enemy enemy) {
        if (isColliding(hero, enemy)) {
            resolveCollision(hero, enemy);
        }
    }

    private boolean isColliding(Hero hero, Enemy enemy) {
        double heroRadius = 15; // Raio do herói (triângulo)
        double enemyRadius = 15; // Raio do inimigo (círculo)

        double distance = Math.sqrt(Math.pow(hero.getX() - enemy.getX(), 2) + Math.pow(hero.getY() - enemy.getY(), 2));
        return distance < (heroRadius + enemyRadius);
    }

    private void resolveCollision(Hero hero, Enemy enemy) {
        // O herói recebe dano do inimigo
        hero.takeDamage(enemy.getHealth());

        // Verifica se o herói está vivo
        if (hero.getHealth() <= 0) {
            // Aqui você pode adicionar lógica para quando o herói morre
            System.out.println(hero.getName() + " foi derrotado!");
        }

        // O inimigo recebe dano do herói
        enemy.takeDamage(hero.getAttackPower());

        // Verifica se o inimigo está vivo
        if (!enemy.isAlive()) {
            gameArea.getChildren().remove(enemy.getShape());
            enemies.remove(enemy); // Remove o inimigo da lista
        }

        // Empurra o herói para trás (opcional)
        hero.pushBack();
    }

    private void showOptionsMenu() {
        System.out.println("Options Menu");
        // Aqui você pode implementar a lógica para mostrar opções
    }

    public void exitGame() {
        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
