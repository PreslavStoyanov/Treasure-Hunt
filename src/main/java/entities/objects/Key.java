package entities.objects;

import View.GamePanel;
import entities.types.Object;

import static entities.types.EntityType.KEY;
import static utilities.images.ImageUtils.setupDefaultImage;

public class Key extends Object
{

    public Key(GamePanel gp, int x, int y)
    {
        super(gp);
        this.worldX = x * GamePanel.tileSize;
        this.worldY = y * GamePanel.tileSize;
        name = "Key";
        type = KEY;
        image = setupDefaultImage("/objects/key.png");
    }
}
