package breakout;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsObject;

public class BrickHandler{
    private List <Brick> bricks;
    private CanvasWindow canvas;
    private int brickCount = 0;

    public BrickHandler(CanvasWindow canvas) {
        this.bricks = new ArrayList<>();
        this.canvas = canvas;
    }

    public void makeBrickRows(double xCoor, double yCoor, Color color) {
        double xCoor1 = xCoor;
        for (int i = 0; i < 10; i++) {
            Brick brick = new Brick(xCoor1, yCoor, canvas);
            bricks.add(brick);
            // brick.addToCanvas(canvas);
            xCoor1 += 60;
            brickCount++;
        }
    }


    public boolean bricksStillRemain(){
        return brickCount >0;

    }


    public int getNumOfBricks() {
        return brickCount;
    }

    public List<Brick> getBrickList() {
        return bricks;
    }

    // public void removeBrickFromList(Brick brick) {
    //     if (bricks.contains(brick)) {
    //         bricks.remove(brick);
    //         brickCount -= 1;
    //     }
    // }


    // public void handleCollision(Brick brick){
    //     brick.removeFromCanvas();
    //     removeBrickFromList(brick);
    // }

    public Brick getBrickCollision(Ball ball, BrickHandler handler){
 
        GraphicsObject collisionObject = ball.objectCollisions(canvas, handler);
            if(collisionObject!= null && collisionObject instanceof Brick){
                Brick brick=(Brick) collisionObject;
                removeBrick(brick);
                return brick;
            }
        return null;
        // for (Brick brick : bricks) {
        //     if (ball.getBall().getBoundsInParent().intersects(brick.getBoundsInParent())) {
        //         return brick; // Return the collided brick
        //     }
        // }
        // return null;
    }


    public void removeBrick(Brick brick){
        if(bricks.contains(brick)){
            bricks.remove(brick);
            canvas.remove(brick);
            brick.removeFromCanvas();
            brickCount--;
        }
    }
    

}