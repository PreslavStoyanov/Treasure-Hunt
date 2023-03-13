package entities.objects;

import View.GamePanel;
import entities.types.EntityType;
import entities.types.Object;

import static entities.types.EntityType.BOOTS;
import static utilities.images.ImageUtils.setupDefaultImage;

public class Boots extends Object
{
    public Boots(GamePanel gp, int x, int y)
    {
        super(gp);
        this.worldX = x * GamePanel.tileSize;
        this.worldY = y * GamePanel.tileSize;
        name = "Boots";
        type = BOOTS;
        downSprites.add(setupDefaultImage("/objects/boots.png"));
    }
}
