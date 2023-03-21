package assets;

import application.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity
{
    public final GamePanel gp;
    public String name;
    public EntityType type;
    public boolean hasCollision;
    public int worldX;
    public int worldY;
    public BufferedImage image;
    public Rectangle solidArea;
    public int solidAreaDefaultX;
    public int solidAreaDefaultY;

    public Entity(GamePanel gp)
    {
        this.gp = gp;
        this.solidArea = new Rectangle(0, 0, 48, 48);
    }

    public abstract void draw(Graphics2D g2);

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
