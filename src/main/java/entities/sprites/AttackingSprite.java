package entities.sprites;

import java.awt.image.BufferedImage;

import static View.GamePanel.tileSize;
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

    public void setxInTiles(int xInTiles)
    {
        this.xInTiles = xInTiles;
    }

    public int getyInTilesSize()
    {
        return yInTilesSize;
    }

    public void setyInTilesSize(int yInTilesSize)
    {
        this.yInTilesSize = yInTilesSize;
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
