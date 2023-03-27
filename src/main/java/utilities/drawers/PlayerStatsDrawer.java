package utilities.drawers;

import assets.entities.movingentities.liveentities.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

import static application.Application.objectsImagesUrls;
import static application.GamePanel.HALF_TILE_SIZE;
import static application.GamePanel.TILE_SIZE;
import static utilities.drawers.DrawerUtils.drawFillingBar;
import static utilities.drawers.UserInterfaceController.g2;
import static utilities.drawers.UserInterfaceController.pixelFont;
import static utilities.images.ImageUtils.setupDefaultSizeImage;

public class PlayerStatsDrawer
{
    private final BufferedImage heartFull;
    private final BufferedImage heartHalf;
    private final BufferedImage heartBlank;
    private final BufferedImage energyImage;

    public PlayerStatsDrawer()
    {
        heartFull = setupDefaultSizeImage(objectsImagesUrls.get("fullHeart"));
        heartHalf = setupDefaultSizeImage(objectsImagesUrls.get("halfHeart"));
        heartBlank = setupDefaultSizeImage(objectsImagesUrls.get("blankHeart"));
        energyImage = setupDefaultSizeImage(objectsImagesUrls.get("energy"));
    }

    public void drawPlayerStats(Player player)
    {
        g2.setFont(pixelFont);
        drawHearts(player);
        drawPlayerEnergy(player.energy, player.maxEnergy);
        drawPlayerExpBar(player.exp, player.maxExp);
    }

    private void drawHearts(Player player)
    {
        drawBlankHearts(player.maxLife);
        drawHalfHearts(player.life);
        drawFullHearts(player.life);
    }

    private void drawPlayerExpBar(int exp, int maxExp)
    {
        drawFillingBar(exp, maxExp,
                TILE_SIZE * 4, -6,
                8, TILE_SIZE, HALF_TILE_SIZE / 2,
                1, new Color(147, 178, 6));
    }

    private void drawPlayerEnergy(int energy, int maxEnergy)
    {
        drawFillingBar(energy, maxEnergy,
                TILE_SIZE, TILE_SIZE + HALF_TILE_SIZE,
                3, TILE_SIZE, HALF_TILE_SIZE,
                2, new Color(247, 255, 25));
        g2.drawImage(energyImage, HALF_TILE_SIZE, TILE_SIZE + 16, null);
    }

    private void drawBlankHearts(int maxLife)
    {
        int x = HALF_TILE_SIZE;
        for (int i = 0; i < maxLife / 2; i++)
        {
            g2.drawImage(heartBlank, x, HALF_TILE_SIZE, null);
            x += TILE_SIZE;
        }
    }

    private void drawHalfHearts(int life)
    {
        int x = HALF_TILE_SIZE;
        for (int i = 0; i < life; i += 2)
        {
            g2.drawImage(heartHalf, x, HALF_TILE_SIZE, null);
            x += TILE_SIZE;
        }
    }

    private void drawFullHearts(int life)
    {
        int x = HALF_TILE_SIZE;
        for (int i = 0; i < life; i++)
        {
            i++;
            if (i < life)
            {
                g2.drawImage(heartFull, x, HALF_TILE_SIZE, null);
            }
            x += TILE_SIZE;
        }
    }
}
