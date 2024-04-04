package breakout;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.geom.Rectangle2D;

public class Brick{

    private Rectangle brick;

    private CanvasWindow canvas;
    Color NEW_COLOR = new Color(220, 200, 250);


    public Brick(double x, double y, CanvasWindow canvas){
        brick = new Rectangle(x, y, BrickHandler.BRICK_WIDTH, BrickHandler.BRICK_HEIGHT);
        this.canvas=canvas;
    }

    public void setFillColor() {
        brick.setFillColor(NEW_COLOR);
    }

    public void setStrokeColor() {
        brick.setStrokeColor(Color.BLACK);
    }

    public void addToCanvas() {
        canvas.add(brick);
    }

    public void removeFromCanvas(){
        canvas.remove(brick);
    }


}

