package assets;

import application.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity
{
    public final GamePanel gp;
    public String name;
    public EntityType type;
    public boolean isHittingTileWithCollision;
    public int worldX;
    public int worldY;
    public BufferedImage defaultImage;
    public Rectangle solidArea;
    public int solidAreaDefaultX;
    public int solidAreaDefaultY;

    public Entity(GamePanel gp)
    {
        this.gp = gp;
        this.solidArea = new Rectangle(0, 0, 48, 48);
    }

    public void draw(Graphics2D g2)
    {
        int screenX = worldX + Math.min(gp.player.screenX - gp.player.worldX, 0);
        int screenY = worldY + Math.min(gp.player.screenY - gp.player.worldY, 0);
        if (gp.isOnScreen(worldX, worldY))
        {
            g2.drawImage(defaultImage, screenX, screenY, null);
        }
    }

    public void setWorldLocation(int worldX, int worldY)
    {
        this.worldX = worldX;
        this.worldY = worldY;
    }

    public void setSolidAreaAndDefaultLocation(int x, int y, int width, int height)
    {
        solidArea.setBounds(x, y, width, height);
        solidAreaDefaultX = x;
        solidAreaDefaultY = y;
    }
}
