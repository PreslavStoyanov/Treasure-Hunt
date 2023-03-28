package assets.entities.objects.storables.equppables;

import application.GamePanel;
import assets.entities.objects.storables.EquipableItem;

import java.util.Optional;

import static utilities.drawers.MessageDrawer.addMessage;
import static utilities.sound.Sound.TAKE_WEAPON;

public class DefenseObject extends EquipableItem
{

    public DefenseObject(GamePanel gp)
    {
        super(gp);
        this.interactSound = TAKE_WEAPON;
    }

    @Override
    public void useItem()
    {
        super.useItem();
        gp.player.defense = gp.player.calculateDefense();
    }

    @Override
    public void equip()
    {
        gp.player.currentShield.ifPresent(DefenseObject::deEquip);
        gp.player.currentShield = Optional.of(this);
        addMessage(String.format("You equipped the %s", name));
    }

    @Override
    public void deEquip()
    {
        gp.player.currentShield = Optional.empty();
    }
}
