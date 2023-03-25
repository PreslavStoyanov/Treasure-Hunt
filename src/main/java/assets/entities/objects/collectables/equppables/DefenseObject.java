package assets.entities.objects.collectables.equppables;

import application.GamePanel;
import assets.entities.objects.StorableObject;
import assets.entities.objects.collectables.EquipableItem;
import assets.interfaces.Equipable;

import static utilities.drawers.MessageDrawer.addMessage;

public class DefenseObject extends EquipableItem
{
    public int defenseValue;

    public DefenseObject(GamePanel gp)
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
        if (gp.player.currentShield != null)
        {
            gp.player.currentShield.deEquip();
        }
        gp.player.currentShield = this;
        isEquipped = true;
        gp.player.defense = gp.player.calculateDefense();
        addMessage(String.format("You equipped the %s", name));
    }

    @Override
    public void deEquip()
    {
        isEquipped = false;
    }
}
