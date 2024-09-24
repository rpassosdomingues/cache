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
        RadialGradient gradient = new RadialGradient(
                0, 0, // proportional radius and center
                0.5, 0.5, // center of the gradient
                radius, // radius
                true, // proportional
                CycleMethod.REPEAT, // cycle method
                new Stop(0, Color.RED), // start color
                new Stop(1, Color.YELLOW) // end color
        );
        setFill(gradient);
    }
}
