package assets.entities.objects;

import application.GamePanel;
import assets.entities.Object;

import java.awt.image.BufferedImage;

import static application.Application.properties;
import static assets.EntityType.HEART;
import static utilities.images.ImageUtils.setupDefaultImage;

public class Heart extends Object
{
    public BufferedImage fullHeart;
    public BufferedImage halfHeart;
    public BufferedImage blankHeart;

    public Heart(GamePanel gp)
    {
        super(gp);
        name = "Heart";
        type = HEART;
        fullHeart = setupDefaultImage(properties.get("images.fullHeart"));
        halfHeart = setupDefaultImage(properties.get("images.halfHeart"));
        blankHeart = setupDefaultImage(properties.get("images.blankHeart"));
    }
}
