package assets.entities.objects;

import application.GamePanel;
import assets.EntityType;
import assets.entities.Object;
import utilities.sound.Sound;

import static application.Application.objectsImagesUrls;
import static application.GamePanel.TILE_SIZE;
import static assets.EntityType.BOAT;
import static assets.EntityType.BOAT_PADDLE;
import static utilities.drawers.MessageDrawer.addMessage;
import static utilities.images.ImageUtils.setupDefaultSizeImage;

public class Boat extends Object
{
    private final int sailingX;
    private final int sailingY;
    private final EntityType toolForInteraction = BOAT_PADDLE;

    public Boat(GamePanel gp, int boatX, int boatY, int sailingX, int sailingY)
    {
        super(gp);
        this.setWorldLocation(boatX * TILE_SIZE, boatY * TILE_SIZE);
        this.sailingX = sailingX;
        this.sailingY = sailingY;
        name = "Boat";
        type = BOAT;
        interactSound = Sound.TAKE_BOOTS; //TODO add SHIPPING sound
        defaultImage = setupDefaultSizeImage(objectsImagesUrls.get("boat"));
    }

    @Override
    public void interact()
    {
        if (gp.getFrameCounter() % 120 == 0)
        {
            if (gp.player.inventory.isItemInStorage(toolForInteraction))
            {
                gp.soundEffectsHandler.playSoundEffect(interactSound);
                sail(sailingX, sailingY);
                addMessage("Landed!");
            }
            else
            {
                addMessage("You need a paddle!");
            }
        }
    }

    public void sail(int sailingX, int sailingY)
    {
        gp.player.setWorldLocation(sailingX * TILE_SIZE, sailingY * TILE_SIZE);
    }
}
