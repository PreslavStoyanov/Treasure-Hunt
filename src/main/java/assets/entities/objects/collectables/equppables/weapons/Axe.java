package assets.entities.objects.collectables.equppables.weapons;

import application.GamePanel;
import assets.EntityType;
import assets.entities.objects.collectables.equppables.Weapon;
import utilities.sound.Sound;

import java.awt.*;

import static application.Application.objectsImagesUrls;
import static utilities.images.ImageUtils.setupDefaultSizeImage;

public class Axe extends Weapon
{
    public Axe(GamePanel gp)
    {
        super(gp);
        this.name = "Axe";
        this.type = EntityType.AXE;
        this.value = 2;
        this.attackArea = new Rectangle(0, 0, 30, 30);
        swingSound = Sound.SWING_AXE;
        this.description = String.format("""
                [%s]
                An old axe!""", name);
        this.defaultImage = setupDefaultSizeImage(objectsImagesUrls.get("axe"));
    }
}
