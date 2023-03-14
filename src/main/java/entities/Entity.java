package entities;

import View.GamePanel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import entities.types.EntityType;

import java.awt.*;
import java.awt.image.BufferedImage;

import static entities.types.EntityType.*;
import static utilities.sound.Sound.RECEIVE_DAMAGE;

public class Entity
{
    public final GamePanel gp;
    public final ObjectMapper objectMapper;
    public String name;
    public int speed;
    public EntityType type;
    public int attack;
    public boolean hasCollision;
    public int worldX;
    public int worldY;
    public BufferedImage thumbUp;
    public BufferedImage image;
    public BufferedImage image2;
    public BufferedImage image3;
    public String direction = "down";
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX;
    public int solidAreaDefaultY;


    public Entity(GamePanel gp)
    {
        this.gp = gp;
        objectMapper = new ObjectMapper(new YAMLFactory());
    }

    public void update()
    {
        hasCollision = gp.collisionChecker.isCollisionTile(this);
        gp.collisionChecker.checkObjectsForCollisions(this, gp.objects);
        gp.collisionChecker.checkLiveEntitiesForCollision(this, gp.npcs);
        gp.collisionChecker.checkLiveEntitiesForCollision(this, gp.monsters);
        boolean isContactingPlayer = gp.collisionChecker.checkForCollisionWithPlayer(this, gp.player);
        if (!gp.player.isInvincible && isContactingPlayer)
        {
            if (type.equals(SLIME))
            {
                gp.playSoundEffect(RECEIVE_DAMAGE);
                int damage = attack - gp.player.defense;
                if (damage < 0)
                {
                    damage = 0;
                }
                gp.player.life -= damage;
                gp.player.isInvincible = true;
            }
            else if (type.equals(DEMON))
            {
                gp.playSoundEffect(RECEIVE_DAMAGE);
                int damage = attack - gp.player.defense;
                if (damage < 0)
                {
                    damage = 0;
                }
                gp.player.life -= damage;
                gp.player.isInvincible = true;
            }
        }
        if (!hasCollision)
        {
            switch (direction)
            {
                case "up" -> worldY -= speed;
                case "down" -> worldY += speed;
                case "left" -> worldX -= speed;
                case "right" -> worldX += speed;
            }
        }
    }

    public void draw(Graphics2D g2)
    {

    }
}
