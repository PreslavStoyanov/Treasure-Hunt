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
        gp.ui.getHomeScreenDrawer().decreaseCommandNumber();
        if (gp.ui.getHomeScreenDrawer().getCommandNumber() < 0)
        {
            gp.ui.getHomeScreenDrawer().setCommandNumber(2);
        }
    }

    private void moveSelectionDown()
    {
        gp.ui.getHomeScreenDrawer().increaseCommandNumber();
        if (gp.ui.getHomeScreenDrawer().getCommandNumber() > 2)
        {
            gp.ui.getHomeScreenDrawer().setCommandNumber(0);
        }
    }

    private void handleSelection()
    {
        if (gp.ui.getHomeScreenDrawer().getCommandNumber() == 0)
        {
            openNewGame();
        }
        if (gp.ui.getHomeScreenDrawer().getCommandNumber() == 1)
        {
            openHelpScreen();
        }
        if (gp.ui.getHomeScreenDrawer().getCommandNumber() == 2)
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
