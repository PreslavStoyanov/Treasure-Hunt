package assets.entities.movingentities.projectiles;

import application.GamePanel;
import assets.entities.movingentities.Projectile;

public class Fireball extends Projectile
{
    public Fireball(GamePanel gp)
    {
        super(gp);
        this.name = "Fireball";
        this.movingSpeed = 3;
        this.attackValue = 2;
        this.maxFlightTime = 80;
        this.usageCosts = 1;
        this.sprites = setSprites("src/main/resources/fireball_sprites.yaml");
    }

    @Override
    public void changeMovingDirection()
    {

    }
}
