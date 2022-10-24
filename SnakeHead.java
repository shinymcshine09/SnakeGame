import java.util.ArrayList;
import java.util.List;

/**
 * For creating an instance of a snake head.
 */
public class SnakeHead implements GameObject
{
    private String direction;
    private List<SnakeBody> bodyParts;
    private int previousXAxis;
    private int previousYAxis;
    private String previousDirection;
    private int x;
    private int y;

    public SnakeHead(int x, int y) {
        this.x = x;
        this.y = y;
        direction = "right";
        bodyParts = new ArrayList<>();
        previousXAxis = 0;
        previousYAxis = 0;
        previousDirection = "right";
    }
    
    @Override
    public int getXAxis() {
        return x;
    }

    @Override
    public void setXAxis(int newXAxis) {
        x = newXAxis;
    }

    @Override
    public int getYAxis() {
        return y;
    }

    @Override
    public void setYAxis(int newYAxis) {
        y = newYAxis;
    }

    /**
     * Returns direction of the snake head.
     */
    public String getDirection()
    {
        return direction;
    }

    /**
     * Sets previous direction of the snake head.
     */
    public void setPreviousDirection() {
        previousDirection = this.getDirection();
    }

    public String getPreviousDirection()
    {
        return previousDirection;
    }

    public void setPreviousXAxis()
    {
        previousXAxis = this.getXAxis();
    }

    public void setPreviousYAxis()
    {
        previousYAxis = this.getYAxis();
    }

    public int getPreviousXAxis()
    {
        return previousXAxis;
    }

    public int getPreviousYAxis()
    {
        return previousYAxis;
    }
    
    /**
     * Returns the body parts of the snake apart from the head.
     * 
     * @return list of snake body parts.
     */
    public List<SnakeBody> getBodyPartsList()
    {
        return bodyParts;
    }

    /**
     * adds one body part to the body parts list.
     */
    public void addBodyPart()
    {
        if (bodyParts.size() == 0) {
            SnakeBody newBody = new SnakeBody(getPreviousXAxis(), getPreviousYAxis());
            newBody.setDirection(getDirection());
            bodyParts.add(newBody);
        }
        else {
            SnakeBody newBody = new SnakeBody(bodyParts.get(bodyParts.size() - 1).getPreviousXAxis(), bodyParts.get(bodyParts.size() - 1).getPreviousYAxis());
            newBody.setDirection(bodyParts.get(bodyParts.size() - 1).getPreviousDirection());
            bodyParts.add(newBody);
        }
    }

    /**
     * Changes the axis of all the body parts so that they follow the snake head.
     */
    public void moveBodyParts()
    {
        for (int i = 0 ; i < bodyParts.size() ; i++) {
            if (i == 0) {
                bodyParts.get(i).changeXAxis(getPreviousXAxis());
                bodyParts.get(i).changeYAxis(getPreviousYAxis());
                bodyParts.get(i).setDirection(getDirection());
            }

            else {
                bodyParts.get(i).changeXAxis(bodyParts.get(i - 1).getPreviousXAxis());
                bodyParts.get(i).changeYAxis(bodyParts.get(i - 1).getPreviousYAxis());
                bodyParts.get(i).setDirection(bodyParts.get(i - 1).getPreviousDirection());
            }
        }
    }
    
    /**
     * Changes the dirrection of the snake as long as it is not going back on itself.
     * 
     * @param newDirection string of the new direction
     * @param squareSize size of a square on screen.
     */
    public void changeDirection(String newDirection, int squareSize)
    {
        setPreviousDirection();
        if (newDirection.equals("right") && !getDirection().equals("left") 
                && this.getXAxis() + squareSize != bodyParts.get(0).getXAxis() 
                && this.getYAxis() != bodyParts.get(0).getYAxis()) {
            direction = newDirection;
        }
        else if (newDirection.equals("left") && !getDirection().equals("right")
                && this.getXAxis() - squareSize != bodyParts.get(0).getXAxis() 
                && this.getYAxis() != bodyParts.get(0).getYAxis()) {
            direction = newDirection;
        }
        else if (newDirection.equals("up") && !getDirection().equals("down")
                && this.getYAxis() - squareSize != bodyParts.get(0).getYAxis() 
                && this.getXAxis() != bodyParts.get(0).getXAxis()) {
            direction = newDirection;
        }
        else if (newDirection.equals("down") && !getDirection().equals("up")
                && this.getYAxis() + squareSize != bodyParts.get(0).getYAxis() 
                && this.getXAxis() != bodyParts.get(0).getXAxis()) {
            direction = newDirection;
        }
    }

    /**
     * Changes the x axis as long as to the given parameters according to current game sizes.
     * 
     * @param newXAxis
     * @param gameAreaSize
     * @param squareSize
     */
    public void changeXAxis(int newXAxis, int gameAreaSize, int squareSize)
    {
        if (newXAxis == bodyParts.get(0).getXAxis() && getYAxis() == bodyParts.get(0).getYAxis()) {
            //changeYAxis(newXAxis, gameAreaSize, squareSize);
        }
        else if (newXAxis >= gameAreaSize && newXAxis > 0) {
           setXAxis(0); 
        }
        else if (newXAxis < 0 && newXAxis < gameAreaSize) {
            setXAxis(gameAreaSize - squareSize);
        }
        else {
            setXAxis(newXAxis);
        }
    }

    /**
     * Changes the y axis as long as to the given parameters according to current game sizes.
     * 
     * @param newYAxis
     * @param gameAreaSize
     * @param squareSize
     */
    public void changeYAxis(int newYAxis, int gameAreaSize, int squareSize)
    {
        if (newYAxis == bodyParts.get(0).getYAxis() && getXAxis() == bodyParts.get(0).getXAxis()) {
            //changeYAxis(newYAxis, gameAreaSize, squareSize);
        }
        else if (newYAxis >= gameAreaSize && newYAxis > 0) {
            setYAxis(0);
        }
        else if (newYAxis < gameAreaSize && newYAxis < 0) {
            setYAxis(gameAreaSize - squareSize);
        }
        else {
            setYAxis(newYAxis);
        }
    }

    public Boolean checkIfBodyTouching()
    {
        for (SnakeBody bodyPart : bodyParts) {
            if (this.getXAxis() == bodyPart.getXAxis() && this.getYAxis() == bodyPart.getYAxis()) {
                return true;
            }
        }
        return false;
    }
}