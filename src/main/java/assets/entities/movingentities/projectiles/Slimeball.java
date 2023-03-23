package assets.entities.movingentities.projectiles;

import application.GamePanel;
import assets.entities.movingentities.Projectile;
import utilities.sound.Sound;

public class Slimeball extends Projectile
{
    public Slimeball(GamePanel gp)
    {
        super(gp);
        this.solidArea.setSize(20, 20);
        this.name = "Slimeball";
        this.movingSpeed = 2;
        this.attackValue = 2;
        this.maxFlightTime = 60;
        this.castEnergyNeeded = 10;
        this.shootSound = Sound.FIREBALL_SOUND; //TODO add slimeball shoot sound
        this.sprites = setSprites("src/main/resources/projectiles/slimeball_sprites.yaml");
    }

    @Override
    public void changeMovingDirection()
    {

    }
}
