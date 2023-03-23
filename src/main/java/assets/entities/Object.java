package assets.entities;

import application.GamePanel;
import assets.Entity;

import java.awt.*;

import static application.GamePanel.tileSize;

public abstract class Object extends Entity
{
    public String description;

    public Object(GamePanel gp)
    {
        super(gp);
    }

    @Override
    public void draw(Graphics2D g2)
    {
        int screenX = worldX + Math.min(gp.player.screenX - gp.player.worldX, 0);
        int screenY = worldY + Math.min(gp.player.screenY - gp.player.worldY, 0);
        g2.drawImage(image, screenX, screenY, null);
    }
}
