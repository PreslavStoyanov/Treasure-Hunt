package Model.Monsters;

import Model.Entity.Entity;
import View.GamePanel;

import java.util.Random;

public class Demon extends Entity {
    GamePanel gp;

    public Demon(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = 3;
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

    public void getImage() {
        downSprites.add(setup("/monster/demon/demon00.png", gp.tileSize, gp.tileSize));
        downSprites.add(setup("/monster/demon/demon01.png", gp.tileSize, gp.tileSize));
        downSprites.add(setup("/monster/demon/demon02.png", gp.tileSize, gp.tileSize));
        downSprites.add(setup("/monster/demon/demon03.png", gp.tileSize, gp.tileSize));

        upSprites.add(setup("/monster/demon/demon04.png", gp.tileSize, gp.tileSize));
        upSprites.add(setup("/monster/demon/demon05.png", gp.tileSize, gp.tileSize));
        upSprites.add(setup("/monster/demon/demon06.png", gp.tileSize, gp.tileSize));
        upSprites.add(setup("/monster/demon/demon07.png", gp.tileSize, gp.tileSize));

        rightSprites.add(setup("/monster/demon/demon08.png", gp.tileSize, gp.tileSize));
        rightSprites.add(setup("/monster/demon/demon09.png", gp.tileSize, gp.tileSize));
        rightSprites.add(setup("/monster/demon/demon10.png", gp.tileSize, gp.tileSize));
        rightSprites.add(setup("/monster/demon/demon11.png", gp.tileSize, gp.tileSize));

        leftSprites.add(setup("/monster/demon/demon12.png", gp.tileSize, gp.tileSize));
        leftSprites.add(setup("/monster/demon/demon13.png", gp.tileSize, gp.tileSize));
        leftSprites.add(setup("/monster/demon/demon14.png", gp.tileSize, gp.tileSize));
        leftSprites.add(setup("/monster/demon/demon15.png", gp.tileSize, gp.tileSize));
    }

    public void setAction() {
        actionLockCounter++;

        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = 1 + random.nextInt(100);
            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75) {
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }

    public void damageReaction() {
        speed = 2;
        actionLockCounter = 0;
        switch (gp.player.direction) {
            case "up" -> direction = "down";
            case "down" -> direction = "up";
            case "left" -> direction = "right";
            case "right" -> direction = "left";
        }
    }

    @Override
    public void update() {
        super.update();
        spriteNumberChanger(upSprites.size(), 30);
    }
}
