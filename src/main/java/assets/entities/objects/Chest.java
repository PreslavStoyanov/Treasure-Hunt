package assets.entities.interactivetiles;

import application.GamePanel;
import assets.ObjectType;
import assets.entities.InteractiveTile;
import assets.interfaces.ItemDroppable;

import static application.Application.interactiveTilesUrls;
import static application.Application.objectsImagesUrls;
import static application.GamePanel.TILE_SIZE;
import static assets.EntityType.CHEST;
import static assets.EntityType.KEY;
import static utilities.images.ImageUtils.setupDefaultSizeImage;
import static utilities.sound.Sound.WIN_SOUND;

public class Chest extends InteractiveTile implements ItemDroppable
{
    private final ObjectType itemDrop;

    public Chest(GamePanel gp, int x, int y, ObjectType itemDrop)
    {
        super(gp);
        this.setWorldLocation(x * TILE_SIZE, y * TILE_SIZE);
        this.itemDrop = itemDrop;
        toolForInteraction = KEY;
        name = "Chest";
        type = CHEST;
        isTransitional = true;
        interactSound = WIN_SOUND; //TODO add CHEST_OPEN sound
        defaultImage = setupDefaultSizeImage(interactiveTilesUrls.get("chest"));
        afterInteractionImage = setupDefaultSizeImage(interactiveTilesUrls.get("opened-chest"));
    }

    @Override
    public void doAction()
    {
        dropItem();
    }

    @Override
    public void dropItem()
    {
        gp.entitySetter.addObject(itemDrop, worldX + TILE_SIZE, worldY + TILE_SIZE);
    }
}
