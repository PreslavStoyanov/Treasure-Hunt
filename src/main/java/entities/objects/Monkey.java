package entities.objects;

import View.GamePanel;
import entities.entity.Entity;

import static utilities.images.ImageUtils.setupDefaultImage;

public class Monkey extends Entity
{

    public Monkey(GamePanel gp, int x, int y)
    {
        super(gp);
        this.worldX = x * gp.tileSize;
        this.worldY = y * gp.tileSize;
        name = "Monkey";
        downSprites.add(setupDefaultImage("/objects/monkey.png"));
        collision = true;
    }
}
