package entities.Objects;

import View.GamePanel;
import entities.Entity.Entity;

public class Key extends Entity
{

    public Key(GamePanel gp, int x, int y)
    {
        super(gp);
        this.worldX = x * gp.tileSize;
        this.worldY = y * gp.tileSize;
        name = "Key";
        downSprites.add(setupDefaultImage("/objects/key.png"));
    }
}
