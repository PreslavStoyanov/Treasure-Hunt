package utilities.statehandlers;

import application.GamePanel;

import java.awt.event.KeyEvent;

import static utilities.sound.Sound.MOVE_CURSOR;

public final class HelpStateHandler
{
    private final GamePanel gp;

    public HelpStateHandler(GamePanel gp)
    {
        this.gp = gp;
    }

    public void handleHelpStateEvent(KeyEvent keyPressed)
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
