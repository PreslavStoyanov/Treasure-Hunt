package assets;

import java.util.List;
import java.util.stream.Stream;

public enum EntityType
{
    PLAYER,
    DEMON,
    SLIME,
    OLD_MAN,
    OLD_WOMAN,
    CHEST,
    DOOR,
    MONKEY,
    BOOTS,
    KEY,
    HEALTH_POTION,
    HEART,
    SHIELD,
    SWORD,
    AXE;

    public static final List<EntityType> MONSTER_TYPES = List.of(DEMON, SLIME);
    public static final List<EntityType> USABLE_OBJECTS = List.of(BOOTS, KEY, HEALTH_POTION, SWORD, SHIELD, AXE);
    public static final List<EntityType> STATIC_OBJECTS = List.of(DOOR, MONKEY, CHEST, HEART);
    public static final List<EntityType> OBJECT_TYPES = Stream.of(USABLE_OBJECTS, STATIC_OBJECTS)
            .flatMap(List::stream).toList();
    public static final List<EntityType> WEAPON_OBJECTS = List.of(AXE, SWORD);
    public static final List<EntityType> NPC_TYPES = List.of(OLD_MAN, OLD_WOMAN);
}
