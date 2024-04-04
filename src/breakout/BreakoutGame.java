
package breakout;
import java.awt.Color;
import java.util.List;
// import org.w3c.dom.events.MouseEvent;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
// import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.Rectangle;


/**
 * The game of Breakout.
 */
public class BreakoutGame {
    private CanvasWindow canvas;
    private static final int CANVAS_WIDTH = 600;
    private static final int CANVAS_HEIGHT = 750;
    Color NEW_COLOR = new Color(220, 200, 250);

    private Rectangle paddle;
    private Ball ball;
    private BrickHandler brickHandler;
    
    
    public BreakoutGame() {
        canvas = new CanvasWindow("Breakout!", CANVAS_WIDTH, CANVAS_HEIGHT);
        brickHandler=new BrickHandler(canvas);
        brickHandler.createBricks();
        

        paddle = new Rectangle(CANVAS_WIDTH / 2 - 50, CANVAS_HEIGHT - 50, 100, 10);
        paddle.setFillColor(NEW_COLOR);
        canvas.add(paddle);

        ball = new Ball(CANVAS_WIDTH/2, CANVAS_HEIGHT/2, 3, 3, CANVAS_WIDTH, CANVAS_HEIGHT, brickHandler);
        ball.addToCanvas(canvas);
        animateBall();

        canvas.onMouseMove(event -> {
            double newX = event.getPosition().getX();
            if (newX >= 0 && newX <= canvas.getWidth() - paddle.getWidth()) {
                paddle.setX(newX);
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
        });
    }

    
    public static void main(String[] args){
        new BreakoutGame();
    }
}

