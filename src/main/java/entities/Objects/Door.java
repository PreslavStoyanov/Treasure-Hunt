package entities.Objects;

import View.GamePanel;
import entities.Entity.Entity;

public class Door extends Entity
{
    public Door(GamePanel gp, int x, int y)
    {
        super(gp);
        this.worldX = x * gp.tileSize;
        this.worldY = y * gp.tileSize;
        name = "Door";
        downSprites.add(setupDefaultImage("/objects/door.png"));
        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
