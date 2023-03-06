package entities.Objects;

import View.GamePanel;
import entities.Entity.Entity;

public class Sword extends Entity
{

    public Sword(GamePanel gp)
    {
        super(gp);
        name = "Sword";
        image = setup("/objects/sword.png", gp.tileSize, gp.tileSize);
        attackValue = 1;
    }
}
