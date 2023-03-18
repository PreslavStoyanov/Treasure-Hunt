package assets.entities.objects;

import application.GamePanel;
import assets.entities.Object;

import static assets.EntityType.KEY;
import static utilities.images.ImageUtils.setupDefaultImage;

public class Key extends Object
{

    public Key(GamePanel gp)
    {
        super(gp);
        this.name = "Key";
        this.type = KEY;
        this.image = setupDefaultImage("/objects/key.png");
        this.description = String.format("[%s]\nIt can open doors!", name);
    }


}
