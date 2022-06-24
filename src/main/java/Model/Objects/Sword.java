package Model.Objects;

import Model.Entity.Entity;
import View.GamePanel;

public class Sword extends Entity {

    public Sword(GamePanel gp) {
        super(gp);
        name = "Sword";
        image = setup("/objects/sword.png", gp.tileSize, gp.tileSize);
        attackValue = 1;
    }
}
