package utilities.drawers;

import assets.entities.movingentities.liveentities.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

import static application.Application.defaultImagesUrls;
import static application.GamePanel.*;
import static utilities.drawers.DrawerUtils.*;
import static utilities.drawers.UserInterfaceController.g2;
import static utilities.drawers.UserInterfaceController.pixelFont;
import static utilities.images.ImageUtils.setupDefaultImage;

public class PlayerStatsDrawer
{
    private final BufferedImage heartFull;
    private final BufferedImage heartHalf;
    private final BufferedImage heartBlank;

    public PlayerStatsDrawer()
    {
        this.heartFull = setupDefaultImage(defaultImagesUrls.get("fullHeart"));
        this.heartHalf = setupDefaultImage(defaultImagesUrls.get("halfHeart"));
        this.heartBlank = setupDefaultImage(defaultImagesUrls.get("blankHeart"));
    }

    public void drawPlayerStats(Player player)
    {
        g2.setFont(pixelFont);
        drawBlankHearts(player.maxLife);
        drawHalfHearts(player.life);
        drawFullHearts(player.life);
        drawPlayerEnergy(player.energy, player.projectile.castEnergyNeeded);
        drawPlayerExpBar(player.exp, player.maxExp);
    }

    private void drawPlayerExpBar(int exp, int maxExp)
    {
        drawFillingBar(exp, maxExp,
                tileSize * 4, -6,
                8, tileSize, halfTileSize / 2,
                1, new Color(147, 178, 6));
    }

    private void drawPlayerEnergy(int energy, int castEnergyNeeded)
    {
        drawFillingBar(energy, castEnergyNeeded,
                halfTileSize, tileSize + halfTileSize,
                3, tileSize, halfTileSize,
                2, new Color(199, 183, 0));
    }

    private void drawBlankHearts(int maxLife)
    {
        int x = halfTileSize;
        for (int i = 0; i < maxLife / 2; i++)
        {
            g2.drawImage(heartBlank, x, halfTileSize, null);
            x += tileSize;
        }
    }

    private void drawHalfHearts(int life)
    {
        int x = halfTileSize;
        for (int i = 0; i < life; i += 2)
        {
            g2.drawImage(heartHalf, x, halfTileSize, null);
            x += tileSize;
        }
    }

    private void drawFullHearts(int life)
    {
        int x = halfTileSize;
        for (int i = 0; i < life; i++)
        {
            i++;
            if (i < life)
            {
                g2.drawImage(heartFull, x, halfTileSize, null);
            }
            x += tileSize;
        }
    }
}
