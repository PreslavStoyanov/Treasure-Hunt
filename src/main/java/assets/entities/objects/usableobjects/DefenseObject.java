package assets.entities.objects.usableobjects;

import application.GamePanel;
import assets.entities.Object;
import assets.entities.objects.UsableObject;

import static utilities.drawers.MessageDrawer.addMessage;

public class DefenseObject extends UsableObject
{
    public int defenseValue;

    public DefenseObject(GamePanel gp)
    {
        super(gp);
    }

    @Override
    public void useItem()
    {
        if (gp.player.currentShield.type != type)
        {
            gp.player.currentShield = this;
            gp.player.defense = gp.player.calculateDefense();
            addMessage(String.format("You equipped the %s", name));
        }
    }
}
