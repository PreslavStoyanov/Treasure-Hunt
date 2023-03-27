package utilities.drawers;

import utilities.keyboard.EndScreenKeyboardHandler;

import java.awt.*;

import static application.GamePanel.SCREEN_WIDTH;
import static application.GamePanel.SCREEN_HEIGHT;
import static utilities.drawers.DrawerUtils.drawCenteredText;
import static utilities.drawers.DrawerUtils.drawRoundFilledRect;
import static utilities.drawers.UserInterfaceController.g2;
import static utilities.drawers.UserInterfaceController.pixelFont;
import static utilities.keyboard.EndScreenKeyboardHandler.*;

public class EndScreenDrawer
{
    public static void drawGameWinScreen(double playTime)
    {
        g2.setFont(pixelFont);
        drawRoundFilledRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, new Color(0, 0, 0, 150));
        drawCenteredText("Congratulations!", 2, true, 40F);
        drawCenteredText(String.format("You found the treasure for : %.2fs!", playTime), 3, true, 20F);
        drawCenteredText("NEW GAME", 6, optionSelection == 1, 20F);
        drawCenteredText("MAIN MENU", 7, optionSelection == 2, 20F);
    }

    public static void drawGameLoseScreen()
    {
        g2.setFont(pixelFont);
        drawRoundFilledRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, new Color(0, 0, 0, 150));
        drawCenteredText("Game Over!", 2, true, 40F);
        drawCenteredText("RETRY", 6, optionSelection == 1, 20F);
        drawCenteredText("MAIN MENU", 7, optionSelection == 2, 20F);
    }
}
