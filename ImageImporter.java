import java.io.*;

import javax.imageio.ImageIO;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Class for importing the game images.
 */
public class ImageImporter
{
    private BufferedImage snakeHeadImageLeft;
    private BufferedImage snakeHeadImageRight;
    private BufferedImage snakeHeadImageUp;
    private BufferedImage snakeHeadImageDown;
    private BufferedImage snakeBodyImageLeft;
    private BufferedImage snakeBodyImageRight;
    private BufferedImage snakeBodyImageUp;
    private BufferedImage snakeBodyImageDown;
    private BufferedImage snakeTailImageRight;
    private BufferedImage snakeTailImageLeft;
    private BufferedImage snakeTailImageUp;
    private BufferedImage snakeTailImageDown;
    private BufferedImage food;
    private BufferedImage specialFood;

    /**
     * Imports the images needed for the game.
     */
    public ImageImporter()
    {
        try {
            snakeHeadImageRight = ImageIO.read(new File("./resources/snakehead.png"));
            snakeBodyImageRight = ImageIO.read(new File("./resources/snakebody.png"));
            snakeTailImageRight = ImageIO.read(new File("./resources/snaketail.png"));
            food = ImageIO.read(new File("./resources/normalfood.png"));
            specialFood = ImageIO.read(new File("./resources/specialfood.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        snakeHeadImageLeft = rotateImage(snakeHeadImageRight, 180);
        snakeHeadImageUp = rotateImage(snakeHeadImageRight, -90);
        snakeHeadImageDown = rotateImage(snakeHeadImageRight, 90);

        snakeBodyImageLeft = rotateImage(snakeBodyImageRight, 180);
        snakeBodyImageUp = rotateImage(snakeBodyImageRight, -90);
        snakeBodyImageDown = rotateImage(snakeBodyImageRight, 90);

        snakeTailImageLeft = rotateImage(snakeTailImageRight, 180);
        snakeTailImageUp = rotateImage(snakeTailImageRight, -90);
        snakeTailImageDown = rotateImage(snakeTailImageRight, 90);
    }

    /**
     * Rotates a given image by the given angle.
     * 
     * @param image image that you want to rotate.
     * @param angle angle you want the image to be rotated.
     * @return rotated image as a BufferedImage.
     */
    public BufferedImage rotateImage(BufferedImage image, int angle) {
        int w = image.getWidth();    
        int h = image.getHeight();

        BufferedImage rotated = new BufferedImage(w, h, image.getType());  
        Graphics2D graphic = rotated.createGraphics();
        graphic.rotate(Math.toRadians(angle), w/2, h/2);
        graphic.drawImage(image, null, 0, 0);
        graphic.dispose();
        return rotated;
    }

    /**
     * Returns a left facing snake head.
     * 
     * @return
     */
    public BufferedImage getSnakeHeadLeft()
    {
        return snakeHeadImageLeft;
    }

    /**
     * Returns a right facing snake head.
     * 
     * @return
     */
    public BufferedImage getSnakeHeadRight()
    {
        return snakeHeadImageRight;
    }

    /**
     * Returns a upward facing snake head.
     * @return
     */
    public BufferedImage getSnakeHeadUp()
    {
        return snakeHeadImageUp;
    }

    /**
     * Returns a downward facing snake head.
     * 
     * @return
     */
    public BufferedImage getSnakeHeadDown()
    {
        return snakeHeadImageDown;
    }

    /**
     * Returns a upward facing snake body.
     * 
     * @return
     */
    public BufferedImage getSnakeBodyUp()
    {
        return snakeBodyImageUp;
    }

    /**
     * Returns a downward facing snake body.
     * 
     * @return
     */
    public BufferedImage getSnakeBodyDown()
    {
        return snakeBodyImageDown;
    }

    /**
     * Returns a right facing snake body.
     * @return
     */
    public BufferedImage getSnakeBodyRight()
    {
        return snakeBodyImageRight;
    }

    /**
     * Returns a left facing snake body.
     * @return
     */
    public BufferedImage getSnakeBodyLeft()
    {
        return snakeBodyImageLeft;
    }

    /**
     * Returns a upward facing snake tail.
     * @return
     */
    public BufferedImage getSnakeTailUp()
    {
        return snakeTailImageUp;
    }

    /**
     * Returns a downwards facing snake tail.
     * @return
     */
    public BufferedImage getSnakeTailDown()
    {
        return snakeTailImageDown;
    }

    /**
     * Returns a right facing snake tail.
     * @return
     */
    public BufferedImage getSnakeTailRight()
    {
        return snakeTailImageRight;
    }

    /**
     * Returns a left facing snake tail.
     * @return
     */
    public BufferedImage getSnakeTailLeft()
    {
        return snakeTailImageLeft;
    }

    /**
     * Returns the food image.
     * @return
     */
    public BufferedImage getFood()
    {
        return food;
    }

    public BufferedImage getSpecialFood()
    {
        return specialFood;
    }
}