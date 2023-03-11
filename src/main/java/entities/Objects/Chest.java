package entities.Objects;

import View.GamePanel;
import entities.Entity.Entity;

import static utilities.images.ImageUtils.setupDefaultImage;

public class Chest extends Entity
{

    public Chest(GamePanel gp, int x, int y)
    {
        super(gp);
        this.worldX = x * gp.tileSize;
        this.worldY = y * gp.tileSize;
        name = "Chest";
        downSprites.add(setupDefaultImage("/objects/chest.png"));
    }
}
