package utilities.drawers;

import View.GamePanel;

import java.awt.*;
import java.util.List;
import java.util.stream.IntStream;

import static utilities.drawers.UserInterfaceController.g2;

public class DrawerUtils
{

    public static void drawCenteredText(String text, float numberOfTilesFromTop, boolean shouldDrawShadow, float fontSize)
    {
        g2.setFont(g2.getFont().deriveFont(fontSize));
        float x = getXForCenteredText(text);
        float y = GamePanel.tileSize * numberOfTilesFromTop;
        if (shouldDrawShadow)
        {
            drawShadow(text, x, y);
        }

        drawText(text, x, y);
    }

    private static void drawText(String text, float x, float y)
    {
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
    }

    private static void drawShadow(String text, float x, float y)
    {
        g2.setColor(Color.black);
        g2.drawString(text, x + 5, y + 5);
    }

    private static int getXForCenteredText(String text)
    {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return GamePanel.screenWidth / 2 - length / 2;
    }

    public static float getXForAlignToRightText(String text, float tailX)
    {
        float length = (float) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
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

    public static void drawListWithSameMarginRightAligned(List<String> values, float margin, float x, float y)
    {
        IntStream.range(0, values.size()).forEach(i -> g2.drawString(values.get(i), getXForAlignToRightText(values.get(i), x), y + margin * i));
    }

    public static void drawListWithSameMargin(List<String> values, float margin, float x, float y)
    {
        IntStream.range(0, values.size()).forEach(i -> g2.drawString(values.get(i), x, y + margin * i));
    }
}
