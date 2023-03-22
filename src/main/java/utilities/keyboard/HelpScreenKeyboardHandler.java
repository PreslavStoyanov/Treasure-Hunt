package utilities.keyboard;

import application.GamePanel;

import java.awt.event.KeyEvent;

import static utilities.GameState.HOME_STATE;
import static utilities.sound.Sound.MOVE_CURSOR;

public record HelpScreenKeyboardHandler(GamePanel gp)
{

    public void handleHelpScreenKeys(KeyEvent keyPressed)
    {
        if (keyPressed.getKeyCode() == KeyEvent.VK_ENTER)
        {
            gp.soundHandler.playSoundEffect(MOVE_CURSOR);
            gp.setGameState(HOME_STATE);
        }
    }
}
