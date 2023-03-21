package utilities.keyboard;

import application.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static utilities.drawers.CoordinatesDrawer.shouldShowCoordinates;

public class KeyboardHandler implements KeyListener
{
    private final GamePanel gp;
    public final HomeScreenKeyboardHandler homeScreenKeyboardHandler;
    public final HelpScreenKeyboardHandler helpScreenKeyboardHandler;
    public final PauseScreenKeyboardHandler pauseScreenKeyboardHandler;
    public final DialogueScreenKeyboardHandler dialogueScreenKeyboardHandler;
    public final CharacterScreenKeyboardHandler characterScreenKeyboardHandler;
    public final PlayScreenKeyboardHandler playScreenKeyboardHandler;

    public KeyboardHandler(GamePanel gp)
    {
        this.gp = gp;
        this.homeScreenKeyboardHandler = new HomeScreenKeyboardHandler(gp);
        this.helpScreenKeyboardHandler = new HelpScreenKeyboardHandler(gp);
        this.pauseScreenKeyboardHandler = new PauseScreenKeyboardHandler(gp);
        this.dialogueScreenKeyboardHandler = new DialogueScreenKeyboardHandler(gp);
        this.characterScreenKeyboardHandler = new CharacterScreenKeyboardHandler(gp);
        this.playScreenKeyboardHandler = new PlayScreenKeyboardHandler(gp);
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
    }

    @Override
    public void keyPressed(KeyEvent event)
    {
        switch (gp.getGameState())
        {
            case HOME_STATE -> homeScreenKeyboardHandler.handleHomeScreenKeys(event);
            case HELP_STATE -> helpScreenKeyboardHandler.handleHelpScreenKeys(event);
            case PAUSE_STATE -> pauseScreenKeyboardHandler.handlePauseScreenKeys(event);
            case PLAY_STATE -> playScreenKeyboardHandler.handlePlayScreenKeysOnPress(event);
            case DIALOGUE_STATE -> dialogueScreenKeyboardHandler.handleDialogueScreenKeys(event);
            case CHARACTER_STATE -> characterScreenKeyboardHandler.handleCharacterScreenKeys(event);
        }
        if (event.getKeyCode() == KeyEvent.VK_O)
        {
            shouldShowCoordinates = !shouldShowCoordinates;
        }
    }

    @Override
    public void keyReleased(KeyEvent event)
    {
        playScreenKeyboardHandler.handlePlayScreenKeysOnRelease(event);
    }
}
