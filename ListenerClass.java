import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JCheckBox;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class to implement all the listener interfaces needed for the game.
 */
public class ListenerClass implements KeyListener, WindowListener, ActionListener, ChangeListener
{
    private SnakeGame snakeGame;
    private SnakeHead snakeHead;
    private int squareSize;
    private MusicClass musicClass;

    /**
     * Constructor to pass all required variables.
     * 
     * @param snakeGame the game this is for.
     * @param musicClass the music the game is using.
     * @param snakeHead the snake head in the current game.
     * @param squareSize square size in the current game.
     */
    public ListenerClass(SnakeGame snakeGame, MusicClass musicClass, SnakeHead snakeHead, int squareSize)
    {
        this.snakeGame = snakeGame;
        this.snakeHead = snakeHead;
        this.squareSize = squareSize;
        this.musicClass = musicClass;
    }

    /**
     * Allows you to change the snakehead passed to this class for new games.
     */
    public void changeSnakeHead(SnakeHead snakeHead)
    {
        this.snakeHead = snakeHead;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    /**
     * Changes direction of the snake depending on keys pressed.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            // left arrow
            case 37:
                snakeHead.changeDirection("left", squareSize);
                break;
            // a
            case 65:
                snakeHead.changeDirection("left", squareSize);
                break;
            // up arrow
            case 38:
                snakeHead.changeDirection("up", squareSize);
                break;
            // w
            case 87:
                snakeHead.changeDirection("up", squareSize);
                break;
            // right arrow
            case 39:
                snakeHead.changeDirection("right", squareSize);
                break;
            // d
            case 68:
                snakeHead.changeDirection("right", squareSize);
                break;
            // down arrow
            case 40:
                snakeHead.changeDirection("down", squareSize);
                break;
            // s
            case 83:
                snakeHead.changeDirection("down", squareSize);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void windowOpened(WindowEvent e) {
        
    }

    /**
     * Stops the music when the game window closes.
     */
    @Override
    public void windowClosing(WindowEvent e) {
        snakeGame.setGameStillRunning(false);
        if (musicClass.getMusicOn()) {
            musicClass.getClip().stop();
        }
        musicClass.playMusic("./resources/snakemusicmenu.wav");
    }

    @Override
    public void windowClosed(WindowEvent e) {
        
    }

    @Override
    public void windowIconified(WindowEvent e) {
        
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        
    }

    @Override
    public void windowActivated(WindowEvent e) {
        
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("New Game")) {
            snakeGame.startNewGame();
        }
        else if (e.getActionCommand().equals("Options")) {
            snakeGame.getNewUI().createOptionsMenu();
        }
        else if (e.getActionCommand().equals("Quit")) {
            System.exit(0);
        }
        else if (e.getActionCommand().equals("Ok")) {
            snakeGame.getNewUI().closeGameOverWindow();
            snakeGame.getGameFrame().dispose();
            musicClass.stopMusic();
            musicClass.playMusic("./resources/snakemusicmenu.wav");
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();
        if (source instanceof JCheckBox) {
            JCheckBox eCheckBox = (JCheckBox) source;
            if (eCheckBox.getActionCommand().equals("Game Music on/off")) {
                if (eCheckBox.isSelected()) {
                    musicClass.setMusicBoolean(true);
                }
                else if (!eCheckBox.isSelected()) {
                    musicClass.setMusicBoolean(false);
                }
            }
            else if (eCheckBox.getActionCommand().equals("Stabilisers on/off")) {
                if (eCheckBox.isSelected()) {
                    snakeGame.setStabilisers(true);
                }
                else {
                    snakeGame.setStabilisers(false);
                }
            }
            else if (eCheckBox.getActionCommand().equals("Short Game")) {
                snakeGame.setNumberOfSquares(8);
            }
            else if (eCheckBox.getActionCommand().equals("Medium Game")) {
                snakeGame.setNumberOfSquares(12);
            }
            else if (eCheckBox.getActionCommand().equals("Long Game")) {
                snakeGame.setNumberOfSquares(16);
            }
        }
        if (source instanceof JSlider) {
            JSlider eSlider = (JSlider) source;
            if (!eSlider.getValueIsAdjusting()) {
                snakeGame.setGameSpeed(400 - (eSlider.getValue() - 200));
            }
        }
    }
}