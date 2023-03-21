package utilities.sound;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static application.Application.soundsUrls;
import static utilities.sound.Sound.*;

public class SoundHandler
{
    private Clip clip;
    private final Map<Sound, URL> sounds;

    public SoundHandler()
    {
        this.sounds = new HashMap<>();
        sounds.put(PLAYBACK, getSoundUrl("playback"));
        sounds.put(COIN, getSoundUrl("coin"));
        sounds.put(POWER_UP, getSoundUrl("power-up"));
        sounds.put(UNLOCK, getSoundUrl("unlock"));
        sounds.put(WIN, getSoundUrl("win-sound"));
        sounds.put(GAME_OVER, getSoundUrl("gameover"));
        sounds.put(HIT, getSoundUrl("hit"));
        sounds.put(RECEIVE_DAMAGE, getSoundUrl("receive-damage"));
        sounds.put(SWING_SWORD, getSoundUrl("swing-sword"));
        sounds.put(SWING_AXE, getSoundUrl("swing-axe"));
        sounds.put(FIREBALL_SOUND, getSoundUrl("fireball-sound"));
        sounds.put(LEVEL_UP, getSoundUrl("level-up"));
        sounds.put(CURSOR, getSoundUrl("cursor"));
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

    private URL getSoundUrl(Object url)
    {
        return getClass().getResource(soundsUrls.get(url).toString());
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
