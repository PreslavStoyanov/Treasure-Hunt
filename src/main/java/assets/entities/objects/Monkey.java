package assets.entities.objects;

import application.GamePanel;
import assets.entities.Object;

import static application.Application.properties;
import static application.GamePanel.tileSize;
import static assets.EntityType.MONKEY;
import static utilities.images.ImageUtils.setupDefaultImage;

public class Monkey extends Object
{

    public Monkey(GamePanel gp, int x, int y)
    {
        super(gp);
        this.setWorldLocation(x * tileSize, y * tileSize);
        setSolidAreaAndDefaultLocation(8, 16, 30, 30);
        name = "Monkey";
        type = MONKEY;
        hasCollision = true;
        image = setupDefaultImage(properties.get("images.monkey"));
    }
}
