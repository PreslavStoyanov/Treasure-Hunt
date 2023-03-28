package utilities;

import application.GamePanel;
import assets.Entity;
import assets.EntityType;
import assets.entities.MovingEntity;

import static application.GamePanel.TILE_SIZE;
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

        int entityLeftCol = entityLeftWorldX / TILE_SIZE;
        int entityRightCol = entityRightWorldX / TILE_SIZE;
        int entityTopRow = entityTopWorldY / TILE_SIZE;
        int entityBottomRow = entityBottomWorldY / TILE_SIZE;

        switch (movingEntity.direction)
        {
            case UP ->
            {
                entityTopRow = (entityTopWorldY - movingEntity.movingSpeed) / TILE_SIZE;
                return areSurroundingTilesWithCollision(
                        getTileNumber(entityLeftCol, entityTopRow),
                        getTileNumber(entityRightCol, entityTopRow));

            }
            case DOWN ->
            {
                entityBottomRow = (entityBottomWorldY + movingEntity.movingSpeed) / TILE_SIZE;
                return areSurroundingTilesWithCollision(
                        getTileNumber(entityLeftCol, entityBottomRow),
                        getTileNumber(entityRightCol, entityBottomRow));
            }
            case LEFT ->
            {
                entityLeftCol = (entityLeftWorldX - movingEntity.movingSpeed) / TILE_SIZE;
                return areSurroundingTilesWithCollision(
                        getTileNumber(entityLeftCol, entityTopRow),
                        getTileNumber(entityLeftCol, entityBottomRow));
            }
            case RIGHT ->
            {
                entityRightCol = (entityRightWorldX + movingEntity.movingSpeed) / TILE_SIZE;
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
            movingEntity.isTransitional = true;
            return true;
        }
        else if (OBJECT_TYPES.contains(entity.type))
        {
            if (entity.isTransitional)
            {
                movingEntity.isTransitional = true;
            }
            return movingEntity.type.equals(EntityType.PLAYER);
        }
        else if (MOVING_ENTITIES.contains(entity.type))
        {
            if (entity != movingEntity)
            {
                movingEntity.isTransitional = true;
                return true;
            }
            else return false;
        }
        else if (INTERACTIVE_TILES.contains(entity.type))
        {
            return entity.isTransitional;
        }
        else return true;
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
