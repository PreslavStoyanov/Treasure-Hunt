package utilities.statehandlers;

import application.GamePanel;
import assets.Inventory;
import assets.entities.objects.StorableObject;
import utilities.GameState;

import java.awt.event.KeyEvent;
import java.util.Objects;

import static utilities.drawers.InventoryWindowDrawer.INVENTORY_COLS;
import static utilities.drawers.InventoryWindowDrawer.INVENTORY_ROWS;
import static utilities.sound.Sound.MOVE_CURSOR;

public record InventoryKeyboardHandler(GamePanel gp)
{
    public void handleInventoryEvent(KeyEvent keyPressed, Inventory inventory)
    {
        switch (keyPressed.getKeyCode())
        {
            case KeyEvent.VK_ESCAPE -> gp.returnToPreviousGameState();
            case KeyEvent.VK_E, KeyEvent.VK_ENTER -> selectItem(inventory);
            case KeyEvent.VK_W, KeyEvent.VK_UP -> moveInventoryCursorUp(inventory);
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> moveInventoryCursorDown(inventory);
            case KeyEvent.VK_A, KeyEvent.VK_LEFT -> moveInventoryCursorLeft(inventory);
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> moveInventoryCursorRight(inventory);
        }
    }

    private void selectItem(Inventory inventory)
    {
        if (Objects.requireNonNull(gp.getGameState()) == GameState.CHARACTER_STATE)
        {
            inventory.getItemOnCurrentSlot().ifPresent(StorableObject::useItem);
        }
    }

    private void moveInventoryCursorUp(Inventory inventory)
    {
        inventory.inventorySlotCursorRow = Math.max(--inventory.inventorySlotCursorRow, 0);
        gp.soundEffectsHandler.playSoundEffect(MOVE_CURSOR);
    }

    private void moveInventoryCursorDown(Inventory inventory)
    {
        inventory.inventorySlotCursorRow = Math.min(++inventory.inventorySlotCursorRow, INVENTORY_ROWS - 1);
        gp.soundEffectsHandler.playSoundEffect(MOVE_CURSOR);
    }

    private void moveInventoryCursorLeft(Inventory inventory)
    {
        inventory.inventorySlotCursorCol = Math.max(--inventory.inventorySlotCursorCol, 0);
        gp.soundEffectsHandler.playSoundEffect(MOVE_CURSOR);
    }

    private void moveInventoryCursorRight(Inventory inventory)
    {
        inventory.inventorySlotCursorCol = Math.min(++inventory.inventorySlotCursorCol, INVENTORY_COLS - 1);
        gp.soundEffectsHandler.playSoundEffect(MOVE_CURSOR);
    }
}
