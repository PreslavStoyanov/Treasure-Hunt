package entities.Objects;

import View.GamePanel;
import entities.Entity.Entity;

public class Boots extends Entity
{
    public Boots(GamePanel gp)
    {
        super(gp);
        name = "Boots";
        downSprites.add(setup("/objects/boots.png", gp.tileSize, gp.tileSize));
    }
}
