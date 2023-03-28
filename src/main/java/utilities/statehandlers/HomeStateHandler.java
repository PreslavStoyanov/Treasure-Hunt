package utilities.statehandlers;

import application.GamePanel;

import java.awt.event.KeyEvent;
import java.util.Map;

import static utilities.GameState.OPTIONS_STATE;
import static utilities.GameState.PLAY_STATE;
import static utilities.sound.Sound.MOVE_CURSOR;
import static utilities.statehandlers.HomeStateHandler.HomeMenuSelection.*;

public final class HomeStateHandler
{
    private final GamePanel gp;
    public static int homeMenuSelection = 1;
    public static final Map<Integer, HomeMenuSelection> homeMenuSelections =
            Map.of(1, NEW_GAME,
                    2, OPTIONS,
                    3, QUIT);

    public HomeStateHandler(GamePanel gp)
    {
        this.gp = gp;
    }

    public void handleHomeStateEvent(KeyEvent pressedKey)
    {
        switch (pressedKey.getKeyCode())
        {
            case KeyEvent.VK_W, KeyEvent.VK_UP -> moveSelectionUp();
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> moveSelectionDown();
            case KeyEvent.VK_ENTER -> handleSelection(homeMenuSelections.get(homeMenuSelection));
        }
    }

    private void moveSelectionUp()
    {
        gp.soundEffectsHandler.playSoundEffect(MOVE_CURSOR);
        homeMenuSelection = Math.max(--homeMenuSelection, 1);
    }

    private void moveSelectionDown()
    {
        gp.soundEffectsHandler.playSoundEffect(MOVE_CURSOR);
        homeMenuSelection = Math.min(++homeMenuSelection, 3);
    }

    private void handleSelection(HomeMenuSelection homeMenuSelection)
    {
        switch (homeMenuSelection)
        {
            case NEW_GAME ->
            {
                gp.setGameState(PLAY_STATE);
                gp.isGameStarted = true;
            }
            case OPTIONS -> gp.setGameState(OPTIONS_STATE);
            case QUIT -> System.exit(0);
        }
    }

    public enum HomeMenuSelection
    {
        NEW_GAME,
        OPTIONS,
        QUIT
    }
}
