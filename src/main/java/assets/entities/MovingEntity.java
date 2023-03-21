package assets.entities;

import application.GamePanel;
import assets.Entity;
import assets.entities.movingentities.sprites.DefaultSprite;
import assets.entities.movingentities.sprites.Sprites;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static application.GamePanel.tileSize;
import static assets.entities.MovingEntity.Direction.*;

public abstract class MovingEntity extends Entity
{
    public final ObjectMapper objectMapper;
    public Direction direction = DOWN;
    public Sprites sprites = new Sprites();
    public int movingSpeed;
    public boolean isContactingPlayer;
    public int spriteNumber = 0;

    public MovingEntity(GamePanel gp)
    {
        super(gp);
        objectMapper = new ObjectMapper(new YAMLFactory());
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
            g2.drawImage(switchWalkingSpritesByDirection(), screenX, screenY, tileSize, tileSize, null);
        }
    }

    public void update()
    {
        changeMovingDirection();
        hasCollision = gp.collisionChecker.isTileColliding(this);
        interactWithEntities();

        this.isContactingPlayer = gp.collisionChecker.isCollidingWithPlayer(this, gp.player);

        if (!hasCollision && !isContactingPlayer)
        {
            handleMoving();
        }
    }

    public BufferedImage switchWalkingSpritesByDirection()
    {
        BufferedImage image;
        switch (this.direction)
        {
            case UP -> image = changeDefaultSprite(sprites.getDefaultUpSprites());
            case DOWN -> image = changeDefaultSprite(sprites.getDefaultDownSprites());
            case LEFT -> image = changeDefaultSprite(sprites.getDefaultLeftSprites());
            case RIGHT -> image = changeDefaultSprite(sprites.getDefaultRightSprites());
            default -> throw new IllegalStateException("Unexpected value: " + direction);
        }
        return image;
    }

    public void handleMoving()
    {
        switch (this.direction)
        {
            case UP -> worldY -= movingSpeed;
            case DOWN -> worldY += movingSpeed;
            case LEFT -> worldX -= movingSpeed;
            case RIGHT -> worldX += movingSpeed;
        }
        changeSpriteNumber();
    }

    public abstract void changeMovingDirection();

    public abstract void interactWithEntities();

    private void changeSpriteNumber() {
        if (gp.getFrameCounter() % ((sprites.getDefaultUpSprites().size() * 5) / movingSpeed) == 0) {
            spriteNumber = (spriteNumber + 1) % sprites.getDefaultUpSprites().size();
        }
    }

    private BufferedImage changeDefaultSprite(List<DefaultSprite> defaultSprites)
    {
        for (int i = 0; i < defaultSprites.size(); i++)
        {
            if (spriteNumber == i)
            {
                return defaultSprites.get(i).getImage();
            }
        }
        return defaultSprites.get(0).getImage();
    }

    public enum Direction
    {
        UP,
        DOWN,
        LEFT,
        RIGHT
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
}
