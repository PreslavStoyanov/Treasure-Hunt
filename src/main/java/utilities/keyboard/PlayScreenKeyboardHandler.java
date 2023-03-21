package utilities.keyboard;

import application.GamePanel;

import java.awt.event.KeyEvent;

import static utilities.GameState.CHARACTER_STATE;
import static utilities.GameState.PAUSE_STATE;

public class PlayScreenKeyboardHandler
{
    private final GamePanel gp;
    public boolean isUpPressed;
    public boolean isDownPressed;
    public boolean isLeftPressed;
    public boolean isRightPressed;
    public boolean isShootProjectileButtonPressed;
    public boolean isTalkButtonPressed;
    public boolean isInventoryButtonPressed;
    public boolean isEnergyButtonPressed;
    public boolean isSpacePressed;

    public PlayScreenKeyboardHandler(GamePanel gp)
    {
        this.gp = gp;
    }

    public void handlePlayScreenKeysOnPress(KeyEvent keyPressed)
    {
        switch (keyPressed.getKeyCode())
        {
            case KeyEvent.VK_W -> isUpPressed = true;
            case KeyEvent.VK_S -> isDownPressed = true;
            case KeyEvent.VK_A -> isLeftPressed = true;
            case KeyEvent.VK_D -> isRightPressed = true;
            case KeyEvent.VK_E -> isTalkButtonPressed = true;
            case KeyEvent.VK_F -> isEnergyButtonPressed = true;
            case KeyEvent.VK_R -> isShootProjectileButtonPressed = true;
            case KeyEvent.VK_Q -> gp.setGameState(CHARACTER_STATE);
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
            case KeyEvent.VK_W -> isUpPressed = false;
            case KeyEvent.VK_S -> isDownPressed = false;
            case KeyEvent.VK_A -> isLeftPressed = false;
            case KeyEvent.VK_D -> isRightPressed = false;
            case KeyEvent.VK_Q -> isInventoryButtonPressed = false;
            case KeyEvent.VK_R -> isShootProjectileButtonPressed = false;
            case KeyEvent.VK_E -> isTalkButtonPressed = false;
            case KeyEvent.VK_F -> isEnergyButtonPressed = false;
            case KeyEvent.VK_SPACE -> isSpacePressed = false;
        }
    }
}
