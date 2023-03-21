package utilities.drawers;

import assets.entities.movingentities.liveentities.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

import static application.Application.defaultImagesUrls;
import static application.GamePanel.halfTileSize;
import static application.GamePanel.tileSize;
import static utilities.drawers.DrawerUtils.drawRoundFilledRect;
import static utilities.drawers.DrawerUtils.drawRoundRect;
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
    }

    private void drawPlayerEnergy(int energy, int castEnergyNeeded)
    {
        double oneScale = (double) tileSize / castEnergyNeeded;
        double energyBarValue = oneScale * energy;

        drawRoundFilledRect(halfTileSize, tileSize + halfTileSize,
                tileSize * 3, halfTileSize,
                new Color(35, 35, 35));

        drawRoundFilledRect(halfTileSize, tileSize + halfTileSize,
                (int) energyBarValue * 3, halfTileSize,
                new Color(199, 183, 0));

        drawRoundRect(halfTileSize, tileSize + halfTileSize, tileSize * 3, halfTileSize, 2);
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
