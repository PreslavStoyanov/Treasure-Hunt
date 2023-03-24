package assets.entities.objects.collectables.defenseobjects;

import application.GamePanel;
import assets.entities.objects.collectables.DefenseObject;

import static application.Application.defaultImagesUrls;
import static assets.EntityType.SHIELD;
import static utilities.images.ImageUtils.setupDefaultSizeImage;

public class Shield extends DefenseObject
{
    public Shield(GamePanel gp)
    {
        super(gp);
        this.name = "Shield";
        this.type = SHIELD;
        this.defenseValue = 1;
        this.description = String.format("""
                [%s]
                Made of iron!""", name);
        this.defaultImage = setupDefaultSizeImage(defaultImagesUrls.get("iron-shield"));
    }
}
