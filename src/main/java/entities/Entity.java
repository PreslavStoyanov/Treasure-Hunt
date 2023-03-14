package entities;

import View.GamePanel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import entities.sprites.Sprites;
import entities.sprites.WalkingSprite;
import entities.types.EntityType;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static View.GamePanel.tileSize;
import static entities.types.EntityType.*;
import static utilities.sound.Sound.RECEIVE_DAMAGE;

public class Entity
{
    public final ObjectMapper objectMapper;
    public GamePanel gp;

    public String name;
    public int speed;
    public int maxLife;
    public int life;
    public EntityType type;
    public int level;
    public int strength;
    public int agility;
    public int attack;
    public int defense;
    public int exp;
    public int nextLevelExp;
    public int coins;
    public Entity currentWeapon;
    public Entity currentShield;
    public int attackValue;
    public int defenseValue;

    public boolean hasCollision = false;
    public boolean isInvincible = false;
    public boolean isAttacking = false;
    public boolean isAlive = true;
    public boolean isDying = false;
    int dyingCounter = 0;
    public boolean isHpBarOn = false;
    public int hpBarCounter = 0;
    public int invincibleCounter = 60;
    public int worldX, worldY;
    public BufferedImage thumbUp;
    public BufferedImage image;
    public BufferedImage image2;
    public BufferedImage image3;
    public String direction = "down";
    public int actionLockCounter;

    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    public int solidAreaDefaultX;
    public int solidAreaDefaultY;

    public int spriteCounter = 0;
    public int spriteNumber = 0;

    public List<String> dialogues = new LinkedList<>();
    public Sprites sprites = new Sprites();
    public List<BufferedImage> upSprites = new ArrayList<>();
    public List<BufferedImage> downSprites = new ArrayList<>();
    public List<BufferedImage> leftSprites = new ArrayList<>();
    public List<BufferedImage> rightSprites = new ArrayList<>();

    public Entity(GamePanel gp)
    {
        this.gp = gp;
        objectMapper = new ObjectMapper(new YAMLFactory());
    }

    public void update()
    {
        setAction();
        hasCollision = gp.collisionChecker.isCollisionTile(this);
        gp.collisionChecker.checkObjectsForCollisions(this, gp.objects);
        gp.collisionChecker.checkLiveAssetsForCollision(this, gp.npcs);
        gp.collisionChecker.checkLiveAssetsForCollision(this, gp.monsters);
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
        changeSpriteNumber(sprites.getWalkingUpSprites().size(), 30);
        setInvincibleTime(40);
    }

    public void draw(Graphics2D g2)
    {
        BufferedImage image = null;
        int screenX = worldX + Math.min(gp.player.screenX - gp.player.worldX, 0);
        int screenY = worldY + Math.min(gp.player.screenY - gp.player.worldY, 0);

        if (worldX + tileSize > gp.player.worldX - gp.player.screenX
                && worldX - tileSize < gp.player.worldX + gp.player.screenX
                && worldY + tileSize > gp.player.worldY - gp.player.screenY
                && worldY - tileSize < gp.player.worldY + gp.player.screenY)
        {
            image = switchSpriteByDirection();
        }
        if (monstersTypes.contains(type) && isHpBarOn)
        {
            double oneScale = (double) tileSize / maxLife;
            double hpBarValue = oneScale * life;

            g2.setColor(new Color(35, 35, 35));
            g2.fillRect(screenX - 1, screenY - 16, tileSize + 2, 12);

            g2.setColor(new Color(255, 0, 30));
            g2.fillRect(screenX, screenY - 15, (int) hpBarValue, 10);

            hpBarCounter++;
            if (hpBarCounter > 300)
            {
                hpBarCounter = 0;
                isHpBarOn = false;
            }
        }

        if (isInvincible)
        {
            isHpBarOn = true;
            hpBarCounter = 0;
            changeAlpha(g2, 0.5F);
        }
        if (isDying)
        {
            dyingAnimation(g2);
        }
        g2.drawImage(image, screenX, screenY, tileSize, tileSize, null);
        changeAlpha(g2, 1F);
    }

