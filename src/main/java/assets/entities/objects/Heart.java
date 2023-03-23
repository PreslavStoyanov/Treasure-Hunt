package assets.entities.objects;

import application.GamePanel;
import assets.entities.Object;
import assets.entities.objects.interfaces.Consumable;

import java.awt.*;
import java.awt.image.BufferedImage;

import static application.Application.defaultImagesUrls;
import static application.GamePanel.tileSize;
import static assets.EntityType.HEART;
import static utilities.drawers.MessageDrawer.addMessage;
import static utilities.images.ImageUtils.setupDefaultSizeImage;
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
        defaultImage = setupImage(defaultImagesUrls.get("fullHeart"), tileSize - 16, tileSize - 16);
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
