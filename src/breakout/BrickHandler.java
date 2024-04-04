package breakout;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Rectangle;

public class BrickHandler{
    private List <Brick> bricks;
    private CanvasWindow canvas;
    private Ball ball;
    int brickCount = 0;

    public static final int BRICK_WIDTH = 60;
    public static final int BRICK_HEIGHT = 20;
    public static final int BRICK_SPACING = 15;


    public BrickHandler(CanvasWindow canvas) {
        this.bricks = new ArrayList<>();
        this.canvas = canvas;
    }


    public void createBricks() {
        int rows = 7;
        int cols = 9;
        int startX = 4; 
        int startY = 50; 

        for (int row = 0; row < rows; row++) {
            // Color color = getRowColor(row);

            for (int col = 0; col < cols; col++) {
                int x = startX + (BRICK_WIDTH + BRICK_SPACING) * col;
                int y = startY + (BRICK_HEIGHT + BRICK_SPACING) * row;
                Brick brick = new Brick(x, y, canvas);
                brick.setFillColor();
                brick.setStrokeColor();
                brick.addToCanvas();
                // brickColors.add(color);
            }
        }        
    }

    // public Color getRowColor(int row){
    //     switch(row){
    //         case 0:
    //         case 1:
    //             return Color.RED;
    //         case 2:
    //         case 3:
    //             return Color.ORANGE;
    //         case 4:
    //         case 5:
    //             return Color.YELLOW;
    //         case 6:
    //         case 7:
    //             return Color.GREEN;
    //         case 8:
    //         case 9:
    //             return Color.CYAN;
    //         default:
    //             return Color.MAGENTA;
    //     }
    // }


    public boolean bricksStillRemain(){
        return brickCount >0;

    }

    public int getNumOfBricks() {
        return brickCount;
    }

    public List<Brick> getBrickList() {
        return bricks;
    }

    public void removeBrickFromList(Ball ball) {
        // if (bricks.contains(brick)) {
        // //     bricks.remove(brick);
            brickCount -= 1;
        // // }

        // if (brickCount <=1) {
        //     brickCount-=1;
        //     System.out.println("Working?");
        //     if (brickCount==0) {
        //         ball.displayWinMessage(canvas); 
                
        //     }
        // }

        // if (brickCount >= 0) {
        //     ball.displayWinMessage(canvas);
        // } else {
        //     brickCount--;
        // }
    }


}

