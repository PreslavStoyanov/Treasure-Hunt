package Model.Objects;

import Model.Entity.Entity;
import View.GamePanel;

public class Boots extends Entity {
    public Boots(GamePanel gp) {
        super(gp);
        name = "Boots";
        downSprites.add(setup("/objects/boots.png", gp.tileSize, gp.tileSize));
    }
}
