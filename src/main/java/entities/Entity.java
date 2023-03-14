package entities;

import View.GamePanel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import entities.types.EntityType;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity
{
    public final GamePanel gp;
    public final ObjectMapper objectMapper;
    public String name;
    public EntityType type;
    public boolean hasCollision;
    public int worldX;
    public int worldY;
    public BufferedImage thumbUp;
    public BufferedImage image;
    public BufferedImage image2;
    public BufferedImage image3;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX;
    public int solidAreaDefaultY;


    public Entity(GamePanel gp)
    {
        this.gp = gp;
        objectMapper = new ObjectMapper(new YAMLFactory());
    }

    public void draw(Graphics2D g2)
    {

    }

    public void setWorldLocation(int worldX, int worldY)
    {
        this.worldX = worldX;
        this.worldY = worldY;
    }
}
