package entities.objects;

import View.GamePanel;
import entities.entity.Entity;

import static utilities.images.ImageUtils.setupDefaultImage;

public class Shield extends Entity
{
    public Shield(GamePanel gp)
    {
        super(gp);
        name = "Shield";
        image = setupDefaultImage("/objects/shield.png");
        defenseValue = 1;
    }
}
