package utilities.drawers;

import View.GamePanel;

import java.awt.image.BufferedImage;

import static View.Main.properties;
import static utilities.images.ImageUtils.setupDefaultImage;
import static utilities.drawers.UserInterfaceController.g2;
import static utilities.drawers.UserInterfaceController.pixelFont;

public class PlayerLifeDrawer
{
    GamePanel gp;
    private final BufferedImage heartFull;
    private final BufferedImage heartHalf;
    private final BufferedImage heartBlank;

    public PlayerLifeDrawer(GamePanel gp)
    {
        this.gp = gp;
        this.heartFull = setupDefaultImage(properties.get("images.fullHeart"));
        this.heartHalf = setupDefaultImage(properties.get("images.halfHeart"));
        this.heartBlank = setupDefaultImage(properties.get("images.blankHeart"));
    }

    public void drawPlayerLife()
    {
        UserInterfaceController.g2.setFont(pixelFont);
        drawBlankHearts();
        drawHalfHearts();
        drawFullHearts();
    }

    private void drawFullHearts()
    {
        int x = GamePanel.tileSize / 2;
        int y = GamePanel.tileSize / 2;
        for (int i = 0; i < gp.player.life; i++)
        {
            i++;
            if (i < gp.player.life)
            {
                g2.drawImage(heartFull, x, y, null);
            }
            x += GamePanel.tileSize;
        }
    }

    private void drawHalfHearts()
    {
        int y = GamePanel.tileSize / 2;
        int x = GamePanel.tileSize / 2;
        for (int i = 0; i < gp.player.life; i+=2)
        {
            g2.drawImage(heartHalf, x, y, null);
            x += GamePanel.tileSize;
        }
    }

    private void drawBlankHearts()
    {
        final int MAX_HEARTS = gp.player.maxLife / 2;
        int y = GamePanel.tileSize / 2;
        int x = GamePanel.tileSize / 2;
        for (int i = 0; i < MAX_HEARTS; i++)
        {
            g2.drawImage(heartBlank, x, y, null);
            x += GamePanel.tileSize;
        }
    }
}
