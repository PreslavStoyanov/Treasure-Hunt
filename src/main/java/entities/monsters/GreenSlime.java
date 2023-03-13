package entities.monsters;

import View.GamePanel;
import entities.entity.Entity;

import static utilities.images.ImageUtils.setupDefaultImage;

public class GreenSlime extends Entity
{
    GamePanel gp;

    public GreenSlime(GamePanel gp, int x, int y)
    {
        super(gp);
        this.worldX = x * gp.tileSize;
        this.worldY = y * gp.tileSize;
        this.gp = gp;
        type = 2;
        name = "Green slime";
        speed = 1;
        maxLife = 3;
        life = maxLife;
        attack = 1;
        defense = 0;
        exp = 2;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        getImage();
    }

    public void getImage()
    {
        upSprites.add(setupDefaultImage("/monster/greenSlime/greenSlime1.png"));
        upSprites.add(setupDefaultImage("/monster/greenSlime/greenSlime2.png"));
        downSprites.add(setupDefaultImage("/monster/greenSlime/greenSlime1.png"));
        downSprites.add(setupDefaultImage("/monster/greenSlime/greenSlime2.png"));
        leftSprites.add(setupDefaultImage("/monster/greenSlime/greenSlime1.png"));
        leftSprites.add(setupDefaultImage("/monster/greenSlime/greenSlime2.png"));
        rightSprites.add(setupDefaultImage("/monster/greenSlime/greenSlime1.png"));
        rightSprites.add(setupDefaultImage("/monster/greenSlime/greenSlime2.png"));
    }

    public void damageReaction()
    {
        actionLockCounter = 0;
        direction = gp.player.direction;
    }
}
