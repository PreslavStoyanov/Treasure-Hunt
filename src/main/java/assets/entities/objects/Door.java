package assets.entities.objects;

import application.GamePanel;
import assets.entities.Object;
import assets.entities.objects.interfaces.Interactable;

import java.util.Optional;

import static application.Application.defaultImagesUrls;
import static application.GamePanel.tileSize;
import static assets.EntityType.DOOR;
import static assets.EntityType.KEY;
import static utilities.drawers.MessageDrawer.addMessage;
import static utilities.images.ImageUtils.setupDefaultSizeImage;
import static utilities.sound.Sound.OPEN_DOOR;

public class Door extends Object implements Interactable
{
    public Door(GamePanel gp, int x, int y)
    {
        super(gp);
        this.setWorldLocation(x * tileSize, y * tileSize);
        name = "Door";
        type = DOOR;
        hasCollision = true;
        defaultImage = setupDefaultSizeImage(defaultImagesUrls.get("door"));
        setSolidAreaAndDefaultLocation(0, 16,48, 32);
    }

    @Override
    public void interact()
    {
        Optional<CollectableObject> keyToRemove = gp.player.inventory.stream()
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
