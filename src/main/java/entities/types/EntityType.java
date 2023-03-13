package entities.types;

import java.util.List;

public enum EntityType
{
    PLAYER,
    DEMON,
    SLIME,
    OLD_MAN,
    OLD_WOMAN,
    BOOTS,
    CHEST,
    DOOR,
    HEART,
    KEY,
    MONKEY,
    SHIELD,
    SWORD;

    public static final List<EntityType> monstersTypes = List.of(DEMON, SLIME);
    public static final List<EntityType> objectTypes = List.of(
            BOOTS,
            CHEST,
            DOOR,
            HEART,
            KEY,
            MONKEY,
            SHIELD,
            SWORD);
    public static final List<EntityType> npcTypes = List.of(OLD_MAN, OLD_WOMAN);
}
