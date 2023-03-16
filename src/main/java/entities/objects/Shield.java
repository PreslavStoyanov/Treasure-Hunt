package entities.objects;

import application.GamePanel;
import entities.types.Object;

import static entities.types.EntityType.SHIELD;
import static utilities.images.ImageUtils.setupDefaultImage;

public class Shield extends Object
{
    public Shield(GamePanel gp)
    {
        super(gp);
        this.name = "Shield";
        this.type = SHIELD;
        this.image = setupDefaultImage("/objects/shield.png");
        this.defenseValue = 1;
        this.description = String.format("[%s]\nMade of wood!", name);
    }
}
