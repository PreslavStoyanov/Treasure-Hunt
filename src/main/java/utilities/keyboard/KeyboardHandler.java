package utilities.keyboard;

import View.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static utilities.GameState.*;

public class KeyboardHandler implements KeyListener
{
    GamePanel gp;
    public final HomeScreenKeyboardHandler homeScreenKeyboardHandler;
    public final HelpScreenKeyboardHandler helpScreenKeyboardHandler;
    public final PauseScreenKeyboardHandler pauseScreenKeyboardHandler;
    public final DialogueScreenKeyboardHandler dialogueScreenKeyboardHandler;
    public final CharacterScreenKeyboardHandler characterScreenKeyboardHandler;
    public boolean upPressed;
    public boolean downPressed;
    public boolean leftPressed;
    public boolean rightPressed;
    public boolean thumbUpPressed;
    public boolean ePressed;
    public boolean spacePressed;

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
        else if (keyPressed == KeyEvent.VK_L)
        {
            reloadMap();
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
            upPressed = false;
        }
        if (releasedKey == KeyEvent.VK_S)
        {
            downPressed = false;
        }
        if (releasedKey == KeyEvent.VK_A)
        {
            leftPressed = false;
        }
        if (releasedKey == KeyEvent.VK_D)
        {
            rightPressed = false;
        }
        if (releasedKey == KeyEvent.VK_Q)
        {
            thumbUpPressed = false;
        }
        if (releasedKey == KeyEvent.VK_SPACE)
        {
            spacePressed = false;
        }
    }

    private void handlePlayScreenKeysOnPress(int keyPressed)
    {
        if (keyPressed == KeyEvent.VK_W)
        {
            upPressed = true;
        }
        if (keyPressed == KeyEvent.VK_S)
        {
            downPressed = true;
        }
        if (keyPressed == KeyEvent.VK_A)
        {
            leftPressed = true;
        }
        if (keyPressed == KeyEvent.VK_D)
        {
            rightPressed = true;
        }
        if (keyPressed == KeyEvent.VK_Q)
        {
            thumbUpPressed = true;
        }
        if (keyPressed == KeyEvent.VK_P)
        {
            openPauseScreen();
        }
        if (keyPressed == KeyEvent.VK_E)
        {
            ePressed = true;
        }
        if (keyPressed == KeyEvent.VK_B)
        {
            openCharacterScreen();
        }
        if (keyPressed == KeyEvent.VK_SPACE)
        {
            spacePressed = true;
            gp.player.attacking = true;
        }
        if (keyPressed == KeyEvent.VK_O)
        {
            gp.toggleShowingCoordinates();
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
        gp.tileManager.loadMap("/maps/world01.txt");
    }
}
