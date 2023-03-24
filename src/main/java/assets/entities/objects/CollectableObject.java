package assets.entities.objects;

import application.GamePanel;
import assets.entities.Object;
import assets.entities.objects.interfaces.Interactable;


import static utilities.drawers.MessageDrawer.addMessage;

public class CollectableObject extends Object implements Interactable
{
    public CollectableObject(GamePanel gp)
    {
        super(gp);
    }

    @Override
    public void interact()
    {
        collect();
        super.interact();
        addMessage(String.format("You got %s", name));
    }

    public void collect()
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
