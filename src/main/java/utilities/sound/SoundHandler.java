package utilities.sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;
import java.util.Map;

import static View.Main.properties;
import static utilities.sound.Sound.*;

public class SoundHandler
{
    private Clip clip;
    private final Map<Sound, URL> sounds;

    public SoundHandler()
    {
        sounds = Map.of(
                PLAYBACK, getURL("sounds.playback"),
                COIN, getURL("sounds.coin"),
                POWER_UP, getURL("sounds.power-up"),
                UNLOCK, getURL("sounds.unlock"),
                WIN, getURL("sounds.win-sound"),
                HIT, getURL("sounds.hit"),
                RECEIVE_DAMAGE, getURL("sounds.receive-damage"),
                SWING_WEAPON, getURL("sounds.swing-weapon"),
                LEVEL_UP, getURL("sounds.level-up"));
    }

    private URL getURL(Object url)
    {
        return getClass().getResource(properties.get(url).toString());
    }

    public void setFile(Sound sound)
    {
        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(sounds.get(sound)))
        {
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public void play()
    {
        clip.start();
    }

    public void loop()
    {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop()
    {
        clip.stop();
    }
}
