package assets.entities.objects;

import application.GamePanel;
import assets.entities.Object;

import static assets.EntityType.DOOR;
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
        image = setupDefaultImage("/objects/door.png");
        hasCollision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
