/**
 * Interface with essential method headers for any game 
 * object in the game area. Makes it easier when creating
 * new game objects
 */
public interface GameObject
{

    public int getXAxis();

    public void setXAxis(int newXAxis);
    
    public int getYAxis();

    public void setYAxis(int newYAxis);
}