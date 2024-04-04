// Yeshe Jangchup
// The BrickHandler class involves the creation of the bricks and handling them. It displays a grid of bricks on the canvas and keeps track of the bricks
// Acknowledgements: Rocky

package breakout;
import edu.macalester.graphics.CanvasWindow;

/*
 * Creates the bricks and handles brick objects
 */
public class BrickHandler{    
    private CanvasWindow canvas;

    int brickCount = 0;

    public static final int BRICK_WIDTH = 65;
    public static final int BRICK_HEIGHT = 20;
    public static final int BRICK_SPACING = 7;

    public BrickHandler(CanvasWindow canvas) {
        this.canvas = canvas;
    }

    /*
     * Generates a grid of bricks on the canvas, 
     * iterating over rows and columns to determine positoining
     */
    public void createBricks() {
        int rows = 9;
        int cols = 9;

        brickCount = rows*cols;

        int startX = 5; 
        int startY = 55; 

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int x = startX + (BRICK_WIDTH + BRICK_SPACING) * col;
                int y = startY + (BRICK_HEIGHT + BRICK_SPACING) * row;
                Brick brick = new Brick(x, y, canvas);
                brick.setFillColor();
                brick.setStrokeColor();
                brick.addToCanvas();
            }
        }        
    }

    /*
     * Returns the number of bricks in the bricks list.
     */
    public int getNumOfBricks() {
        return brickCount;
    }

    /*
     * Decrements the brickCount variable
     */
    public void removeBrickFromList(Ball ball){
        brickCount --;

    }
}