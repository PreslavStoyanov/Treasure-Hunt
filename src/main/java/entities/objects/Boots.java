package entities.objects;

import application.GamePanel;
import entities.types.Object;

import static entities.types.EntityType.BOOTS;
import static utilities.images.ImageUtils.setupDefaultImage;

public class Boots extends Object
{
    public Boots(GamePanel gp, int x, int y)
    {
        super(gp);
        this.worldX = x * GamePanel.tileSize;
        this.worldY = y * GamePanel.tileSize;
        this.name = "Boots";
        this.type = BOOTS;
        this.image = setupDefaultImage("/objects/boots.png");
        this.description = String.format("[%s]\nMake you faster!", name);
    }
}
