package utilities.drawers;

import View.GamePanel;

import java.awt.*;
import java.util.List;

import static utilities.drawers.DrawerUtils.drawSubWindow;
import static utilities.drawers.DrawerUtils.getXForAlignToRightText;
import static utilities.drawers.UIController.g2;

public class CharacterWindowDrawer
{

    private static final int LINE_HEIGHT = 35;

    private final GamePanel gp;

    public CharacterWindowDrawer(GamePanel gp)
    {
        this.gp = gp;
    }

    public void drawCharacterScreen()
    {
        final int frameX = GamePanel.tileSize;
        final int frameY = GamePanel.tileSize;
        final int frameWidth = GamePanel.tileSize * 5;
        final int frameHeight = GamePanel.tileSize * 10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(15F));

        int textX = frameX + 20;
        int textY = frameY + GamePanel.tileSize;
        drawLabels(textX, textY);

        textX = frameX + frameWidth - 30;
        drawParameters(textX, textY);
    }

    private void drawParameters(int textX, int textY)
    {
        java.util.List<String> parameters = java.util.List.of(
                gp.player.life + "/" + gp.player.maxLife,
                String.valueOf(gp.player.level),
                String.valueOf(gp.player.strength),
                String.valueOf(gp.player.agility),
                String.valueOf(gp.player.attack),
                String.valueOf(gp.player.defense),
                String.valueOf(gp.player.exp),
                String.valueOf(gp.player.nextLevelExp),
                String.valueOf(gp.player.coins));

        for (int i = 0; i < parameters.size(); i++)
        {
            String parameter = parameters.get(i);
            g2.drawString(parameter, getXForAlignToRightText(parameter, textX), textY + LINE_HEIGHT * i);
        }

        g2.drawImage(gp.player.currentWeapon.image, textX - GamePanel.tileSize, textY + LINE_HEIGHT * 7 + GamePanel.tileSize, null);
        g2.drawImage(gp.player.currentShield.image, textX - GamePanel.tileSize, textY + LINE_HEIGHT * 7 + GamePanel.tileSize * 2 + 10, null);

    }

    private static void drawLabels(int textX, int textY)
    {
        java.util.List<String> labels = List.of(
                "Life",
                "Level",
                "Strength",
                "Agility",
                "Attack",
                "Defense",
                "Exp",
                "Next level",
                "Coins");

        for (int i = 0; i < labels.size(); i++)
        {
            g2.drawString(labels.get(i), textX, textY + LINE_HEIGHT * i);
        }

        g2.drawString("Weapon", textX, textY + LINE_HEIGHT * 9 + 10);
        g2.drawString("Shield", textX, textY + LINE_HEIGHT * 10 + 30);
    }
}
