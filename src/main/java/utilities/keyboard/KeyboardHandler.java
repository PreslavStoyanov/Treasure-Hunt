package utilities.keyboard;

import View.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static utilities.GameState.*;
import static utilities.drawers.CoordinatesDrawer.shouldShowCoordinates;

public class KeyboardHandler implements KeyListener
{
    private final GamePanel gp;
    public final HomeScreenKeyboardHandler homeScreenKeyboardHandler;
    public final HelpScreenKeyboardHandler helpScreenKeyboardHandler;
    public final PauseScreenKeyboardHandler pauseScreenKeyboardHandler;
    public final DialogueScreenKeyboardHandler dialogueScreenKeyboardHandler;
    public final CharacterScreenKeyboardHandler characterScreenKeyboardHandler;
    public boolean isWPressed;
    public boolean isSPressed;
    public boolean isAPressed;
    public boolean isDPressed;
    public boolean isQPressed;
    public boolean isEPressed;
    public boolean isSpacePressed;

    public KeyboardHandler(GamePanel gp)
    {
        this.gp = gp;
        this.homeScreenKeyboardHandler = new HomeScreenKeyboardHandler(gp);
        this.helpScreenKeyboardHandler = new HelpScreenKeyboardHandler(gp);
        this.pauseScreenKeyboardHandler = new PauseScreenKeyboardHandler(gp);
        this.dialogueScreenKeyboardHandler = new DialogueScreenKeyboardHandler(gp);
        this.characterScreenKeyboardHandler = new CharacterScreenKeyboardHandler(gp);
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        int keyPressed = e.getKeyCode();

        if (gp.getGameState() == HOME_STATE)
        {
            homeScreenKeyboardHandler.handleHomeScreenKeys(keyPressed);
        }
        else if (gp.getGameState() == HELP_STATE)
        {
            helpScreenKeyboardHandler.handleHelpScreenKeys(keyPressed);
        }
        else if (gp.getGameState() == PAUSE_STATE)
        {
            pauseScreenKeyboardHandler.handlePauseScreenKeys(keyPressed);
        }
        else if (gp.getGameState() == PLAY_STATE)
        {
            handlePlayScreenKeysOnPress(keyPressed);
        }
        else if (gp.getGameState() == DIALOGUE_STATE)
        {
            dialogueScreenKeyboardHandler.handleDialogueScreenKeys(keyPressed);
        }
        else if (gp.getGameState() == CHARACTER_STATE)
        {
            characterScreenKeyboardHandler.handleCharacterScreenKeys(keyPressed);
        }
        if (keyPressed == KeyEvent.VK_L)
        {
            reloadMap();
        }
        if (keyPressed == KeyEvent.VK_O)
        {
            shouldShowCoordinates = !shouldShowCoordinates;
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        handlePlayScreenKeysOnRelease(e.getKeyCode());
    }

    private void handlePlayScreenKeysOnRelease(int releasedKey)
    {
        if (releasedKey == KeyEvent.VK_W)
        {
            isWPressed = false;
        }
        if (releasedKey == KeyEvent.VK_S)
        {
            isSPressed = false;
        }
        if (releasedKey == KeyEvent.VK_A)
        {
            isAPressed = false;
        }
        if (releasedKey == KeyEvent.VK_D)
        {
            isDPressed = false;
        }
        if (releasedKey == KeyEvent.VK_Q)
        {
            isQPressed = false;
        }
        if (releasedKey == KeyEvent.VK_SPACE)
        {
            isSpacePressed = false;
        }
    }

    private void handlePlayScreenKeysOnPress(int keyPressed)
    {
        if (keyPressed == KeyEvent.VK_W)
        {
            isWPressed = true;
        }
        if (keyPressed == KeyEvent.VK_S)
        {
            isSPressed = true;
        }
        if (keyPressed == KeyEvent.VK_A)
        {
            isAPressed = true;
        }
        if (keyPressed == KeyEvent.VK_D)
        {
            isDPressed = true;
        }
        if (keyPressed == KeyEvent.VK_Q)
        {
            isQPressed = true;
        }
        if (keyPressed == KeyEvent.VK_P)
        {
            openPauseScreen();
        }
        if (keyPressed == KeyEvent.VK_E)
        {
            isEPressed = true;
        }
        if (keyPressed == KeyEvent.VK_B)
        {
            openCharacterScreen();
        }
        if (keyPressed == KeyEvent.VK_SPACE)
        {
            isSpacePressed = true;
            gp.player.attacking = true;
        }
    }

    private void openPauseScreen()
    {
        gp.setGameState(PAUSE_STATE);
    }

    private void openCharacterScreen()
    {
        gp.setGameState(CHARACTER_STATE);
    }

    private void reloadMap()
    {
        gp.tileManager.loadTileMap("/maps/world01.txt");
    }
}
