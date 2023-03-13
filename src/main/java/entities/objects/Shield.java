package entities.objects;

import View.GamePanel;
import entities.types.EntityType;
import entities.types.Object;

import static entities.types.EntityType.SHIELD;
import static utilities.images.ImageUtils.setupDefaultImage;

public class Shield extends Object
{
    public Shield(GamePanel gp)
    {
        super(gp);
        name = "Shield";
        type = SHIELD;
        image = setupDefaultImage("/objects/shield.png");
        defenseValue = 1;
    }
}
