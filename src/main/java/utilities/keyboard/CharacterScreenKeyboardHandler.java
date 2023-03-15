package utilities.keyboard;

import application.GamePanel;

import java.awt.event.KeyEvent;

import static utilities.GameState.PLAY_STATE;

public record CharacterScreenKeyboardHandler(GamePanel gp)
{
    public void handleCharacterScreenKeys(int keyPressed)
    {
        if (keyPressed == KeyEvent.VK_B)
        {
            quitCharacterScreen();
        }
    }

    private void quitCharacterScreen()
    {
        gp.setGameState(PLAY_STATE);
    }
}
