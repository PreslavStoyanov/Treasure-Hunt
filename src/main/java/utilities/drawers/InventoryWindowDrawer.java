package utilities.drawers;

import assets.Inventory;
import assets.entities.Object;
import assets.entities.objects.storables.EquipableItem;

import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

import static application.GamePanel.TILE_SIZE;
import static assets.EntityType.EQUIPPABLE_ITEMS;
import static utilities.drawers.DrawerUtils.*;
import static utilities.drawers.UserInterfaceController.g2;

public class InventoryWindowDrawer
{
    public static final int INVENTORY_COLS = 5;
    public static final int INVENTORY_ROWS = 4;

    public static void drawInventoryWindow(Inventory inventory, int frameX, int frameY, int frameWidth, int frameHeight)
    {
        drawSubWindow(frameX, frameY, frameWidth, frameHeight, 5);
        drawSlots(frameX, frameY);
        drawItems(inventory, frameX, frameY);
        drawCursor(inventory, frameX, frameY);
        drawDescriptionWindow(inventory, frameX, frameY, frameWidth, frameHeight);
    }

    private static void drawSlots(int frameX, int frameY)
    {
        int slotX = frameX + 25;
        int slotY = frameY + 25;
        for (int row = 0; row < INVENTORY_ROWS; row++)
        {
            for (int col = 0; col < INVENTORY_COLS; col++)
            {
                drawBlackRoundFilledRect(slotX + TILE_SIZE * col, slotY + TILE_SIZE * row, TILE_SIZE, TILE_SIZE, 100);
            }
        }
    }

    private static void drawItems(Inventory inventory, int frameX, int frameY)
    {
        final int itemXStart = frameX + 25;
        final int itemYStart = frameY + 25;
        int itemX = itemXStart;
        int itemY = itemYStart;
        for (int i = 0; i < inventory.getStorage().size(); i++)
        {
            Object object = inventory.getStorage().get(i);

            if (EQUIPPABLE_ITEMS.contains(object.type) && ((EquipableItem) object).isEquipped)
            {
                drawRoundFilledRect(itemX, itemY, TILE_SIZE, TILE_SIZE,
                        new Color(162, 137, 0, 255));
            }

            g2.drawImage(object.defaultImage, itemX + 3, itemY + 3, TILE_SIZE - 6, TILE_SIZE - 6, null);
            itemX += TILE_SIZE;

            if (i == 4 || i == 9 || i == 14)
            {
                itemY += TILE_SIZE;
                itemX = itemXStart;
            }
        }
    }

    private static void drawCursor(Inventory inventory, int frameX, int frameY)
    {
        final int cursorX = frameX + 25;
        final int cursorY = frameY + 25;
        drawRoundRect(cursorX + TILE_SIZE * inventory.inventorySlotCursorCol,
                cursorY + TILE_SIZE * inventory.inventorySlotCursorRow,
                TILE_SIZE, TILE_SIZE, 2);
    }

    private static void drawDescriptionWindow(Inventory inventory, int frameX, int frameY, int frameWidth, int frameHeight)
    {
        int textX = frameX + 20;
        AtomicInteger textY = new AtomicInteger(frameY + frameHeight + 30);

        g2.setFont(g2.getFont().deriveFont(12F));
        inventory.getItemOnCurrentSlot().ifPresent(item ->
        {
            drawSubWindow(frameX, frameY + frameHeight, frameWidth, frameHeight - TILE_SIZE * 3, 5);
            String[] split = item.description.split("\n");
            for (String line : split)
            {
                g2.drawString(line, textX, textY.get());
                textY.set(textY.get() + 15);
            }
        });
    }
}
