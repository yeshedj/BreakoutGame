package breakout;

import edu.macalester.graphics.Rectangle;
import java.awt.Color;

public class Paddle{
    private static final double WIDTH = 90;
    private static final double HEIGHT = 10;
    private static final Color COLOR = Color.BLACK;
    private Rectangle paddle;

    public Paddle(double canvasWidth, double canvasHeight){
        double paddleX = (canvasWidth - WIDTH)/2;
        double paddleY = canvasHeight - HEIGHT - 100;
        paddle = new Rectangle(paddleX, paddleY, WIDTH,HEIGHT);
        paddle.setFillColor(COLOR);
        // add(paddle);
    }
    public Rectangle shape(){
        return paddle;
    }

    public void setPostiion(double x, double y){
        paddle.setPosition(x,y);
    }
    
}
