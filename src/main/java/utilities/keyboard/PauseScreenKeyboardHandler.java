package utilities.keyboard;

import application.GamePanel;

import java.awt.event.KeyEvent;

import static utilities.GameState.GAME_LOSE_STATE;
import static utilities.GameState.PLAY_STATE;

public record PauseScreenKeyboardHandler(GamePanel gp)
{
    public void handlePauseScreenKeys(KeyEvent keyPressed)
    {
        switch (keyPressed.getKeyCode())
        {
            case KeyEvent.VK_P -> gp.returnToPreviousGameState();
            case KeyEvent.VK_ENTER -> gp.setGameState(GAME_LOSE_STATE);
        }
    }
}
