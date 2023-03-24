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

    public static final List<EntityType> OBJECT_TYPES = List.of(CHEST, DOOR, MONKEY, BOOTS, KEY, POTION, HEART, ENERGY, COIN, SHIELD, SWORD, AXE);
    public static final List<EntityType> MONSTER_TYPES = List.of(DEMON, SLIME);
    public static final List<EntityType> NPC_TYPES = List.of(OLD_MAN, OLD_WOMAN);
    public static final List<EntityType> MOVING_ENTTIES = Stream.of(MONSTER_TYPES, NPC_TYPES, List.of(PLAYER))
            .flatMap(List::stream).toList();
}
