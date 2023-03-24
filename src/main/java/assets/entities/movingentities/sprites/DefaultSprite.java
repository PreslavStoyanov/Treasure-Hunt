package assets.entities.movingentities.sprites;

import java.awt.image.BufferedImage;

import static utilities.images.ImageUtils.setupDefaultSizeImage;

public class DefaultSprite
{
    private BufferedImage image;

    public BufferedImage getImage()
    {
        return image;
    }

    public void setImage(String path)
    {
        this.image = setupDefaultSizeImage(path);
    }
}
