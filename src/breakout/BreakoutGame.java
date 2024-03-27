package breakout;

import java.awt.Color;
import java.util.List;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.Rectangle;

/**
 * The game of Breakout.
 */
public class BreakoutGame {
    private static final int CANVAS_WIDTH = 600;
    private static final int CANVAS_HEIGHT = 750;
    // private static final int EDGE = 790;
    private static final int TOTAL_BRICKS = 100;
    private static final int BRICK_ROWS = 10;
    private static final int BRICK_COLS = TOTAL_BRICKS / BRICK_ROWS;
    private static final double BALL_RADIUS = 10;


    private CanvasWindow canvas;
    private List<Rectangle> bricks;
    private Ball ball;


    public BreakoutGame() {
        canvas = new CanvasWindow("Breakout!", CANVAS_WIDTH, CANVAS_HEIGHT);
        
        Brick brick = new Brick();
        bricks = brick.createBricks(BRICK_ROWS, BRICK_COLS);
        for (Rectangle rect : bricks){
            canvas.add(rect);
        }
        
        Paddle paddle = new Paddle(CANVAS_WIDTH, CANVAS_HEIGHT);
        canvas.add(paddle.shape());

        ball = new Ball(CANVAS_WIDTH/2, CANVAS_HEIGHT/2, 3, 3, CANVAS_HEIGHT, CANVAS_WIDTH, Color.BLACK );
        
        // ball.setFillColor(Color.BLACK);
        canvas.add(ball.getShape());
        
        animateBall();
    }

    public void animateBall(){
        // double dx=3;
        // double dy=3;
        canvas.animate(()->{
            ball.move();
        });
    }

    public static void main(String[] args){
        new BreakoutGame();
    }
}
