package assets.entities.objects;

import application.GamePanel;
import assets.entities.Object;
import assets.entities.objects.interfaces.Interactable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

import static application.Application.defaultImagesUrls;
import static assets.EntityType.COIN;
import static utilities.drawers.MessageDrawer.addMessage;
import static utilities.images.ImageUtils.setupDefaultSizeImage;
import static utilities.sound.Sound.TAKE_COIN;

public class Coin extends Object implements Interactable
{
    private final List<BufferedImage> images;
    private int spriteNumber = 0;
    private final int value;

    public Coin(GamePanel gp)
    {
        super(gp);
        solidArea.setSize(30, 30);
        name = "Gold coin";
        type = COIN;
        interactSound = TAKE_COIN;
        value = 1;
        description = String.format("""
           [%s]
           Gold coin!
           Used for trading!""", name);
        defaultImage = setupDefaultSizeImage(defaultImagesUrls.get("gold-coin-one"));
        images = List.of(setupDefaultSizeImage(defaultImagesUrls.get("gold-coin-one")),
                setupDefaultSizeImage(defaultImagesUrls.get("gold-coin-two")),
                setupDefaultSizeImage(defaultImagesUrls.get("gold-coin-three")),
                setupDefaultSizeImage(defaultImagesUrls.get("gold-coin-two")));
    }

    @Override
    public void interact()
    {
        super.interact();
        gp.player.coins++;
        addMessage(String.format("You got %d coin", value));
    }

    @Override
    public void draw(Graphics2D g2)
    {
        int screenX = worldX + Math.min(gp.player.screenX - gp.player.worldX, 0);
        int screenY = worldY + Math.min(gp.player.screenY - gp.player.worldY, 0);
        g2.drawImage(images.get(spriteNumber), screenX, screenY, null);
        if (gp.getFrameCounter() % 20 == 0)
        {
            spriteNumber++;
            if (spriteNumber >= images.size())
            {
                spriteNumber = 0;
            }
        }
    }
}
