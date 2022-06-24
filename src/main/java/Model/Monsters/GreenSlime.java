package Model.Monsters;

import Model.Entity.Entity;
import View.GamePanel;

import java.util.Random;

public class GreenSlime extends Entity {
    GamePanel gp;
    public GreenSlime(GamePanel gp) {
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

    public void getImage () {
        upSprites.add(setup("/monster/greenSlime/greenSlime1.png", gp.tileSize, gp.tileSize));
        upSprites.add(setup("/monster/greenSlime/greenSlime2.png", gp.tileSize, gp.tileSize));
        downSprites.add(setup("/monster/greenSlime/greenSlime1.png", gp.tileSize, gp.tileSize));
        downSprites.add(setup("/monster/greenSlime/greenSlime2.png", gp.tileSize, gp.tileSize));
        leftSprites.add(setup("/monster/greenSlime/greenSlime1.png", gp.tileSize, gp.tileSize));
        leftSprites.add(setup("/monster/greenSlime/greenSlime2.png", gp.tileSize, gp.tileSize));
        rightSprites.add(setup("/monster/greenSlime/greenSlime1.png", gp.tileSize, gp.tileSize));
        rightSprites.add(setup("/monster/greenSlime/greenSlime2.png", gp.tileSize, gp.tileSize));
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
        actionLockCounter = 0;
        direction = gp.player.direction;
    }
}
