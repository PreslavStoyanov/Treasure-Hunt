package assets.entities.objects;

import application.GamePanel;
import assets.entities.Object;
import assets.interfaces.Interactive;

import java.util.Optional;

import static application.Application.objectsImagesUrls;
import static application.GamePanel.TILE_SIZE;
import static assets.EntityType.KEY;
import static assets.EntityType.MONKEY;
import static utilities.drawers.MessageDrawer.addMessage;
import static utilities.images.ImageUtils.setupDefaultSizeImage;
import static utilities.sound.Sound.MONKEY_LAUGH;

public class Monkey extends Object implements Interactive
{

    public Monkey(GamePanel gp, int x, int y)
    {
        super(gp);
        this.setWorldLocation(x * TILE_SIZE, y * TILE_SIZE);
        setSolidAreaAndDefaultLocation(8, 16, 30, 30);
        name = "Monkey";
        type = MONKEY;
        isTransitional = true;
        defaultImage = setupDefaultSizeImage(objectsImagesUrls.get("monkey"));
    }

    @Override
    public void interact()
    {
        Optional<StorableObject> keyToRemove = gp.player.inventory.stream()
                .filter(obj -> obj.type.equals(KEY))
                .findFirst();

        if (keyToRemove.isPresent())
        {
            interactSound = MONKEY_LAUGH;
            gp.player.inventory.remove(keyToRemove.get());
            addMessage("The monkey robbed you and ran out!");
            super.interact();
        }
        else
        {
            addMessage("You have nothing for me!");
        }
    }
}
