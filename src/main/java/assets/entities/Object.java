package assets.entities;

import application.GamePanel;
import assets.Entity;
import assets.entities.objects.interfaces.Interactable;
import utilities.sound.Sound;

import java.awt.*;

public abstract class Object extends Entity implements Interactable
{
    public String description;
    public Sound interactSound;

    public Object(GamePanel gp)
    {
        super(gp);
    }

    @Override
    public void draw(Graphics2D g2)
    {
        int screenX = worldX + Math.min(gp.player.screenX - gp.player.worldX, 0);
        int screenY = worldY + Math.min(gp.player.screenY - gp.player.worldY, 0);
        g2.drawImage(defaultImage, screenX, screenY, null);
    }

    @Override
    public void interact()
    {
        gp.objects.remove(this);
        gp.soundHandler.playSoundEffect(interactSound);
    }
}
