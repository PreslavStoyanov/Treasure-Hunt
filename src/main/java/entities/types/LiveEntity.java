package entities.types;

import application.GamePanel;
import entities.Direction;
import entities.Entity;
import entities.sprites.Sprites;
import entities.sprites.WalkingSprite;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import static application.GamePanel.tileSize;
import static entities.Direction.*;

public class LiveEntity extends Entity
{
    public int actionLockCounter;
    public boolean isInvincible = false;
    public int invincibleCounter = 60;
    public int spriteCounter = 0;
    public int spriteNumber = 0;
    public int maxLife;
    public int life;
    public int defense;
    public int speed;
    public int exp;
    public int attack;
    public boolean isContactingPlayer;
    public Direction direction = DOWN;
    public Sprites sprites = new Sprites();

    public LiveEntity(GamePanel gp)
    {
        super(gp);
    }

    @Override
    public void draw(Graphics2D g2)
    {
        int screenX = worldX + Math.min(gp.player.screenX - gp.player.worldX, 0);
        int screenY = worldY + Math.min(gp.player.screenY - gp.player.worldY, 0);
        if (worldX + tileSize > gp.player.worldX - gp.player.screenX
                && worldX - tileSize < gp.player.worldX + gp.player.screenX
                && worldY + tileSize > gp.player.worldY - gp.player.screenY
                && worldY - tileSize < gp.player.worldY + gp.player.screenY)
        {
            g2.drawImage(switchSpriteByDirection(), screenX, screenY, tileSize, tileSize, null);
        }
    }

    public void update()
    {
        setDefaultWalking();
        hasCollision = gp.collisionChecker.isTileColliding(this);
        gp.collisionChecker.areObjectsColliding(this, gp.objects);
        gp.collisionChecker.areLiveEntitiesColliding(this, gp.npcs);
        gp.collisionChecker.areLiveEntitiesColliding(this, gp.monsters);
        this.isContactingPlayer = gp.collisionChecker.isCollidingWithPlayer(this, gp.player);

        if (!hasCollision && !isContactingPlayer)
        {
            handleMoving();
        }
        changeSpriteNumber(sprites.getWalkingUpSprites().size(), 30);
        setInvincibleTime(40);
    }

    private BufferedImage switchSpriteByDirection()
    {
        switch (direction)
        {
            case UP ->
            {
                return changeSprite(sprites.getWalkingUpSprites(), spriteNumber);
            }
            case DOWN ->
            {
                return changeSprite(sprites.getWalkingDownSprites(), spriteNumber);
            }
            case LEFT ->
            {
                return changeSprite(sprites.getWalkingLeftSprites(), spriteNumber);
            }
            case RIGHT ->
            {
                return changeSprite(sprites.getWalkingRightSprites(), spriteNumber);
            }
            default -> throw new IllegalStateException("Unexpected value: " + direction);
        }
    }

    private BufferedImage changeSprite(List<WalkingSprite> sprites, int spriteNumber)
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

    private void setDefaultWalking()
    {
        actionLockCounter++;

        if (actionLockCounter != 120)
        {
            return;
        }
        switch (new Random().nextInt(4))
        {
            case 0 -> direction = UP;
            case 1 -> direction = DOWN;
            case 2 -> direction = LEFT;
            case 3 -> direction = RIGHT;
        }
        actionLockCounter = 0;
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

    public void handleMoving()
    {
        switch (direction)
        {
            case UP -> worldY -= speed;
            case DOWN -> worldY += speed;
            case LEFT -> worldX -= speed;
            case RIGHT -> worldX += speed;
        }
    }
}
