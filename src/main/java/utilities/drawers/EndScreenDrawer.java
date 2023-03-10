package utilities.drawers;

import View.GamePanel;

import java.awt.*;

import static utilities.drawers.DrawerUtils.drawCenteredText;
import static utilities.drawers.UIController.*;

public class EndScreenDrawer
{
    public void drawEndScreen(double playTime)
    {
        drawCenteredText("Congratulations!", 3, true, 40F);
        drawCenteredText("You found the treasure!", 5, true, 30F);
        drawCenteredText("Your Time is : " + decimalFormat.format(playTime) + "!", 8, false, 20F);
    }
}
