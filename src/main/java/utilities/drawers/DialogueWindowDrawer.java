package utilities.drawers;

import static application.GamePanel.*;
import static utilities.drawers.DrawerUtils.drawSubWindow;
import static utilities.drawers.UserInterfaceController.g2;

public class DialogueWindowDrawer
{
    private static final int FRAME_X = tileSize * 2;
    private static final int FRAME_Y = halfTileSize;
    private static final int FRAME_WIDTH = screenWidth - (tileSize * 4);
    private static final int FRAME_HEIGHT = tileSize * 4;

    public static String currentDialogue = "";

    public static void drawDialogueScreen()
    {
        drawSubWindow(FRAME_X, FRAME_Y, FRAME_WIDTH, FRAME_HEIGHT, 5);
        drawDialogueLines();
    }

    private static void drawDialogueLines()
    {
        int x = tileSize * 3;
        int y = halfTileSize + tileSize;
        for (String line : currentDialogue.split("\n"))
        {
            g2.drawString(line, x, y);
            y += 40;
        }
    }
}
