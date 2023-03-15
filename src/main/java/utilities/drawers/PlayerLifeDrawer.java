package utilities.drawers;

import application.GamePanel;
import entities.types.Player;

import java.awt.image.BufferedImage;

import static application.Main.properties;
import static utilities.drawers.UserInterfaceController.g2;
import static utilities.drawers.UserInterfaceController.pixelFont;
import static utilities.images.ImageUtils.setupDefaultImage;

public class PlayerLifeDrawer
{
    private final BufferedImage heartFull;
    private final BufferedImage heartHalf;
    private final BufferedImage heartBlank;

    public PlayerLifeDrawer()
    {
        this.heartFull = setupDefaultImage(properties.get("images.fullHeart"));
        this.heartHalf = setupDefaultImage(properties.get("images.halfHeart"));
        this.heartBlank = setupDefaultImage(properties.get("images.blankHeart"));
    }

    public void drawPlayerLife(Player player)
    {
        g2.setFont(pixelFont);
        drawBlankHearts(player.maxLife);
        drawHalfHearts(player.life);
        drawFullHearts(player.life);
    }

    private void drawBlankHearts(int maxLife)
    {
        int y = GamePanel.tileSize / 2;
        int x = GamePanel.tileSize / 2;
        for (int i = 0; i < maxLife / 2; i++)
        {
            g2.drawImage(heartBlank, x, y, null);
            x += GamePanel.tileSize;
        }
    }

    private void drawHalfHearts(int life)
    {
        int y = GamePanel.tileSize / 2;
        int x = GamePanel.tileSize / 2;
        for (int i = 0; i < life; i += 2)
        {
            g2.drawImage(heartHalf, x, y, null);
            x += GamePanel.tileSize;
        }
    }

    private void drawFullHearts(int life)
    {
        int x = GamePanel.tileSize / 2;
        int y = GamePanel.tileSize / 2;
        for (int i = 0; i < life; i++)
        {
            i++;
            if (i < life)
            {
                g2.drawImage(heartFull, x, y, null);
            }
            x += GamePanel.tileSize;
        }
    }
}
