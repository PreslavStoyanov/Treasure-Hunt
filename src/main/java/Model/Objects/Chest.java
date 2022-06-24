package Model.Objects;

import Model.Entity.Entity;
import View.GamePanel;

public class Chest extends Entity {

    public Chest(GamePanel gp) {
        super(gp);
        name = "Chest";
        downSprites.add(setup("/objects/chest.png", gp.tileSize, gp.tileSize));
    }
}
