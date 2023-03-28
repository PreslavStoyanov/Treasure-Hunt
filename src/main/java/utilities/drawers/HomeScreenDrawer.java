package utilities.drawers;

import static utilities.drawers.DrawerUtils.drawCenteredText;
import static utilities.drawers.UserInterfaceController.g2;
import static utilities.drawers.UserInterfaceController.pixelFont;
import static utilities.statehandlers.HomeStateHandler.HomeMenuSelection.*;
import static utilities.statehandlers.HomeStateHandler.homeMenuSelection;
import static utilities.statehandlers.HomeStateHandler.homeMenuSelections;

public class HomeScreenDrawer
{
    public static void drawHomeScreen()
    {
        g2.setFont(pixelFont);
        drawCenteredText("Treasure Hunt", 3, true, 56F);
        drawCenteredText("NEW GAME", 6.5F, homeMenuSelections.get(homeMenuSelection).equals(NEW_GAME), 24F);
        drawCenteredText("OPTIONS", 7.5F, homeMenuSelections.get(homeMenuSelection).equals(OPTIONS), 24F);
        drawCenteredText("QUIT", 8.5F, homeMenuSelections.get(homeMenuSelection).equals(QUIT), 24);
    }
}
