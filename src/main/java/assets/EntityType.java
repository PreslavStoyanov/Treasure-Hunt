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
    POTION,
    HEART,
    ENERGY,
    COIN,
    SHIELD,
    SWORD,
    AXE;

    public static final List<EntityType> COLLECTABLE_OBJECTS = List.of(BOOTS, KEY, POTION, SWORD, AXE, SHIELD);
    public static final List<EntityType> CONSUMABLE_OBJECTS = List.of(POTION);
    public static final List<EntityType> EQUIPABLE_OBJECTS = List.of(AXE, SWORD, SHIELD, BOOTS);
    public static final List<EntityType> INTERACTABLE_OBJECTS = List.of(DOOR, MONKEY, CHEST, ENERGY, HEART, COIN);
    public static final List<EntityType> OBJECT_TYPES = Stream.of(COLLECTABLE_OBJECTS, INTERACTABLE_OBJECTS)
            .flatMap(List::stream).toList();

    public static final List<EntityType> MONSTER_TYPES = List.of(DEMON, SLIME);
    public static final List<EntityType> NPC_TYPES = List.of(OLD_MAN, OLD_WOMAN);
}
