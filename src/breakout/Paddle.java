// Yeshe Jangchup
// The Paddle class creates the rectangle paddle shape
// Acknowledements: Tenzin

package breakout;

import edu.macalester.graphics.Rectangle;

/*
 * Creates a rectangular paddle shape
 */
public class Paddle extends Rectangle{
    private static final double WIDTH = 90;
    private static final double HEIGHT = 10;

    public Paddle(double canvasWidth, double canvasHeight){
        super((canvasWidth - WIDTH), (canvasHeight - HEIGHT) - 20, WIDTH, HEIGHT);

    }
}
