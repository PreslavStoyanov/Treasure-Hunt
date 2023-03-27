package utilities.keyboard;

import application.GamePanel;

import java.awt.event.KeyEvent;

import static utilities.sound.Sound.MOVE_CURSOR;

public final class HelpScreenKeyboardHandler
{
    private final GamePanel gp;

    public HelpScreenKeyboardHandler(GamePanel gp)
    {
        this.gp = gp;
    }

    public void handleHelpScreenKeys(KeyEvent keyPressed)
    {
        switch (keyPressed.getKeyCode())
        {
            case KeyEvent.VK_ENTER, KeyEvent.VK_ESCAPE ->
            {
                gp.soundEffectsHandler.playSoundEffect(MOVE_CURSOR);
                gp.returnToPreviousGameState();
            }
        }
    }
}
