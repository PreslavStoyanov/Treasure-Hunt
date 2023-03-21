package assets.entities.movingentities.projectiles;

import application.GamePanel;
import assets.entities.movingentities.Projectile;

public class Rock extends Projectile
{
    public Rock(GamePanel gp)
    {
        super(gp);
        this.name = "Rock";
        this.movingSpeed = 3;
        this.attackValue = 1;
        this.maxFlightTime = 60;
        this.castEnergyNeeded = 10;
        this.sprites = setSprites("src/main/resources/rock_projectile_sprites.yaml");
    }

    @Override
    public void changeMovingDirection()
    {

    }
}
