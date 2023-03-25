package assets.entities.objects.collectables;

import application.GamePanel;
import assets.entities.objects.StorableObject;
import assets.interfaces.Equipable;

public abstract class EquipableItem extends StorableObject implements Equipable
{
    public int value;
    public boolean isEquipped = false;

    public EquipableItem(GamePanel gp)
    {
        super(gp);
    }

    @Override
    public void useItem()
    {
        if (isEquipped)
        {
            deEquip();
        }
        else
        {
            equip();
        }
        isEquipped = !isEquipped;
    }

    @Override
    public abstract void equip();

    @Override
    public abstract void deEquip();
}
