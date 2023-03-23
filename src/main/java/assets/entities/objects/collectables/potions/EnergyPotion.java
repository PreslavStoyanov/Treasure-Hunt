package assets.entities.objects.collectables.potions;

import application.GamePanel;
import assets.entities.objects.collectables.Potion;
import assets.entities.objects.interfaces.Consumable;

import static application.Application.defaultImagesUrls;
import static utilities.drawers.MessageDrawer.addMessage;
import static utilities.images.ImageUtils.setupDefaultSizeImage;
import static utilities.sound.Sound.DRINK_POTION;

public class EnergyPotion extends Potion implements Consumable
{

    public EnergyPotion(GamePanel gp)
    {
        super(gp);

        this.value = 50;
        this.name = "Energy Potion";
        this.description = String.format("""
                [%s]
                Gives you %d energy!""", name, value);
        this.defaultImage = setupDefaultSizeImage(defaultImagesUrls.get("energy-potion"));
    }

    @Override
    public void consume()
    {
        gp.player.increaseEnergy(value);
        super.consume();
        addMessage(String.format("You gained %d energy!", value));
    }
}
