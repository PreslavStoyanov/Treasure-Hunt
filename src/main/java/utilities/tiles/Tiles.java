package utilities.tiles;

import java.awt.image.BufferedImage;
import java.util.Map;

import static utilities.images.ImageUtils.setupDefaultSizeImage;

public class Tiles
{
    private Map<Integer, Tile> tiles;

    public Map<Integer, Tile> getTiles()
    {
        return tiles;
    }

    public static class Tile
    {
        private BufferedImage image;

        private boolean hasCollision;

        public BufferedImage getImage()
        {
            return image;
        }

        public void setImage(String path)
        {
            this.image = setupDefaultSizeImage(path);
        }

        public boolean hasCollision()
        {
            return hasCollision;
        }

        public void setHasCollision(boolean hasCollision)
        {
            this.hasCollision = hasCollision;
        }
    }
}
