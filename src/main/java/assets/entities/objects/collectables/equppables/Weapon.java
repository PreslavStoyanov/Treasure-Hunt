package assets.entities.objects.collectables.equppables;

import application.GamePanel;
import assets.entities.objects.collectables.EquipableItem;
import assets.interfaces.Equipable;

import java.awt.*;

import static utilities.drawers.MessageDrawer.addMessage;

public class Weapon extends EquipableItem
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
        if (gp.player.currentWeapon != null)
        {
            gp.player.currentWeapon.deEquip();
        }
        gp.player.currentWeapon = this;
        isEquipped = true;
        gp.player.attackValue = gp.player.calculateAttack();
        addMessage(String.format("You equipped the %s", name));
    }

    @Override
    public void deEquip()
    {
        isEquipped = false;
    }
}
