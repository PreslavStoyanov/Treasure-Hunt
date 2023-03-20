package utilities.drawers;

import static utilities.drawers.DrawerUtils.drawCenteredText;
import static utilities.drawers.UserInterfaceController.g2;
import static utilities.drawers.UserInterfaceController.pixelFont;

public class HelpScreenDrawer
{
    public static void drawHelpScreen()
    {
        g2.setFont(pixelFont);
        drawCenteredText("Buttons", 1, false, 24F);
        drawCenteredText("W A S D | UP LEFT DOWN RIGHT", 1.6F, false, 15F);
        drawCenteredText("SPACE | ATTACK", 2.2F, false, 15F);
        drawCenteredText("P | PAUSE", 2.8F, false, 15F);
        drawCenteredText("E | TALK WITH NPC", 3.4F, false, 15F);
        drawCenteredText("B | CHARACTER MENU", 4, false, 15F);
        drawCenteredText("O | SHOW COORDINATES", 4.6F, false, 15F);
        drawCenteredText("(HOLD E | DANCE)", 10F, false, 10F);
        drawCenteredText("MAIN MENU", 11, true, 36F);
    }
}
