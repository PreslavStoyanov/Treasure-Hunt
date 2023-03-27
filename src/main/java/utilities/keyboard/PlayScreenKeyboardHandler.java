package utilities.keyboard;

import application.GamePanel;

import java.awt.event.KeyEvent;

import static utilities.GameState.*;

public class PlayScreenKeyboardHandler
{
    private final GamePanel gp;
    public static boolean isUpButtonPressed;
    public static boolean isDownButtonPressed;
    public static boolean isLeftButtonPressed;
    public static boolean isRightButtonPressed;
    public static boolean isShootProjectileButtonPressed;
    public static boolean isTalkButtonPressed;
    public static boolean isEnergyButtonPressed;
    public static boolean isSwingButtonPressed;

    public PlayScreenKeyboardHandler(GamePanel gp)
    {
        this.gp = gp;
    }

    public void handlePlayScreenKeysOnPress(KeyEvent keyPressed)
    {
        switch (keyPressed.getKeyCode())
        {
            case KeyEvent.VK_W -> isUpButtonPressed = true;
            case KeyEvent.VK_S -> isDownButtonPressed = true;
            case KeyEvent.VK_A -> isLeftButtonPressed = true;
            case KeyEvent.VK_D -> isRightButtonPressed = true;
            case KeyEvent.VK_E -> isTalkButtonPressed = true;
            case KeyEvent.VK_F -> isEnergyButtonPressed = true;
            case KeyEvent.VK_R -> isShootProjectileButtonPressed = true;
            case KeyEvent.VK_Q -> gp.setGameState(CHARACTER_STATE);
            case KeyEvent.VK_P -> gp.setGameState(PAUSE_STATE);
            case KeyEvent.VK_ESCAPE -> gp.setGameState(OPTIONS_STATE);
            case KeyEvent.VK_SPACE -> isSwingButtonPressed = true;
        }
    }

    public void handlePlayScreenKeysOnRelease(KeyEvent keyReleased)
    {
        switch (keyReleased.getKeyCode())
        {
            case KeyEvent.VK_W -> isUpButtonPressed = false;
            case KeyEvent.VK_S -> isDownButtonPressed = false;
            case KeyEvent.VK_A -> isLeftButtonPressed = false;
            case KeyEvent.VK_D -> isRightButtonPressed = false;
            case KeyEvent.VK_R -> isShootProjectileButtonPressed = false;
            case KeyEvent.VK_E -> isTalkButtonPressed = false;
            case KeyEvent.VK_F -> isEnergyButtonPressed = false;
        }
    }
}
