package utilities.keyboard;

import application.GamePanel;

import java.awt.event.KeyEvent;

import static utilities.GameState.PLAY_STATE;

public record PauseScreenKeyboardHandler(GamePanel gp)
{
    public void handlePauseScreenKeys(int keyPressed)
    {
        if (keyPressed == KeyEvent.VK_P)
        {
            unpauseGame();
        }
    }

    private void unpauseGame()
    {
        gp.setGameState(PLAY_STATE);
    }
}
