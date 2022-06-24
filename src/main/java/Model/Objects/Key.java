package Model.Objects;

import Model.Entity.Entity;
import View.GamePanel;

public class Key extends Entity {

    public Key(GamePanel gp) {
        super(gp);
        name = "Key";
        downSprites.add(setup("/objects/key.png", gp.tileSize, gp.tileSize));

    }
}
