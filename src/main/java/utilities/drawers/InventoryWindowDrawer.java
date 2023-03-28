package utilities.drawers;

import assets.entities.Object;
import assets.entities.movingentities.liveentities.Player;
import assets.entities.objects.collectables.EquipableItem;

import java.awt.*;

import static application.GamePanel.TILE_SIZE;
import static assets.EntityType.EQUIPPABLE_ITEMS;
import static assets.entities.movingentities.liveentities.Player.getInventoryItemIndex;
import static utilities.drawers.DrawerUtils.*;
import static utilities.drawers.UserInterfaceController.g2;

public class InventoryWindowDrawer
{
    private static final int FRAME_X = TILE_SIZE * 9;
    private static final int FRAME_Y = TILE_SIZE;
    private static final int FRAME_WIDTH = TILE_SIZE * 6;
    private static final int FRAME_HEIGHT = TILE_SIZE * 5;
    public static final int INVENTORY_COLS = 5;
    public static final int INVENTORY_ROWS = 4;
    public static int inventorySlotCursorCol = 0;
    public static int inventorySlotCursorRow = 0;

    public static void drawInventoryWindow(Player player)
    {
        drawSubWindow(FRAME_X, FRAME_Y, FRAME_WIDTH, FRAME_HEIGHT, 5);
        drawSlots();
        drawItems(player);
        drawCursor();
        drawDescriptionWindow(player);
    }

    private static void drawSlots()
    {
        int slotX = FRAME_X + 25;
        int slotY = FRAME_Y + 25;
        for (int row = 0; row < INVENTORY_ROWS; row++)
        {
            for (int col = 0; col < INVENTORY_COLS; col++)
            {
                drawBlackRoundFilledRect(slotX + TILE_SIZE * col, slotY + TILE_SIZE * row, TILE_SIZE, TILE_SIZE, 100);
            }
        }
    }

    private static void drawItems(Player player)
    {
        final int itemXStart = FRAME_X + 25;
        final int itemYStart = FRAME_Y + 25;
        int itemX = itemXStart;
        int itemY = itemYStart;
        for (int i = 0; i < player.inventory.size(); i++)
        {
            Object object = player.inventory.get(i);

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

    private static void drawCursor()
    {
        final int cursorX = FRAME_X + 25;
        final int cursorY = FRAME_Y + 25;
        drawRoundRect(cursorX + TILE_SIZE * inventorySlotCursorCol,
                cursorY + TILE_SIZE * inventorySlotCursorRow,
                TILE_SIZE, TILE_SIZE, 2);
    }

    private static void drawDescriptionWindow(Player player)
    {
        int textX = FRAME_X + 20;
        int textY = FRAME_Y + FRAME_HEIGHT + 30;

        g2.setFont(g2.getFont().deriveFont(12F));

        int inventoryItemIndex = getInventoryItemIndex();
        if (inventoryItemIndex < player.inventory.size())
        {
            drawSubWindow(FRAME_X, FRAME_Y + FRAME_HEIGHT, FRAME_WIDTH, FRAME_HEIGHT - TILE_SIZE * 3, 5);
            String[] split = player.inventory.get(inventoryItemIndex).description.split("\n");
            for (String line : split)
            {
                g2.drawString(line, textX, textY);
                textY += 15;
            }
        }
    }
}
