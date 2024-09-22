package src;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class Control {
    private VBox controlPanel;

    public Control() {
        controlPanel = new VBox(10); // Espaçamento entre os botões.
        Button newGameButton = new Button("New Game");
        Button pauseButton = new Button("Pause");
        Button restartButton = new Button("Restart");
        Button exitButton = new Button("Exit");

        newGameButton.setOnAction(e -> newGame());
        pauseButton.setOnAction(e -> pauseGame());
        restartButton.setOnAction(e -> restartGame());
        exitButton.setOnAction(e -> exitGame());

        controlPanel.getChildren().addAll(newGameButton, pauseButton, restartButton, exitButton);
    }

    public VBox getControlPanel() {
        return controlPanel;
    }

    private void newGame() {
        System.out.println("Starting new game...");
    }

    private void pauseGame() {
        System.out.println("Pausing game...");
    }

    private void restartGame() {
        System.out.println("Restarting game...");
    }

    private void exitGame() {
        System.exit(0);
    }
}
