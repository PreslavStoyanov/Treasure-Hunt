package assets.entities.objects;

import application.GamePanel;
import assets.entities.Object;

import static application.Application.properties;
import static assets.EntityType.HEART;
import static utilities.images.ImageUtils.setupDefaultImage;

public class Heart extends Object
{
    public Heart(GamePanel gp)
    {
        super(gp);
        name = "Heart";
        type = HEART;
        image = setupDefaultImage(properties.get("images.fullHeart"));
        image2 = setupDefaultImage(properties.get("images.halfHeart"));
        image3 = setupDefaultImage(properties.get("images.blankHeart"));
    }
}
