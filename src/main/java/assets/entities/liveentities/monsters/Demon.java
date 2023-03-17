package assets.entities.liveentities.monsters;

import application.GamePanel;
import assets.EntityType;
import assets.entities.liveentities.Monster;

import static assets.entities.LiveEntity.Direction.*;

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

    public void reactToDamage()
    {
        speed = 2;
        actionLockCounter = 0;
        switch (gp.player.direction)
        {
            case UP -> direction = DOWN;
            case DOWN -> direction = UP;
            case LEFT -> direction = RIGHT;
            case RIGHT -> direction = LEFT;
        }
    }
}
