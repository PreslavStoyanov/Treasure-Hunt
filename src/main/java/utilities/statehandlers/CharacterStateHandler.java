package utilities.statehandlers;

import application.GamePanel;
import assets.entities.objects.StorableObject;

import java.awt.event.KeyEvent;
import java.util.Optional;

import static assets.entities.movingentities.liveentities.Player.getInventoryItemIndex;
import static utilities.GameState.PLAY_STATE;
import static utilities.drawers.InventoryWindowDrawer.*;
import static utilities.sound.Sound.MOVE_CURSOR;

public record CharacterStateHandler(GamePanel gp)
{
    public void handleCharacterStateEvent(KeyEvent keyPressed)
    {
        switch (keyPressed.getKeyCode())
        {
            case KeyEvent.VK_Q -> gp.setGameState(PLAY_STATE);
            case KeyEvent.VK_E, KeyEvent.VK_ENTER -> selectItem();
            case KeyEvent.VK_W, KeyEvent.VK_UP -> moveInventoryCursorUp();
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> moveInventoryCursorDown();
            case KeyEvent.VK_A, KeyEvent.VK_LEFT -> moveInventoryCursorLeft();
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> moveInventoryCursorRight();
        }
    }


    private void selectItem()
    {
        try
        {
            Optional.ofNullable(gp.player.inventory.get(getInventoryItemIndex())).ifPresent(StorableObject::useItem);
        }
        catch (IndexOutOfBoundsException e)
        {
            //expect to not have item at the given inventory index
        }
    }

    private void moveInventoryCursorUp()
    {
        inventorySlotCursorRow = Math.max(--inventorySlotCursorRow, 0);
        gp.soundEffectsHandler.playSoundEffect(MOVE_CURSOR);
    }

    private void moveInventoryCursorDown()
    {
        inventorySlotCursorRow = Math.min(++inventorySlotCursorRow, INVENTORY_ROWS - 1);
        gp.soundEffectsHandler.playSoundEffect(MOVE_CURSOR);
    }

    private void moveInventoryCursorLeft()
    {
        inventorySlotCursorCol = Math.max(--inventorySlotCursorCol, 0);
        gp.soundEffectsHandler.playSoundEffect(MOVE_CURSOR);
    }

    private void moveInventoryCursorRight()
    {
        inventorySlotCursorCol = Math.min(++inventorySlotCursorCol, INVENTORY_COLS - 1);
        gp.soundEffectsHandler.playSoundEffect(MOVE_CURSOR);
    }
}
