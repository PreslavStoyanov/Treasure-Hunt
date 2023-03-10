package entities.Objects;

import View.GamePanel;
import entities.Entity.Entity;

import static utilities.ImageUtils.setupDefaultImage;

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
