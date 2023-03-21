package assets.entities.objects;

import application.GamePanel;
import assets.entities.Object;

import static application.Application.properties;
import static application.GamePanel.tileSize;
import static assets.EntityType.DOOR;
import static utilities.images.ImageUtils.setupDefaultImage;

public class Door extends Object
{
    public Door(GamePanel gp, int x, int y)
    {
        super(gp);
        this.setWorldLocation(x * tileSize, y * tileSize);
        name = "Door";
        type = DOOR;
        hasCollision = true;
        image = setupDefaultImage(properties.get("images.door"));
        setSolidAreaAndDefaultLocation(0, 16,48, 32);
    }
}
