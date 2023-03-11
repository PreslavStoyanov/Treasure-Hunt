package utilities;

import View.GamePanel;
import entities.Entity.Entity;

import java.util.ArrayList;

public class CollisionChecker
{
    private final GamePanel gp;

    public CollisionChecker(GamePanel gp)
    {
        this.gp = gp;
    }

    public void checkTile(Entity entity)
    {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / GamePanel.tileSize;
        int entityRightCol = entityRightWorldX / GamePanel.tileSize;
        int entityTopRow = entityTopWorldY / GamePanel.tileSize;
        int entityBottomRow = entityBottomWorldY / GamePanel.tileSize;

        int tileNumber1, tileNumber2;

        switch (entity.direction)
        {
            case "up" ->
            {
                entityTopRow = (entityTopWorldY - entity.speed) / GamePanel.tileSize;
                tileNumber1 = setTileNumber(entityLeftCol, entityTopRow);
                tileNumber2 = setTileNumber(entityRightCol, entityTopRow);
                setCollisionOn(entity, tileNumber1, tileNumber2);
            }
            case "down" ->
            {
                entityBottomRow = (entityBottomWorldY + entity.speed) / GamePanel.tileSize;
                tileNumber1 = setTileNumber(entityLeftCol, entityBottomRow);
                tileNumber2 = setTileNumber(entityRightCol, entityBottomRow);
                setCollisionOn(entity, tileNumber1, tileNumber2);
            }
            case "left" ->
            {
                entityLeftCol = (entityLeftWorldX - entity.speed) / GamePanel.tileSize;
                tileNumber1 = setTileNumber(entityLeftCol, entityTopRow);
                tileNumber2 = setTileNumber(entityLeftCol, entityBottomRow);
                setCollisionOn(entity, tileNumber1, tileNumber2);
            }
            case "right" ->
            {
                entityRightCol = (entityRightWorldX + entity.speed) / GamePanel.tileSize;
                tileNumber1 = setTileNumber(entityRightCol, entityTopRow);
                tileNumber2 = setTileNumber(entityRightCol, entityBottomRow);
                setCollisionOn(entity, tileNumber1, tileNumber2);
            }
        }

    }

    public int checkObjectsForCollisions(Entity entity, ArrayList<Entity> targets, boolean isPlayer)
    {
        int index = -1;

        for (int i = 0; i < targets.size(); i++)
        {
            Entity target = targets.get(i);

            if (target == null)
            {
                continue;
            }

            increaseSolidAreaWorldCoordinates(entity);
            increaseSolidAreaWorldCoordinates(target);

            changeEntityDirection(entity);

            if (isObjectIntersects(entity, target, isPlayer))
            {
                index = i;
            }

            setSolidAreaDefaultCoordinates(entity);
            setSolidAreaDefaultCoordinates(target);
        }
        return index;
    }

    public int checkEntitiesForCollision(Entity entity, ArrayList<Entity> targets)
    {
        int index = -1;

        for (int i = 0; i < targets.size(); i++)
        {
            Entity target = targets.get(i);
            if (target == null)
            {
                continue;
            }
            increaseSolidAreaWorldCoordinates(entity);
            increaseSolidAreaWorldCoordinates(target);
            changeEntityDirection(entity);

            if (isEntityIntersects(entity, target))
            {
                entity.collisionOn = true;
                index = i;
            }

            setSolidAreaDefaultCoordinates(entity);
            setSolidAreaDefaultCoordinates(target);
        }

        return index;
    }

    public boolean checkForCollisionWithPlayer(Entity entity, Entity player)
    {
        boolean contactPlayer = false;
        increaseSolidAreaWorldCoordinates(entity);
        increaseSolidAreaWorldCoordinates(player);
        changeEntityDirection(entity);

        if (entity.solidArea.intersects(player.solidArea))
        {
            entity.collisionOn = true;
            contactPlayer = true;
        }

        setSolidAreaDefaultCoordinates(entity);
        setSolidAreaDefaultCoordinates(player);

        return contactPlayer;
    }

    public boolean isEntityIntersects(Entity entity, Entity target)
    {
        if (!entity.solidArea.intersects(target.solidArea))
        {
            return false;
        }
        return target != entity;
    }

    public boolean isObjectIntersects(Entity entity, Entity target, boolean isPlayer)
    {
        if (!entity.solidArea.intersects(target.solidArea))
        {
            return false;
        }
        if (target.collision)
        {
            entity.collisionOn = true;
        }
        return isPlayer;
    }

    private static void increaseSolidAreaWorldCoordinates(Entity entity)
    {
        entity.solidArea.x += entity.worldX;
        entity.solidArea.y += entity.worldY;
    }

    private void changeEntityDirection(Entity entity)
    {
        switch (entity.direction)
        {
            case "up" -> entity.solidArea.y -= entity.speed;
            case "down" -> entity.solidArea.y += entity.speed;
            case "left" -> entity.solidArea.x -= entity.speed;
            case "right" -> entity.solidArea.x += entity.speed;
        }
    }

    private static void setSolidAreaDefaultCoordinates(Entity entity)
    {
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
    }

    public void setCollisionOn(Entity entity, int tileNumber1, int tileNumber2)
    {
        if (gp.tileManager.tile[tileNumber1].haveCollision() || gp.tileManager.tile[tileNumber2].haveCollision())
        {
            entity.collisionOn = true;
        }
    }

    public int setTileNumber(int entityLeftCol, int entityBottomRow)
    {
        return gp.tileManager.mapTileNumber[entityLeftCol][entityBottomRow];
    }
}
