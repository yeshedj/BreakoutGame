package breakout;
import edu.macalester.graphics.Ellipse;
import java.awt.Color;


public class Ball {
    private static final double RADIUS = 10;
    private Ellipse ball;
    private double dx;
    private double dy;
    private double canvasHeight = 800;
    private double canvasWidth = 600;

    public Ball(double x, double y, double dx, double dy, double canvasHeight, double canvasWidth, Color color){
        ball = new Ellipse(x-RADIUS, y-RADIUS, 2*RADIUS, 2*RADIUS);
        ball.setFillColor(color);
        this.dx=dx;
        this.dy=dy;
    }
    public Ellipse getShape(){
        return ball;
    }
    public void move(){
        double x = ball.getX();
        double y = ball.getY();

        ball.moveBy(dx, dy);

        if (x <= 0 || x + 2 * RADIUS >= canvasWidth) {
        }
        if (y <= 0 || y + 2 * RADIUS >= canvasHeight) {
            dy = -dy; 
        }

    }
}
