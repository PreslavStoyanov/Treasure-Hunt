package utilities;

import View.GamePanel;
import entities.entity.OldMan;
import entities.entity.OldWoman;
import entities.monsters.Demon;
import entities.monsters.GreenSlime;
import entities.monsters.OrangeSlime;
import entities.objects.*;

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
        gp.npcs.add(new OldMan(gp, 27, 25));
        gp.npcs.add(new OldWoman(gp, 29, 12));
    }

    public void setObjects()
    {
        gp.objects.add(new Boots(gp, 9, 52));
        gp.objects.add(new Boots(gp, 31, 28));

        gp.objects.add(new Monkey(gp, 33, 28));

        gp.objects.add(new Key(gp, 8, 39));
        gp.objects.add(new Key(gp, 30, 10));
        gp.objects.add(new Key(gp, 48, 11));
        gp.objects.add(new Key(gp, 34, 52));

        gp.objects.add(new Door(gp, 8, 33));
        gp.objects.add(new Door(gp, 8, 28));
        gp.objects.add(new Door(gp, 9, 11));

        gp.objects.add(new Chest(gp, 9, 9));
    }
}