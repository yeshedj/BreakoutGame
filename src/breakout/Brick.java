// Yeshe Jangchup
// The Brick class creates a rectangle shape for each brick and has methods to modify color,  and also to add it to the canvas.
// Acknowledgements: Rocky

package breakout;
import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Rectangle;

/*
 * Creates a rectangular shape for each brick
 * Also modifies the brick color and canvas visibility
 */
public class Brick{

    private Rectangle brick;
    private CanvasWindow canvas;
    Color NEW_COLOR = new Color(220, 200, 250);

    public Brick(double x, double y, CanvasWindow canvas){
        this.canvas=canvas;
        brick = new Rectangle(x, y, BrickHandler.BRICK_WIDTH, BrickHandler.BRICK_HEIGHT);
    }

    /*
     * Sets the fill color of the brick shape to NEW_COLOR,
     * defined in the class
     */
    public void setFillColor() {
        brick.setFillColor(NEW_COLOR);
    }

    /*
     * Sets the stroke color of the brick shape to black 
     */
    public void setStrokeColor() {
        brick.setStrokeColor(Color.BLACK);
    }

    /*
     * Adds the brick shape to the canvas so it is displayed when the game is run
     */
    public void addToCanvas() {
        canvas.add(brick);
    }
}

