package Model.Objects;

import Model.Entity.Entity;
import View.GamePanel;

public class Shield extends Entity {
    public Shield(GamePanel gp) {
        super(gp);
        name = "Shield";
        image = setup("/objects/shield.png", gp.tileSize, gp.tileSize);
        defenseValue = 1;
    }
}
