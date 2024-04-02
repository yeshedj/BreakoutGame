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

    // private static final int TOTAL_BRICKS = 100;
    // private static final int BRICK_ROWS = 10;
    // private static final int BRICK_COLS = TOTAL_BRICKS / BRICK_ROWS;

    // private List<Rectangle> bricks;
    private Rectangle paddle;
    private Ball ball;
    private BrickHandler brickHandler;


    public BreakoutGame() {
        canvas = new CanvasWindow("Breakout!", CANVAS_WIDTH, CANVAS_HEIGHT);
        
        brickHandler=new BrickHandler(canvas);
        brickHandler.makeBrickRows(0, 50, Color.RED);

        paddle = new Rectangle(CANVAS_WIDTH / 2 - 50, CANVAS_HEIGHT - 50, 100, 10);
        paddle.setFillColor(Color.BLACK);
        canvas.add(paddle);

        ball = new Ball(CANVAS_WIDTH/2, CANVAS_HEIGHT/2, 3, 3, CANVAS_WIDTH, CANVAS_HEIGHT);
        canvas.add(ball.getBall());
        animateBall();

        canvas.onMouseMove(event -> {
            if (event.getPosition().getX() <= CANVAS_WIDTH-paddle.getWidth()){
                paddle.setX(event.getPosition().getX());
            }
        });

        canvas.onClick(event->{
            double clickX = event.getPosition().getX();
            double clickY = event.getPosition().getY();

            if (clickX >= 0 && clickX <= canvas.getWidth() && clickY >= 0 && clickY <= canvas.getHeight()) {
                ball.resetPositionAndVelocity(canvas);
            }
        });

    }

    
    public void animateBall(){
        canvas.animate(()->{
            ball.move(canvas, paddle, brickHandler);
            ball.checkBrickCollision(canvas, brickHandler);
        });
    }


    public static void main(String[] args){
        new BreakoutGame();
    }
}
