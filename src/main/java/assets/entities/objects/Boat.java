package assets.entities.objects;

import application.GamePanel;
import assets.EntityType;
import assets.entities.Object;
import assets.interfaces.Teleportable;
import utilities.sound.Sound;

import static application.Application.objectsImagesUrls;
import static application.GamePanel.TILE_SIZE;
import static assets.EntityType.BOAT;
import static assets.EntityType.BOAT_PADDLE;
import static utilities.drawers.MessageDrawer.addMessage;
import static utilities.images.ImageUtils.setupDefaultSizeImage;

public class Boat extends Object implements Teleportable
{
    private final int teleportX;
    private final int teleportY;
    private final EntityType toolForInteraction;

    public Boat(GamePanel gp, int shipLocationX, int shipLocationY, int teleportX, int teleportY)
    {
        super(gp);
        solidArea.setSize(50, 50);
        this.setWorldLocation(shipLocationX * TILE_SIZE, shipLocationY * TILE_SIZE);
        this.teleportX = teleportX;
        this.teleportY = teleportY;
        name = "Boat";
        type = BOAT;
        interactSound = Sound.TAKE_BOOTS; //TODO add SHIPPING sound
        toolForInteraction = BOAT_PADDLE;
        defaultImage = setupDefaultSizeImage(objectsImagesUrls.get("boat"));
    }

    @Override
    public void interact()
    {
        if (gp.getFrameCounter() % 120 == 0)
        {
            if (gp.player.getRequiredToolForInteraction(toolForInteraction).isPresent())
            {
                gp.soundEffectsHandler.playSoundEffect(interactSound);
                teleport(teleportX, teleportY);
                addMessage("Landed!");
            }
            else
            {
                addMessage("You need a paddle!");
            }
        }
    }

    @Override
    public void teleport(int teleportX, int teleportY)
    {
        gp.player.setWorldLocation(teleportX * TILE_SIZE, teleportY * TILE_SIZE);
    }
}
