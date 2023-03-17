package assets.entities.sprites;

import assets.EntityType;

import java.util.List;
import java.util.Map;

public class Sprites
{
    private List<WalkingSprite> walkingUpSprites;
    private List<WalkingSprite> walkingDownSprites;
    private List<WalkingSprite> walkingLeftSprites;
    private List<WalkingSprite> walkingRightSprites;
    private Map<EntityType, AttackingSprites> attackingSprites;

    public Map<EntityType, AttackingSprites> getAttackingSprites()
    {
        return attackingSprites;
    }

    public List<WalkingSprite> getWalkingUpSprites()
    {
        return walkingUpSprites;
    }

    public List<WalkingSprite> getWalkingDownSprites()
    {
        return walkingDownSprites;
    }

    public List<WalkingSprite> getWalkingLeftSprites()
    {
        return walkingLeftSprites;
    }

    public List<WalkingSprite> getWalkingRightSprites()
    {
        return walkingRightSprites;
    }
}
