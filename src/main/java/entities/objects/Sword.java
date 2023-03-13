package entities.objects;

import View.GamePanel;
import entities.entity.Entity;

import static utilities.images.ImageUtils.setupDefaultImage;

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
