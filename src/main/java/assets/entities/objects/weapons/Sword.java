package assets.entities.objects.weapons;

import application.GamePanel;
import assets.EntityType;
import assets.entities.objects.Weapon;

import java.awt.*;

import static utilities.images.ImageUtils.setupDefaultImage;

public class Sword extends Weapon
{

    public Sword(GamePanel gp)
    {
        super(gp);
        this.name = "Sword";
        this.type = EntityType.SWORD;
        this.image = setupDefaultImage("/objects/sword.png");
        this.attackValue = 1;
        this.attackArea = new Rectangle(0, 0, 36, 36);
        this.description = String.format("[%s]\nAn old sword!", name);
    }
}
