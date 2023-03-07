package utilities.keyboard;

import View.GamePanel;

import java.awt.event.KeyEvent;

import static utilities.GameState.HELP_STATE;
import static utilities.GameState.PLAY_STATE;

public record HomeScreenKeyboardHandler(GamePanel gp)
{

    public void handleHomeScreenKeys(int pressedKey)
    {
        if (pressedKey == KeyEvent.VK_W)
        {
            moveSelectionUp();
        }
        if (pressedKey == KeyEvent.VK_S)
        {
            moveSelectionDown();
        }
        if (pressedKey == KeyEvent.VK_ENTER)
        {
            handleSelection();
        }
    }

    private void moveSelectionUp()
    {
        gp.ui.commandNumber--;
        if (gp.ui.commandNumber < 0)
        {
            gp.ui.commandNumber = 2;
        }
    }

    private void moveSelectionDown()
    {
        gp.ui.commandNumber++;
        if (gp.ui.commandNumber > 2)
        {
            gp.ui.commandNumber = 0;
        }
    }

    private void handleSelection()
    {
        if (gp.ui.commandNumber == 0)
        {
            openNewGame();
        }
        if (gp.ui.commandNumber == 1)
        {
            openHelpScreen();
        }
        if (gp.ui.commandNumber == 2)
        {
            quitGame();
        }
    }

    private void openNewGame()
    {
        gp.setGameState(PLAY_STATE);
        gp.setUpNewGame();
    }

    private void openHelpScreen()
    {
        gp.setGameState(HELP_STATE);
    }

    private static void quitGame()
    {
        System.exit(0);
    }
}
