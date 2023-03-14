package utilities;

import View.GamePanel;
import entities.Entity;
import entities.types.EntityType;
import entities.types.LiveEntity;
import entities.types.Object;
import entities.types.Player;

import java.util.List;

import static View.GamePanel.tileSize;

public class CollisionChecker
{
    private final GamePanel gp;

    public CollisionChecker(GamePanel gp)
    {
        this.gp = gp;
    }

    public boolean isCollisionTile(Entity entity)
    {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / tileSize;
        int entityRightCol = entityRightWorldX / tileSize;
        int entityTopRow = entityTopWorldY / tileSize;
        int entityBottomRow = entityBottomWorldY / tileSize;

        switch (entity.direction)
        {
            case "up" ->
            {
                entityTopRow = (entityTopWorldY - entity.speed) / tileSize;
                return areSurroundingTilesWithCollision(
                        getTileNumber(entityLeftCol, entityTopRow),
                        getTileNumber(entityRightCol, entityTopRow));

            }
            case "down" ->
            {
                entityBottomRow = (entityBottomWorldY + entity.speed) / tileSize;
                return areSurroundingTilesWithCollision(
                        getTileNumber(entityLeftCol, entityBottomRow),
                        getTileNumber(entityRightCol, entityBottomRow));
            }
            case "left" ->
            {
                entityLeftCol = (entityLeftWorldX - entity.speed) / tileSize;
                return areSurroundingTilesWithCollision(
                        getTileNumber(entityLeftCol, entityTopRow),
                        getTileNumber(entityLeftCol, entityBottomRow));
            }
            case "right" ->
            {
                entityRightCol = (entityRightWorldX + entity.speed) / tileSize;
                return areSurroundingTilesWithCollision(
                        getTileNumber(entityRightCol, entityTopRow),
                        getTileNumber(entityRightCol, entityBottomRow));
            }
            default ->
            {
                return false;
            }
        }
    }

    public int checkObjectsForCollisions(Entity entity, List<Object> objects)
    {
        int objectIndex = -1;
        for (int i = 0; i < objects.size(); i++)
        {
            Object object = objects.get(i);
            increaseSolidAreaWorldCoordinates(entity);
            increaseSolidAreaWorldCoordinates(object);
            changeEntityDirection(entity);

            if (isObjectIntersects(entity, object))
            {
                objectIndex = i;
            }

            setSolidAreaDefaultCoordinates(entity);
            setSolidAreaDefaultCoordinates(object);
        }
        return objectIndex;
    }

    public int checkLiveEntitiesForCollision(Entity entity, List<? extends LiveEntity> liveEntities)
    {
        int assetIndex = -1;
        for (int i = 0; i < liveEntities.size(); i++)
        {
            LiveEntity asset = liveEntities.get(i);
            increaseSolidAreaWorldCoordinates(entity);
            increaseSolidAreaWorldCoordinates(asset);
            changeEntityDirection(entity);

            if (isEntityIntersects(entity, asset))
            {
                entity.hasCollision = true;
                assetIndex = i;
            }

            setSolidAreaDefaultCoordinates(entity);
            setSolidAreaDefaultCoordinates(asset);
        }

        return assetIndex;
    }

    public boolean checkForCollisionWithPlayer(Entity entity, Player player)
    {
        boolean contactPlayer = false;
        increaseSolidAreaWorldCoordinates(entity);
        increaseSolidAreaWorldCoordinates(player);
        changeEntityDirection(entity);

        if (entity.solidArea.intersects(player.solidArea))
        {
            entity.hasCollision = true;
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

    public boolean isObjectIntersects(Entity entity, Entity target)
    {
        if (!entity.solidArea.intersects(target.solidArea))
        {
            return false;
        }
        if (target.hasCollision)
        {
            entity.hasCollision = true;
        }
        return entity.type.equals(EntityType.PLAYER);
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

    public boolean areSurroundingTilesWithCollision(int tileNumber1, int tileNumber2)
    {
        return gp.tileManager.getTiles().getTiles().get(tileNumber1).hasCollision()
                || gp.tileManager.getTiles().getTiles().get(tileNumber2).hasCollision();
    }

    public int getTileNumber(int entityLeftCol, int entityBottomRow)
    {
        return gp.tileManager.getMapTileNumbers()[entityLeftCol][entityBottomRow];
    }
}
