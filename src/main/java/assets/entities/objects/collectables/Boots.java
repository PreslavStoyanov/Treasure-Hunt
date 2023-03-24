package assets.entities.objects.collectables;

import application.GamePanel;
import assets.entities.objects.CollectableObject;
import assets.entities.objects.interfaces.Equipable;

import static application.Application.defaultImagesUrls;
import static assets.EntityType.BOOTS;
import static utilities.images.ImageUtils.setupDefaultSizeImage;
import static utilities.sound.Sound.POWER_UP;

public class Boots extends CollectableObject implements Equipable
{
    public Boots(GamePanel gp)
    {
        super(gp);
        this.name = "Boots";
        this.type = BOOTS;
        this.interactSound = POWER_UP; //TODO add TAKE_BOOTS sound
        this.defaultImage = setupDefaultSizeImage(defaultImagesUrls.get("boots"));
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
        gp.soundHandler.playSoundEffect(POWER_UP);
        gp.player.movingSpeed += 2;
    }

    @Override
    public void deEquip()
    {
        gp.player.movingSpeed -= 2;
    }
}
