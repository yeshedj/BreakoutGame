// Yeshe Jangchup
// The BreakOut Game initializes the game window, sets up the paddle, ball, and bricks. It handles the movement of the paddle based on the user's mouse moving and also handles the animation of the ball.
// Acknowledgements: Stephanie, William, Tenzin

package breakout;
import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Rectangle;


/**
 * The game of Breakout. 
 * Sets up the paddle, ball, and bricks.
 */
public class BreakoutGame {
    private CanvasWindow canvas;
    private Rectangle paddle;
    private Ball ball;
    private BrickHandler brickHandler;

    private static final int CANVAS_WIDTH = 650;
    private static final int CANVAS_HEIGHT = 750;

    Color NEW_COLOR = new Color(220, 200, 250);

    public BreakoutGame() {
        canvas = new CanvasWindow("Breakout!", CANVAS_WIDTH, CANVAS_HEIGHT);
        canvas.setBackground(Color.BLACK);
      
        brickHandler=new BrickHandler(canvas);
        brickHandler.createBricks();

        paddle = new Rectangle(CANVAS_WIDTH / 2 - 50, CANVAS_HEIGHT - 50, 100, 10);
        paddle.setFillColor(NEW_COLOR);
        canvas.add(paddle);

        ball = new Ball(CANVAS_HEIGHT, CANVAS_HEIGHT, CANVAS_HEIGHT, CANVAS_HEIGHT, CANVAS_WIDTH, CANVAS_HEIGHT, brickHandler);
        ball.addToCanvas(canvas);
        animateBall();

        canvas.onMouseMove(event -> {
            double newX = event.getPosition().getX();
            if (newX >= 0 && newX <= canvas.getWidth() - paddle.getWidth()) {
                paddle.setX(newX);
            }
        });
    }

    /*
     * Animates the movement of the ball on the canvas. 
     * Responds to collision, changes direction, and interacts with other elements
     */
    public void animateBall(){
        canvas.animate(()->{
            ball.move(canvas, paddle, brickHandler);
        });
    }

    /*
     * Initializes and starts the game when the program runs
     */
  public static void main(String[] args){
        new BreakoutGame();
    }
}