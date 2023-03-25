package assets.entities.objects.collectables;

import application.GamePanel;
import assets.entities.objects.StorableObject;
import assets.interfaces.Equipable;

public class EquipableItem extends StorableObject implements Equipable
{
    public boolean isEquipped = false;

    public EquipableItem(GamePanel gp)
    {
        super(gp);
    }

    @Override
    public void equip()
    {

    }

    @Override
    public void deEquip()
    {

    }
}
