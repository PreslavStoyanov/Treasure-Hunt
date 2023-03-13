package entities.objects;

import View.GamePanel;
import entities.types.EntityType;
import entities.types.Object;

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
        downSprites.add(setupDefaultImage("/objects/chest.png"));
    }
}
