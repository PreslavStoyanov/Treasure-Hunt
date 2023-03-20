package utilities;

import application.GamePanel;
import assets.Entity;
import assets.EntityType;
import assets.entities.MovingEntity;
import assets.entities.Object;
import assets.entities.movingentities.liveentities.Player;

import static application.GamePanel.tileSize;

public class CollisionChecker
{
    private final GamePanel gp;

    public CollisionChecker(GamePanel gp)
    {
        this.gp = gp;
    }

    public boolean isTileColliding(MovingEntity movingEntity)
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

    public boolean isObjectColliding(MovingEntity movingEntity, Object object)
    {
        increaseSolidAreaWorldCoordinates(movingEntity);
        increaseSolidAreaWorldCoordinates(object);
        changeEntityDirection(movingEntity);

        boolean result = isObjectIntersects(movingEntity, object);

        resetDefaultLocation(movingEntity);
        resetDefaultLocation(object);

        return result;
    }
    public boolean isLiveEntityColliding(MovingEntity movingEntity, MovingEntity target)
    {
        increaseSolidAreaWorldCoordinates(movingEntity);
        increaseSolidAreaWorldCoordinates(target);
        changeEntityDirection(movingEntity);

        boolean result = isEntityIntersects(movingEntity, target);

        resetDefaultLocation(movingEntity);
        resetDefaultLocation(target);
        return result;
    }

    public boolean isCollidingWithPlayer(MovingEntity movingEntity, Player player)
    {
        boolean isContactingPlayer = false;
        increaseSolidAreaWorldCoordinates(movingEntity);
        increaseSolidAreaWorldCoordinates(player);
        changeEntityDirection(movingEntity);

        if (movingEntity.solidArea.intersects(player.solidArea))
        {
            movingEntity.hasCollision = true;
            isContactingPlayer = true;
        }

        resetDefaultLocation(movingEntity);
        resetDefaultLocation(player);

        return isContactingPlayer;
    }

    private boolean isEntityIntersects(MovingEntity movingEntity, MovingEntity target)
    {
        if (!movingEntity.solidArea.intersects(target.solidArea))
        {
            return false;
        }
        if (target != movingEntity)
        {
            movingEntity.hasCollision = true;
            return true;
        }
        else return false;
    }

    private boolean isObjectIntersects(MovingEntity movingEntity, Object object)
    {
        if (!movingEntity.solidArea.intersects(object.solidArea))
        {
            return false;
        }
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
