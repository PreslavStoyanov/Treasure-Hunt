package assets.entities.objects;

import application.GamePanel;
import assets.EntityType;
import assets.ObjectType;
import assets.entities.Object;
import assets.interfaces.ItemDroppable;

import java.awt.image.BufferedImage;

import static application.Application.objectsImagesUrls;
import static application.GamePanel.TILE_SIZE;
import static assets.EntityType.CHEST;
import static assets.EntityType.KEY;
import static utilities.drawers.MessageDrawer.addMessage;
import static utilities.images.ImageUtils.setupDefaultSizeImage;
import static utilities.sound.Sound.OPEN_DOOR;

public class Chest extends Object implements ItemDroppable
{
    private final ObjectType itemDrop;
    private boolean isOpened = false;
    private final EntityType toolForInteraction;
    private final BufferedImage afterInteractionImage;

    public Chest(GamePanel gp, int x, int y, ObjectType itemDrop)
    {
        super(gp);
        this.setWorldLocation(x * TILE_SIZE, y * TILE_SIZE);
        this.itemDrop = itemDrop;
        toolForInteraction = KEY;
        name = "Chest";
        type = CHEST;
        isTransitional = true;
        interactSound = OPEN_DOOR; //TODO add CHEST_OPEN sound
        defaultImage = setupDefaultSizeImage(objectsImagesUrls.get("chest"));
        afterInteractionImage = setupDefaultSizeImage(objectsImagesUrls.get("chest-opened"));
    }

    @Override
    public void interact()
    {
        if (!isOpened)
        {
            if (gp.player.inventory.ifPresentRemoveItemByType(toolForInteraction))
            {
                defaultImage = afterInteractionImage;
                gp.soundEffectsHandler.playSoundEffect(interactSound);
                dropItem();
                isOpened = true;
            }
            else
            {
                addMessage("You need a key!");
            }
        }
    }

    @Override
    public void dropItem()
    {
        gp.entitySetter.addObject(itemDrop, worldX + TILE_SIZE, worldY + TILE_SIZE);
    }
}
