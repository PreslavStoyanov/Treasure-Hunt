package utilities.images;

import application.GamePanel;
import assets.Entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class ImageUtils
{
    public static BufferedImage setupDefaultSizeImage(Object imagePath)
    {
        return setupImage(imagePath, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
    }

    public static BufferedImage setupImage(Object imagePath, int width, int height)
    {
        try
        {
            BufferedImage image = ImageIO.read(Objects.requireNonNull(Entity.class.getResourceAsStream(imagePath.toString())));
            return scaleImage(image, width, height);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    private static BufferedImage scaleImage(BufferedImage original, int width, int height)
    {
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, width, height, null);
        g2.dispose();
        return scaledImage;
    }
}
