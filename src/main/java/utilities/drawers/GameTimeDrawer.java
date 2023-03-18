package utilities.drawers;

import application.GamePanel;

import java.awt.*;

import static utilities.drawers.UserInterfaceController.g2;


public class GameTimeDrawer
{
    public static double playTime = 0;

    public static void drawTime()
    {
        g2.setColor(Color.white);
        playTime += (double) 1 / 60;
        g2.drawString(String.format("Time : %.2f", playTime), GamePanel.tileSize * 11, GamePanel.tileSize);
    }
}
