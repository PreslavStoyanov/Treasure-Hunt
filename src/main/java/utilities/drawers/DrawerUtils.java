package utilities.drawers;

import application.GamePanel;

import java.awt.*;
import java.util.List;
import java.util.stream.IntStream;

import static application.GamePanel.tileSize;
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

    public static void drawFillingBar(int value, int valueMax,
                               int x, int y,
                               int scale, int width, int height,
                               int strokeWidth, Color color)
    {
        double oneScale = (double) tileSize / valueMax;
        double barValue = oneScale * value;
        drawRoundFilledRect(x, y, width * scale, height, new Color(35, 35, 35));
        drawRoundFilledRect(x, y, (int) barValue * scale, height, color);
        drawRoundRect(x, y, width * scale, height, strokeWidth);
    }

    public static void drawSubWindow(int x, int y, int width, int height, int strokeWidth)
    {
        drawBlackRoundFilledRect(x, y, width, height, 210);
        drawRoundRect(x + 5, y + 5, width - 10, height - 10, strokeWidth);
    }

    public static void drawBlackRoundFilledRect(int x, int y, int width, int height, int alpha)
    {
        drawRoundFilledRect(x, y, width, height, new Color(0, 0, 0, alpha));
    }

    public static void drawRoundFilledRect(int x, int y, int width, int height, Color color)
    {
        g2.setColor(color);
        g2.fillRoundRect(x, y, width, height, 15, 15);
    }

    public static void drawRoundRect(int x, int y, int width, int height, int strokeWidth)
    {
        g2.setColor(new Color(255, 255, 255));
        g2.setStroke(new BasicStroke(strokeWidth));
        g2.drawRoundRect(x, y, width, height, 15, 15);
    }

    public static void drawListWithSameMarginRightAligned(List<String> values, float margin, float x, float y)
    {
        IntStream.range(0, values.size()).forEach(i -> g2.drawString(values.get(i), getXForAlignToRightText(values.get(i), x), y + margin * i));
    }

    public static void drawListWithSameMargin(List<String> values, float margin, float x, float y)
    {
        IntStream.range(0, values.size()).forEach(i -> g2.drawString(values.get(i), x, y + margin * i));
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

    private static float getXForAlignToRightText(String text, float tailX)
    {
        float length = (float) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return tailX - length;
    }
}
