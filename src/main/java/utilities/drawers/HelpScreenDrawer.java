package utilities.drawers;

import static application.GamePanel.*;
import static utilities.drawers.DrawerUtils.drawCenteredText;
import static utilities.drawers.DrawerUtils.drawSubWindow;
import static utilities.drawers.UserInterfaceController.g2;
import static utilities.drawers.UserInterfaceController.pixelFont;

public class HelpScreenDrawer
{
    private static final int FRAME_WIDTH = TILE_SIZE * 10;
    private static final int FRAME_HEIGHT = TILE_SIZE * 8;
    private static final int FRAME_X = (SCREEN_WIDTH - FRAME_WIDTH) / 2;
    private static final int FRAME_Y = (SCREEN_HEIGHT - FRAME_HEIGHT) / 2;

    public static void drawHelpScreen()
    {
        g2.setFont(pixelFont);
        drawSubWindow(FRAME_X, FRAME_Y, FRAME_WIDTH, FRAME_HEIGHT, 3);
        drawCenteredText("Buttons", 1.5F, false, 18F);
        drawCenteredText("W A S D | UP LEFT DOWN RIGHT", 2.6F, false, 15F);
        drawCenteredText("SPACE | ATTACK", 3.2F, false, 15F);
        drawCenteredText("R | SHOOT", 3.8F, false, 15F);
        drawCenteredText("F | CONCENTRATE", 4.4F, false, 15F);
        drawCenteredText("E | TALK WITH NPC", 5F, false, 15F);
        drawCenteredText("Q | INVENTORY", 5.6F, false, 15F);
        drawCenteredText("P | PAUSE", 6.2F, false, 15F);
        drawCenteredText("O | SHOW COORDINATES", 6.8F, false, 15F);
        drawCenteredText("ESCAPE | OPTIONS", 7.4F, false, 15F);
        drawCenteredText("BACK", 8.2F, true, 15F);
    }
}
