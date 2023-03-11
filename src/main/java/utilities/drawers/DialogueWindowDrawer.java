package utilities.drawers;

import View.GamePanel;

import static utilities.drawers.DrawerUtils.drawSubWindow;
import static utilities.drawers.UserInterfaceController.g2;

public class DialogueWindowDrawer
{
    public static String currentDialogue = "";

    public static void drawDialogueScreen()
    {
        drawDialogueWindow();
        drawDialogueLines();
    }

    private static void drawDialogueLines()
    {
        int x = GamePanel.tileSize * 3;
        int y = GamePanel.tileSize / 2 + GamePanel.tileSize;
        for (String line : currentDialogue.split("\n"))
        {
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    private static void drawDialogueWindow()
    {
        int x = GamePanel.tileSize * 2;
        int y = GamePanel.tileSize / 2;
        int width = GamePanel.screenWidth - (GamePanel.tileSize * 4);
        int height = GamePanel.tileSize * 4;

        drawSubWindow(x, y, width, height);
    }
}
