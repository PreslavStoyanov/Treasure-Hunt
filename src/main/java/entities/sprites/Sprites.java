package entities.sprites;

import java.util.ArrayList;
import java.util.List;

public class Sprites
{
    private List<WalkingSprite> walkingUpSprites;
    private List<WalkingSprite> walkingDownSprites;
    private List<WalkingSprite> walkingLeftSprites;
    private List<WalkingSprite> walkingRightSprites;
    private List<AttackingSprite> attackingUpSprites;
    private List<AttackingSprite> attackingDownSprites;
    private List<AttackingSprite> attackingLeftSprites;
    private List<AttackingSprite> attackingRightSprites;


    public List<WalkingSprite> getWalkingUpSprites()
    {
        return walkingUpSprites;
    }

    public void setWalkingUpSprites(List<WalkingSprite> walkingUpSprites)
    {
        this.walkingUpSprites = walkingUpSprites;
    }

    public List<WalkingSprite> getWalkingDownSprites()
    {
        return walkingDownSprites;
    }

    public void setWalkingDownSprites(List<WalkingSprite> walkingDownSprites)
    {
        this.walkingDownSprites = walkingDownSprites;
    }

    public List<WalkingSprite> getWalkingLeftSprites()
    {
        return walkingLeftSprites;
    }

    public void setWalkingLeftSprites(List<WalkingSprite> walkingLeftSprites)
    {
        this.walkingLeftSprites = walkingLeftSprites;
    }

    public List<WalkingSprite> getWalkingRightSprites()
    {
        return walkingRightSprites;
    }

    public void setWalkingRightSprites(List<WalkingSprite> walkingRightSprites)
    {
        this.walkingRightSprites = walkingRightSprites;
    }

    public List<AttackingSprite> getAttackingUpSprites()
    {
        return attackingUpSprites;
    }

    public void setAttackingUpSprites(List<AttackingSprite> attackingUpSprites)
    {
        this.attackingUpSprites = attackingUpSprites;
    }

    public List<AttackingSprite> getAttackingDownSprites()
    {
        return attackingDownSprites;
    }

    public void setAttackingDownSprites(List<AttackingSprite> attackingDownSprites)
    {
        this.attackingDownSprites = attackingDownSprites;
    }

    public List<AttackingSprite> getAttackingLeftSprites()
    {
        return attackingLeftSprites;
    }

    public void setAttackingLeftSprites(List<AttackingSprite> attackingLeftSprites)
    {
        this.attackingLeftSprites = attackingLeftSprites;
    }

    public List<AttackingSprite> getAttackingRightSprites()
    {
        return attackingRightSprites;
    }

    public void setAttackingRightSprites(List<AttackingSprite> attackingRightSprites)
    {
        this.attackingRightSprites = attackingRightSprites;
    }
}
