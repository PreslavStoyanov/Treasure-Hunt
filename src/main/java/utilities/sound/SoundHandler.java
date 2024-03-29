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
    private final Map<Sound, URL> sounds = new HashMap<>();
    private FloatControl floatControl;
    public boolean volume;

    public SoundHandler(boolean volume)
    {
        this.volume = volume;
        sounds.put(MAIN_BACKGROUND_MUSIC, getSoundUrl("main-background-music"));
        sounds.put(POWER_UP, getSoundUrl("power-up"));
        sounds.put(LEVEL_UP, getSoundUrl("level-up"));
        sounds.put(WIN_SOUND, getSoundUrl("win-sound"));
        sounds.put(GAMEOVER_SOUND, getSoundUrl("gameover-sound"));
        sounds.put(HIT_MONSTER, getSoundUrl("hit-monster"));
        sounds.put(RECEIVE_DAMAGE, getSoundUrl("receive-damage"));
        sounds.put(SWING_SWORD, getSoundUrl("swing-sword"));
        sounds.put(SWING_AXE, getSoundUrl("swing-axe"));
        sounds.put(FIREBALL_SOUND, getSoundUrl("fireball-sound"));
        sounds.put(SLIMEBALL_SOUND, getSoundUrl("slimeball-sound"));
        sounds.put(MOVE_CURSOR, getSoundUrl("move-cursor"));
        sounds.put(GOSSIP, getSoundUrl("gossip"));
        sounds.put(OPEN_DOOR, getSoundUrl("open-door"));
        sounds.put(TAKE_COIN, getSoundUrl("take-coin"));
        sounds.put(TAKE_KEY, getSoundUrl("take-key"));
        sounds.put(TAKE_POTION, getSoundUrl("take-potion"));
        sounds.put(TAKE_WEAPON, getSoundUrl("take-axe"));
        sounds.put(DRINK_POTION, getSoundUrl("drink-potion"));
        sounds.put(MONKEY_LAUGH, getSoundUrl("monkey-laugh"));
        sounds.put(TREE_CHOP, getSoundUrl("tree-chop"));
        sounds.put(TAKE_BOOTS, getSoundUrl("take-boots"));
    }

    public boolean toggleVolume()
    {
        volume = !volume;
        setVolume(volume);
        return volume;
    }

    public void setVolume(boolean soundVolume)
    {
        floatControl.setValue(soundVolume ? 6F : -80F);
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
            floatControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            setVolume(volume);
        }
        catch (IOException | LineUnavailableException | UnsupportedAudioFileException e)
        {
            throw new RuntimeException("Couldn't load the music file", e);
        }
    }
}
