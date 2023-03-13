package utilities.drawers;

import View.GamePanel;

import java.awt.*;
import java.text.DecimalFormat;

import static utilities.drawers.UserInterfaceController.g2;


public class GameTimeDrawer
{
    public static double playTime;
    public static DecimalFormat decimalFormat = new DecimalFormat("#0.00");

    public static void drawTime()
    {
        g2.setColor(Color.white);
        playTime += (double) 1 / 60;
        g2.drawString("Time: " + decimalFormat.format(playTime), GamePanel.tileSize * 11, GamePanel.tileSize);
    }
}
