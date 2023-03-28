package assets.entities.objects.storables.potions;

import application.GamePanel;
import assets.entities.objects.storables.Potion;
import assets.interfaces.Consumable;

import static application.Application.objectsImagesUrls;
import static utilities.drawers.MessageDrawer.addMessage;
import static utilities.images.ImageUtils.setupDefaultSizeImage;

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
        this.defaultImage = setupDefaultSizeImage(objectsImagesUrls.get("energy-potion"));
    }

    @Override
    public void consume()
    {
        gp.player.increaseEnergy(value);
        super.consume();
        addMessage(String.format("You gained %d energy!", value));
    }
}
