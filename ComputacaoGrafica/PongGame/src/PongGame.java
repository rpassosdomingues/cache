import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PongGame extends Application {

    private double ballSpeedX = 3;
    private double ballSpeedY = 3;
    private double paddleSpeed = 6;

    @Override
    public void start(Stage primaryStage) {
        // Tamanho da tela
        double width = 600;
        double height = 400;

        // Criar a área do jogo
        Pane root = new Pane();
        Scene scene = new Scene(root, width, height, Color.BLACK);

        // Criar a bola
        Circle ball = new Circle(10, Color.WHITE);
        ball.setCenterX(width / 2);
        ball.setCenterY(height / 2);

        // Criar a raquete do jogador
        Rectangle paddle = new Rectangle(100, 10, Color.WHITE);
        paddle.setX(width / 2 - 50);
        paddle.setY(height - 30);

        // Adicionar a bola e a raquete à área do jogo
        root.getChildren().addAll(ball, paddle);

        // Controlar o movimento da raquete
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT && paddle.getX() > 0) {
                paddle.setX(paddle.getX() - paddleSpeed);
            }
            if (event.getCode() == KeyCode.RIGHT && paddle.getX() + paddle.getWidth() < width) {
                paddle.setX(paddle.getX() + paddleSpeed);
            }
        });

        // Animação da bola
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), e -> {
            // Mover a bola
            ball.setCenterX(ball.getCenterX() + ballSpeedX);
            ball.setCenterY(ball.getCenterY() + ballSpeedY);

            // Verificar colisão com as bordas laterais
            if (ball.getCenterX() <= ball.getRadius() || ball.getCenterX() >= width - ball.getRadius()) {
                ballSpeedX *= -1;
            }

            // Verificar colisão com o topo
            if (ball.getCenterY() <= ball.getRadius()) {
                ballSpeedY *= -1;
            }

            // Verificar colisão com a raquete
            if (ball.getBoundsInParent().intersects(paddle.getBoundsInParent())) {
                ballSpeedY *= -1;
            }

            // Verificar se a bola caiu
            if (ball.getCenterY() >= height) {
                ball.setCenterX(width / 2);
                ball.setCenterY(height / 2);
                ballSpeedY *= -1; // Redefinir o movimento da bola
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        // Configurar a janela
        primaryStage.setTitle("Pong Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
