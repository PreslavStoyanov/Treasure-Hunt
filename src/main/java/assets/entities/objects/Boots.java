package assets.entities.objects;

import application.GamePanel;
import assets.entities.Object;

import static assets.EntityType.BOOTS;
import static utilities.images.ImageUtils.setupDefaultImage;

public class Boots extends Object
{
    public Boots(GamePanel gp)
    {
        super(gp);
        this.name = "Boots";
        this.type = BOOTS;
        this.image = setupDefaultImage("/objects/boots.png");
        this.description = String.format("[%s]\nMake you faster!", name);
    }
}
