package assets.entities.objects.collectables;

import application.GamePanel;
import assets.entities.objects.StorableObject;
import assets.interfaces.Equipable;

import static application.Application.objectsImagesUrls;
import static assets.EntityType.BOOTS;
import static utilities.images.ImageUtils.setupDefaultSizeImage;
import static utilities.sound.Sound.POWER_UP;

public class Boots extends StorableObject implements Equipable
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
        //TODO draw equip effect in inventory
        gp.soundHandler.playSoundEffect(POWER_UP);
        gp.player.movingSpeed += 2;
    }

    @Override
    public void deEquip()
    {
        gp.player.movingSpeed -= 2;
    }
}
