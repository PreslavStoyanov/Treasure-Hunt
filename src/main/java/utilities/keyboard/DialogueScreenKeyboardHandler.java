package utilities.keyboard;

import View.GamePanel;

import java.awt.event.KeyEvent;

import static utilities.GameState.PAUSE_STATE;
import static utilities.GameState.PLAY_STATE;

public record DialogueScreenKeyboardHandler(GamePanel gp)
{
    public void handleDialogueScreenKeys(int code)
    {
        if (code == KeyEvent.VK_E)
        {
            quitDialogue();
        }
        if (code == KeyEvent.VK_P)
        {
            pauseGame();
        }
        if (code == KeyEvent.VK_O)
        {
            gp.toggleShowingCoordinates();
        }
    }

    private void pauseGame()
    {
        gp.setGameState(PAUSE_STATE);
    }

    private void quitDialogue()
    {
        gp.setGameState(PLAY_STATE);
    }
}
