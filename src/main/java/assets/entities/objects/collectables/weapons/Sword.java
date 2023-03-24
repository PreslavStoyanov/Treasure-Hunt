package assets.entities.objects.collectables.weapons;

import application.GamePanel;
import assets.EntityType;
import assets.entities.objects.collectables.Weapon;

import java.awt.*;

import static application.Application.objectsImagesUrls;
import static utilities.images.ImageUtils.setupDefaultSizeImage;

public class Sword extends Weapon
{

    public Sword(GamePanel gp)
    {
        super(gp);
        this.name = "Sword";
        this.type = EntityType.SWORD;
        this.attackValue = 1;
        this.attackArea = new Rectangle(0, 0, 36, 36);
        this.description = String.format("""
                [%s]
                An old sword!""", name);
        this.defaultImage = setupDefaultSizeImage(objectsImagesUrls.get("sword"));
    }
}
