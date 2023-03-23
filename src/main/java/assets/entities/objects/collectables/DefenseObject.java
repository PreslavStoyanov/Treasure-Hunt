package assets.entities.objects.collectables;

import application.GamePanel;
import assets.entities.objects.CollectableObject;
import assets.entities.objects.interfaces.Equipable;

import static utilities.drawers.MessageDrawer.addMessage;

public class DefenseObject extends CollectableObject implements Equipable
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
        gp.player.currentShield = this;
        gp.player.defense = gp.player.calculateDefense();
        addMessage(String.format("You equipped the %s", name));
    }

    @Override
    public void deEquip()
    {

    }
}
