package entities.Objects;

import View.GamePanel;
import entities.Entity.Entity;

public class Heart extends Entity
{
    public Heart(GamePanel gp)
    {
        super(gp);
        name = "Heart";
        image = setup("/objects/heart_full.png", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/heart_half.png", gp.tileSize, gp.tileSize);
        image3 = setup("/objects/heart_blank.png", gp.tileSize, gp.tileSize);
    }
}
