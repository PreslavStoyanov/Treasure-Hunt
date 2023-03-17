package utilities.drawers;

import static utilities.drawers.DrawerUtils.drawCenteredText;
import static utilities.drawers.UserInterfaceController.g2;
import static utilities.drawers.UserInterfaceController.pixelFont;

public class EndScreenDrawer
{
    public static void drawGameWinScreen(double playTime)
    {
        g2.setFont(pixelFont);
        drawCenteredText("Congratulations!", 3, true, 40F);
        drawCenteredText("You found the treasure!", 5, true, 30F);
        drawCenteredText(String.format("Your time played is : %.2f!", playTime), 8, false, 20F);
    }

    public static void drawGameLoseScreen(double playTime)
    {
        g2.setFont(pixelFont);
        drawCenteredText("Game Over!", 3, true, 40F);
        drawCenteredText("You didn't found the treasure!", 5, true, 24F);
        drawCenteredText(String.format("Your time played is : %.2f!", playTime), 8, false, 20F);
    }
}
