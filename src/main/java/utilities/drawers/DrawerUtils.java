package utilities.drawers;

import View.GamePanel;

import java.awt.*;

import static utilities.drawers.UserInterfaceController.g2;

public class DrawerUtils
{

    public static void drawCenteredText(String text, int numberOfTilesFromTop, boolean shouldDrawShadow, float fontSize)
    {
        g2.setFont(g2.getFont().deriveFont(fontSize));
        int x = getXForCenteredText(text);
        int y = GamePanel.tileSize * numberOfTilesFromTop;
        if (shouldDrawShadow)
        {
            drawShadow(text, x, y);
        }

        drawText(text, x, y);
    }

    private static void drawText(String text, int x, int y)
    {
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
    }

    private static void drawShadow(String text, int x, int y)
    {
        g2.setColor(Color.black);
        g2.drawString(text, x + 5, y + 5);
    }

    private static int getXForCenteredText(String text)
    {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return GamePanel.screenWidth / 2 - length / 2;
    }

    public static int getXForAlignToRightText(String text, int tailX)
    {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return tailX - length;
    }

    public static void drawSubWindow(int x, int y, int width, int height)
    {
        Color c = new Color(0, 0, 0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }
}
