package utilities.keyboard;

import application.GamePanel;
import utilities.sound.Sound;

import java.awt.event.KeyEvent;

import static utilities.GameState.PLAY_STATE;
import static utilities.drawers.InventoryWindowDrawer.*;
import static utilities.sound.Sound.CURSOR;

public record CharacterScreenKeyboardHandler(GamePanel gp)
{
    public void handleCharacterScreenKeys(int keyPressed)
    {
        switch (keyPressed)
        {
            case KeyEvent.VK_B -> quitCharacterScreen();
            case KeyEvent.VK_W -> moveInventoryCursorUp();
            case KeyEvent.VK_S -> moveInventoryCursorDown();
            case KeyEvent.VK_A -> moveInventoryCursorLeft();
            case KeyEvent.VK_D -> moveInventoryCursorRight();
        }
    }

    private void moveInventoryCursorUp()
    {
        inventorySlotCursorRow = Math.max(--inventorySlotCursorRow, 0);
        gp.soundHandler.playSoundEffect(CURSOR);
    }

    private void moveInventoryCursorDown()
    {
        inventorySlotCursorRow = Math.min(++inventorySlotCursorRow, INVENTORY_ROWS - 1);
        gp.soundHandler.playSoundEffect(CURSOR);
    }

    private void moveInventoryCursorLeft()
    {
        inventorySlotCursorCol = Math.max(--inventorySlotCursorCol, 0);
        gp.soundHandler.playSoundEffect(CURSOR);
    }

    private void moveInventoryCursorRight()
    {
        inventorySlotCursorCol = Math.min(++inventorySlotCursorCol, INVENTORY_COLS - 1);
        gp.soundHandler.playSoundEffect(CURSOR);
    }

    private void quitCharacterScreen()
    {
        gp.setGameState(PLAY_STATE);
    }
}
