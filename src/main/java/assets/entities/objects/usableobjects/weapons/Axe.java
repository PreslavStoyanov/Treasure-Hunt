package assets.entities.objects.usableobjects.weapons;

import application.GamePanel;
import assets.EntityType;
import assets.entities.objects.usableobjects.Weapon;

import java.awt.*;

import static application.Application.properties;
import static utilities.images.ImageUtils.setupDefaultImage;

public class Axe extends Weapon
{
    public Axe(GamePanel gp)
    {
        super(gp);
        this.name = "Axe";
        this.type = EntityType.AXE;
        this.attackValue = 2;
        this.attackArea = new Rectangle(0, 0, 30, 30);
        this.description = String.format("[%s]\nAn old axe!", name);
        this.image = setupDefaultImage(properties.get("images.axe"));
    }
}
