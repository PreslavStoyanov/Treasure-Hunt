package assets.entities.objects.usableobjects.weapons;

import application.GamePanel;
import assets.EntityType;
import assets.entities.objects.usableobjects.Weapon;

import java.awt.*;

import static utilities.images.ImageUtils.setupDefaultImage;

public class Axe extends Weapon
{
    public Axe(GamePanel gp)
    {
        super(gp);
        this.name = "Axe";
        this.type = EntityType.AXE;
        this.image = setupDefaultImage("/objects/axe.png");
        this.attackValue = 2;
        this.attackArea = new Rectangle(0, 0, 30, 30);
        this.description = String.format("[%s]\nAn old axe!", name);
    }
}
