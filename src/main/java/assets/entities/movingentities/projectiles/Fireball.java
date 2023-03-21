package assets.entities.movingentities.projectiles;

import application.GamePanel;
import assets.entities.movingentities.Projectile;

public class Fireball extends Projectile
{
    public Fireball(GamePanel gp)
    {
        super(gp);
        this.solidArea.setSize(15, 15);
        this.name = "Fireball";
        this.movingSpeed = 3;
        this.attackValue = 2;
        this.maxFlightTime = 80;
        this.castEnergyNeeded = 50;
        this.sprites = setSprites("src/main/resources/fireball_projectile_sprites.yaml");
    }

    @Override
    public void changeMovingDirection()
    {

    }
}
