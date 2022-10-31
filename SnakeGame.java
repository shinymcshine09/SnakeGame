import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import java.util.*;

import javax.swing.*;

/**
 * Main class of the snake game deals with movement, game settings and painting graphics.
 * Run the main method to play.
 */
public class SnakeGame extends JPanel
{
    private JFrame newFrame;
    private SnakeHead snakeHead;
    private List<SnakeBody> snakeBodyParts;
    private Food food;
    private SpecialFood specialFood;
    private int score;
    private int gameSpeed;
    private int gameSpeedAtStartOfGame;
    private int gameAreaSize;
    private int squareSize;
    private int numberOfSquares;
    private int timer;
    private ImageImporter imageImporter;
    private MusicClass musicClass;
    private UIClass newUI;
    private ListenerClass newListener;
    private boolean gameRunning;
    private boolean stabilisers;
    private boolean specialFoodActive;

    public static void main(String[] args) {
        SnakeGame newGame = new SnakeGame();
    }

    public SnakeGame() {
        imageImporter = new ImageImporter();
        gameSpeed = 300;
        musicClass = new MusicClass();
        stabilisers = true;
        specialFoodActive = false;
        food = new Food(0, 0);
        specialFood = new SpecialFood(0, 0);
        numberOfSquares = 12;
        setScreenAndSquareSize();
        snakeHead = new SnakeHead(((gameAreaSize / squareSize) / 2) * squareSize, ((gameAreaSize / squareSize) / 2) * squareSize);
        newListener = new ListenerClass(this, musicClass, snakeHead, squareSize);
        newUI = new UIClass(this, musicClass, newListener);
        newUI.createGameMenu();
    }

    /**
     * Sets the number of square on both axis in the game area.
     * 
     * @param numberOfSquares new number of squares on both axis.
     */
    public void setNumberOfSquares(int numberOfSquares)
    {
        this.numberOfSquares = numberOfSquares;
    }

    public int getNumberOfSquares()
    {
        return numberOfSquares;
    }

    /**
     * Sets the speed of the game.
     * 
     * @param gameSpeed new game speed.
     */
    public void setGameSpeed(int gameSpeed)
    {
        this. gameSpeed = gameSpeed;
    }


    public SnakeHead getSnakeHead()
    {
        return snakeHead;
    }

