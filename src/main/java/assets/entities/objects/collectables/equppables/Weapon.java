package assets.entities.objects.collectables.equppables;

import application.GamePanel;
import assets.entities.objects.collectables.EquipableItem;
import utilities.sound.Sound;

import java.awt.*;
import java.util.Optional;

import static utilities.drawers.MessageDrawer.addMessage;
import static utilities.sound.Sound.TAKE_AXE;

public class Weapon extends EquipableItem
{
    public Rectangle attackArea;
    public Sound swingSound;

    public Weapon(GamePanel gp)
    {
        super(gp);
        this.interactSound = TAKE_AXE;
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
