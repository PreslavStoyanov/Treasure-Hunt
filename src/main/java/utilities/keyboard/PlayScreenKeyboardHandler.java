package utilities.keyboard;

import application.GamePanel;

import java.awt.event.KeyEvent;

import static utilities.GameState.CHARACTER_STATE;
import static utilities.GameState.PAUSE_STATE;

public class PlayScreenKeyboardHandler
{
    private final GamePanel gp;
    public boolean isWPressed;
    public boolean isSPressed;
    public boolean isAPressed;
    public boolean isDPressed;
    public boolean isQPressed;
    public boolean isEPressed;
    public boolean isRPressed;
    public boolean isFPressed;
    public boolean isSpacePressed;

    public PlayScreenKeyboardHandler(GamePanel gp)
    {
        this.gp = gp;
    }

    public void handlePlayScreenKeysOnPress(KeyEvent keyPressed)
    {
        switch (keyPressed.getKeyCode())
        {
            case KeyEvent.VK_W -> isWPressed = true;
            case KeyEvent.VK_S -> isSPressed = true;
            case KeyEvent.VK_A -> isAPressed = true;
            case KeyEvent.VK_D -> isDPressed = true;
            case KeyEvent.VK_Q -> isQPressed = true;
            case KeyEvent.VK_E -> isEPressed = true;
            case KeyEvent.VK_F -> isFPressed = true;
            case KeyEvent.VK_R -> gp.setGameState(CHARACTER_STATE);
            case KeyEvent.VK_P -> gp.setGameState(PAUSE_STATE);
            case KeyEvent.VK_SPACE ->
            {
                isSpacePressed = true;
                gp.player.isAttacking = true;
            }
        }
    }

    public void handlePlayScreenKeysOnRelease(KeyEvent keyReleased)
    {
        switch (keyReleased.getKeyCode())
        {
            case KeyEvent.VK_W -> isWPressed = false;
            case KeyEvent.VK_S -> isSPressed = false;
            case KeyEvent.VK_A -> isAPressed = false;
            case KeyEvent.VK_D -> isDPressed = false;
            case KeyEvent.VK_Q -> isQPressed = false;
            case KeyEvent.VK_R -> isRPressed = false;
            case KeyEvent.VK_E -> isEPressed = false;
            case KeyEvent.VK_F -> isFPressed = false;
            case KeyEvent.VK_SPACE -> isSpacePressed = false;
        }
    }
}
