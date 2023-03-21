package assets.entities.objects.usableobjects.defenseobjects;

import application.GamePanel;
import assets.entities.objects.usableobjects.DefenseObject;

import static assets.EntityType.SHIELD;
import static utilities.images.ImageUtils.setupDefaultImage;

public class Shield extends DefenseObject
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
