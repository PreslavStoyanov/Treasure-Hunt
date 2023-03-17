package utilities.sound;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static application.Application.properties;
import static utilities.sound.Sound.*;

public class SoundHandler
{
    private Clip clip;
    private final Map<Sound, URL> sounds;

    public SoundHandler()
    {
        this.sounds = new HashMap<>();
        sounds.put(PLAYBACK, getURL("sounds.playback"));
        sounds.put(COIN, getURL("sounds.coin"));
        sounds.put(POWER_UP, getURL("sounds.power-up"));
        sounds.put(UNLOCK, getURL("sounds.unlock"));
        sounds.put(WIN, getURL("sounds.win-sound"));
        sounds.put(HIT, getURL("sounds.hit"));
        sounds.put(RECEIVE_DAMAGE, getURL("sounds.receive-damage"));
        sounds.put(SWING_SWORD, getURL("sounds.swing-weapon"));
        sounds.put(SWING_AXE, getURL("sounds.swing-weapon"));
        sounds.put(LEVEL_UP, getURL("sounds.level-up"));
        sounds.put(CURSOR, getURL("sounds.cursor"));
    }

    public void playMusic(Sound sound)
    {
        setFile(sound);
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void playSoundEffect(Sound sound)
    {
        setFile(sound);
        clip.start();
    }

    public void stop()
    {
        clip.stop();
    }

    private URL getURL(Object url)
    {
        return getClass().getResource(properties.get(url).toString());
    }

    private void setFile(Sound sound)
    {
        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(sounds.get(sound)))
        {
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        }
        catch (IOException | LineUnavailableException | UnsupportedAudioFileException e)
        {
            throw new RuntimeException("Couldn't load the music file", e);
        }
    }
}
