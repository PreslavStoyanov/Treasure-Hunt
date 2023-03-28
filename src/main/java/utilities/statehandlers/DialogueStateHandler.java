package utilities.statehandlers;

import application.GamePanel;

import java.awt.event.KeyEvent;

public record DialogueStateHandler(GamePanel gp)
{
    public void handleDialogueStateEvent(KeyEvent keyPressed)
    {
        switch (keyPressed.getKeyCode())
        {
            case KeyEvent.VK_E, KeyEvent.VK_ESCAPE ->
            {
                gp.returnToPreviousGameState();
                gp.soundEffectsHandler.stop();
            }
        }
    }
}
