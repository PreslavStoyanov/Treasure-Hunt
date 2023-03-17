package utilities.keyboard;

import application.GamePanel;

import java.awt.event.KeyEvent;

import static utilities.GameState.HELP_STATE;
import static utilities.GameState.PLAY_STATE;
import static utilities.drawers.HomeScreenDrawer.HomeMenuOption.*;
import static utilities.drawers.HomeScreenDrawer.homeMenuOption;

public record HomeScreenKeyboardHandler(GamePanel gp)
{

    public void handleHomeScreenKeys(int pressedKey)
    {
        switch (pressedKey)
        {
            case KeyEvent.VK_W, KeyEvent.VK_UP -> moveSelectionUp();
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> moveSelectionDown();
            case KeyEvent.VK_ENTER -> handleSelection();
        }
    }

    private void moveSelectionUp()
    {
        if (homeMenuOption.equals(NEW_GAME))
        {
            homeMenuOption = QUIT;
        }
        else if (homeMenuOption.equals(HELP))
        {
            homeMenuOption = NEW_GAME;
        }
        else if (homeMenuOption.equals(QUIT))
        {
            homeMenuOption = HELP;
        }
    }

    private void moveSelectionDown()
    {
        if (homeMenuOption.equals(NEW_GAME))
        {
            homeMenuOption = HELP;
        }
        else if (homeMenuOption.equals(HELP))
        {
            homeMenuOption = QUIT;
        }
        else if (homeMenuOption.equals(QUIT))
        {
            homeMenuOption = NEW_GAME;
        }
    }

    private void handleSelection()
    {
        if (homeMenuOption.equals(NEW_GAME))
        {
            openNewGame();
        }
        if (homeMenuOption.equals(HELP))
        {
            openHelpScreen();
        }
        if (homeMenuOption.equals(QUIT))
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
