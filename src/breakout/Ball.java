package breakout;
import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Rectangle;


public class Ball extends GraphicsGroup {
    private static final double RADIUS = 10;
    private Ellipse ball;
    private double dx, dy;
    // private double canvasHeight ;
    // private double canvasWidth;

    private double topLeftX, topLeftY;
    private double bottomRightX, bottomRightY;
    private GraphicsText lostMessage;
    private boolean lostMessageShown = false;


    public Ball(double centerX, double centerY, double initialSpeedX, double initialSpeedY, double canvasWidth, double canvasHeight){

        this.ball = new Ellipse(centerX-RADIUS, centerY - RADIUS, 2*RADIUS, 2*RADIUS);
        this.ball.setFillColor(Color.BLACK);

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

        GraphicsObject collidedObject = this.objectCollisions(canvas, handler);
            if (collidedObject instanceof Brick) {
                System.out.println("Brick Hit!");
                Brick brick = (Brick) collidedObject;
                handler.removeBrick(brick);
                
            } else if (collidedObject == paddle) {
                this.dy = -Math.abs(this.dy); 
                System.out.println("Paddle Hit!");
            }

        
        // if(collidedObject==paddle){
        //     this.dy = -Math.abs(this.dy);
        //     System.out.println("Paddle Hit!");
        // }
        // else if (collidedObject instanceof Brick) {
        //     System.out.println("Brick Hit!");
        //     Brick brick = (Brick) collidedObject;
        //     handler.removeBrick(brick);
        //     this.dy=-this.dy;
        // }


        // Brick collidedBrick = handler.getBrickCollision(this);
        // if(collidedBrick != null){
        //     handler.handleCollision(collidedBrick);
        //     this.dy=-this.dy;
        // }
        // checkBrickCollision(handler, canvas);
    }

    public void displayLostMessage(CanvasWindow canvas){
        double centerX=canvas.getWidth()/2;
        double centerY=canvas.getHeight()/2;
        lostMessage=new GraphicsText("Uh oh! Click to Try Again", centerX, centerY);
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

    public GraphicsObject objectCollisions(CanvasWindow canvas, BrickHandler handler){
        GraphicsObject topLeftObj = canvas.getElementAt(this.topLeftX, this.topLeftY);
        GraphicsObject topRightObj = canvas.getElementAt(this.bottomRightX, this.topLeftY);
        GraphicsObject bottomLeftObj = canvas.getElementAt(this.topLeftX, this.bottomRightY);
        GraphicsObject bottomRightObj = canvas.getElementAt(this.bottomRightX, this.bottomRightY);

        if (topLeftObj != null || topLeftObj instanceof Brick) {
            return topLeftObj;
        } else if (topRightObj != null || topRightObj instanceof Brick) {
            return topRightObj;
        } else if (bottomLeftObj != null || bottomLeftObj instanceof Brick) {
            return bottomLeftObj;
        } else if (bottomRightObj != null || bottomRightObj instanceof Brick) {
            return bottomRightObj;
        } 

        GraphicsObject collidedObject = canvas.getElementAt(this.ball.getCenter());
        if (collidedObject != null && collidedObject instanceof Brick) {
            return collidedObject;
        }

        return null;

        
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

    public void checkBrickCollision(CanvasWindow canvas, BrickHandler handler){
  
        GraphicsObject collisionObject = objectCollisions(canvas, handler);
        // for(Brick brick : handler.getBrickList()){
            if(collisionObject!= null && collisionObject instanceof Brick){
                Brick brick=(Brick) collisionObject;
                handler.removeBrick(brick);
                System.out.println("Working?");
            }

    }
}



