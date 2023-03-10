package entities.Entity;

import View.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.*;

public class Entity
{
    GamePanel gp;

    public String name;
    public int speed;
    public int maxLife;
    public int life;
    public int type;
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

    public boolean collision = false;
    public boolean collisionOn = false;
    public boolean invincible = false;
    public boolean attacking = false;
    public boolean alive = true;
    public boolean dying = false;
    int dyingCounter = 0;
    public boolean hpBarOn = false;
    public int hpBarCounter = 0;
    public int invincibleCounter = 60;
    public int worldX, worldY;
    public BufferedImage thumbUp, image, image2, image3;
    public String direction = "down";
    public int actionLockCounter;

    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    public int solidAreaDefaultX, solidAreaDefaultY;

    public int spriteCounter = 0;
    public int spriteNumber = 0;

    List<String> dialogues = new LinkedList<>();
    public List<BufferedImage> upSprites = new ArrayList<>();
    public List<BufferedImage> downSprites = new ArrayList<>();
    public List<BufferedImage> leftSprites = new ArrayList<>();
    public List<BufferedImage> rightSprites = new ArrayList<>();
    public List<BufferedImage> upAttackSprites = new ArrayList<>();
    public List<BufferedImage> downAttackSprites = new ArrayList<>();
    public List<BufferedImage> leftAttackSprites = new ArrayList<>();
    public List<BufferedImage> rightAttackSprites = new ArrayList<>();

    public Entity(GamePanel gp)
    {
        this.gp = gp;
    }

    public void update()
    {
        setAction();
        collisionOn = false;
        gp.collisionChecker.checkTile(this);
        gp.collisionChecker.checkObject(this, false);
        gp.collisionChecker.checkEntity(this, gp.npcs);
        gp.collisionChecker.checkEntity(this, gp.monsters);
        boolean contactPlayer = gp.collisionChecker.checkPlayer(this);
        if (!gp.player.invincible && contactPlayer)
        {
            if (this.type == 2)
            {
                gp.playSoundEffect(6);
                int damage = attack - gp.player.defense;
                if (damage < 0)
                {
                    damage = 0;
                }
                gp.player.life -= damage;
                gp.player.invincible = true;
            }
            else if (this.type == 3)
            {
                gp.playSoundEffect(6);
                int damage = attack - gp.player.defense;
                if (damage < 0)
                {
                    damage = 0;
                }
                gp.player.life -= damage;
                gp.player.invincible = true;
            }
        }
        if (!collisionOn)
        {
            switch (direction)
            {
                case "up" -> worldY -= speed;
                case "down" -> worldY += speed;
                case "left" -> worldX -= speed;
                case "right" -> worldX += speed;
            }
        }
        spriteNumberChanger(upSprites.size(), 30);
        setInvincibleTime(40);
    }

    public void draw(Graphics2D g2)
    {
        BufferedImage image = null;
        int screenX = worldX + Math.min(gp.player.screenX - gp.player.worldX, 0);
        int screenY = worldY + Math.min(gp.player.screenY - gp.player.worldY, 0);

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX
                && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
                && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY
                && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY)
        {
            image = switchDirection(image);
        } /*else if (gp.player.screenX > gp.player.worldX ||
                gp.player.screenY > gp.player.worldY ||
                rightOffset > gp.worldWidth - gp.player.worldX ||
                bottomOffset > gp.worldHeight - gp.player.worldY) {
            image = switchDirection(image);
            //offsets
        }*/
        if ((type == 2 || type == 3) && hpBarOn)
        {
            double oneScale = (double) gp.tileSize / maxLife;
            double hpBarValue = oneScale * life;

            g2.setColor(new Color(35, 35, 35));
            g2.fillRect(screenX - 1, screenY - 16, gp.tileSize + 2, 12);

            g2.setColor(new Color(255, 0, 30));
            g2.fillRect(screenX, screenY - 15, (int) hpBarValue, 10);

            hpBarCounter++;
            if (hpBarCounter > 300)
            {
                hpBarCounter = 0;
                hpBarOn = false;
            }
        }

        if (invincible)
        {
            hpBarOn = true;
            hpBarCounter = 0;
            changeAlpha(g2, 0.5F);
        }
        if (dying)
        {
            dyingAnimation(g2);
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        changeAlpha(g2, 1F);
    }

    public void setAction()
    {
        actionLockCounter++;

        if (actionLockCounter == 120)
        {
            Random random = new Random();
            int i = 1 + random.nextInt(100);
            if (i <= 25)
            {
                direction = "up";
            }
            if (i > 25 && i <= 50)
            {
                direction = "down";
            }
            if (i > 50 && i <= 75)
            {
                direction = "left";
            }
            if (i > 75)
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
            case "up" -> direction = "down";
            case "down" -> direction = "up";
            case "left" -> direction = "right";
            case "right" -> direction = "left";
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
            dying = false;
            alive = false;
        }
    }

    public BufferedImage switchDirection(BufferedImage image)
    {
        switch (direction)
        {
            case "up" -> image = changeSprite(image, upSprites, spriteNumber);
            case "down" -> image = changeSprite(image, downSprites, spriteNumber);
            case "left" -> image = changeSprite(image, leftSprites, spriteNumber);
            case "right" -> image = changeSprite(image, rightSprites, spriteNumber);
        }
        return image;
    }

    public void setInvincibleTime(int time)
    {
        if (invincible)
        {
            invincibleCounter--;
            if (invincibleCounter < 0)
            {
                invincible = false;
                invincibleCounter = time;
            }
        }
    }

    public void changeAlpha(Graphics2D g2, float alpha)
    {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
    }

    public void spriteNumberChanger(int numberOfSprites, int speedOfChanging)
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

    public BufferedImage changeSprite(BufferedImage image, List<BufferedImage> sprites, int spriteNumber)
    {
        for (int i = 0; i < sprites.size(); i++)
        {
            if (spriteNumber == i)
            {
                image = sprites.get(i);
                break;
            }
        }
        return image;
    }
}
