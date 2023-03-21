package assets.entities.movingentities.projectiles;

import application.GamePanel;
import assets.entities.movingentities.Projectile;

public class Slimeball extends Projectile
{
    public Slimeball(GamePanel gp)
    {
        super(gp);
        this.solidArea.setSize(15, 15);
        this.name = "Slimeball";
        this.movingSpeed = 2;
        this.attackValue = 2;
        this.maxFlightTime = 60;
        this.castEnergyNeeded = 10;
        this.sprites = setSprites("src/main/resources/slimeball_projectile_sprites.yaml");
    }

    @Override
    public void changeMovingDirection()
    {

    }
}
