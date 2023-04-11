package utilities.statehandlers;

import application.GamePanel;

import java.awt.event.KeyEvent;

import static utilities.GameState.TRADE_STATE;

public record DialogueStateHandler(GamePanel gp)
{
    public void handleDialogueStateEvent(KeyEvent keyPressed)
    {
        switch (keyPressed.getKeyCode())
        {
            case KeyEvent.VK_E, KeyEvent.VK_ESCAPE ->
            {
                gp.returnToPreviousGameState();
            }
            case KeyEvent.VK_ENTER -> gp.setGameState(TRADE_STATE);
        }
    }
}
