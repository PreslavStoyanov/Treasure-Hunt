package entities.objects;

import application.GamePanel;
import entities.types.EntityType;
import entities.types.Object;

import static utilities.images.ImageUtils.setupDefaultImage;

public class Sword extends Object
{

    public Sword(GamePanel gp)
    {
        super(gp);
        name = "Sword";
        type = EntityType.SWORD;
        image = setupDefaultImage("/objects/sword.png");
        attackValue = 1;
    }
}
