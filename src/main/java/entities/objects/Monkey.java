package entities.objects;

import View.GamePanel;
import entities.types.EntityType;
import entities.types.Object;

import static entities.types.EntityType.MONKEY;
import static utilities.images.ImageUtils.setupDefaultImage;

public class Monkey extends Object
{

    public Monkey(GamePanel gp, int x, int y)
    {
        super(gp);
        this.worldX = x * GamePanel.tileSize;
        this.worldY = y * GamePanel.tileSize;
        name = "Monkey";
        type = MONKEY;
        downSprites.add(setupDefaultImage("/objects/monkey.png"));
        collision = true;
    }
}
