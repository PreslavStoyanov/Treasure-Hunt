package assets.entities.objects.usableobjects;

import application.GamePanel;
import assets.entities.objects.UsableObject;

import static application.Application.defaultImagesUrls;
import static assets.EntityType.HEALTH_POTION;
import static utilities.drawers.MessageDrawer.addMessage;
import static utilities.images.ImageUtils.setupDefaultImage;
import static utilities.sound.Sound.DRINK_POTION;
import static utilities.sound.Sound.POWER_UP;

public class HealthPotion extends UsableObject
{
    private final int value = 4;

    public HealthPotion(GamePanel gp)
    {
        super(gp);
        this.solidArea.setSize(36, 36);
        this.name = "Health Potion";
        this.type = HEALTH_POTION;
        this.description = String.format("[%s]\nGives you 2 hearts!", name);
        this.image = setupDefaultImage(defaultImagesUrls.get("health-potion"));
    }

    @Override
    public void useItem()
    {
        gp.player.increaseLife(value);
        gp.player.inventory.remove(this);
        gp.soundHandler.playSoundEffect(DRINK_POTION);
        addMessage("You gained 2 hearts!");
    }
}
