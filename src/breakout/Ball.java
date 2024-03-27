package breakout;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsGroup;

import java.awt.Color;


public class Ball extends GraphicsGroup {
    private static final double RADIUS = 10;
    private Ellipse ball;
    private double dx;
    private double dy;
    private double canvasHeight ;
    private double canvasWidth;

    public Ball(double x, double y, double dx, double dy, double canvasHeight, double canvasWidth, Color color){
        ball = new Ellipse(x-RADIUS, y-RADIUS, 2*RADIUS, 2*RADIUS);
        ball.setFillColor(color);
        this.canvasHeight=canvasHeight;
        this.canvasWidth=canvasWidth;
        this.dx=5;
        this.dy=5;
    }

    public Ellipse ballShape(){
        return ball;
    }

    public void move(){

        ball.moveBy(dx, dy);
        

        if (ball.getX() <= 0 || ball.getX() + 2 * RADIUS >= canvasWidth) {
            dx = -dx;
        }if (ball.getY() <= 0|| ball.getY() + 2 * RADIUS >= canvasHeight) {
            dy = -dy;
        }
    }

    
    public void ballPaddleCollision(){
        if (ball.getY() + 2 * RADIUS >= canvasHeight - 50) {
            dy = -dy;
        }
        else if (ball.getBounds().intersects(paddle.getBounds())){
            dy = -dy;
        }
    }

        
        
    
}
