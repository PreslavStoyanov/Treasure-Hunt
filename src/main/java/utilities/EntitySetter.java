package utilities;

import application.GamePanel;
import assets.entities.Object;
import assets.entities.liveentities.monsters.Demon;
import assets.entities.liveentities.monsters.GreenSlime;
import assets.entities.liveentities.monsters.OrangeSlime;
import assets.entities.liveentities.npcs.OldMan;
import assets.entities.liveentities.npcs.OldWoman;
import assets.entities.objects.*;
import assets.entities.objects.weapons.Axe;

import static application.GamePanel.tileSize;

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

        gp.monsters.add(new Demon(gp, 7, 14));
        gp.monsters.add(new Demon(gp, 9, 14));
        gp.monsters.add(new Demon(gp, 11, 14));
    }

    public void setNpcs()
    {
        gp.npcs.add(new OldMan(gp, 27, 26));
        gp.npcs.add(new OldWoman(gp, 29, 12));
    }

    public void setObjects()
    {
        addCollectableItem(new Axe(gp), 24, 24);

        addCollectableItem(new Boots(gp), 9, 52);

        addCollectableItem(new Key(gp), 8, 39);
        addCollectableItem(new Key(gp), 30, 10);
        addCollectableItem(new Key(gp), 48, 11);
        addCollectableItem(new Key(gp), 34, 52);

        addCollectableItem(new HealthPotion(gp), 25, 25);

        gp.objects.add(new Monkey(gp, 33, 28));

        gp.objects.add(new Door(gp, 8, 33));
        gp.objects.add(new Door(gp, 8, 28));
        gp.objects.add(new Door(gp, 9, 11));

        gp.objects.add(new Chest(gp, 9, 9));
    }

    private void addCollectableItem(Object object, int x, int y)
    {
        object.setWorldLocation(x * tileSize, y * tileSize);
        gp.objects.add(object);
    }
}