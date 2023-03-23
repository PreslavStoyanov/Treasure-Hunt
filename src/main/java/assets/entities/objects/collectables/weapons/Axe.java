package assets.entities.objects.collectables.weapons;

import application.GamePanel;
import assets.EntityType;
import assets.entities.objects.collectables.Weapon;

import java.awt.*;

import static application.Application.defaultImagesUrls;
import static utilities.images.ImageUtils.setupDefaultSizeImage;
import static utilities.sound.Sound.TAKE_AXE;

public class Axe extends Weapon
{
    public Axe(GamePanel gp)
    {
        super(gp);
        this.name = "Axe";
        this.type = EntityType.AXE;
        this.interactSound = TAKE_AXE;
        this.attackValue = 2;
        this.attackArea = new Rectangle(0, 0, 30, 30);
        this.description = String.format("""
                [%s]
                An old axe!""", name);
        this.defaultImage = setupDefaultSizeImage(defaultImagesUrls.get("axe"));
    }
}
