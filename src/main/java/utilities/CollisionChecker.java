package utilities;

import application.GamePanel;
import assets.Entity;
import assets.EntityType;
import assets.entities.MovingEntity;
import assets.entities.Object;

import static application.GamePanel.tileSize;
import static assets.EntityType.*;

public class CollisionChecker
{
    private final GamePanel gp;

    public CollisionChecker(GamePanel gp)
    {
        this.gp = gp;
    }

    public boolean isHittingCollisionTile(MovingEntity movingEntity)
    {
        int entityLeftWorldX = movingEntity.worldX + movingEntity.solidArea.x;
        int entityRightWorldX = movingEntity.worldX + movingEntity.solidArea.x + movingEntity.solidArea.width;
        int entityTopWorldY = movingEntity.worldY + movingEntity.solidArea.y;
        int entityBottomWorldY = movingEntity.worldY + movingEntity.solidArea.y + movingEntity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / tileSize;
        int entityRightCol = entityRightWorldX / tileSize;
        int entityTopRow = entityTopWorldY / tileSize;
        int entityBottomRow = entityBottomWorldY / tileSize;

        switch (movingEntity.direction)
        {
            case UP ->
            {
                entityTopRow = (entityTopWorldY - movingEntity.movingSpeed) / tileSize;
                return areSurroundingTilesWithCollision(
                        getTileNumber(entityLeftCol, entityTopRow),
                        getTileNumber(entityRightCol, entityTopRow));

            }
            case DOWN ->
            {
                entityBottomRow = (entityBottomWorldY + movingEntity.movingSpeed) / tileSize;
                return areSurroundingTilesWithCollision(
                        getTileNumber(entityLeftCol, entityBottomRow),
                        getTileNumber(entityRightCol, entityBottomRow));
            }
            case LEFT ->
            {
                entityLeftCol = (entityLeftWorldX - movingEntity.movingSpeed) / tileSize;
                return areSurroundingTilesWithCollision(
                        getTileNumber(entityLeftCol, entityTopRow),
                        getTileNumber(entityLeftCol, entityBottomRow));
            }
            case RIGHT ->
            {
                entityRightCol = (entityRightWorldX + movingEntity.movingSpeed) / tileSize;
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

    public boolean isEntityColliding(MovingEntity movingEntity, Entity entity)
    {
        increaseSolidAreaWorldCoordinates(movingEntity);
        increaseSolidAreaWorldCoordinates(entity);
        changeEntityDirection(movingEntity);

        boolean result = isEntityIntersects(movingEntity, entity);

        resetDefaultLocation(movingEntity);
        resetDefaultLocation(entity);
        return result;
    }

    private boolean isEntityIntersects(MovingEntity movingEntity, Entity entity)
    {
        if (!movingEntity.solidArea.intersects(entity.solidArea))
        {
            return false;
        }

        if (entity.type.equals(PLAYER))
        {
            movingEntity.hasCollision = true;
            return true;
        }
        else if (OBJECT_TYPES.contains(entity.type))
        {
            return isObjectIntersects(movingEntity, entity);
        }
        else if (MOVING_ENTITIES.contains(entity.type))
        {
            return isMovingEntityIntersects(movingEntity, entity);
        }
        else if (INTERACTIVE_TILES.contains(entity.type))
        {
            return isInteractiveTileIntersects(entity);
        }
        return true;
    }

    private static boolean isInteractiveTileIntersects(Entity entity)
    {
        return entity.hasCollision;
    }

    private static boolean isMovingEntityIntersects(MovingEntity movingEntity, Entity target)
    {
        if (target != movingEntity)
        {
            movingEntity.hasCollision = true;
            return true;
        }
        return false;
    }

    private static boolean isObjectIntersects(MovingEntity movingEntity, Entity object)
    {
        if (object.hasCollision)
        {
            movingEntity.hasCollision = true;
        }
        return movingEntity.type.equals(EntityType.PLAYER);
    }

    private static void increaseSolidAreaWorldCoordinates(Entity entity)
    {
        entity.solidArea.setLocation(entity.solidArea.x + entity.worldX, entity.solidArea.y + entity.worldY);
    }

    private void changeEntityDirection(MovingEntity movingEntity)
    {
        switch (movingEntity.direction)
        {
            case UP -> movingEntity.solidArea.y -= movingEntity.movingSpeed;
            case DOWN -> movingEntity.solidArea.y += movingEntity.movingSpeed;
            case LEFT -> movingEntity.solidArea.x -= movingEntity.movingSpeed;
            case RIGHT -> movingEntity.solidArea.x += movingEntity.movingSpeed;
        }
    }

    private static void resetDefaultLocation(Entity entity)
    {
        entity.solidArea.setLocation(entity.solidAreaDefaultX, entity.solidAreaDefaultY);
    }

    private boolean areSurroundingTilesWithCollision(int tileNumber1, int tileNumber2)
    {
        return gp.tileManager.getTiles().getTiles().get(tileNumber1).hasCollision()
                || gp.tileManager.getTiles().getTiles().get(tileNumber2).hasCollision();
    }

    private int getTileNumber(int entityLeftCol, int entityBottomRow)
    {
        return gp.tileManager.getMapTileNumbers()[entityLeftCol][entityBottomRow];
    }
}
