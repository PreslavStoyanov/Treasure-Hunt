package assets.entities.objects.collectables;

import application.GamePanel;
import assets.entities.objects.CollectableObject;
import assets.entities.objects.interfaces.Equipable;

import java.awt.*;

import static utilities.drawers.MessageDrawer.addMessage;

public class Weapon extends CollectableObject implements Equipable
{
    public int attackValue;
    public Rectangle attackArea;

    public Weapon(GamePanel gp)
    {
        super(gp);
    }

    @Override
    public void useItem()
    {
        equip();
    }

    @Override
    public void equip()
    {
        gp.player.currentWeapon = this;
        gp.player.attackValue = gp.player.calculateAttack();
        addMessage(String.format("You equipped the %s", name));
    }

    @Override
    public void deEquip()
    {

    }
}
