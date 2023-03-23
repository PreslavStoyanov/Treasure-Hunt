package assets.entities.movingentities;

import application.GamePanel;
import assets.entities.MovingEntity;

import static assets.EntityType.MONSTER_TYPES;
import static assets.EntityType.PLAYER;

public abstract class Projectile extends MovingEntity
{
    public AliveEntity shooter;
    private boolean flying;
    private int flightTime = 0;
    public int maxFlightTime;
    public int attackValue;
    public int castEnergyNeeded;

    public Projectile(GamePanel gp)
    {
        super(gp);
    }

    public boolean isFlying()
    {
        return flying;
    }

    public void setFlying(boolean flying)
    {
        this.flying = flying;
    }

    public void shoot(AliveEntity shooter)
    {
        this.shooter = shooter;
        flightTime = 0;
        setWorldLocation(shooter.worldX, shooter.worldY);
        direction = shooter.direction;
        setFlying(true);
    }

    @Override
    public void update()
    {
        handleMoving();
//        if (gp.collisionChecker.isTileColliding(this))
//        {
//            setFlying(false);
//        }
        interactWithEntities();
        flightTime++;
        if (flightTime > maxFlightTime)
        {
            setFlying(false);
        }
    }

    @Override
    public void interactWithEntities()
    {
        if (shooter.type.equals(PLAYER))
        {
            gp.monsters.stream()
                    .filter(monster -> gp.collisionChecker.isLiveEntityColliding(this, monster))
                    .findFirst()
                    .ifPresent(monster -> {
                        gp.player.damageMonster(monster, flightTime / 20);
                        setFlying(false);
                    });
        }
        else if (MONSTER_TYPES.contains(shooter.type))
        {
            boolean isContactingPlayer = gp.collisionChecker.isCollidingWithPlayer(this, gp.player);

            if (!gp.player.isInvincible && isContactingPlayer)
            {
                gp.player.takeDamage(attackValue);
            }
        }
    }
}
