package utilities.drawers;

import static utilities.drawers.DrawerUtils.drawCenteredText;
import static utilities.drawers.UserInterfaceController.pixelFont;

public class HelpScreenDrawer
{
    public void drawHelpScreen()
    {
        UserInterfaceController.g2.setFont(pixelFont);
        drawCenteredText("Buttons", 1, false, 24F);
        drawCenteredText("W | MOVE UP", 2, false, 15F);
        drawCenteredText("S | MOVE DOWN", 3, false, 15F);
        drawCenteredText("A | MOVE LEFT", 4, false, 15F);
        drawCenteredText("D | MOVE RIGHT", 5, false, 15F);
        drawCenteredText("SPACE | ATTACK", 6, false, 15F);
        drawCenteredText("P | PAUSE", 7, false, 15F);
        drawCenteredText("E | TALK WITH NPC", 8, false, 15F);
        drawCenteredText("MAIN MENU", 11, true, 36F);
    }
}
