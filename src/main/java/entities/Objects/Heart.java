package entities.Objects;

import View.GamePanel;
import entities.Entity.Entity;

public class Heart extends Entity
{
    public Heart(GamePanel gp)
    {
        super(gp);
        name = "Heart";
        image = setupDefaultImage("/objects/heart_full.png");
        image2 = setupDefaultImage("/objects/heart_half.png");
        image3 = setupDefaultImage("/objects/heart_blank.png");
    }
}
