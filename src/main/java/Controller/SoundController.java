package Controller;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class SoundController {
    Clip clip;
    URL[] soundURL = new URL[30];

    public SoundController() {
        soundURL[0] = getClass().getResource("/sounds/TreasureHuntPlayback.wav");
        soundURL[1] = getClass().getResource("/sounds/coin.wav");
        soundURL[2] = getClass().getResource("/sounds/powerUp.wav");
        soundURL[3] = getClass().getResource("/sounds/unlock.wav");
        soundURL[4] = getClass().getResource("/sounds/winSound.wav");
        soundURL[5] = getClass().getResource("/sounds/hitMonster.wav");
        soundURL[6] = getClass().getResource("/sounds/receiveDamage.wav");
        soundURL[7] = getClass().getResource("/sounds/swingWeapon.wav");
        soundURL[8] = getClass().getResource("/sounds/levelUp.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }
}
