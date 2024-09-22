package src;

import javafx.scene.paint.Color;
import javafx.scene.shape.Sphere;

public class Enemy {
    private Sphere head;
    private Sphere body;
    private double speed;
    private double positionX;
    private double positionY;

    public Enemy(double positionX, double positionY, double speed) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.speed = speed;

        // Criar um boneco de neve translúcido (cabeça e corpo)
        head = new Sphere(10);
        head.setTranslateX(positionX);
        head.setTranslateY(positionY - 15);
        head.setMaterial(new javafx.scene.paint.PhongMaterial(Color.rgb(0, 0, 139, 0.5))); // Azul escuro translúcido

        body = new Sphere(15);
        body.setTranslateX(positionX);
        body.setTranslateY(positionY);
        body.setMaterial(new javafx.scene.paint.PhongMaterial(Color.rgb(0, 0, 139, 0.5)));
    }

    public void move() {
        positionX += speed;
        head.setTranslateX(positionX);
        body.setTranslateX(positionX);
    }

    public Sphere[] getParts() {
        return new Sphere[]{head, body};
    }
}
