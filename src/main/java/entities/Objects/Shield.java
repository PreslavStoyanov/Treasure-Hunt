package entities.Objects;

import View.GamePanel;
import entities.Entity.Entity;

public class Shield extends Entity
{
    public Shield(GamePanel gp)
    {
        super(gp);
        name = "Shield";
        image = setup("/objects/shield.png", gp.tileSize, gp.tileSize);
        defenseValue = 1;
    }
}
