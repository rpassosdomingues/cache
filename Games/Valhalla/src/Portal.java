package src;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Stop;

public class Portal extends Circle {
    public Portal(double x, double y, double radius, boolean isStart) {
        super(x, y, radius);

        // Gradiente de arco-íris com transição suave
        RadialGradient gradient = new RadialGradient(0, 0, 0.5, 0.5, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, isStart ? Color.BLUE : Color.RED),
                new Stop(0.5, Color.GREEN),
                new Stop(1, isStart ? Color.RED : Color.BLUE));

        this.setFill(gradient);
    }
}
