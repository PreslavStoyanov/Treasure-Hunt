package assets.entities.objects.storables.equppables;

import application.GamePanel;
import assets.entities.objects.storables.EquipableItem;

import static application.Application.objectsImagesUrls;
import static assets.EntityType.BOOTS;
import static utilities.images.ImageUtils.setupDefaultSizeImage;
import static utilities.sound.Sound.POWER_UP;
import static utilities.sound.Sound.TAKE_BOOTS;

public class Boots extends EquipableItem
{
    public Boots(GamePanel gp)
    {
        super(gp);
        name = "Boots";
        type = BOOTS;
        interactSound = TAKE_BOOTS;
        value = 2;
        defaultImage = setupDefaultSizeImage(objectsImagesUrls.get("boots"));
        description = String.format("""
                [%s]
                Make you faster!""", name);
    }

    @Override
    public void equip()
    {
        gp.soundEffectsHandler.playSoundEffect(POWER_UP);
        gp.player.movingSpeed += value;
    }

    @Override
    public void deEquip()
    {
        gp.player.movingSpeed -= value;
    }
}
