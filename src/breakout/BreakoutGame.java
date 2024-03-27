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
    private static final int CANVAS_HEIGHT = 800;
    // private static final int EDGE = 790;
    private static final int TOTAL_BRICKS = 100;
    private static final int BRICK_ROWS = 10;
    private static final int BRICK_COLS = TOTAL_BRICKS / BRICK_ROWS;
    private static final double BALL_RADIUS = 10;


    private CanvasWindow canvas;
    private List<Rectangle> bricks;
    private Ellipse ball;


    public BreakoutGame() {
        canvas = new CanvasWindow("Breakout!", CANVAS_WIDTH, CANVAS_HEIGHT);
        
        Brick brick = new Brick();
        bricks = brick.createBricks(BRICK_ROWS, BRICK_COLS);
        for (Rectangle rect : bricks){
            canvas.add(rect);
        }
        
        Paddle paddle = new Paddle(CANVAS_WIDTH, CANVAS_HEIGHT);
        canvas.add(paddle.shape());

        ball = new Ellipse((CANVAS_WIDTH - 2 * BALL_RADIUS) / 2, (CANVAS_HEIGHT - 2 * BALL_RADIUS) / 2, 2 * BALL_RADIUS, 2 * BALL_RADIUS);
        ball.setFillColor(Color.BLACK);
        canvas.add(ball);
        animateBall();
    }

    public void animateBall(){
        double dx=1;
        double dy=1;
        canvas.animate(()->{
            ball.moveBy(dx, dy);
        });
        
    }

    public static void main(String[] args){
        new BreakoutGame();
    }
}
