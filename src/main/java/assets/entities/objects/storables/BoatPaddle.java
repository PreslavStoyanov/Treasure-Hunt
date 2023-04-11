package assets.entities.objects.storables;

import application.GamePanel;
import assets.entities.objects.StorableObject;

import static application.Application.objectsImagesUrls;
import static assets.EntityType.BOAT_PADDLE;
import static utilities.drawers.MessageDrawer.addMessage;
import static utilities.images.ImageUtils.setupDefaultSizeImage;
import static utilities.sound.Sound.TAKE_BOOTS;

public class BoatPaddle extends StorableObject
{

    public BoatPaddle(GamePanel gp)
    {
        super(gp);
        this.name = "Boat Paddle";
        this.type = BOAT_PADDLE;
        this.interactSound = TAKE_BOOTS; //TODO add TAKE_BOAT_PADDLE sound
        this.price = 10;
        this.description = String.format("""
                [%s]
                Used for shipping!""", name);
        this.defaultImage = setupDefaultSizeImage(objectsImagesUrls.get("boat-paddle"));
    }

    @Override
    public void useItem()
    {
        addMessage("Find a Ship");
    }
}
