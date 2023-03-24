package assets.entities.movingentities.liveentities.artificials.monsters;

import application.GamePanel;
import assets.EntityType;
import assets.entities.movingentities.liveentities.artificials.Monster;

import static application.GamePanel.tileSize;
import static assets.entities.MovingEntity.Direction.*;

public class Demon extends Monster
{
    GamePanel gp;

    public Demon(GamePanel gp, int x, int y)
    {
        super(gp);
        this.setWorldLocation(x * tileSize, y * tileSize);
        setSolidAreaAndDefaultLocation(8, 16, 30, 30);
        this.gp = gp;
        type = EntityType.DEMON;
        name = "Demon";
        movingSpeed = 1;
        maxLife = 10;
        life = maxLife;
        attackValue = 5;
        defense = 0;
        exp = 5;

        setSolidAreaAndDefaultLocation(3, 18, 42, 30);
        sprites = setSprites("src/main/resources/monster/demon_sprites.yaml");
    }

    public void reactToDamage()
    {
        super.reactToDamage();
        movingSpeed = 2;
        switch (gp.player.direction)
        {
            case UP -> direction = DOWN;
            case DOWN -> direction = UP;
            case LEFT -> direction = RIGHT;
            case RIGHT -> direction = LEFT;
        }
    }
}