    /**
     * Sets the screen and square size based on the number of vertical pixels.
     */
    public void setScreenAndSquareSize()
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        gameAreaSize = (int)screenSize.getHeight() - 200;
        squareSize = gameAreaSize / numberOfSquares;
        while (!(gameAreaSize % squareSize == 0)) {
            gameAreaSize = gameAreaSize - 1;
        }
    }

    /**
     * Sets the isGameStillGoing variable to the given boolean. 
     * 
     * @param isGameStillGoing new gameStillGoing definition.
     */
    public void setGameStillRunning(boolean isGameRunning)
    {
        gameRunning = isGameRunning;
    }

    public JFrame getGameFrame()
    {
        return newFrame;
    }

    public int getGameSpeed()
    {
        return gameSpeed;
    }

    public int getScore()
    {
        return score;
    }

    public int getGameAreaSize()
    {
        return gameAreaSize;
    }

    public int getSquareSize()
    {
        return squareSize;
    }

    public UIClass getNewUI()
    {
        return newUI;
    }

    public void setStabilisers(boolean stabiliserCondition)
    {
        stabilisers = stabiliserCondition;
    }

    public boolean getStabilisers()
    {
        return stabilisers;
    }

    /**
     * Resets game settings to whatever is selected in the options menu.
     */
    public void resetGame()
    {
        if (gameSpeedAtStartOfGame != 0) {
            gameSpeed = gameSpeedAtStartOfGame;
        }
        
        score = 0;
    }

    /**
     * Sets up and begins a new game.
     */
    public void startNewGame()
    {
        gameRunning = true;
        gameSpeedAtStartOfGame = gameSpeed;
        setScreenAndSquareSize();
        snakeHead = new SnakeHead(((gameAreaSize / squareSize) / 2) * squareSize, ((gameAreaSize / squareSize) / 2) * squareSize);
        snakeHead.addBodyPart();
        newListener.changeSnakeHead(snakeHead);
        snakeBodyParts = snakeHead.getBodyPartsList();
        createGameArea();
        food = food.createNewFood(gameAreaSize, squareSize);
        
        // Stops awt thread exception by opening a new thread.
        (new Thread() {
        public void run() {
            while (gameRunning) {
                try {
                    movement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        }).start();
    }
    
    /**
     * Creates a new game area.
     */
    public void createGameArea()
    {
        newFrame = new JFrame("Snake, Score: " + score);
        newFrame.addWindowListener(newListener);
        newFrame.setSize(gameAreaSize, gameAreaSize + 28);
        this.setSize(gameAreaSize, gameAreaSize);
        Color background = new Color(186, 154, 50);
        this.setBackground(background);
        newFrame.add(this);
        newFrame.addKeyListener(newListener);
        newFrame.setLocationRelativeTo(null);
        newFrame.setVisible(true);
        if (musicClass.getClip().isActive()) {
            musicClass.stopMusic();
        }
        musicClass.playMusic("./resources/snakemusicmain.wav");
    }

    /**
     * Paints the game area drawing all game objects.
     */
    @Override
    public void paintComponent(Graphics g) {
        if (stabilisers) {
            // Draws the rows of the game grid.
            for (int rows = 1; rows < gameAreaSize; rows++) {
                g.drawLine(0, rows * squareSize, gameAreaSize, rows * squareSize);
            }
            // Draws the columns of the game grid.
            for (int columns = 1; columns < gameAreaSize; columns++) {
                g.drawLine(columns * squareSize, 0, columns * squareSize, gameAreaSize);
            }
        }

        // Draws snake bodyparts according to the current coordinates.
        for (int i = 0 ; i < snakeBodyParts.size() ; i++) {
            if (i == snakeBodyParts.size() - 1) {
                switch (snakeBodyParts.get(i).getDirection()) {
                    case "left":
                        g.drawImage(imageImporter.getSnakeTailLeft(), snakeBodyParts.get(i).getXAxis(), snakeBodyParts.get(i).getYAxis(), squareSize, squareSize,  null);
                        break;
                    case "right":
                        g.drawImage(imageImporter.getSnakeTailRight(), snakeBodyParts.get(i).getXAxis(), snakeBodyParts.get(i).getYAxis(), squareSize, squareSize,  null);
                        break;
                    case "up":
                        g.drawImage(imageImporter.getSnakeTailUp(), snakeBodyParts.get(i).getXAxis(), snakeBodyParts.get(i).getYAxis(), squareSize, squareSize,  null);
                        break;
                    case "down":
                        g.drawImage(imageImporter.getSnakeTailDown(), snakeBodyParts.get(i).getXAxis(), snakeBodyParts.get(i).getYAxis(), squareSize, squareSize,  null);
                        break;
                }
            }
            else {
                switch (snakeBodyParts.get(i).getDirection()) {
                    case "left":
                        g.drawImage(imageImporter.getSnakeBodyLeft(), snakeBodyParts.get(i).getXAxis(), snakeBodyParts.get(i).getYAxis(), squareSize, squareSize,  null);
                        break;
                    case "right":
                        g.drawImage(imageImporter.getSnakeBodyRight(), snakeBodyParts.get(i).getXAxis(), snakeBodyParts.get(i).getYAxis(), squareSize, squareSize,  null);
                        break;
                    case "up":
                        g.drawImage(imageImporter.getSnakeBodyUp(), snakeBodyParts.get(i).getXAxis(), snakeBodyParts.get(i).getYAxis(), squareSize, squareSize,  null);
                        break;
                    case "down":
                        g.drawImage(imageImporter.getSnakeBodyDown(), snakeBodyParts.get(i).getXAxis(), snakeBodyParts.get(i).getYAxis(), squareSize, squareSize,  null);
                        break;
                }
            }
        }
        if (score % 150 == 0 && score != 0 && timer < 60) {
            specialFoodActive = true;
            g.drawImage(imageImporter.getSpecialFood(), specialFood.getXAxis(), specialFood.getYAxis(), squareSize, squareSize, null);
        }
        else {
            specialFoodActive = false;
        }
        // Draws food.
        g.drawImage(imageImporter.getFood(), food.getXAxis(), food.getYAxis(), squareSize, squareSize, null);
        // Draws snake head.
        switch (snakeHead.getDirection()) {
            case "left":
                g.drawImage(imageImporter.getSnakeHeadLeft(), snakeHead.getXAxis(), snakeHead.getYAxis(), squareSize, squareSize,  null);
                break;
            case "right":
                g.drawImage(imageImporter.getSnakeHeadRight(), snakeHead.getXAxis(), snakeHead.getYAxis(), squareSize, squareSize,  null);
                break;
            case "up":
                g.drawImage(imageImporter.getSnakeHeadUp(), snakeHead.getXAxis(), snakeHead.getYAxis(), squareSize, squareSize,  null);
                break;
            case "down":
                g.drawImage(imageImporter.getSnakeHeadDown(), snakeHead.getXAxis(), snakeHead.getYAxis(), squareSize, squareSize,  null);
                break;
        }
    }

    /**
     * Method that deals with moving the different objects during a game.
     * 
     * @throws InterruptedException
     */
    public void movement() throws InterruptedException
    {
        while (gameRunning) {
            snakeHead.setPreviousXAxis();
            snakeHead.setPreviousYAxis();
            // Changes axis of snake head according to the snake heads direction.
            switch (snakeHead.getDirection()) {
                case "right":
                    snakeHead.changeXAxis(snakeHead.getXAxis() + squareSize, gameAreaSize, squareSize);
                    break;
                case "left":
                    snakeHead.changeXAxis(snakeHead.getXAxis() - squareSize, gameAreaSize, squareSize);
                    break;
                case "up":
                    snakeHead.changeYAxis(snakeHead.getYAxis() - squareSize, gameAreaSize, squareSize);
                    break;
                case "down":
                    snakeHead.changeYAxis(snakeHead.getYAxis() + squareSize, gameAreaSize, squareSize);
                    break;
            }
            
            snakeHead.moveBodyParts();
            
            if (checkIfEaten(food)) {
                snakeHead.addBodyPart();
                food = food.createNewFood(gameAreaSize, squareSize);
                score += 10;
                // Every 100 points game speed increases.
                if (score % 100 == 0) {
                    gameSpeed -= 25;
                }
                newFrame.setTitle("Snake, Score: " + score);
            }

            if (checkIfEaten(specialFood) && specialFoodActive) {
                snakeHead.addBodyPart();
                specialFood = specialFood.createSpecialFood(gameAreaSize, squareSize);
                score += 30;
                gameSpeed += 50;
                newFrame.setTitle("Snake, Score: " + score);
            }

            while (ifFoodTouchingSnake(food) || (specialFood.getXAxis() == food.getXAxis() && specialFood.getYAxis() == food.getYAxis())) {
                food = food.createNewFood(gameAreaSize, squareSize);
            }

            while (ifFoodTouchingSnake(specialFood) || (specialFood.getXAxis() == food.getXAxis() && specialFood.getYAxis() == food.getYAxis())) {
                specialFood = specialFood.createSpecialFood(gameAreaSize, squareSize);
            }

            if (snakeHead.checkIfBodyTouching()) {
                newUI.createGameOverFrame();
                resetGame();
                gameRunning = false;
            }
            
            repaint();

            if (score % 250 == 0) {
                timer += 1;
            }
            else {
                timer = 0;
            }

            Thread.sleep(gameSpeed);

        }
    }

    /**
     * Checks if a newly created food item is in the same coordinates as a part of the snake.
     * 
     * @return true if a food item is on a snake and false if not.
     */
    public Boolean ifFoodTouchingSnake(Food foodObject)
    {
        for (SnakeBody bodyPart : snakeBodyParts) {
            if (bodyPart.getXAxis() == foodObject.getXAxis() && bodyPart.getYAxis() == foodObject.getYAxis()) {
                return true;
            }
        }
        if (checkIfEaten(foodObject)) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the snake head is in the same coordinates as the current food item.
     * 
     * @return true if snake in the same position as the food and false if not.
     */
    public Boolean checkIfEaten(Food foodObject)
    {
        if ((snakeHead.getXAxis()) == foodObject.getXAxis() &&
                (snakeHead.getYAxis()) == foodObject.getYAxis()) {
            return true;
        }
        return false;
    }
}