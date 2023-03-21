package assets.entities.objects.usableobjects;

import application.GamePanel;
import assets.entities.Object;
import assets.entities.objects.UsableObject;

import static application.Application.properties;
import static assets.EntityType.BOOTS;
import static utilities.images.ImageUtils.setupDefaultImage;

public class Boots extends UsableObject
{
    public Boots(GamePanel gp)
    {
        super(gp);
        this.name = "Boots";
        this.type = BOOTS;
        this.image = setupDefaultImage(properties.get("images.boots"));
        this.description = String.format("[%s]\nMake you faster!", name);
    }

    @Override
    public void useItem()
    {

    }
}
