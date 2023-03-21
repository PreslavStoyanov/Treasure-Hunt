package assets.entities.objects.usableobjects;

import application.GamePanel;
import assets.entities.objects.UsableObject;

import static application.Application.defaultImagesUrls;
import static assets.EntityType.KEY;
import static utilities.images.ImageUtils.setupDefaultImage;

public class Key extends UsableObject
{

    public Key(GamePanel gp)
    {
        super(gp);
        this.name = "Key";
        this.type = KEY;
        this.description = String.format("[%s]\nIt can open doors!", name);
        this.image = setupDefaultImage(defaultImagesUrls.get("key"));
    }

    @Override
    public void useItem()
    {

    }


}
