package entities.Objects;

import View.GamePanel;
import entities.Entity.Entity;

public class Chest extends Entity
{

    public Chest(GamePanel gp)
    {
        super(gp);
        name = "Chest";
        downSprites.add(setup("/objects/chest.png", gp.tileSize, gp.tileSize));
    }
}
