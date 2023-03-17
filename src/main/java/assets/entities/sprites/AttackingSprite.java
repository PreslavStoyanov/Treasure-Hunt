package assets.entities.sprites;

import java.awt.image.BufferedImage;

import static application.GamePanel.tileSize;
import static utilities.images.ImageUtils.setupImage;

public class AttackingSprite
{
    private int xInTiles;
    private int yInTilesSize;
    private BufferedImage image;

    public int getxInTiles()
    {
        return xInTiles;
    }

    public int getyInTilesSize()
    {
        return yInTilesSize;
    }

    public BufferedImage getImage()
    {
        return image;
    }

    public void setImage(String path)
    {
        this.image = setupImage(path, this.xInTiles * tileSize, this.yInTilesSize * tileSize);
    }
}
