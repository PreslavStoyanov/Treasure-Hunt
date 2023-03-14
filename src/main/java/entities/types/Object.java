package entities.types;

import View.GamePanel;
import entities.Entity;

import java.awt.*;

import static View.GamePanel.tileSize;

public class Object extends Entity
{

    public int attackValue;
    public int defenseValue;

    public Object(GamePanel gp)
    {
        super(gp);
    }

    @Override
    public void draw(Graphics2D g2)
    {
        int screenX = worldX + Math.min(gp.player.screenX - gp.player.worldX, 0);
        int screenY = worldY + Math.min(gp.player.screenY - gp.player.worldY, 0);
        g2.drawImage(image, screenX, screenY, tileSize, tileSize, null);
    }
}
