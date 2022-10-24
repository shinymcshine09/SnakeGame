public class SnakeBody implements GameObject
{
    private int previousXAxis;
    private int previousYAxis;
    private String direction;
    private String previousDirection;
    private int x;
    private int y;

    public SnakeBody(int x, int y)
    {
        this.x = x;
        this.y = y;
        direction = "right";
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

    public int getPreviousXAxis()
    {
        return previousXAxis;
    }

    public int getPreviousYAxis()
    {
        return previousYAxis;
    }

    public void setPreviousXAxis(int oldXAxis)
    {
        previousXAxis = oldXAxis;
    }

    public void setPreviousYAxis(int oldYAxis)
    {
        previousYAxis = oldYAxis;
    }

    public void changeXAxis(int newXAxis)
    {
        setPreviousXAxis(getXAxis());
        setXAxis(newXAxis);
    }

    public void changeYAxis(int newYAxis)
    {
        setPreviousYAxis(getYAxis());
        setYAxis(newYAxis);
    }

    public String getDirection()
    {
        return direction;
    }

    public void setDirection(String direction)
    {
        setPreviousDirection();
        this.direction = direction;
    }

    public void setPreviousDirection() {
        previousDirection = this.getDirection();
    }

    public String getPreviousDirection()
    {
        return previousDirection;
    }
}