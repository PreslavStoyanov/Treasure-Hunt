package utilities.drawers;

import static utilities.drawers.DrawerUtils.drawCenteredText;
import static utilities.drawers.UIController.decimalFormat;
import static utilities.drawers.UIController.pixelFont;

public class EndScreenDrawer
{
    public void drawEndScreen(double playTime)
    {
        UIController.g2.setFont(pixelFont);
        drawCenteredText("Congratulations!", 3, true, 40F);
        drawCenteredText("You found the treasure!", 5, true, 30F);
        drawCenteredText("Your Time is : " + decimalFormat.format(playTime) + "!", 8, false, 20F);
    }
}
