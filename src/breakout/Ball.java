// Yeshe Jangchup
// The Ball class defines the behavior of the ball object in the BreakOut Game. It handles collisions with walls, the paddle, and other objects while also updating its position
// Acknowledgements: Rocky, Stephanie, William, Nadezhdha

package breakout;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.GraphicsText;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

/*
 * Handles the balls collision with the walls, paddle, and bricks
 */
public class Ball extends Ellipse {
    private Ellipse ball;
    private BrickHandler brickHandler;

    private double velocityX, velocityY;

    private double topLeftX, topLeftY;
    private double bottomRightX, bottomRightY;

    private static final double RADIUS = 10;
    private static final Color NEW_COLOR = new Color(220, 200, 250);

    private boolean addedToCanvas = false;
    private int remainingLives = 2; 



    public Ball(double centerX, double centerY, double initialSpeedX, double initialSpeedY, double canvasWidth, double canvasHeight, BrickHandler brickHandler){
        super(centerX - RADIUS, centerY - RADIUS, 2 * RADIUS, 2 * RADIUS);
        
        this.ball=this;
        this.ball.setFillColor(NEW_COLOR);

        this.brickHandler = brickHandler;
        
        this.topLeftX=centerX-RADIUS;
        this.topLeftY=centerY-RADIUS;

        this.bottomRightX=centerX+RADIUS;
        this.bottomRightY=centerY+RADIUS;

        ballVelocity(getCanvas());
    }

    /*
     * Updates the position of the ball on the canvas based on the velocity.
     * Handles the balls collision with canvas walls, paddles, and bricks
     * Also implements the three lives in the game
     */
    public void move(CanvasWindow canvas, Rectangle paddle, BrickHandler handler){
        this.ball.moveBy(this.velocityX,this.velocityY);

        this.topLeftX = this.ball.getX();
        this.topLeftY = this.ball.getY();

        this.bottomRightX = this.ball.getX() + 2 * RADIUS;
        this.bottomRightY = this.ball.getY() + 2 * RADIUS;

        if (this.topLeftX <= 0 || this.bottomRightX  >= canvas.getWidth()) {
            this.velocityX = -this.velocityX;
        }
        if (this.topLeftY <= 0 || this.bottomRightY >= canvas.getHeight()) {
            this.velocityY = -this.velocityY;
        }

        if (this.bottomRightY >= canvas.getHeight()) {
            if (remainingLives > 0) {
                ballPosition(canvas);
                this.velocityX=0;
                this.velocityY=0;
                
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        ballVelocity(canvas);
                        move(canvas, paddle, handler);
                    }
                }, 1000); 

                remainingLives--;

            } 
            else {
                if(handler.getNumOfBricks()==0){
                    displayGameOverMessage(canvas);
                }
                else{
                    displayGameOverMessage(canvas);
                }
                this.velocityX = 0;
                this.velocityY = 0;
                removeFromCanvas(canvas);
            }
        }
    
        GraphicsObject collisionObject = objectCollisions(canvas);
        if (collisionObject == paddle) {
            if (velocityY > 0) {
                velocityY = -velocityY;
            }
        } 
        else {
            if (collisionObject != null) {
                canvas.remove(collisionObject);
                if (brickHandler != null) { 
                brickHandler.removeBrickFromList(this);
                    if(brickHandler.getNumOfBricks()==0){
                        displayYouWinMessage(canvas);
                        this.removeFromCanvas(canvas);
                    }
                }
                velocityY = -velocityY;
            }
        }
    }

    private void displayYouWinMessage(CanvasWindow canvas){
        GraphicsText youWinMessage = new GraphicsText("You Win", (canvas.getWidth() / 2)-50, (canvas.getHeight() / 2)+20);
        FontStyle style = FontStyle.BOLD;
        youWinMessage.setFont(style, 20);
        youWinMessage.setFillColor(NEW_COLOR);
        canvas.add(youWinMessage);
    }

    private void displayGameOverMessage(CanvasWindow canvas) {
        GraphicsText gameOverMessage = new GraphicsText("Game Over", (canvas.getWidth() / 2)-60, (canvas.getHeight() / 2)+70);
        FontStyle style = FontStyle.BOLD;
        gameOverMessage.setFont(style, 20);
        gameOverMessage.setFillColor(NEW_COLOR);
        canvas.add(gameOverMessage);
    }
  
    private GraphicsObject objectCollisions(CanvasWindow canvas){
        GraphicsObject topLeftObj = canvas.getElementAt(this.topLeftX, this.topLeftY);
        GraphicsObject topRightObj = canvas.getElementAt(this.bottomRightX, this.topLeftY);
        GraphicsObject bottomLeftObj = canvas.getElementAt(this.topLeftX, this.bottomRightY);
        GraphicsObject bottomRightObj = canvas.getElementAt(this.bottomRightX, this.bottomRightY);

        if (topLeftObj != null) {
            return topLeftObj;
        } else if (topRightObj != null) {
            return topRightObj;
        } else if (bottomLeftObj != null) {
            return bottomLeftObj;
        } else if (bottomRightObj != null) {
            return bottomRightObj;
        } else {
            return null;
        }
    }

    private void ballPosition(CanvasWindow canvas){
        double initialX = (canvas.getWidth() - 2 * RADIUS) / 2;
        double initialY = (canvas.getHeight() - 2 * RADIUS) / 2;
        this.ball.setPosition(initialX, initialY);
    }

    private void ballVelocity(CanvasWindow canvas){
        double randomSpeed = 5.0 + Math.random() *(10.0-5.0);
        this.velocityX = randomSpeed;
        if (Math.random() < 0.5) {
            this.velocityX = -this.velocityX; 
        }
        if (Math.random() < 0.5) {
            this.velocityY = 10.0;
        } 
        else {
            this.velocityY = 5.0;
        }
    }

    private void removeFromCanvas(CanvasWindow canvas) {
        if (addedToCanvas) {
            canvas.remove(this);
            addedToCanvas = false;
        }
    }

    /*
     * Adds the ball object to the canvas
     */
    public void addToCanvas(CanvasWindow canvas) {
        canvas.add(this);
        addedToCanvas = true;
    }
}
    
