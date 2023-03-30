package utilities.statehandlers;

import application.GamePanel;
import assets.Inventory;

import java.awt.event.KeyEvent;

import static utilities.drawers.MessageDrawer.addMessage;

public final class TradeStateHandler
{
    private final GamePanel gp;
    public static Inventory inventoryToBuyFrom = new Inventory();
    public static SubState subState = SubState.SELL;

    public TradeStateHandler(GamePanel gp)
    {
        this.gp = gp;
    }

    public void handleTradeStateEvent(KeyEvent keyPressed, InventoryKeyboardHandler inventoryKeyboardHandler)
    {
        switch (subState)
        {
            case BUY -> inventoryKeyboardHandler.handleInventoryEvent(keyPressed, inventoryToBuyFrom);
            case SELL -> inventoryKeyboardHandler.handleInventoryEvent(keyPressed, gp.player.inventory);
        }

        switch (keyPressed.getKeyCode())
        {
            case KeyEvent.VK_T -> switchState();
            case KeyEvent.VK_ENTER ->
            {
                switch (subState)
                {
                    case BUY -> buyItem();
                    case SELL -> sellItem();
                }
            }
        }
    }

    private void buyItem()
    {
        inventoryToBuyFrom.getItemOnCurrentSlot()
                .ifPresent(item -> {
                    if (gp.player.inventory.haveSpace() && item.isAffordable())
                    {
                        gp.player.coins -= item.price;
                        gp.player.inventory.add(item);
                        addMessage(String.format("You bought %s", item.name));
                    }
                });
    }

    private void sellItem()
    {
        gp.player.inventory.getItemOnCurrentSlot()
                .ifPresent(item -> {
                    if (!item.isEquippedItem())
                    {
                        gp.player.inventory.getStorage().remove(item);
                        gp.player.coins += item.price;
                        addMessage(String.format("You sold %s", item.name));
                    }
                });
    }

    private static void switchState()
    {
        subState = subState.equals(SubState.BUY) ? SubState.SELL : SubState.BUY;
    }

    public enum SubState
    {
        BUY,
        SELL
    }
}

