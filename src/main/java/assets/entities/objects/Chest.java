package assets.entities.objects;

import application.GamePanel;
import assets.EntityType;
import assets.entities.Object;

import static utilities.images.ImageUtils.setupDefaultImage;

public class Chest extends Object
{

    public Chest(GamePanel gp, int x, int y)
    {
        super(gp);
        this.worldX = x * GamePanel.tileSize;
        this.worldY = y * GamePanel.tileSize;
        name = "Chest";
        type = EntityType.CHEST;
        image = setupDefaultImage("/objects/chest.png");
    }
}
