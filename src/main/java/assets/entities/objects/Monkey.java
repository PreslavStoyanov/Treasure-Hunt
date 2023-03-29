package assets.entities.objects;

import application.GamePanel;
import assets.EntityType;
import assets.entities.Object;

import static application.Application.objectsImagesUrls;
import static application.GamePanel.TILE_SIZE;
import static assets.EntityType.KEY;
import static assets.EntityType.MONKEY;
import static utilities.drawers.MessageDrawer.addMessage;
import static utilities.images.ImageUtils.setupDefaultSizeImage;
import static utilities.sound.Sound.MONKEY_LAUGH;

public class Monkey extends Object
{

    private static final EntityType toolForInteraction = KEY;

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
        if (gp.player.inventory.ifPresentRemoveItemByType(toolForInteraction))
        {
            interactSound = MONKEY_LAUGH;
            addMessage("The monkey robbed you and ran out!");
            super.interact();
        }
        else
        {
            addMessage("You have nothing for me!");
        }
    }
}
