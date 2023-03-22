package assets.entities.objects;

import application.GamePanel;
import assets.EntityType;
import assets.entities.Object;

import static application.Application.defaultImagesUrls;
import static application.GamePanel.tileSize;
import static utilities.images.ImageUtils.setupDefaultImage;

public class Chest extends Object
{

    public Chest(GamePanel gp, int x, int y)
    {
        super(gp);
        this.setWorldLocation(x * tileSize, y * tileSize);
        name = "Chest";
        type = EntityType.CHEST;
        image = setupDefaultImage(defaultImagesUrls.get("chest"));
    }
}
