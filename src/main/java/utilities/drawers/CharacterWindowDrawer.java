package utilities.drawers;

import assets.entities.movingentities.liveentities.Player;

import java.util.List;

import static application.GamePanel.TILE_SIZE;
import static utilities.drawers.DrawerUtils.*;
import static utilities.drawers.UserInterfaceController.g2;

public class CharacterWindowDrawer
{
    private static final int FRAME_X = TILE_SIZE;
    private static final int FRAME_Y = TILE_SIZE;
    private static final int FRAME_WIDTH = TILE_SIZE * 5;
    private static final int FRAME_HEIGHT = TILE_SIZE * 7;
    private static final int LINE_HEIGHT = 35;

    public static void drawCharacterWindow(Player player)
    {
        drawSubWindow(FRAME_X, FRAME_Y, FRAME_WIDTH, FRAME_HEIGHT, 5);
        g2.setFont(g2.getFont().deriveFont(15F));
        drawLabels();
        drawParameters(player);
    }

    private static void drawLabels()
    {
        float textX = FRAME_X + 20;
        float textY = FRAME_Y + TILE_SIZE;
        drawListWithSameMargin(List.of(
                        "Level",
                        "Max Energy",
                        "Strength",
                        "Agility",
                        "Speed",
                        "Attack",
                        "Defense",
                        "Coins"),
                LINE_HEIGHT, textX, textY);
    }

    private static void drawParameters(Player player)
    {
        int textX = FRAME_X + FRAME_WIDTH - 30;
        int textY = FRAME_Y + TILE_SIZE;
        drawListWithSameMarginRightAligned(List.of(
                        String.valueOf(player.level),
                        String.valueOf(player.maxEnergy),
                        String.valueOf(player.strength),
                        String.valueOf(player.agility),
                        String.valueOf(player.movingSpeed),
                        String.valueOf(player.attackValue),
                        String.valueOf(player.defense),
                        String.valueOf(player.coins)),
                LINE_HEIGHT, textX, textY);
    }
}
