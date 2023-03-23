package assets.entities.movingentities.liveentities.artificials.monsters;

import application.GamePanel;
import assets.EntityType;
import assets.entities.movingentities.liveentities.artificials.Monster;
import assets.entities.movingentities.projectiles.Slimeball;

import java.util.Random;

import static application.GamePanel.tileSize;
import static assets.entities.MovingEntity.Direction.*;
import static assets.entities.MovingEntity.Direction.RIGHT;

public class GreenSlime extends Monster
{
    GamePanel gp;

    public GreenSlime(GamePanel gp, int x, int y)
    {
        super(gp);
        this.gp = gp;
        this.setWorldLocation(x * tileSize, y * tileSize);
        setSolidAreaAndDefaultLocation(3, 18, 42, 30);
        type = EntityType.SLIME;
        name = "Green slime";
        movingSpeed = 1;
        maxLife = 3;
        life = maxLife;
        attackValue = 1;
        defense = 0;
        exp = 2;
        projectile = new Slimeball(gp);
        sprites = setSprites("src/main/resources/monster/green_slime_sprites.yaml");
    }

    @Override
    public void update()
    {
        super.update();
        if (gp.getFrameCounter() % 120 == 0 && new Random().nextInt(3) == 2)
        {
            projectile.shoot(this);
        }
    }

    public void reactToDamage()
    {
        super.reactToDamage();
        direction = gp.player.direction;
    }
}
