package utilities.keyboard;

import View.GamePanel;

import java.awt.event.KeyEvent;

import static utilities.GameState.HOME_STATE;

public record HelpScreenKeyboardHandler(GamePanel gp)
{

    public void handleHelpScreenKeys(int keyPressed)
    {
        if (keyPressed == KeyEvent.VK_ENTER)
        {
            openHomeScreen();
        }
    }

    private void openHomeScreen()
    {
        gp.setGameState(HOME_STATE);
    }
}
