package assets.entities.movingentities.sprites;

import java.util.List;

public class AttackingSprites
{
    private List<AttackingSprite> upSprites;
    private List<AttackingSprite> downSprites;
    private List<AttackingSprite> leftSprites;
    private List<AttackingSprite> rightSprites;

    public List<AttackingSprite> getUpSprites()
    {
        return upSprites;
    }

    public List<AttackingSprite> getDownSprites()
    {
        return downSprites;
    }

    public List<AttackingSprite> getLeftSprites()
    {
        return leftSprites;
    }

    public List<AttackingSprite> getRightSprites()
    {
        return rightSprites;
    }
}
