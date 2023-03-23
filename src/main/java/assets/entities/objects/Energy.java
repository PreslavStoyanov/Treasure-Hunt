package assets.entities.objects;

import application.GamePanel;
import assets.entities.Object;
import assets.entities.objects.interfaces.Consumable;

import static application.Application.defaultImagesUrls;
import static assets.EntityType.ENERGY;
import static utilities.drawers.MessageDrawer.addMessage;
import static utilities.images.ImageUtils.setupDefaultSizeImage;
import static utilities.sound.Sound.POWER_UP;

public class Energy extends Object
{
    private final int value = 20;

    public Energy(GamePanel gp)
    {
        super(gp);
        solidArea.setSize(36, 36);
        name = "Energy";
        type = ENERGY;
        interactSound = POWER_UP;
        defaultImage = setupDefaultSizeImage(defaultImagesUrls.get("energy"));
    }

    @Override
    public void interact()
    {
        super.interact();
        gp.player.increaseEnergy(value);
        addMessage(String.format("You got %d energy", value));
    }
}
