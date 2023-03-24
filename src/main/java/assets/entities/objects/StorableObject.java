package assets.entities.objects;

import application.GamePanel;
import assets.entities.Object;
import assets.interfaces.Interactive;


import static utilities.drawers.MessageDrawer.addMessage;

public class StorableObject extends Object implements Interactive
{
    public StorableObject(GamePanel gp)
    {
        super(gp);
    }

    @Override
    public void interact()
    {
        store();
        super.interact();
        addMessage(String.format("You got %s", name));
    }

    public void store()
    {
        if (gp.player.inventory.size() == gp.player.inventoryCapacity)
        {
            addMessage("Inventory is full!");
            return;
        }
        gp.player.inventory.add(this);
    }

    public void useItem()
    {

    }
}
