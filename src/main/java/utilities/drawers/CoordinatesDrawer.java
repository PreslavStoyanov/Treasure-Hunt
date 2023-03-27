package utilities.drawers;

import assets.entities.movingentities.liveentities.Player;

import java.awt.*;
import java.util.List;
import java.util.stream.IntStream;

import static application.GamePanel.TILE_SIZE;
import static utilities.drawers.UserInterfaceController.g2;

public class CoordinatesDrawer
{
    public static boolean shouldShowCoordinates = false;

    public static void showPlayerCoordinates(long drawStart, Player player)
    {
        g2.setFont(new Font("Arial", Font.BOLD, 20));
        g2.setColor(Color.white);
        int x = 10;
        int y = TILE_SIZE * 6;
        int lineHeight = 20;

        List<String> coordinates = List.of(
                "Invincible: " + player.invincibleCounter,
                "WorldX: " + player.worldX,
                "WorldY: " + player.worldY,
                "Col (x): " + ((player.worldX + player.solidArea.x) / TILE_SIZE),
                "Row: (y): " + ((player.worldY + player.solidArea.y) / TILE_SIZE),
                "Draw Time: " + (System.nanoTime() - drawStart));

        IntStream.range(0, coordinates.size())
                .forEach(i -> g2.drawString(coordinates.get(i), x, y + lineHeight * i));
    }
}
