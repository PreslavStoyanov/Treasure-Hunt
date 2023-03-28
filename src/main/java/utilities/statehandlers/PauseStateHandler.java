package utilities.statehandlers;

import application.GamePanel;

import java.awt.event.KeyEvent;

public record PauseStateHandler(GamePanel gp)
{
    public void handlePauseStateEvent(KeyEvent keyPressed)
    {
        switch (keyPressed.getKeyCode())
        {
            case KeyEvent.VK_P, KeyEvent.VK_ESCAPE, KeyEvent.VK_ENTER -> gp.returnToPreviousGameState();
        }
    }
}
