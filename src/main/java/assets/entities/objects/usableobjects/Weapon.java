package assets.entities.objects.usableobjects;

import application.GamePanel;
import assets.entities.objects.UsableObject;

import java.awt.*;

import static utilities.drawers.MessageDrawer.addMessage;

public class Weapon extends UsableObject
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
        if (gp.player.currentWeapon.type != type)
        {
            gp.player.currentWeapon = this;
            gp.player.attackValue = gp.player.calculateAttack();
            addMessage(String.format("You equipped the %s", name));
        }
    }
}
