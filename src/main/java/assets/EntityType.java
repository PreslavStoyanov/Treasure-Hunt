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
    MERCHANT_GIRL,
    CHEST,
    BOAT,
    BOAT_PADDLE,
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
    AXE,
    TREE;

    public static final List<EntityType> OBJECT_TYPES = List.of(CHEST, DOOR, MONKEY, BOOTS, KEY, POTION, HEART, ENERGY, COIN, SHIELD, SWORD, AXE, BOAT, BOAT_PADDLE);
    public static final List<EntityType> MONSTER_TYPES = List.of(DEMON, SLIME);
    public static final List<EntityType> NPC_TYPES = List.of(OLD_MAN, OLD_WOMAN, MERCHANT_GIRL);
    public static final List<EntityType> INTERACTIVE_TILES = List.of(TREE);
    public static final List<EntityType> EQUIPPABLE_ITEMS = List.of(AXE, SWORD, SHIELD, BOOTS);
    public static final List<EntityType> MOVING_ENTITIES = Stream.of(MONSTER_TYPES, NPC_TYPES, List.of(PLAYER))
            .flatMap(List::stream).toList();
}
