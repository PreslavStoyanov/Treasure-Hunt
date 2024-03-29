package assets.entities.movingentities.liveentities;

import application.GamePanel;
import assets.entities.movingentities.AliveEntity;

import java.util.Random;

import static assets.entities.MovingEntity.Direction.*;

public class Artificial extends AliveEntity
{
    public Artificial(GamePanel gp)
    {
        super(gp);
    }

    @Override
    public void changeMovingDirection()
    {
        if (gp.getFrameCounter() % 120 != 0)
        {
            return;
        }
        switch (new Random().nextInt(4))
        {
            case 0 -> direction = UP;
            case 1 -> direction = DOWN;
            case 2 -> direction = LEFT;
            case 3 -> direction = RIGHT;
        }
    }

    @Override
    public void interactWithEntities()
    {
        gp.objects.forEach(object -> gp.collisionChecker.isEntityTransitional(this, object));
        gp.npcs.forEach(npc -> gp.collisionChecker.isEntityTransitional(this, npc));
        gp.monsters.forEach(monster -> gp.collisionChecker.isEntityTransitional(this, monster));
    }
}
