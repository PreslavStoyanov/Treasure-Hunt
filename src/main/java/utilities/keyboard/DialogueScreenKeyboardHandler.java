package utilities.keyboard;

import application.GamePanel;

import java.awt.event.KeyEvent;

import static utilities.GameState.PLAY_STATE;

public record DialogueScreenKeyboardHandler(GamePanel gp)
{
    public void handleDialogueScreenKeys(KeyEvent keyPressed)
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
