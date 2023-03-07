package entities.Objects;

import View.GamePanel;
import entities.Entity.Entity;

public class Sword extends Entity
{

    public Sword(GamePanel gp)
    {
        super(gp);
        name = "Sword";
        image = setupDefaultImage("/objects/sword.png");
        attackValue = 1;
    }
}
