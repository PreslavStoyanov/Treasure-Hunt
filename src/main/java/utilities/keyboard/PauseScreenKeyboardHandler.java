package utilities.keyboard;

import application.GamePanel;

import java.awt.event.KeyEvent;

import static utilities.GameState.GAME_OVER_STATE;

public record PauseScreenKeyboardHandler(GamePanel gp)
{
    public void handlePauseScreenKeys(KeyEvent keyPressed)
    {
        switch (keyPressed.getKeyCode())
        {
            case KeyEvent.VK_P, KeyEvent.VK_ESCAPE, KeyEvent.VK_ENTER -> gp.returnToPreviousGameState();
        }
    }
}
