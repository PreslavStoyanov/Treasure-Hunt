package utilities.statehandlers;

import application.GamePanel;

import java.awt.event.KeyEvent;

import static utilities.sound.Sound.MOVE_CURSOR;

public class EndStateHandler
{
    private final GamePanel gp;
    public static int optionSelection = 1;

    public EndStateHandler(GamePanel gp)
    {
        this.gp = gp;
    }

    public void handleEndStateEvent(KeyEvent keyPressed)
    {
        switch (keyPressed.getKeyCode())
        {
            case KeyEvent.VK_ENTER -> handleSelection();
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
        optionSelection = Math.min(++optionSelection, 2);
    }

    private void handleSelection()
    {
        switch (optionSelection)
        {
            case 1 ->
            {
                switch (gp.getGameState())
                {
                    case GAME_WIN_STATE -> gp.startNewGame();
                    case GAME_OVER_STATE -> gp.retryGame();
                }
            }
            case 2 -> gp.backToMainMenu();
        }
        gp.soundEffectsHandler.playSoundEffect(MOVE_CURSOR);
    }
}
