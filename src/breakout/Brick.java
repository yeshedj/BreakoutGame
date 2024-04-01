package breakout;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

public class Brick extends GraphicsGroup{
    private static final int WIDTH = 50;
    private static final int HEIGHT = 10;
    private static final int SPACING = 5;
    private List<Rectangle> bricks;

    private List<Color> brickColors;
    private CanvasWindow canvas;

    public Brick(Color color, double x, double y, CanvasWindow canvas){
        bricks = new ArrayList<>();
        brickColors = new ArrayList<>();
        this.canvas=canvas;
        createBricks();
    }

    public void createBricks() {
        int rows = 10;
        int cols = 10;
        int startX = 25; 
        int startY = 50; 

        for (int row = 0; row < rows; row++) {
            Color color = getRowColor(row);

            for (int col = 0; col < cols; col++) {
                int x = startX + (WIDTH + SPACING) * col;
                int y = startY + (HEIGHT + SPACING) * row;
                Rectangle brick = new Rectangle(x, y, WIDTH, HEIGHT);
                brick.setFillColor(color);
                brick.setStrokeColor(color);
                bricks.add(brick);
                canvas.add(brick);
                brickColors.add(color);
            }
        }
        // return bricks;
        
    }
    public Color getRowColor(int row){
        switch(row){
            case 0:
            case 1:
                return Color.RED;
            case 2:
            case 3:
                return Color.ORANGE;
            case 4:
            case 5:
                return Color.YELLOW;
            case 6:
            case 7:
                return Color.GREEN;
            case 8:
            case 9:
                return Color.CYAN;
            default:
                return Color.MAGENTA;
        }
    }

    public void addToCanvas(CanvasWindow canvas){
        for(Rectangle brick : bricks){
            canvas.add(brick);
        }
    }

    public void removeFromCanvas(){
        for(Rectangle brick:bricks){
            canvas.remove(brick);
        }
    }

}
