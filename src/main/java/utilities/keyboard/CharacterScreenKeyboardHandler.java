package utilities.keyboard;

import application.GamePanel;
import assets.entities.Object;
import assets.entities.objects.Weapon;

import java.awt.event.KeyEvent;

import static assets.EntityType.weaponObjects;
import static assets.entities.liveentities.Player.getInventoryItemIndex;
import static utilities.GameState.PLAY_STATE;
import static utilities.drawers.InventoryWindowDrawer.*;
import static utilities.drawers.MessageDrawer.addMessage;
import static utilities.sound.Sound.CURSOR;

public record CharacterScreenKeyboardHandler(GamePanel gp)
{
    public void handleCharacterScreenKeys(int keyPressed)
    {
        switch (keyPressed)
        {
            case KeyEvent.VK_B, KeyEvent.VK_ESCAPE -> quitCharacterScreen();
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
            Object object = gp.player.inventory.get(getInventoryItemIndex());
            if (weaponObjects.contains(object.type) && gp.player.currentWeapon.type != object.type)
            {
                gp.player.currentWeapon = (Weapon) object;
                addMessage(String.format("You equipped the %s", object.name));
                gp.player.attack = gp.player.calculateAttack();

            }
        }
        catch (IndexOutOfBoundsException e)
        {
            addMessage("The item is not capable to be equipped");
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
