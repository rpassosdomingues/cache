package src;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private Scenario currentStage;
    private Stage1 stage1;
    private Stage2 stage2;
    private Stage3 stage3;

    private BorderPane mainLayout;
    
    @Override
    public void start(Stage primaryStage) {
        stage1 = new Stage1(50, 150, 2);
        stage2 = new Stage2(30, 120, 3);
        stage3 = new Stage3(40, 100, 4);

        mainLayout = new BorderPane();

        VBox controlPanel = new VBox(10);
        Button newGameButton = new Button("New Game");
        Button optionsButton = new Button("Options");
        Button exitButton = new Button("Exit");
        
        newGameButton.setOnAction(e -> showNewGameMenu(controlPanel));
        optionsButton.setOnAction(e -> showOptionsMenu(controlPanel));
        exitButton.setOnAction(e -> primaryStage.close());

        controlPanel.getChildren().addAll(newGameButton, optionsButton, exitButton);
        mainLayout.setRight(controlPanel);
        
        // Setando a cena inicial
        Scene scene = new Scene(mainLayout, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Game Stages");
        primaryStage.show();
    }

    private void showNewGameMenu(VBox controlPanel) {
        controlPanel.getChildren().clear();

        // ComboBox para selecionar o estágio
        ComboBox<String> stageSelector = new ComboBox<>();
        stageSelector.getItems().addAll("Stage 1", "Stage 2", "Stage 3");

        Button startButton = new Button("Start");
        Button backButton = new Button("Back");

        startButton.setOnAction(e -> {
            String selectedStage = stageSelector.getValue();
            switch (selectedStage) {
                case "Stage 1":
                    loadStage(stage1);
                    break;
                case "Stage 2":
                    loadStage(stage2);
                    break;
                case "Stage 3":
                    loadStage(stage3);
                    break;
                default:
                    break;
            }
        });

        backButton.setOnAction(e -> showMainMenu(controlPanel));

        controlPanel.getChildren().addAll(stageSelector, startButton, backButton);
    }

    private void showOptionsMenu(VBox controlPanel) {
        controlPanel.getChildren().clear();

        ComboBox<String> difficultySelector = new ComboBox<>();
        difficultySelector.getItems().addAll("Easy", "Intermediate", "Difficult");

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> showMainMenu(controlPanel));

        controlPanel.getChildren().addAll(difficultySelector, backButton);
    }

    private void showMainMenu(VBox controlPanel) {
        controlPanel.getChildren().clear();

        Button newGameButton = new Button("New Game");
        Button optionsButton = new Button("Options");
        Button exitButton = new Button("Exit");

        newGameButton.setOnAction(e -> showNewGameMenu(controlPanel));
        optionsButton.setOnAction(e -> showOptionsMenu(controlPanel));
        exitButton.setOnAction(e -> System.exit(0));

        controlPanel.getChildren().addAll(newGameButton, optionsButton, exitButton);
    }

    public void loadStage(Scenario stage) {
        currentStage = stage; // Atualiza o estágio atual
        stage.setup();

        // Atualizando a área de jogo
        mainLayout.setCenter(stage.getRoot()); // Coloca o jogo no centro do layout
    }

    public static void main(String[] args) {
        launch(args);
    }
}
