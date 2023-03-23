package assets.entities.movingentities.projectiles;

import application.GamePanel;
import assets.entities.movingentities.Projectile;
import utilities.sound.Sound;

public class Fireball extends Projectile
{
    public Fireball(GamePanel gp)
    {
        super(gp);
        this.solidArea.setSize(20, 20);
        this.name = "Fireball";
        this.movingSpeed = 3;
        this.attackValue = 2;
        this.maxFlightTime = 80;
        this.castEnergyNeeded = 50;
        this.shootSound = Sound.FIREBALL_SOUND;
        this.sprites = setSprites("src/main/resources/projectiles/fireball_sprites.yaml");
    }

    @Override
    public void changeMovingDirection()
    {

    }
}
