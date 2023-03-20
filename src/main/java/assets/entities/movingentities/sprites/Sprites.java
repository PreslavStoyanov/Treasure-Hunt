package assets.entities.movingentities.sprites;

import assets.EntityType;

import java.util.List;
import java.util.Map;

public class Sprites
{
    private List<DefaultSprite> defaultUpSprites;
    private List<DefaultSprite> defaultDownSprites;
    private List<DefaultSprite> defaultLeftSprites;
    private List<DefaultSprite> defaultRightSprites;
    private Map<EntityType, AttackingSprites> attackingSprites;

    public Map<EntityType, AttackingSprites> getAttackingSprites()
    {
        return attackingSprites;
    }

    public List<DefaultSprite> getDefaultUpSprites()
    {
        return defaultUpSprites;
    }

    public List<DefaultSprite> getDefaultDownSprites()
    {
        return defaultDownSprites;
    }

    public List<DefaultSprite> getDefaultLeftSprites()
    {
        return defaultLeftSprites;
    }

    public List<DefaultSprite> getDefaultRightSprites()
    {
        return defaultRightSprites;
    }
}
