package utilities.drawers;

import entities.types.Player;

import static application.GamePanel.tileSize;
import static utilities.drawers.DrawerUtils.drawRoundRect;
import static utilities.drawers.DrawerUtils.drawSubWindow;
import static utilities.drawers.UserInterfaceController.g2;

public class InventoryWindowDrawer
{
    private static final int FRAME_X = tileSize * 9;
    private static final int FRAME_Y = tileSize;
    private static final int FRAME_WIDTH = tileSize * 6;
    private static final int FRAME_HEIGHT = tileSize * 5;
    public static final int INVENTORY_COLS = 5;
    public static final int INVENTORY_ROWS = 4;
    public static int inventorySlotCursorCol = 0;
    public static int inventorySlotCursorRow = 0;

    public static void drawInventoryWindow(Player player)
    {
        drawSubWindow(FRAME_X, FRAME_Y, FRAME_WIDTH, FRAME_HEIGHT, 5);
        drawItems(player);
        drawCursor();
        drawDescriptionWindow(player);
    }

    private static void drawItems(Player player)
    {
        final int slotXStart = FRAME_X + 25;
        final int slotYStart = FRAME_Y + 25;
        int slotX = slotXStart;
        int slotY = slotYStart;
        for (int i = 0; i < player.inventory.size(); i++)
        {
            g2.drawImage(player.inventory.get(i).image.getScaledInstance(tileSize - 6, tileSize - 6, 1),
                    slotX + 3, slotY + 3, null);
            slotX += tileSize;

            if (i == 4 || i == 9 || i == 14)
            {
                slotY += tileSize;
                slotX = slotXStart;
            }
        }
    }

    private static void drawCursor()
    {
        final int cursorX = FRAME_X + 25;
        final int cursorY = FRAME_Y + 25;
        drawRoundRect(cursorX + tileSize * inventorySlotCursorCol,
                cursorY + tileSize * inventorySlotCursorRow,
                tileSize, tileSize, 2);
    }

    private static void drawDescriptionWindow(Player player)
    {
        drawSubWindow(FRAME_X, FRAME_Y + FRAME_HEIGHT, FRAME_WIDTH, FRAME_HEIGHT - tileSize * 2, 5);
        int textX = FRAME_X + 20;
        int textY = FRAME_Y + FRAME_HEIGHT + 30;

        g2.setFont(g2.getFont().deriveFont(12F));

        int inventoryItemIndex = getInventoryItemIndex();
        if (inventoryItemIndex < player.inventory.size())
        {
            String[] split = player.inventory.get(inventoryItemIndex).description.split("\n");
            for (String line : split)
            {
                g2.drawString(line, textX, textY);
                textY += 15;
            }
        }
    }

    private static int getInventoryItemIndex()
    {
        return inventorySlotCursorCol + inventorySlotCursorRow * INVENTORY_COLS;
    }
}
