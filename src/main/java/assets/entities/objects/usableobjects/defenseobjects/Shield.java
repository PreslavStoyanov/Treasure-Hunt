package assets.entities.objects.usableobjects.defenseobjects;

import application.GamePanel;
import assets.entities.objects.usableobjects.DefenseObject;

import static application.Application.defaultImagesUrls;
import static assets.EntityType.SHIELD;
import static utilities.images.ImageUtils.setupDefaultImage;

public class Shield extends DefenseObject
{
    public Shield(GamePanel gp)
    {
        super(gp);
        this.name = "Shield";
        this.type = SHIELD;
        this.defenseValue = 1;
        this.description = String.format("[%s]\nMade of wood!", name);
        this.image = setupDefaultImage(defaultImagesUrls.get("iron-shield"));
    }
}
