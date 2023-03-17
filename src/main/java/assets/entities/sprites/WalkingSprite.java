package assets.entities.sprites;

import java.awt.image.BufferedImage;

import static utilities.images.ImageUtils.setupDefaultImage;

public class WalkingSprite
{
    private BufferedImage image;

    public BufferedImage getImage()
    {
        return image;
    }

    public void setImage(String path)
    {
        this.image = setupDefaultImage(path);
    }
}
