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

    public static final List<EntityType> monstersTypes = List.of(DEMON, SLIME);
    public static final List<EntityType> collectableObjects = List.of(BOOTS, KEY, HEALTH_POTION, HEART, SWORD, SHIELD, AXE);
    public static final List<EntityType> staticObjects = List.of(DOOR, MONKEY, CHEST);
    public static final List<EntityType> objectTypes = Stream.of(collectableObjects, staticObjects)
            .flatMap(List::stream).toList();
    public static final List<EntityType> weaponObjects = List.of(AXE, SWORD);
    public static final List<EntityType> npcTypes = List.of(OLD_MAN, OLD_WOMAN);
}
