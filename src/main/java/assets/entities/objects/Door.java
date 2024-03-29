package assets.entities.objects;

import application.GamePanel;
import assets.EntityType;
import assets.entities.Object;

import static application.Application.objectsImagesUrls;
import static application.GamePanel.TILE_SIZE;
import static assets.EntityType.DOOR;
import static assets.EntityType.KEY;
import static utilities.drawers.MessageDrawer.addMessage;
import static utilities.images.ImageUtils.setupDefaultSizeImage;
import static utilities.sound.Sound.OPEN_DOOR;

public class Door extends Object
{
    private final EntityType toolForInteraction;

    public Door(GamePanel gp, int x, int y)
    {
        super(gp);
        this.setWorldLocation(x * TILE_SIZE, y * TILE_SIZE);
        name = "Door";
        type = DOOR;
        toolForInteraction = KEY;
        isTransitional = true;
        defaultImage = setupDefaultSizeImage(objectsImagesUrls.get("door"));
        setSolidAreaAndDefaultLocation(0, 16, 48, 32);
    }

    @Override
    public void interact()
    {
        if (gp.player.inventory.ifPresentRemoveItemByType(toolForInteraction))
        {
            interactSound = OPEN_DOOR;
            addMessage("Door opened!");
            super.interact();
        }
        else
        {
            addMessage("You need a key!");
        }
    }
}
