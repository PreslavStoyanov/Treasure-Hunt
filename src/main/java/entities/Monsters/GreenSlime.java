package entities.Monsters;

import View.GamePanel;
import entities.Entity.Entity;

public class GreenSlime extends Entity
{
    GamePanel gp;

    public GreenSlime(GamePanel gp)
    {
        super(gp);
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
        upSprites.add(setup("/monster/greenSlime/greenSlime1.png", gp.tileSize, gp.tileSize));
        upSprites.add(setup("/monster/greenSlime/greenSlime2.png", gp.tileSize, gp.tileSize));
        downSprites.add(setup("/monster/greenSlime/greenSlime1.png", gp.tileSize, gp.tileSize));
        downSprites.add(setup("/monster/greenSlime/greenSlime2.png", gp.tileSize, gp.tileSize));
        leftSprites.add(setup("/monster/greenSlime/greenSlime1.png", gp.tileSize, gp.tileSize));
        leftSprites.add(setup("/monster/greenSlime/greenSlime2.png", gp.tileSize, gp.tileSize));
        rightSprites.add(setup("/monster/greenSlime/greenSlime1.png", gp.tileSize, gp.tileSize));
        rightSprites.add(setup("/monster/greenSlime/greenSlime2.png", gp.tileSize, gp.tileSize));
    }

    public void damageReaction()
    {
        actionLockCounter = 0;
        direction = gp.player.direction;
    }
}
