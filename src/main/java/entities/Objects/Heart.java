package entities.Objects;

import View.GamePanel;
import entities.Entity.Entity;

import static View.Main.properties;
import static utilities.ImageUtils.setupDefaultImage;

public class Heart extends Entity
{
    public Heart(GamePanel gp)
    {
        super(gp);
        name = "Heart";
        image = setupDefaultImage(properties.get("images.fullHeart"));
        image2 = setupDefaultImage(properties.get("images.halfHeart"));
        image3 = setupDefaultImage(properties.get("images.blankHeart"));
    }
}
