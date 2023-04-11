package utilities.drawers;

import assets.Inventory;

import java.awt.image.BufferedImage;
import java.util.concurrent.atomic.AtomicInteger;

import static application.Application.objectsImagesUrls;
import static application.GamePanel.HALF_TILE_SIZE;
import static application.GamePanel.TILE_SIZE;
import static utilities.drawers.DrawerUtils.*;
import static utilities.drawers.UserInterfaceController.g2;
import static utilities.images.ImageUtils.setupImage;
import static utilities.statehandlers.TradeStateHandler.inventoryToBuyFrom;
import static utilities.statehandlers.TradeStateHandler.subState;

public class TradeWindowDrawer
{
    private static final BufferedImage coin = setupImage(objectsImagesUrls.get("gold-coin-one"), 36, 36);

    public static void drawTradeWindow(Inventory playerInventory, int playerCoins)
    {
        InventoryWindowDrawer.drawInventoryWindow(inventoryToBuyFrom,
                TILE_SIZE, TILE_SIZE, TILE_SIZE * 6, TILE_SIZE * 5);
        InventoryWindowDrawer.drawInventoryWindow(playerInventory,
                TILE_SIZE * 9, TILE_SIZE, TILE_SIZE * 6, TILE_SIZE * 5);
        drawSubState();
        drawItemPrice(playerInventory, playerCoins);
    }

    private static void drawSubState()
    {
        drawSubWindow(TILE_SIZE * 7, TILE_SIZE, TILE_SIZE * 2, HALF_TILE_SIZE, 1);
        drawCenteredText(subState.toString(), 1.33F, false, 8F);
    }

    private static void drawItemPrice(Inventory playerInventory, int playerCoins)
    {
        drawSubWindow(TILE_SIZE * 7, TILE_SIZE * 2, TILE_SIZE * 2, TILE_SIZE, 1);
        g2.drawImage(coin, TILE_SIZE * 7, TILE_SIZE * 2 + 6, null);
        AtomicInteger price = new AtomicInteger();
        switch (subState)
        {
            case BUY -> inventoryToBuyFrom.getItemOnCurrentSlot().ifPresent(item -> price.set(item.price));
            case SELL -> playerInventory.getItemOnCurrentSlot().ifPresent(item -> price.set(item.price));
        }
        drawText(String.format("price:%d", price.get()), TILE_SIZE * 7 + 30, TILE_SIZE * 2 + 20, false, 7F);
        drawText(String.format("coins:%d", playerCoins), TILE_SIZE * 7 + 30, TILE_SIZE * 2 + 32, false, 7F);

    }
}
