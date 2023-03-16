package entities.objects;

import application.GamePanel;
import entities.types.Object;

import static entities.types.EntityType.KEY;
import static utilities.images.ImageUtils.setupDefaultImage;

public class Key extends Object
{

    public Key(GamePanel gp, int x, int y)
    {
        super(gp);
        this.worldX = x * GamePanel.tileSize;
        this.worldY = y * GamePanel.tileSize;
        this.name = "Key";
        this.type = KEY;
        this.image = setupDefaultImage("/objects/key.png");
        this.description = String.format("[%s]\nIt can open doors!", name);
    }
}
