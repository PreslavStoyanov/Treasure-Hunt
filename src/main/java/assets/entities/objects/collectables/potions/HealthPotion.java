package assets.entities.objects.collectables.potions;

import application.GamePanel;
import assets.entities.objects.collectables.Potion;
import assets.entities.objects.interfaces.Consumable;

import static application.Application.defaultImagesUrls;
import static utilities.drawers.MessageDrawer.addMessage;
import static utilities.images.ImageUtils.setupDefaultSizeImage;
import static utilities.sound.Sound.DRINK_POTION;

public class HealthPotion extends Potion implements Consumable
{

    public HealthPotion(GamePanel gp)
    {
        super(gp);
        this.value = 4;
        this.name = "Health Potion";
        this.description = String.format("""
                [%s]
                Gives you %d hearts!""", name, value / 2);
        this.defaultImage = setupDefaultSizeImage(defaultImagesUrls.get("health-potion"));
    }

    @Override
    public void consume()
    {
        gp.player.increaseLife(value);
        super.consume();
        addMessage(String.format("You gained %d hearts!", value / 2));
    }
}
