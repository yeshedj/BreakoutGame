package breakout;

import java.awt.Color;
import java.util.List;

// import org.w3c.dom.events.MouseEvent;

import edu.macalester.graphics.CanvasWindow;
// import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.Rectangle;
// import java.awt.event.*;

/**
 * The game of Breakout.
 */
public class BreakoutGame {
    private CanvasWindow canvas;
    private static final int CANVAS_WIDTH = 600;
    private static final int CANVAS_HEIGHT = 750;

    private static final double RADIUS = 10;
    private static int PADDLE_WIDTH = 90;
    private static int PADDLE_HEIGHT = 10;


    private static final int TOTAL_BRICKS = 100;
    private static final int BRICK_ROWS = 10;
    private static final int BRICK_COLS = TOTAL_BRICKS / BRICK_ROWS;

    private List<Rectangle> bricks;
    private Rectangle paddle;
    private Ball ball;


    public BreakoutGame() {
        canvas = new CanvasWindow("Breakout!", CANVAS_WIDTH, CANVAS_HEIGHT);
        
        Brick brick = new Brick();
        bricks = brick.createBricks(BRICK_ROWS, BRICK_COLS);
        for (Rectangle rect : bricks){
            canvas.add(rect);
        }
        
        paddle = new Rectangle(CANVAS_WIDTH / 2 - 50, CANVAS_HEIGHT - 50, 100, 10);
        paddle.setFillColor(Color.BLACK);
        canvas.add(paddle);


        ball = new Ball(CANVAS_WIDTH/2, CANVAS_HEIGHT/2, 3, 3, CANVAS_HEIGHT, CANVAS_WIDTH, Color.BLACK );
        canvas.add(ball.ballShape());
        animateBall();


        canvas.onMouseMove(event -> {
            paddle.setX(event.getPosition().getX());
        });
    }



    
    public void animateBall(){
        canvas.animate(()->{
            ball.move();
            ball.ballPaddleCollision();
        });
    }


    public static void main(String[] args){
        new BreakoutGame();
    }
}
