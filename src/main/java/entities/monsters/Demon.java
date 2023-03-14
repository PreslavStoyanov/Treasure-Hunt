package entities.monsters;

import View.GamePanel;
import entities.sprites.Sprites;
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
        sprites = setSprites("src/main/resources/demon_sprites.yaml");
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
        changeSpriteNumber(sprites.getWalkingUpSprites().size(), 30);
    }
}
