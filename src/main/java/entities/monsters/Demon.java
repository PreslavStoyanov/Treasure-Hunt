package entities.monsters;

import View.GamePanel;
import entities.types.EntityType;
import entities.types.Monster;

import static utilities.images.ImageUtils.setupDefaultImage;

public class Demon extends Monster
{
    GamePanel gp;

    public Demon(GamePanel gp, int x, int y)
    {
        super(gp);
        this.worldX = x * GamePanel.tileSize;
        this.worldY = y * GamePanel.tileSize;
        this.gp = gp;
        type = EntityType.DEMON;
        name = "Demon";
        speed = 1;
        maxLife = 10;
        life = maxLife;
        attack = 5;
        defense = 0;
        exp = 5;

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
        downSprites.add(setupDefaultImage("/monster/demon/demon00.png"));
        downSprites.add(setupDefaultImage("/monster/demon/demon01.png"));
        downSprites.add(setupDefaultImage("/monster/demon/demon02.png"));
        downSprites.add(setupDefaultImage("/monster/demon/demon03.png"));

        upSprites.add(setupDefaultImage("/monster/demon/demon04.png"));
        upSprites.add(setupDefaultImage("/monster/demon/demon05.png"));
        upSprites.add(setupDefaultImage("/monster/demon/demon06.png"));
        upSprites.add(setupDefaultImage("/monster/demon/demon07.png"));

        rightSprites.add(setupDefaultImage("/monster/demon/demon08.png"));
        rightSprites.add(setupDefaultImage("/monster/demon/demon09.png"));
        rightSprites.add(setupDefaultImage("/monster/demon/demon10.png"));
        rightSprites.add(setupDefaultImage("/monster/demon/demon11.png"));

        leftSprites.add(setupDefaultImage("/monster/demon/demon12.png"));
        leftSprites.add(setupDefaultImage("/monster/demon/demon13.png"));
        leftSprites.add(setupDefaultImage("/monster/demon/demon14.png"));
        leftSprites.add(setupDefaultImage("/monster/demon/demon15.png"));
    }

    public void damageReaction()
    {
        speed = 2;
        actionLockCounter = 0;
        switch (gp.player.direction)
        {
            case "up" -> direction = "down";
            case "down" -> direction = "up";
            case "left" -> direction = "right";
            case "right" -> direction = "left";
        }
    }

    @Override
    public void update()
    {
        super.update();
        spriteNumberChanger(upSprites.size(), 30);
    }
}
