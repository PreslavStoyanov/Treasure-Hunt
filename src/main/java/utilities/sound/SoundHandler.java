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
    private final Map<Sound, URL> soundUrls;

    public SoundHandler()
    {
        this.soundUrls = new HashMap<>();
        soundUrls.put(MAIN_BACKGROUND_MUSIC, getSoundUrl("main-background-music"));
        soundUrls.put(POWER_UP, getSoundUrl("power-up"));
        soundUrls.put(LEVEL_UP, getSoundUrl("level-up"));
        soundUrls.put(WIN_SOUND, getSoundUrl("win-sound"));
        soundUrls.put(GAMEOVER_SOUND, getSoundUrl("gameover-sound"));
        soundUrls.put(HIT_MONSTER, getSoundUrl("hit-monster"));
        soundUrls.put(RECEIVE_DAMAGE, getSoundUrl("receive-damage"));
        soundUrls.put(SWING_SWORD, getSoundUrl("swing-sword"));
        soundUrls.put(SWING_AXE, getSoundUrl("swing-axe"));
        soundUrls.put(FIREBALL_SOUND, getSoundUrl("fireball-sound"));
        soundUrls.put(MOVE_CURSOR, getSoundUrl("move-cursor"));
        soundUrls.put(GOSSIP, getSoundUrl("gossip"));
        soundUrls.put(OPEN_DOOR, getSoundUrl("open-door"));
        soundUrls.put(TAKE_KEY, getSoundUrl("take-key"));
        soundUrls.put(TAKE_POTION, getSoundUrl("take-potion"));
        soundUrls.put(DRINK_POTION, getSoundUrl("drink-potion"));
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
        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundUrls.get(sound)))
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
