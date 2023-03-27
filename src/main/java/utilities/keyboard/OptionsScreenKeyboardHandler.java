package utilities.keyboard;

import application.GamePanel;

import java.awt.event.KeyEvent;
import java.util.Map;

import static utilities.GameState.HELP_STATE;
import static utilities.keyboard.OptionsScreenKeyboardHandler.OptionsMenuSelection.*;
import static utilities.sound.Sound.MOVE_CURSOR;

public final class OptionsScreenKeyboardHandler
{
    private final GamePanel gp;
    public static int optionSelection = 1;
    public static final Map<Integer, OptionsMenuSelection> optionMenuSelections =
            Map.of(1, FULL_SCREEN,
                    2, MUSIC,
                    3, SOUND_EFFECTS,
                    4, HELP,
                    5, QUIT,
                    6, BACK);

    public OptionsScreenKeyboardHandler(GamePanel gp)
    {
        this.gp = gp;
    }

    public void handleOptionsScreenKeys(KeyEvent keyPressed)
    {
        switch (keyPressed.getKeyCode())
        {
            case KeyEvent.VK_ESCAPE -> gp.returnToPreviousGameState();
            case KeyEvent.VK_ENTER -> handleSelection(optionMenuSelections.get(optionSelection));
            case KeyEvent.VK_W, KeyEvent.VK_UP -> moveSelectionUp();
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> moveSelectionDown();
        }
    }

    private void moveSelectionUp()
    {
        gp.soundEffectsHandler.playSoundEffect(MOVE_CURSOR);
        optionSelection = Math.max(--optionSelection, 1);
    }

    private void moveSelectionDown()
    {
        gp.soundEffectsHandler.playSoundEffect(MOVE_CURSOR);
        optionSelection = Math.min(++optionSelection, 6);
    }

    private void handleSelection(OptionsMenuSelection optionsMenuSelection)
    {
        switch (optionsMenuSelection)
        {
            case FULL_SCREEN -> gp.setFullScreen();
            case MUSIC -> gp.musicHandler.toggleVolume();
            case SOUND_EFFECTS -> gp.soundEffectsHandler.toggleVolume();
            case HELP -> gp.setGameState(HELP_STATE);
            case QUIT -> System.exit(1);
            case BACK -> gp.returnToPreviousGameState();
        }
        gp.soundEffectsHandler.playSoundEffect(MOVE_CURSOR);
    }

    public enum OptionsMenuSelection
    {
        FULL_SCREEN,
        MUSIC,
        SOUND_EFFECTS,
        HELP,
        QUIT,
        BACK
    }
}
