package utilities.drawers;

import java.awt.*;

import static application.GamePanel.*;
import static utilities.drawers.DrawerUtils.*;
import static utilities.statehandlers.OptionsStateHandler.*;
import static utilities.statehandlers.OptionsStateHandler.OptionsMenuSelection.*;

public class OptionsWindowDrawer
{
    private static final int FRAME_WIDTH = TILE_SIZE * 7;
    private static final int FRAME_HEIGHT = TILE_SIZE * 7;
    private static final int FRAME_X = (SCREEN_WIDTH - FRAME_WIDTH) / 2;
    private static final int FRAME_Y = (SCREEN_HEIGHT - FRAME_HEIGHT) / 2;

    public static void drawOptionWindow(boolean isOnFullScreen, boolean musicVolumeToggle, boolean soundEffectVolumeToggle)
    {
        drawSubWindow(FRAME_X, FRAME_Y, FRAME_WIDTH, FRAME_HEIGHT, 3);
        drawCenteredText("Options", 2, false, 18F);
        drawOptions();
        drawCheckBoxes(isOnFullScreen, musicVolumeToggle, soundEffectVolumeToggle);
    }

    private static void drawCheckBoxes(boolean isOnFullScreen, boolean musicVolumeToggle, boolean soundEffectVolumeToggle)
    {
        drawRoundRect(FRAME_X + FRAME_WIDTH - TILE_SIZE, FRAME_Y + HALF_TILE_SIZE * 3 + 7, HALF_TILE_SIZE, HALF_TILE_SIZE, 1);
        drawRoundRect(FRAME_X + FRAME_WIDTH - TILE_SIZE, FRAME_Y + HALF_TILE_SIZE * 5, HALF_TILE_SIZE, HALF_TILE_SIZE, 1);
        drawRoundRect(FRAME_X + FRAME_WIDTH - TILE_SIZE, FRAME_Y + HALF_TILE_SIZE * 7 - 7, HALF_TILE_SIZE, HALF_TILE_SIZE, 1);

        if (isOnFullScreen)
        {
            drawRoundFilledRect(FRAME_X + FRAME_WIDTH - TILE_SIZE, FRAME_Y + HALF_TILE_SIZE * 3 + 7, HALF_TILE_SIZE, HALF_TILE_SIZE, Color.white);
        }
        if (musicVolumeToggle)
        {
            drawRoundFilledRect(FRAME_X + FRAME_WIDTH - TILE_SIZE, FRAME_Y + HALF_TILE_SIZE * 5, HALF_TILE_SIZE, HALF_TILE_SIZE, Color.white);
        }
        if (soundEffectVolumeToggle)
        {
            drawRoundFilledRect(FRAME_X + FRAME_WIDTH - TILE_SIZE, FRAME_Y + HALF_TILE_SIZE * 7 - 7, HALF_TILE_SIZE, HALF_TILE_SIZE, Color.white);
        }
    }

    private static void drawOptions()
    {
        int textX = FRAME_X + HALF_TILE_SIZE;
        OptionsMenuSelection optionsMenuSelection = optionMenuSelections.get(optionSelection);
        drawText("Full Screen", textX, FRAME_Y + TILE_SIZE * 2, optionsMenuSelection.equals(FULL_SCREEN), 15F);
        if (fullScreenHeight != SCREEN_HEIGHT)
        {
            drawText("(restart to minimize)", textX, FRAME_Y + TILE_SIZE * 2.3F, optionsMenuSelection.equals(FULL_SCREEN), 10F);
        }
        drawText("Music", textX, FRAME_Y + TILE_SIZE * 2.8F, optionsMenuSelection.equals(MUSIC), 15F);
        drawText("Sound Effects", textX, FRAME_Y + TILE_SIZE * 3.6F, optionsMenuSelection.equals(SOUND_EFFECTS), 15F);
        drawText("Help", textX, FRAME_Y + TILE_SIZE * 4.4F, optionsMenuSelection.equals(HELP), 15F);
        drawText("Quit", textX, FRAME_Y + TILE_SIZE * 5.2F, optionsMenuSelection.equals(QUIT), 15F);
        drawText("Back", textX, FRAME_Y + TILE_SIZE * 6F, optionsMenuSelection.equals(BACK), 15F);
    }
}
