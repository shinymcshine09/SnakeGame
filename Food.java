import java.util.Random;
import java.util.TreeMap;

/**
 * Class to create instances of food in the game.
 */
public class Food implements GameObject
{
    private Random ran;
    private int x;
    private int y;
    
    public Food(int x, int y)
    {
        this.x = x;
        this.y = y;
        ran = new Random();
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
     * Method to create a new food item with a new x and y axis.
     * 
     * @param gameAreaSize
     * @param squareSize
     * @return
     */
    public Food createNewFood(int gameAreaSize, int squareSize)
    {
        int x = ran.nextInt(1000000);
        int y = ran.nextInt(1000000);
        // New square size if pixels x and y axis add up to 1000000.
        int square = (1000000 / gameAreaSize) * squareSize;
        // Links x and y axis' to particular squares in the game in a TreeMap.
        TreeMap<Integer, Integer> locations = new TreeMap<>();
        for (int i = 0 ; i < (gameAreaSize / squareSize) ; i++) {
            locations.put(square * i, i * squareSize);
        }
        // Reduces random x int to nearest square.
        Boolean found = false;
        for (int location : locations.keySet()) {
            if (x < location && !found) {
                while (!(locations.containsKey(x))) {
                    x--;
                }
                found = true;
            }
            
        }
        // If x above lastkey in locations set lastkey as location of x.
        if (!found) {
            x = locations.lastKey();
        }
        // same as above but for y int.
        found = false;
        for (int location : locations.keySet()) {
            if (y < location && !found) {
                while (!(locations.containsKey(y))) {
                    y--;
                }
                found = true;
            }
        }

        if (!found) {
            y = locations.lastKey();
        }

        x = locations.get(x);
        y = locations.get(y);
        
        return new Food(x, y);
    }
}