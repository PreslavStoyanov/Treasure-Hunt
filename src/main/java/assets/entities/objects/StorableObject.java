package assets.entities.objects;

import application.GamePanel;
import assets.entities.Object;
import assets.entities.objects.storables.EquipableItem;

import static assets.EntityType.EQUIPPABLE_ITEMS;
import static utilities.drawers.MessageDrawer.addMessage;

public abstract class StorableObject extends Object
{
    public int price = 1;

    public StorableObject(GamePanel gp)
    {
        super(gp);
    }

    @Override
    public void interact()
    {
        if (gp.player.inventory.haveSpace())
        {
            store();
            super.interact();
            addMessage(String.format("You got %s", name));
        }
    }

    public void store()
    {
        gp.player.inventory.add(this);
    }

    public void useItem()
    {

    }

    public boolean isAffordable()
    {
        if (this.price > gp.player.coins)
        {
            addMessage("You don't have enough coins!");
            return false;
        }
        return true;
    }

    public boolean isEquippedItem()
    {
        if (EQUIPPABLE_ITEMS.contains(this.type) && ((EquipableItem) this).isEquipped)
        {
            addMessage("The item is equipped");
            return true;
        }
        return false;
    }
}
