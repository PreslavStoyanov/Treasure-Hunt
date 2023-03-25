package assets.entities.objects.collectables.equppables;

import application.GamePanel;
import assets.entities.objects.collectables.EquipableItem;
import assets.interfaces.Equipable;

import static application.Application.objectsImagesUrls;
import static assets.EntityType.BOOTS;
import static utilities.images.ImageUtils.setupDefaultSizeImage;
import static utilities.sound.Sound.POWER_UP;

public class Boots extends EquipableItem
{
    public Boots(GamePanel gp)
    {
        super(gp);
        this.name = "Boots";
        this.type = BOOTS;
        this.interactSound = POWER_UP; //TODO add TAKE_BOOTS sound
        this.defaultImage = setupDefaultSizeImage(objectsImagesUrls.get("boots"));
        this.description = String.format("""
                [%s]
                Make you faster!""", name);
    }

    @Override
    public void useItem()
    {
        switch (gp.player.movingSpeed)
        {
            case 4 -> equip();
            case 6 -> deEquip();
        }
    }

    @Override
    public void equip()
    {
        isEquipped = true;
        gp.soundHandler.playSoundEffect(POWER_UP);
        gp.player.movingSpeed += 2;
    }

    @Override
    public void deEquip()
    {
        isEquipped = false;
        gp.player.movingSpeed -= 2;
    }
}
