package Model.Objects;

import Model.Entity.Entity;
import View.GamePanel;

public class Door extends Entity {
    public Door(GamePanel gp) {
        super(gp);
        name = "Door";
        downSprites.add(setup("/objects/door.png", gp.tileSize, gp.tileSize));
        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
