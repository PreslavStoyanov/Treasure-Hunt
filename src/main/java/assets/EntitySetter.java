package assets;

import application.GamePanel;
import assets.entities.Object;
import assets.entities.objects.Boat;
import assets.entities.objects.Chest;
import assets.entities.interactivetiles.trees.Apple;
import assets.entities.interactivetiles.trees.Oak;
import assets.entities.movingentities.liveentities.artificials.monsters.Demon;
import assets.entities.movingentities.liveentities.artificials.monsters.GreenSlime;
import assets.entities.movingentities.liveentities.artificials.monsters.OrangeSlime;
import assets.entities.movingentities.liveentities.artificials.npcs.OldMan;
import assets.entities.movingentities.liveentities.artificials.npcs.OldWoman;
import assets.entities.objects.*;
import assets.entities.objects.storables.Key;
import assets.entities.objects.storables.BoatPaddle;
import assets.entities.objects.storables.equppables.Boots;
import assets.entities.objects.storables.equppables.defenseobjects.Shield;
import assets.entities.objects.storables.equppables.weapons.Axe;
import assets.entities.objects.storables.equppables.weapons.Sword;
import assets.entities.objects.storables.potions.EnergyPotion;
import assets.entities.objects.storables.potions.ExpPotion;
import assets.entities.objects.storables.potions.HealthPotion;

import static application.GamePanel.TILE_SIZE;
import static application.GamePanel.WORLD_COLUMNS;
import static assets.ObjectType.*;

public class EntitySetter
{
    GamePanel gp;

    public EntitySetter(GamePanel gp)
    {
        this.gp = gp;
    }

    public void setMapOneEntities()
    {
        setMapOneMonsters();
        setMapOneNpcs();
        setMapOneInteractiveTiles();
        setMapOneObjects();
    }

    private void setMapOneMonsters()
    {
        gp.monsters.clear();
        gp.monsters.add(new OrangeSlime(gp, 36, 27));
        gp.monsters.add(new OrangeSlime(gp, 35, 27));
        gp.monsters.add(new OrangeSlime(gp, 34, 27));

        gp.monsters.add(new GreenSlime(gp, 36, 22));
        gp.monsters.add(new GreenSlime(gp, 35, 22));
        gp.monsters.add(new GreenSlime(gp, 34, 22));

        gp.monsters.add(new Demon(gp, 25, 15));
        gp.monsters.add(new Demon(gp, 24, 15));
        gp.monsters.add(new Demon(gp, 23, 15));
    }

    private void setMapOneNpcs()
    {
        gp.npcs.clear();
        gp.npcs.add(new OldMan(gp, 26, 27));
        gp.npcs.add(new OldWoman(gp, 23, 22));
    }

    private void setMapOneInteractiveTiles()
    {
        gp.breakableTiles.clear();

        Apple apple = new Apple(gp);
        apple.setWorldLocation(17 * TILE_SIZE, 26 * TILE_SIZE);
        gp.breakableTiles.add(apple);

        Oak oak = new Oak(gp);
        oak.setWorldLocation(17 * TILE_SIZE, 25 * TILE_SIZE);
        gp.breakableTiles.add(oak);

        Oak oak2 = new Oak(gp);
        oak2.setWorldLocation(16 * TILE_SIZE, 26 * TILE_SIZE);
        gp.breakableTiles.add(oak2);
    }

    private void setMapOneObjects()
    {
        gp.objects.clear();
        addObject(EXP_POTION, 13, 24);
        addObject(ENERGY_POTION, 14, 24);
        addObject(HEALTH_POTION, 15, 24);
        addObject(AXE, 22, 15);
        addObject(SWORD, 27, 26);
        addObject(SHIELD, 27, 25);

        addObject(BOOTS, 24, 34);

        addObject(KEY, 37, 26);
        addObject(KEY, 14, 26);

        addObject(MONKEY, 22, 23);

        addObject(DOOR, 26, 14);
        addObject(DOOR, 26, 17);

        gp.objects.add(new Chest(gp,23, 12, SHIP_WHEEL));
        gp.objects.add(new Boat(gp, 25, 40, 18, 47));
        gp.objects.add(new Boat(gp, 18, 48, 25, 39));
    }

    public void addObject(ObjectType objectType, int x, int y)
    {
        switch (objectType)
        {
            case DOOR -> gp.objects.add(new Door(gp, x, y));
            case MONKEY -> gp.objects.add(new Monkey(gp, x, y));
            case BOOTS -> addCollectableItem(new Boots(gp), x, y);
            case KEY -> addCollectableItem(new Key(gp), x, y);
            case HEALTH_POTION -> addCollectableItem(new HealthPotion(gp), x, y);
            case ENERGY_POTION -> addCollectableItem(new EnergyPotion(gp), x, y);
            case EXP_POTION -> addCollectableItem(new ExpPotion(gp), x, y);
            case HEART -> addCollectableItem(new Heart(gp), x, y);
            case ENERGY -> addCollectableItem(new Energy(gp), x, y);
            case COIN -> addCollectableItem(new Coin(gp), x, y);
            case SHIELD -> addCollectableItem(new Shield(gp), x, y);
            case SWORD -> addCollectableItem(new Sword(gp), x, y);
            case AXE -> addCollectableItem(new Axe(gp), x, y);
            case SHIP_WHEEL -> addCollectableItem(new BoatPaddle(gp), x, y);
        }
    }

    private void addCollectableItem(Object object, int x, int y)
    {
        if (x <= WORLD_COLUMNS)
        {
            x *= TILE_SIZE;
            y *= TILE_SIZE;
        }
        object.setWorldLocation(x, y);
        gp.objects.add(object);
    }
}
