package utilities.drawers;


import entities.Entity.Player;

import java.awt.*;
import java.util.List;
import java.util.stream.IntStream;

import static View.GamePanel.tileSize;
import static utilities.drawers.UserInterfaceController.g2;

public class CoordinatesDrawer
{
    public static boolean shouldShowCoordinates = false;

    public static void showPlayerCoordinates(long drawStart, Player player)
    {
        g2.setFont(new Font("Arial", Font.BOLD, 20));
        g2.setColor(Color.white);
        int x = 10;
        int y = 400;
        int lineHeight = 20;
        
        List<String> coordinates = List.of(
                "Invincible: " + player.invincibleCounter,
                "WorldX: " + player.worldX,
                "WorldY: " + player.worldY,
                "Col (x): " + ((player.worldX + player.solidArea.x) / tileSize),
                "Row: (y): " + ((player.worldY + player.solidArea.y) / tileSize),
                "Draw Time: " + (System.nanoTime() - drawStart));

        IntStream.range(0, coordinates.size())
                .forEach(i -> g2.drawString(coordinates.get(i), x, y + lineHeight * i));
    }
}
