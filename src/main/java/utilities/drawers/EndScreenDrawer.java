package utilities.drawers;

import static utilities.drawers.DrawerUtils.drawCenteredText;
import static utilities.drawers.GameTimeDrawer.decimalFormat;
import static utilities.drawers.UserInterfaceController.g2;
import static utilities.drawers.UserInterfaceController.pixelFont;

public class EndScreenDrawer
{
    public static void drawEndScreen(double playTime)
    {
        g2.setFont(pixelFont);
        drawCenteredText("Congratulations!", 3, true, 40F);
        drawCenteredText("You found the treasure!", 5, true, 30F);
        drawCenteredText("Your Time is : " + decimalFormat.format(playTime) + "!", 8, false, 20F);
    }
}
