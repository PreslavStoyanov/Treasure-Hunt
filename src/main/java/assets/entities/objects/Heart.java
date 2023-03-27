package assets.entities.objects;

import application.GamePanel;
import assets.entities.Object;

import java.awt.*;

import static application.Application.objectsImagesUrls;
import static application.GamePanel.TILE_SIZE;
import static assets.EntityType.HEART;
import static utilities.drawers.MessageDrawer.addMessage;
import static utilities.images.ImageUtils.setupImage;
import static utilities.sound.Sound.POWER_UP;

public class Heart extends Object
{
    private final int lifeValue = 2;

    public Heart(GamePanel gp)
    {
        super(gp);
        name = "Heart";
        type = HEART;
        interactSound = POWER_UP;
        defaultImage = setupImage(objectsImagesUrls.get("fullHeart"), TILE_SIZE - 16, TILE_SIZE - 16);
    }

    @Override
    public void draw(Graphics2D g2)
    {
        int screenX = worldX + Math.min(gp.player.screenX - gp.player.worldX, 0);
        int screenY = worldY + Math.min(gp.player.screenY - gp.player.worldY, 0);
        g2.drawImage(defaultImage, screenX + 8, screenY + 8, null);
    }

    @Override
    public void interact()
    {
        super.interact();
        gp.player.increaseLife(lifeValue);
        addMessage(String.format("You got %d heart", lifeValue / 2));
    }
}
