package assets.entities.objects;

import application.GamePanel;
import assets.entities.Object;

import static assets.EntityType.MONKEY;
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
        image = setupDefaultImage("/objects/monkey.png");
        hasCollision = true;
    }
}
