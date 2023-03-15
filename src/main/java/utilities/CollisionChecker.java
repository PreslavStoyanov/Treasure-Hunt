package utilities;

import application.GamePanel;
import entities.Entity;
import entities.types.EntityType;
import entities.types.LiveEntity;
import entities.types.Object;
import entities.types.Player;

import java.util.List;

import static application.GamePanel.tileSize;

public class CollisionChecker
{
    private final GamePanel gp;

    public CollisionChecker(GamePanel gp)
    {
        this.gp = gp;
    }

    public boolean isTileColliding(LiveEntity liveEntity)
    {
        int entityLeftWorldX = liveEntity.worldX + liveEntity.solidArea.x;
        int entityRightWorldX = liveEntity.worldX + liveEntity.solidArea.x + liveEntity.solidArea.width;
        int entityTopWorldY = liveEntity.worldY + liveEntity.solidArea.y;
        int entityBottomWorldY = liveEntity.worldY + liveEntity.solidArea.y + liveEntity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / tileSize;
        int entityRightCol = entityRightWorldX / tileSize;
        int entityTopRow = entityTopWorldY / tileSize;
        int entityBottomRow = entityBottomWorldY / tileSize;

        switch (liveEntity.direction)
        {
            case UP ->
            {
                entityTopRow = (entityTopWorldY - liveEntity.speed) / tileSize;
                return areSurroundingTilesWithCollision(
                        getTileNumber(entityLeftCol, entityTopRow),
                        getTileNumber(entityRightCol, entityTopRow));

            }
            case DOWN ->
            {
                entityBottomRow = (entityBottomWorldY + liveEntity.speed) / tileSize;
                return areSurroundingTilesWithCollision(
                        getTileNumber(entityLeftCol, entityBottomRow),
                        getTileNumber(entityRightCol, entityBottomRow));
            }
            case LEFT ->
            {
                entityLeftCol = (entityLeftWorldX - liveEntity.speed) / tileSize;
                return areSurroundingTilesWithCollision(
                        getTileNumber(entityLeftCol, entityTopRow),
                        getTileNumber(entityLeftCol, entityBottomRow));
            }
            case RIGHT ->
            {
                entityRightCol = (entityRightWorldX + liveEntity.speed) / tileSize;
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

    public int areObjectsColliding(LiveEntity liveEntity, List<Object> objects)
    {
        int objectIndex = -1;
        for (int i = 0; i < objects.size(); i++)
        {
            Object object = objects.get(i);
            increaseSolidAreaWorldCoordinates(liveEntity);
            increaseSolidAreaWorldCoordinates(object);
            changeEntityDirection(liveEntity);

            if (isObjectIntersects(liveEntity, object))
            {
                objectIndex = i;
            }

            resetDefaultLocation(liveEntity);
            resetDefaultLocation(object);
        }
        return objectIndex;
    }

    public int areLiveEntitiesColliding(LiveEntity liveEntity, List<? extends LiveEntity> liveEntities)
    {
        int liveEntityIndex = -1;
        for (int i = 0; i < liveEntities.size(); i++)
        {
            LiveEntity targetLiveEntity = liveEntities.get(i);
            increaseSolidAreaWorldCoordinates(liveEntity);
            increaseSolidAreaWorldCoordinates(targetLiveEntity);
            changeEntityDirection(liveEntity);

            if (isEntityIntersects(liveEntity, targetLiveEntity))
            {
                liveEntity.hasCollision = true;
                liveEntityIndex = i;
            }

            resetDefaultLocation(liveEntity);
            resetDefaultLocation(targetLiveEntity);
        }

        return liveEntityIndex;
    }

    public boolean isCollidingWithPlayer(LiveEntity liveEntity, Player player)
    {
        boolean isContactingPlayer = false;
        increaseSolidAreaWorldCoordinates(liveEntity);
        increaseSolidAreaWorldCoordinates(player);
        changeEntityDirection(liveEntity);

        if (liveEntity.solidArea.intersects(player.solidArea))
        {
            liveEntity.hasCollision = true;
            isContactingPlayer = true;
        }

        resetDefaultLocation(liveEntity);
        resetDefaultLocation(player);

        return isContactingPlayer;
    }

    public boolean isEntityIntersects(LiveEntity entity, LiveEntity target)
    {
        if (!entity.solidArea.intersects(target.solidArea))
        {
            return false;
        }
        return target != entity;
    }

    public boolean isObjectIntersects(LiveEntity liveEntity, Object object)
    {
        if (!liveEntity.solidArea.intersects(object.solidArea))
        {
            return false;
        }
        if (object.hasCollision)
        {
            liveEntity.hasCollision = true;
        }
        return liveEntity.type.equals(EntityType.PLAYER);
    }

    private static void increaseSolidAreaWorldCoordinates(Entity entity)
    {
        entity.solidArea.setLocation(entity.solidArea.x + entity.worldX, entity.solidArea.y + entity.worldY);
    }

    private void changeEntityDirection(LiveEntity liveEntity)
    {
        switch (liveEntity.direction)
        {
            case UP -> liveEntity.solidArea.y -= liveEntity.speed;
            case DOWN -> liveEntity.solidArea.y += liveEntity.speed;
            case LEFT -> liveEntity.solidArea.x -= liveEntity.speed;
            case RIGHT -> liveEntity.solidArea.x += liveEntity.speed;
        }
    }

    private static void resetDefaultLocation(Entity entity)
    {
        entity.solidArea.setLocation(entity.solidAreaDefaultX, entity.solidAreaDefaultY);
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
