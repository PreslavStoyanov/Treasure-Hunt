package utilities.keyboard;

import application.GamePanel;

import java.awt.event.KeyEvent;

import static utilities.GameState.HOME_STATE;

public record HelpScreenKeyboardHandler(GamePanel gp)
{

    public void handleHelpScreenKeys(KeyEvent keyPressed)
    {
        if (keyPressed.getKeyCode() == KeyEvent.VK_ENTER)
        {
            openHomeScreen();
        }
    }

    private void openHomeScreen()
    {
        gp.setGameState(HOME_STATE);
    }
}
