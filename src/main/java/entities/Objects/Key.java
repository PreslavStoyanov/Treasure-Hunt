package entities.Objects;

import View.GamePanel;
import entities.Entity.Entity;

public class Key extends Entity
{

    public Key(GamePanel gp)
    {
        super(gp);
        name = "Key";
        downSprites.add(setup("/objects/key.png", gp.tileSize, gp.tileSize));

    }
}
