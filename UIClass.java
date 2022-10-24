import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;

import java.awt.GridLayout;
import java.util.Hashtable;

public class UIClass extends JFrame
{
    private ListenerClass newListener;
    private SnakeGame snakeGame;
    private MusicClass musicClass;
    JFrame gameOverFrame = new JFrame();

    public UIClass(SnakeGame snakeGame, MusicClass musicClass, ListenerClass newListener)
    {
        this.snakeGame = snakeGame;
        this.musicClass = musicClass;
        this.newListener = newListener;
    }

    public void closeGameOverWindow()
    {
        gameOverFrame.dispose();
    }

    /**
     * Creates the first opening game menu.
     */
    public void createGameMenu()
    {
        musicClass.playMusic("./resources/snakemusicmenu.wav");
        GridLayout newLayout = new GridLayout(3, 0);
        JFrame menuFrame = new JFrame("Snake");
        menuFrame.setLayout(newLayout);
        menuFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JButton startButton = new JButton("New Game");
        startButton.addActionListener(newListener);
        JButton optionsButton = new JButton("Options");
        optionsButton.addActionListener(newListener);
        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(newListener);
        menuFrame.add(startButton);
        menuFrame.add(optionsButton);
        menuFrame.add(quitButton);
        menuFrame.setSize(300, 300);
        menuFrame.setLocationRelativeTo(null);
        menuFrame.setVisible(true);
    }

    /**
     * Creates the options JFrame menu.
     */
    public void createOptionsMenu()
    {
        JFrame optionsFrame = new JFrame("Options");
        GridLayout newLayout = new GridLayout(6,0);
        optionsFrame.setLayout(newLayout);
        optionsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JSlider speed = new JSlider(JSlider.HORIZONTAL, 200, 400, 600 - snakeGame.getGameSpeed());
        speed.setMajorTickSpacing(100);
        speed.setMinorTickSpacing(10);
        speed.setPaintTicks(true);
        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
        labelTable.put(200, new JLabel("Slow Speed"));
        labelTable.put(300, new JLabel("Normal Speed"));
        labelTable.put(400, new JLabel("Fast Speed"));
        speed.setLabelTable(labelTable);
        speed.setPaintLabels(true);
        speed.addChangeListener(newListener);
        optionsFrame.setSize(500, 300);
        optionsFrame.add(speed);
        JCheckBox musicButton = new JCheckBox("Game Music on/off");
        if (musicClass.getMusicOn()) {
            musicButton.setSelected(true);
        }
        else {
            musicButton.setSelected(false);
        }
        musicButton.addChangeListener(newListener);
        JCheckBox gridButton = new JCheckBox("Stabilisers on/off");
        if (snakeGame.getStabilisers()) {
            gridButton.setSelected(true);
        }
        else {
            gridButton.setSelected(false);
        }
        gridButton.addChangeListener(newListener);
        JCheckBox smallGameArea = new JCheckBox("Short Game");
        smallGameArea.addChangeListener(newListener);
        JCheckBox mediumGameArea = new JCheckBox("Medium Game");
        mediumGameArea.addChangeListener(newListener);
        JCheckBox largeGameArea = new JCheckBox("Long Game");
        largeGameArea.addChangeListener(newListener);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(smallGameArea);
        buttonGroup.add(mediumGameArea);
        buttonGroup.add(largeGameArea);
        if (snakeGame.getNumberOfSquares() == 12) {
            mediumGameArea.setSelected(true);
        }
        else if (snakeGame.getNumberOfSquares() == 16) {
            largeGameArea.setSelected(true);
        }
        else if (snakeGame.getNumberOfSquares() == 8) {
            smallGameArea.setSelected(true);
        }
        optionsFrame.add(musicButton);
        optionsFrame.add(gridButton);
        optionsFrame.add(smallGameArea);
        optionsFrame.add(mediumGameArea);
        optionsFrame.add(largeGameArea);
        optionsFrame.setLocationRelativeTo(null);
        optionsFrame.setVisible(true);
    }

    /**
     * Creates a game over window with a game over messege.
     */
    public void createGameOverFrame()
    {
        gameOverFrame = new JFrame();
        JLabel endGameLabel = new JLabel("You can't eat yourself!!");
        GridLayout newLayout = new GridLayout(2, 0);
        gameOverFrame.setLayout(newLayout);
        endGameLabel.setHorizontalAlignment(JLabel.CENTER);
        JButton okButton = new JButton("Ok");
        okButton.addActionListener(newListener);
        gameOverFrame.setSize(300, 100);
        gameOverFrame.add(endGameLabel);
        gameOverFrame.add(okButton);
        gameOverFrame.setLocationRelativeTo(null);
        gameOverFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gameOverFrame.setVisible(true);
    }
}