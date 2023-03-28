package assets.entities.objects.storables;

import application.GamePanel;
import assets.entities.objects.StorableObject;

import static application.Application.objectsImagesUrls;
import static assets.EntityType.STEERING_WHEEL;
import static utilities.drawers.MessageDrawer.addMessage;
import static utilities.images.ImageUtils.setupDefaultSizeImage;
import static utilities.sound.Sound.TAKE_KEY;

public class ShipWheel extends StorableObject
{

    public ShipWheel(GamePanel gp)
    {
        super(gp);
        this.name = "Ship Steering Wheel";
        this.type = STEERING_WHEEL;
        this.interactSound = TAKE_KEY; //TODO add TAKE_SHIP_WHEEL sound
        this.description = String.format("""
                [%s]
                Used for shipping!""", name);
        this.defaultImage = setupDefaultSizeImage(objectsImagesUrls.get("steering-wheel"));
    }

    @Override
    public void useItem()
    {
        addMessage("Find a Ship");
    }
}
