package entities.objects;

import View.GamePanel;
import entities.types.EntityType;
import entities.types.Object;

import static View.Main.properties;
import static entities.types.EntityType.HEART;
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
