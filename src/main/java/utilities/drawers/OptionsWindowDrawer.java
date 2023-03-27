package utilities.drawers;

import static application.GamePanel.*;
import static utilities.drawers.DrawerUtils.*;
import static utilities.keyboard.OptionsScreenKeyboardHandler.*;
import static utilities.keyboard.OptionsScreenKeyboardHandler.OptionsMenuSelection.*;

public class OptionsWindowDrawer
{
    private static final int FRAME_WIDTH = TILE_SIZE * 7;
    private static final int FRAME_HEIGHT = TILE_SIZE * 7;
    private static final int FRAME_X = (SCREEN_WIDTH - FRAME_WIDTH) / 2;
    private static final int FRAME_Y = (SCREEN_HEIGHT - FRAME_HEIGHT) / 2;

    public static void drawOptionWindow()
    {
        drawSubWindow(FRAME_X, FRAME_Y, FRAME_WIDTH, FRAME_HEIGHT, 3);
        drawCenteredText("Options", 2, false, 18F);
        drawOptions();
    }

    private static void drawOptions()
    {
        int textX = FRAME_X + HALF_TILE_SIZE;
        OptionsMenuSelection optionsMenuSelection = optionMenuSelections.get(optionSelection);
        drawText("Full Screen", textX, FRAME_Y + TILE_SIZE * 2, optionsMenuSelection.equals(FULL_SCREEN), 15F);
        drawText("(restart to reverse)", textX, FRAME_Y + TILE_SIZE * 2.3F, optionsMenuSelection.equals(FULL_SCREEN), 10F);
        drawText("Music", textX, FRAME_Y + TILE_SIZE * 2.8F, optionsMenuSelection.equals(MUSIC), 15F);
        drawText("Sound Effects", textX, FRAME_Y + TILE_SIZE * 3.6F, optionsMenuSelection.equals(SOUND_EFFECTS), 15F);
        drawText("Help", textX, FRAME_Y + TILE_SIZE * 4.4F, optionsMenuSelection.equals(HELP), 15F);
        drawText("Quit", textX, FRAME_Y + TILE_SIZE * 5.2F, optionsMenuSelection.equals(QUIT), 15F);
        drawText("Back", textX, FRAME_Y + TILE_SIZE * 6F, optionsMenuSelection.equals(BACK), 15F);
    }
}
