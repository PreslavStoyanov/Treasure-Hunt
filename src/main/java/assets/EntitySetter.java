package assets;

import application.GamePanel;
import assets.entities.Object;
import assets.entities.interactivetiles.trees.Apple;
import assets.entities.interactivetiles.trees.Oak;
import assets.entities.movingentities.liveentities.artificials.monsters.Demon;
import assets.entities.movingentities.liveentities.artificials.monsters.GreenSlime;
import assets.entities.movingentities.liveentities.artificials.monsters.OrangeSlime;
import assets.entities.movingentities.liveentities.artificials.npcs.OldMan;
import assets.entities.movingentities.liveentities.artificials.npcs.OldWoman;
import assets.entities.objects.*;
import assets.entities.objects.collectables.Boots;
import assets.entities.objects.collectables.Key;
import assets.entities.objects.collectables.defenseobjects.Shield;
import assets.entities.objects.collectables.potions.EnergyPotion;
import assets.entities.objects.collectables.potions.ExpPotion;
import assets.entities.objects.collectables.potions.HealthPotion;
import assets.entities.objects.collectables.weapons.Axe;
import assets.entities.objects.collectables.weapons.Sword;

import static application.GamePanel.tileSize;
import static assets.ObjectType.*;

public class EntitySetter
{
    GamePanel gp;

    public EntitySetter(GamePanel gp)
    {
        this.gp = gp;
    }

    public void setMonsters()
    {
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

    public void setNpcs()
    {
        gp.npcs.add(new OldMan(gp, 26, 27));
        gp.npcs.add(new OldWoman(gp, 23, 22));
    }

    public void setInteractiveTiles()
    {
        Apple apple = new Apple(gp);
        apple.setWorldLocation(17 * tileSize, 26 * tileSize);
        gp.interactiveTiles.add(apple);

        Oak oak = new Oak(gp);
        oak.setWorldLocation(17 * tileSize, 25 * tileSize);
        gp.interactiveTiles.add(oak);

        Oak oak2 = new Oak(gp);
        oak2.setWorldLocation(16 * tileSize, 26 * tileSize);
        gp.interactiveTiles.add(oak2);
    }

    public void setObjects()
    {
        addObject(EXP_POTION, 13, 24);
        addObject(ENERGY_POTION, 14, 24);
        addObject(HEALTH_POTION, 15, 24);
        addObject(AXE, 22, 15);

        addObject(BOOTS, 24, 34);

        addObject(KEY, 37, 26);
        addObject(KEY, 14, 26);

        addObject(MONKEY, 22, 23);

        addObject(DOOR, 26, 14);
        addObject(DOOR, 26, 17);

        addObject(CHEST, 23, 12);
    }

    public void addObject(ObjectType objectType, int x, int y)
    {
        switch (objectType)
        {
            case CHEST -> gp.objects.add(new Chest(gp, x, y));
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
        }
    }

    private void addCollectableItem(Object object, int x, int y)
    {
        object.setWorldLocation(x * tileSize, y * tileSize);
        gp.objects.add(object);
    }
}
