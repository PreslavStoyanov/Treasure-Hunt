package entities.objects;

import View.GamePanel;
import entities.types.EntityType;
import entities.types.Object;

import static entities.types.EntityType.DOOR;
import static utilities.images.ImageUtils.setupDefaultImage;

public class Door extends Object
{
    public Door(GamePanel gp, int x, int y)
    {
        super(gp);
        this.worldX = x * GamePanel.tileSize;
        this.worldY = y * GamePanel.tileSize;
        name = "Door";
        type = DOOR;
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
