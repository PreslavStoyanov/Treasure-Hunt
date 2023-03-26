package assets.entities.objects;

import application.GamePanel;
import assets.entities.Object;
import assets.interfaces.Interactive;

import java.util.Optional;

import static application.Application.objectsImagesUrls;
import static application.GamePanel.tileSize;
import static assets.EntityType.DOOR;
import static assets.EntityType.KEY;
import static utilities.drawers.MessageDrawer.addMessage;
import static utilities.images.ImageUtils.setupDefaultSizeImage;
import static utilities.sound.Sound.OPEN_DOOR;

public class Door extends Object implements Interactive
{
    public Door(GamePanel gp, int x, int y)
    {
        super(gp);
        this.setWorldLocation(x * tileSize, y * tileSize);
        name = "Door";
        type = DOOR;
        isHittingTileWithCollision = true;
        defaultImage = setupDefaultSizeImage(objectsImagesUrls.get("door"));
        setSolidAreaAndDefaultLocation(0, 16,48, 32);
    }

    @Override
    public void interact()
    {
        Optional<StorableObject> keyToRemove = gp.player.inventory.stream()
                .filter(obj -> obj.type.equals(KEY))
                .findFirst();

        if (keyToRemove.isPresent())
        {
            interactSound = OPEN_DOOR;
            gp.player.inventory.remove(keyToRemove.get());
            addMessage("Door opened!");
            super.interact();
        }
        else
        {
            addMessage("You need a key!");
        }
    }
}
