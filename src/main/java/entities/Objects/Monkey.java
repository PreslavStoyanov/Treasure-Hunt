package entities.Objects;

import View.GamePanel;
import entities.Entity.Entity;

public class Monkey extends Entity
{

    public Monkey(GamePanel gp)
    {
        super(gp);
        name = "Monkey";
        downSprites.add(setup("/objects/monkey.png", gp.tileSize, gp.tileSize));
        collision = true;
    }
}
