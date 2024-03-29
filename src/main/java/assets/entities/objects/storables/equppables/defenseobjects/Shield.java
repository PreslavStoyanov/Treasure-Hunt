package assets.entities.objects.storables.equppables.defenseobjects;

import application.GamePanel;
import assets.entities.objects.storables.equppables.DefenseObject;

import static application.Application.objectsImagesUrls;
import static assets.EntityType.SHIELD;
import static utilities.images.ImageUtils.setupDefaultSizeImage;

public class Shield extends DefenseObject
{
    public Shield(GamePanel gp)
    {
        super(gp);
        this.name = "Shield";
        this.type = SHIELD;
        this.value = 1;
        this.price = 3;
        this.description = String.format("""
                [%s]
                Made of iron!""", name);
        this.defaultImage = setupDefaultSizeImage(objectsImagesUrls.get("iron-shield"));
    }
}
