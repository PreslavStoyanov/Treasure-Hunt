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
        this.name = "Sword";
        this.type = EntityType.SWORD;
        this.image = setupDefaultImage("/objects/sword.png");
        this.attackValue = 1;
        this.description = String.format("[%s]\nAn old sword!", name);
    }
}
