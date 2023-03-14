package utilities.keyboard;

import View.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static utilities.GameState.CHARACTER_STATE;
import static utilities.GameState.PAUSE_STATE;
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

        switch (gp.getGameState())
        {
            case HOME_STATE -> homeScreenKeyboardHandler.handleHomeScreenKeys(keyPressed);
            case HELP_STATE -> helpScreenKeyboardHandler.handleHelpScreenKeys(keyPressed);
            case PAUSE_STATE -> pauseScreenKeyboardHandler.handlePauseScreenKeys(keyPressed);
            case PLAY_STATE -> handlePlayScreenKeysOnPress(keyPressed);
            case DIALOGUE_STATE -> dialogueScreenKeyboardHandler.handleDialogueScreenKeys(keyPressed);
            case CHARACTER_STATE -> characterScreenKeyboardHandler.handleCharacterScreenKeys(keyPressed);
        }
        if (keyPressed == KeyEvent.VK_O)
        {
            shouldShowCoordinates = !shouldShowCoordinates;
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_W -> isWPressed = false;
            case KeyEvent.VK_S -> isSPressed = false;
            case KeyEvent.VK_A -> isAPressed = false;
            case KeyEvent.VK_D -> isDPressed = false;
            case KeyEvent.VK_Q -> isQPressed = false;
            case KeyEvent.VK_SPACE -> isSpacePressed = false;
        }
    }

    private void handlePlayScreenKeysOnPress(int keyPressed)
    {
        switch (keyPressed)
        {
            case KeyEvent.VK_W -> isWPressed = true;
            case KeyEvent.VK_S -> isSPressed = true;
            case KeyEvent.VK_A -> isAPressed = true;
            case KeyEvent.VK_D -> isDPressed = true;
            case KeyEvent.VK_Q -> isQPressed = true;
            case KeyEvent.VK_P -> openPauseScreen();
            case KeyEvent.VK_E -> isEPressed = true;
            case KeyEvent.VK_B -> openCharacterScreen();
            case KeyEvent.VK_SPACE ->
            {
                isSpacePressed = true;
                gp.player.isAttacking = true;
            }
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
}
