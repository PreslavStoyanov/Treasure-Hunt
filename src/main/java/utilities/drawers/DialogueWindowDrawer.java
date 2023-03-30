package utilities.drawers;

import static application.GamePanel.*;
import static utilities.drawers.DrawerUtils.drawSubWindow;
import static utilities.drawers.DrawerUtils.drawText;
import static utilities.drawers.UserInterfaceController.g2;

public class DialogueWindowDrawer
{
    private static final int FRAME_WIDTH = SCREEN_WIDTH - (TILE_SIZE * 4);
    private static final int FRAME_HEIGHT = TILE_SIZE * 4;
    private static final int FRAME_X = (SCREEN_WIDTH - FRAME_WIDTH) / 2;
    private static final int FRAME_Y = HALF_TILE_SIZE;

    public static String currentDialogue = "";

    public static void drawDialogueScreen()
    {
        drawSubWindow(FRAME_X, FRAME_Y, FRAME_WIDTH, FRAME_HEIGHT, 5);
        drawDialogueLines();
        drawText("TRADE", FRAME_X + FRAME_WIDTH - TILE_SIZE * 2, FRAME_Y + FRAME_HEIGHT - 20, true, 15F);
    }

    private static void drawDialogueLines()
    {
        int x = TILE_SIZE * 3;
        int y = HALF_TILE_SIZE + TILE_SIZE;
        for (String line : currentDialogue.split("\n"))
        {
            g2.drawString(line, x, y);
            y += 40;
        }
    }
}
