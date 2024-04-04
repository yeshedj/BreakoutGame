package breakout;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Rectangle;
import java.awt.Color;

public class Paddle extends GraphicsGroup{
    private static final double WIDTH = 90;
    private static final double HEIGHT = 10;
    private Rectangle paddle;

    public Paddle(double canvasWidth, double canvasHeight){
        double paddleX = (canvasWidth - WIDTH);
        double paddleY = (canvasHeight - HEIGHT) -20;
        paddle = new Rectangle(paddleX, paddleY, WIDTH,HEIGHT);
    }
    public Rectangle paddleShape(){
        return paddle;
    }

    public void setPostiion(double x, double y){
        paddle.setPosition(x,y);
    }
    
}
