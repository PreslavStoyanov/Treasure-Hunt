package entities.objects;

import View.GamePanel;
import entities.entity.Entity;

import static utilities.images.ImageUtils.setupDefaultImage;

public class Boots extends Entity
{
    public Boots(GamePanel gp, int x, int y)
    {
        super(gp);
        this.worldX = x * gp.tileSize;
        this.worldY = y * gp.tileSize;
        name = "Boots";
        downSprites.add(setupDefaultImage("/objects/boots.png"));
    }
}