    public void setAction()
    {
        actionLockCounter++;

        if (actionLockCounter == 120)
        {
            Random random = new Random();
            int i = random.nextInt(99);
            if (i < 25)
            {
                direction = "up";
            }
            else if (i < 50)
            {
                direction = "down";
            }
            else if (i < 75)
            {
                direction = "left";
            }
            else
            {
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }

    public void damageReaction()
    {

    }

    public void speak()
    {
        switch (gp.player.direction)
        {
            case "up" -> this.direction = "down";
            case "down" -> this.direction = "up";
            case "left" -> this.direction = "right";
            case "right" -> this.direction = "left";
        }
    }

    public void dyingAnimation(Graphics2D g2)
    {
        dyingCounter++;
        int i = 5;
        if (dyingCounter <= i) changeAlpha(g2, 0f);
        if (dyingCounter > i && dyingCounter <= i * 2) changeAlpha(g2, 1f);
        if (dyingCounter > i * 2 && dyingCounter <= i * 3) changeAlpha(g2, 0f);
        if (dyingCounter > i * 3 && dyingCounter <= i * 4) changeAlpha(g2, 1f);
        if (dyingCounter > i * 4 && dyingCounter <= i * 5) changeAlpha(g2, 0f);
        if (dyingCounter > i * 5 && dyingCounter <= i * 6) changeAlpha(g2, 1f);
        if (dyingCounter > i * 6 && dyingCounter <= i * 7) changeAlpha(g2, 0f);
        if (dyingCounter > i * 7 && dyingCounter <= i * 8) changeAlpha(g2, 1f);
        if (dyingCounter >= i * 8)
        {
            isDying = false;
            isAlive = false;
        }
    }

    private BufferedImage switchSpriteByDirection()
    {
        switch (direction)
        {
            case "up" ->
            {
                return changeSprite(sprites.getWalkingUpSprites(), spriteNumber);
            }
            case "down" ->
            {
                return changeSprite(sprites.getWalkingDownSprites(), spriteNumber);
            }
            case "left" ->
            {
                return changeSprite(sprites.getWalkingLeftSprites(), spriteNumber);
            }
            case "right" ->
            {
                return changeSprite(sprites.getWalkingRightSprites(), spriteNumber);
            }
            default -> throw new IllegalStateException("Unexpected value: " + direction);
        }
    }

    public void setInvincibleTime(int time)
    {
        if (isInvincible)
        {
            invincibleCounter--;
            if (invincibleCounter < 0)
            {
                isInvincible = false;
                invincibleCounter = time;
            }
        }
    }

    public void changeAlpha(Graphics2D g2, float alpha)
    {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
    }

    public void changeSpriteNumber(int numberOfSprites, int speedOfChanging)
    {
        spriteCounter++;
        if (spriteCounter > speedOfChanging)
        {
            while (true)
            {
                if (spriteNumber > numberOfSprites)
                {
                    break;
                }
                spriteNumber++;
                if (spriteNumber == numberOfSprites)
                {
                    spriteNumber = 0;
                }
                break;
            }
            spriteCounter = 0;
        }
    }

    public Sprites setSprites(String filePath)
    {
        try
        {
            return objectMapper.readValue(new File(filePath), Sprites.class);
        }
        catch (IOException e)
        {
            throw new RuntimeException("Setting sprites error", e);
        }
    }

    public BufferedImage changeSprite(List<WalkingSprite> sprites, int spriteNumber)
    {
        for (int i = 0; i < sprites.size(); i++)
        {
            if (spriteNumber == i)
            {
                return sprites.get(i).getImage();
            }
        }
        return sprites.get(0).getImage();
    }
}
