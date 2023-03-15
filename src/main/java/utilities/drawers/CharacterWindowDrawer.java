package utilities.drawers;

import entities.types.Player;

import java.awt.*;
import java.util.List;

import static application.GamePanel.tileSize;
import static utilities.drawers.DrawerUtils.*;
import static utilities.drawers.UserInterfaceController.g2;

public class CharacterWindowDrawer
{
    private static final int LINE_HEIGHT = 35;

    public static void drawCharacterScreen(Player player)
    {
        final int frameX = tileSize;
        final int frameY = tileSize;
        final int frameWidth = tileSize * 5;
        final int frameHeight = tileSize * 10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(15F));

        int textX = frameX + 20;
        int textY = frameY + tileSize;
        drawLabels(textX, textY);

        textX = frameX + frameWidth - 30;
        drawParameters(player, textX, textY);
    }

    private static void drawParameters(Player player, int textX, int textY)
    {
        drawListWithSameMarginRightAligned(List.of(
                        player.life + "/" + player.maxLife,
                        String.valueOf(player.level),
                        String.valueOf(player.strength),
                        String.valueOf(player.agility),
                        String.valueOf(player.attack),
                        String.valueOf(player.defense),
                        String.valueOf(player.exp),
                        String.valueOf(player.nextLevelExp),
                        String.valueOf(player.coins)),
                LINE_HEIGHT, textX, textY);

        g2.drawImage(player.currentWeapon.image, textX - tileSize, textY + LINE_HEIGHT * 7 + tileSize, null);
        g2.drawImage(player.currentShield.image, textX - tileSize, textY + LINE_HEIGHT * 7 + tileSize * 2 + 10, null);
    }

    private static void drawLabels(float textX, float textY)
    {
        drawListWithSameMargin(List.of(
                        "Life",
                        "Level",
                        "Strength",
                        "Agility",
                        "Attack",
                        "Defense",
                        "Exp",
                        "Next level",
                        "Coins"),
                LINE_HEIGHT, textX, textY);

        g2.drawString("Weapon", textX, textY + LINE_HEIGHT * 9 + 10);
        g2.drawString("Shield", textX, textY + LINE_HEIGHT * 10 + 30);
    }
}
