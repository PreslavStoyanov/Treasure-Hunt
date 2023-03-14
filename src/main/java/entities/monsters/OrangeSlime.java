package entities.monsters;

import View.GamePanel;
import entities.types.EntityType;
import entities.types.Monster;

import static utilities.images.ImageUtils.setupDefaultImage;

public class OrangeSlime extends Monster
{
    GamePanel gp;

    public OrangeSlime(GamePanel gp, int x, int y)
    {
        super(gp);
        this.worldX = x * GamePanel.tileSize;
        this.worldY = y * GamePanel.tileSize;
        this.gp = gp;
        type = EntityType.SLIME;
        name = "Orange slime";
        speed = 1;
        maxLife = 3;
        life = maxLife;
        attack = 2;
        defense = 0;
        exp = 3;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        sprites = setSprites("src/main/resources/orange_slime_sprites.yaml");
    }

    public void damageReaction()
    {
        actionLockCounter = 0;
        direction = gp.player.direction;
    }
}
