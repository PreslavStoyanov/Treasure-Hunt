package entities.Objects;

import View.GamePanel;
import entities.Entity.Entity;

import static utilities.ImageUtils.setupDefaultImage;

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
