import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Class to deal with playing music.
 */
public class MusicClass
{
    private Clip clip;
    private boolean musicOn;
    
    public MusicClass()
    {
        musicOn = true;
    }

    /**
     * Returns if music is currently on or off.
     * 
     * @return boolean if music setting is on or not.
     */
    public boolean getMusicOn()
    {
        return musicOn;
    }

    /**
     * Sets music on or off.
     * 
     * @param musicOnOrOff
     */
    public void setMusicBoolean(boolean musicOn)
    {
        this.musicOn = musicOn;
    }

    /**
     * Returns the current clip if any (might be null).
     * 
     * @return
     */
    public Clip getClip()
    {
        return clip;
    }

    /**
     * Method that plays the wav file from the given filepath if the music setting is currently on.
     * 
     * @param filepath string where the wav file to be played is kept.
     */
    public void playMusic(String filepath)
    {
        if (musicOn) {
            try {
                AudioInputStream audio = AudioSystem.getAudioInputStream(new File(filepath));
                clip = AudioSystem.getClip();
                clip.open(audio);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Stops the music currently playing if any is playing.
     */
    public void stopMusic()
    {
        if (clip != null && clip.isActive()) {
            clip.stop();
        }
    }
}