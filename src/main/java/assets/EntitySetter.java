package assets;

import application.GamePanel;
import assets.entities.Object;
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
        gp.monsters.add(new OrangeSlime(gp, 43, 12));
        gp.monsters.add(new OrangeSlime(gp, 42, 13));

        gp.monsters.add(new GreenSlime(gp, 42, 12));
        gp.monsters.add(new GreenSlime(gp, 43, 13));
        gp.monsters.add(new GreenSlime(gp, 44, 13));
        gp.monsters.add(new GreenSlime(gp, 45, 13));
        gp.monsters.add(new GreenSlime(gp, 46, 13));
        gp.monsters.add(new GreenSlime(gp, 33, 30));
        gp.monsters.add(new GreenSlime(gp, 33, 31));
        gp.monsters.add(new OrangeSlime(gp, 33, 32));

        gp.monsters.add(new Demon(gp, 33, 33));
        gp.monsters.add(new Demon(gp, 7, 14));
        gp.monsters.add(new Demon(gp, 9, 14));
        gp.monsters.add(new Demon(gp, 11, 14));
    }

    public void setNpcs()
    {
        gp.npcs.add(new OldMan(gp, 27, 26));
        gp.npcs.add(new OldWoman(gp, 31, 10));
    }

    public void setObjects()
    {
        addObject(ENERGY, 27, 25);
        addObject(HEART, 27, 26);
        addObject(ENERGY_POTION, 27, 24);
        addObject(HEALTH_POTION, 25, 25);
        addObject(COIN, 26, 26);
        addObject(AXE, 24, 24);

        addObject(BOOTS, 9, 52);
        addObject(BOOTS, 29, 26);

        addObject(KEY, 27, 28);
        addObject(KEY, 8, 39);
        addObject(KEY, 30, 10);
        addObject(KEY, 48, 11);
        addObject(KEY, 34, 52);

        addObject(MONKEY, 33, 28);

        addObject(DOOR, 33, 29);
        addObject(DOOR, 8, 33);
        addObject(DOOR, 8, 28);
        addObject(DOOR, 9, 11);

        addObject(CHEST, 9, 9);
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
