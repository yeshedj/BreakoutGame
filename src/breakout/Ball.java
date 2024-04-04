package breakout;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.GraphicsText;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;


public class Ball extends Ellipse {
    private static final double RADIUS = 10;
    private Ellipse ball;
    private double dx, dy;

    private double topLeftX, topLeftY;
    private double bottomRightX, bottomRightY;
    private GraphicsText lostMessage;
    private boolean lostMessageShown = false;
    private BrickHandler brickHandler;
    private boolean addedToCanvas = false;
    Color NEW_COLOR = new Color(220, 200, 250);




    public Ball(double centerX, double centerY, double initialSpeedX, double initialSpeedY, double canvasWidth, double canvasHeight, BrickHandler brickHandler){
        super(centerX - RADIUS, centerY - RADIUS, 2 * RADIUS, 2 * RADIUS);
        this.ball=this;
        this.ball.setFillColor(NEW_COLOR);

        double randomSpeed = 5.0 + Math.random() *(10.0-5.0);
        this.dx = randomSpeed;
        if(Math.random()<0.5){
            this.dy=10.0;
        }else {
            this.dy=5.0;
        }
        this.topLeftX=centerX-RADIUS;
        this.topLeftY=centerY-RADIUS;
        this.bottomRightX=centerX+RADIUS;
        this.bottomRightY=centerY+RADIUS;
        this.brickHandler = brickHandler;

        
    }

    public void move(CanvasWindow canvas, Rectangle paddle, BrickHandler handler){
        this.ball.moveBy(this.dx,this.dy);
        this.topLeftX = this.ball.getX();
        this.topLeftY = this.ball.getY();
        this.bottomRightX = this.ball.getX() + 2 * RADIUS;
        this.bottomRightY = this.ball.getY() + 2 * RADIUS;

        if (this.topLeftX <= 0 || this.bottomRightX  >= canvas.getWidth()) {
            this.dx = -this.dx;
        }
        if (this.topLeftY <= 0 || this.bottomRightY >= canvas.getHeight()) {
            this.dy = -this.dy;
        }


        if(this.bottomRightY >= canvas.getHeight()){
            if(!lostMessageShown){
                displayLostMessage(canvas);
                lostMessageShown=true;
            }
            this.dx=0;
            this.dy=0;
            this.ball.setPosition(this.ball.getX(),canvas.getHeight()-2*RADIUS-1);
        }

        GraphicsObject collisionObject = objectCollisions(canvas);
        if (collisionObject == paddle) {
            if (dy > 0) {
                dy = -dy;
            }
        } 
        else {
            if (collisionObject != null) {
                canvas.remove(collisionObject);
                if (brickHandler != null) { 
                    brickHandler.removeBrickFromList(this);
                    System.out.println("YAAAA");
                }
                dy = -dy;
                
                // if (brickHandler != null && brickHandler.getNumOfBricks() == 0) { // Check if brickHandler is not null and there are no bricks left
                //     displayWinMessage(canvas);

                // }
            }
        }
    }
    public void displayWinMessage(CanvasWindow canvas){
        double centerX = canvas.getWidth() / 2;
        double centerY = canvas.getHeight() / 2;
        GraphicsText winMessage = new GraphicsText("You won!", centerX, centerY);
        winMessage.setCenter(centerX, centerY);
    
        FontStyle style = FontStyle.BOLD;
        double size = 20.0;
        winMessage.setFont(style, size);
    
        winMessage.setFillColor(Color.GREEN);
    
        canvas.add(winMessage);  
        removeFromCanvas(canvas);
  

    }

    public void displayLostMessage(CanvasWindow canvas){
        double centerX=canvas.getWidth()/2;
        double centerY=canvas.getHeight()/2;
        
        lostMessage=new GraphicsText("Click to Try Again", centerX, centerY);
        lostMessage.setCenter(centerX,centerY);

        FontStyle style = FontStyle.ITALIC;
        double size=15.0;
        lostMessage.setFont(style, size);
        canvas.add(lostMessage);

        canvas.onClick(event -> {
            canvas.remove(lostMessage);
            resetPositionAndVelocity(canvas);
            lostMessageShown = false;
        });

    }

    public GraphicsObject objectCollisions(CanvasWindow canvas){
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

    public Ellipse getBall() {
        return this.ball;
    }

    public double getDx() {
        return this.dx;
    }

    public double getDy() {
        return this.dy;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }


    public void resetPositionAndVelocity(CanvasWindow canvas){
        double initialX = (canvas.getWidth() - 2 * RADIUS) / 2;
        double initialY = (canvas.getHeight() - 2 * RADIUS) / 2;
        this.ball.setPosition(initialX, initialY);

        double randomSpeed = 5.0 + Math.random() *(10.0-5.0);
        this.dx = randomSpeed;
        if(Math.random()<0.5){
            this.dy=10.0;
        }else {
            this.dy=5.0;
        }
    }

    public void paddleCollision(CanvasWindow canvas, Rectangle paddle){
        if(objectCollisions(canvas) == paddle){
            dx=-dx;
            canvas.remove(paddle);
        }
    }

    public void brickCollision(CanvasWindow canvas, ArrayList<Brick> bricks){
        GraphicsObject collisionObject = objectCollisions(canvas);
        if (collisionObject != null) {
            canvas.remove(collisionObject);
            // Brick brick = (Brick) collisionObject;
            dy=-dy;
            // canvas.remove(brick);
            
            // brick.removeFromCanvas();
            // bricks.remove(brick);
        } 
    }

   

        public void addToCanvas(CanvasWindow canvas) {
            canvas.add(this);
            addedToCanvas = true;

        }
    
        public void removeFromCanvas(CanvasWindow canvas) {
            // canvas.remove(this);
            if (addedToCanvas) {
                canvas.remove(this);
                addedToCanvas = false;
            }
        }
}
    
