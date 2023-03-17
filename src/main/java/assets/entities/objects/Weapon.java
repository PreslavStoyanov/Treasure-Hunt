package assets.entities.objects;

import application.GamePanel;
import assets.entities.Object;

import java.awt.*;

import static assets.EntityType.weaponObjects;
import static utilities.drawers.MessageDrawer.addMessage;

public class Weapon extends Object
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
            gp.player.attack = gp.player.calculateAttack();
            addMessage(String.format("You equipped the %s", name));
        }
    }
}
