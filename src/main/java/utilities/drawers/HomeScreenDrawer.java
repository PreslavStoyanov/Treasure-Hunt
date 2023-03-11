package utilities.drawers;

import static utilities.drawers.DrawerUtils.drawCenteredText;
import static utilities.drawers.HomeScreenDrawer.HomeMenuOption.*;
import static utilities.drawers.UserInterfaceController.g2;
import static utilities.drawers.UserInterfaceController.pixelFont;

public class HomeScreenDrawer
{
    public static HomeMenuOption homeMenuOption = NEW_GAME;

    public static void drawHomeScreen()
    {
        g2.setFont(pixelFont);
        drawCenteredText("Treasure Hunt", 3, true, 56F);
        drawCenteredText("NEW GAME", 9, homeMenuOption.equals(NEW_GAME), 36F);
        drawCenteredText("HELP", 10, homeMenuOption.equals(HELP), 36F);
        drawCenteredText("QUIT", 11, homeMenuOption.equals(QUIT), 36);
    }

    public enum HomeMenuOption
    {
        NEW_GAME,
        HELP,
        QUIT
    }

}
