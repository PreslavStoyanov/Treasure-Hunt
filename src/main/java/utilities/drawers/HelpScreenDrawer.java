package utilities.drawers;

import java.util.List;

import static application.GamePanel.*;
import static utilities.drawers.DrawerUtils.*;
import static utilities.drawers.UserInterfaceController.g2;
import static utilities.drawers.UserInterfaceController.pixelFont;

public class HelpScreenDrawer
{
    private static final int FRAME_WIDTH = TILE_SIZE * 7;
    private static final int FRAME_HEIGHT = TILE_SIZE * 7;
    private static final int FRAME_X = (SCREEN_WIDTH - FRAME_WIDTH) / 2;
    private static final int FRAME_Y = (SCREEN_HEIGHT - FRAME_HEIGHT) / 2;
    private static final float MARGIN = TILE_SIZE * 0.5F;

    public static void drawHelpScreen()
    {
        g2.setFont(pixelFont);
        drawSubWindow(FRAME_X, FRAME_Y, FRAME_WIDTH, FRAME_HEIGHT, 3);
        drawCenteredText("Buttons", 2, false, 18F);
        drawButtons();
        drawCenteredText("BACK", 7.5F, true, 15F);
    }

    private static void drawButtons()
    {
        g2.setFont(g2.getFont().deriveFont(10F));
        float textX = FRAME_X + 20;
        float textY = TILE_SIZE * 2.6F;
        drawListWithSameMargin(
                List.of("MOVE",
                        "ATTACK",
                        "SHOOT",
                        "CONCENTRATE",
                        "TALK",
                        "INVENTORY",
                        "PAUSE",
                        "OPTIONS",
                        "SHOW COORDINATES"),
                MARGIN, textX, textY);

        textX = FRAME_X + FRAME_WIDTH - 30;
        drawListWithSameMarginRightAligned(
                List.of("WASD",
                        "SPACE",
                        "R",
                        "F",
                        "E",
                        "Q",
                        "P",
                        "ESCAPE",
                        "O"),
                MARGIN, textX, textY);
    }
}
