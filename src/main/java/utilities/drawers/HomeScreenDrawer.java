package utilities.drawers;

import static utilities.drawers.DrawerUtils.drawCenteredText;
import static utilities.drawers.UserInterfaceController.pixelFont;

public class HomeScreenDrawer
{
    private int commandNumber;

    public HomeScreenDrawer()
    {
        commandNumber = 0;
    }

    public int getCommandNumber()
    {
        return commandNumber;
    }

    public void setCommandNumber(int commandNumber)
    {
        this.commandNumber = commandNumber;
    }

    public void decreaseCommandNumber()
    {
        --commandNumber;
    }

    public void increaseCommandNumber()
    {
        ++commandNumber;
    }

    public void drawHomeScreen()
    {
        UserInterfaceController.g2.setFont(pixelFont);
        drawCenteredText("Treasure Hunt", 3, true, 56F);
        drawCenteredText("NEW GAME", 9, commandNumber == 0, 36F);
        drawCenteredText("HELP", 10, commandNumber == 1, 36F);
        drawCenteredText("QUIT", 11, commandNumber == 2, 36);
    }
}
