public class SpecialFood extends Food
{

    public SpecialFood(int x, int y) {
        super(x, y);
    }

    public SpecialFood createSpecialFood(int gameAreaSize, int squareSize)
    {
        Food newFood = super.createNewFood(gameAreaSize, squareSize);
        int newXAxis = newFood.getXAxis();
        int newYAxis = newFood.getYAxis();
        return new SpecialFood(newXAxis, newYAxis);
    }
}