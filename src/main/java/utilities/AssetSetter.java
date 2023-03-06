package utilities;

import View.GamePanel;
import entities.Entity.Entity;
import entities.Entity.OldMan;
import entities.Entity.OldWoman;
import entities.Monsters.Demon;
import entities.Monsters.GreenSlime;
import entities.Monsters.OrangeSlime;
import entities.Objects.*;

import java.util.ArrayList;

public class AssetSetter
{
    GamePanel gp;
    int i;

    public AssetSetter(GamePanel gp)
    {
        this.gp = gp;
    }

    public void setMonster()
    {
        i = 0;
        gp.monsters.add(i, new GreenSlime(gp));
        assertCoordinates(gp.monsters, i, 42, 12);

        gp.monsters.add(++i, new OrangeSlime(gp));
        assertCoordinates(gp.monsters, i, 43, 12);

        gp.monsters.add(++i, new OrangeSlime(gp));
        assertCoordinates(gp.monsters, i, 42, 13);

        gp.monsters.add(++i, new GreenSlime(gp));
        assertCoordinates(gp.monsters, i, 43, 13);

        gp.monsters.add(++i, new GreenSlime(gp));
        assertCoordinates(gp.monsters, i, 44, 13);

        gp.monsters.add(++i, new GreenSlime(gp));
        assertCoordinates(gp.monsters, i, 45, 13);

        gp.monsters.add(++i, new GreenSlime(gp));
        assertCoordinates(gp.monsters, i, 46, 13);

        gp.monsters.add(++i, new Demon(gp));
        assertCoordinates(gp.monsters, i, 7, 14);

        gp.monsters.add(++i, new Demon(gp));
        assertCoordinates(gp.monsters, i, 9, 14);

        gp.monsters.add(++i, new Demon(gp));
        assertCoordinates(gp.monsters, i, 11, 14);
    }

    public void setNpc()
    {
        i = 0;
        gp.npc.add(i, new OldMan(gp));
        assertCoordinates(gp.npc, i, 27, 25);

        gp.npc.add(++i, new OldWoman(gp));
        assertCoordinates(gp.npc, i, 29, 12);
    }

    public void setObject()
    {
        i = 0;
        gp.objects.add(i, new Boots(gp));
        assertCoordinates(gp.objects, i, 9, 52);

        gp.objects.add(++i, new Monkey(gp));
        assertCoordinates(gp.objects, i, 33, 28);

        gp.objects.add(++i, new Key(gp));
        assertCoordinates(gp.objects, i, 8, 39);

        gp.objects.add(++i, new Key(gp));
        assertCoordinates(gp.objects, i, 30, 10);

        gp.objects.add(++i, new Key(gp));
        assertCoordinates(gp.objects, i, 48, 11);

        gp.objects.add(++i, new Key(gp));
        assertCoordinates(gp.objects, i, 34, 52);

        gp.objects.add(++i, new Door(gp));
        assertCoordinates(gp.objects, i, 8, 33);

        gp.objects.add(++i, new Door(gp));
        assertCoordinates(gp.objects, i, 8, 28);

        gp.objects.add(++i, new Door(gp));
        assertCoordinates(gp.objects, i, 9, 11);

        gp.objects.add(++i, new Chest(gp));
        assertCoordinates(gp.objects, i, 9, 9);

    }

    private void assertCoordinates(ArrayList<Entity> list, int tileNumber, int x, int y)
    {
        list.get(tileNumber).worldX = x * gp.tileSize;
        list.get(tileNumber).worldY = y * gp.tileSize;
    }
}