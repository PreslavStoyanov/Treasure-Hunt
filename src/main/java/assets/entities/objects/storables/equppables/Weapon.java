package assets.entities.objects.storables.equppables;

import application.GamePanel;
import assets.entities.objects.storables.EquipableItem;
import utilities.sound.Sound;

import java.awt.*;
import java.util.Optional;

import static utilities.drawers.MessageDrawer.addMessage;
import static utilities.sound.Sound.TAKE_WEAPON;

public abstract class Weapon extends EquipableItem
{
    public Rectangle attackArea;
    public Sound swingSound;

    public Weapon(GamePanel gp)
    {
        super(gp);
        this.interactSound = TAKE_WEAPON;
    }

    @Override
    public void useItem()
    {
        super.useItem();
        gp.player.attackValue = gp.player.calculateAttack();
    }

    @Override
    public void equip()
    {
        gp.player.currentWeapon.ifPresent(Weapon::useItem);
        gp.player.currentWeapon = Optional.of(this);
        addMessage(String.format("You equipped the %s", name));
    }

    @Override
    public void deEquip()
    {
        gp.player.currentWeapon = Optional.empty();
    }
}
