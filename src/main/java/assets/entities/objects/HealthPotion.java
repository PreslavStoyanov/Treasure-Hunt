package assets.entities.objects;

import application.GamePanel;
import assets.entities.Object;

import static assets.EntityType.HEALTH_POTION;
import static utilities.images.ImageUtils.setupDefaultImage;

public class HealthPotion extends Object
{
    public HealthPotion(GamePanel gp)
    {
        super(gp);
        this.name = "Health Potion";
        this.type = HEALTH_POTION;
        this.image = setupDefaultImage("/objects/health_potion.png");
        this.description = String.format("[%s]\nGives you 2 hearts!", name);
    }
}
