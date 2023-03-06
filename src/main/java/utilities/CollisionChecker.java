package utilities;

import View.GamePanel;
import entities.Entity.Entity;

import java.util.ArrayList;

public class CollisionChecker
{
    GamePanel gp;

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

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNumber1, tileNumber2;

        switch (entity.direction)
        {
            case "up" ->
            {
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNumber1 = setTileNumber(entityLeftCol, entityTopRow);
                tileNumber2 = setTileNumber(entityRightCol, entityTopRow);
                setCollisionOn(entity, tileNumber1, tileNumber2);
            }
            case "down" ->
            {
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNumber1 = setTileNumber(entityLeftCol, entityBottomRow);
                tileNumber2 = setTileNumber(entityRightCol, entityBottomRow);
                setCollisionOn(entity, tileNumber1, tileNumber2);
            }
            case "left" ->
            {
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNumber1 = setTileNumber(entityLeftCol, entityTopRow);
                tileNumber2 = setTileNumber(entityLeftCol, entityBottomRow);
                setCollisionOn(entity, tileNumber1, tileNumber2);
            }
            case "right" ->
            {
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNumber1 = setTileNumber(entityRightCol, entityTopRow);
                tileNumber2 = setTileNumber(entityRightCol, entityBottomRow);
                setCollisionOn(entity, tileNumber1, tileNumber2);
            }
        }

    }

    public int checkObject(Entity entity, boolean player)
    {
        int index = 999;

        for (int i = 0; i < gp.objects.size(); i++)
        {
            if (gp.objects.get(i) != null)
            {
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                gp.objects.get(i).solidArea.x = gp.objects.get(i).worldX + gp.objects.get(i).solidArea.x;
                gp.objects.get(i).solidArea.y = gp.objects.get(i).worldY + gp.objects.get(i).solidArea.y;

                switch (entity.direction)
                {
                    case "up" -> entity.solidArea.y -= entity.speed;
                    case "down" -> entity.solidArea.y += entity.speed;
                    case "left" -> entity.solidArea.x -= entity.speed;
                    case "right" -> entity.solidArea.x += entity.speed;
                }
                index = getObjectIndex(entity, player, index, i);
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.objects.get(i).solidArea.x = gp.objects.get(i).solidAreaDefaultX;
                gp.objects.get(i).solidArea.y = gp.objects.get(i).solidAreaDefaultY;
            }
        }
        return index;
    }

    public int checkEntity(Entity entity, ArrayList<Entity> target)
    {
        int index = 999;

        for (int i = 0; i < target.size(); i++)
        {
            if (target.get(i) != null)
            {
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                target.get(i).solidArea.x = target.get(i).worldX + target.get(i).solidArea.x;
                target.get(i).solidArea.y = target.get(i).worldY + target.get(i).solidArea.y;

                switch (entity.direction)
                {
                    case "up" -> entity.solidArea.y -= entity.speed;
                    case "down" -> entity.solidArea.y += entity.speed;
                    case "left" -> entity.solidArea.x -= entity.speed;
                    case "right" -> entity.solidArea.x += entity.speed;
                }
                index = getEntityIndex(entity, target, index, i);
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target.get(i).solidArea.x = target.get(i).solidAreaDefaultX;
                target.get(i).solidArea.y = target.get(i).solidAreaDefaultY;
            }
        }

        return index;
    }

    public boolean checkPlayer(Entity entity)
    {

        boolean contactPlayer = false;
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

        switch (entity.direction)
        {
            case "up" -> entity.solidArea.y -= entity.speed;
            case "down" -> entity.solidArea.y += entity.speed;
            case "left" -> entity.solidArea.x -= entity.speed;
            case "right" -> entity.solidArea.x += entity.speed;
        }
        if (entity.solidArea.intersects(gp.player.solidArea))
        {
            entity.collisionOn = true;
            contactPlayer = true;
        }
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;

        return contactPlayer;
    }

    public int getEntityIndex(Entity entity, ArrayList<Entity> target, int index, int i)
    {
        if (entity.solidArea.intersects(target.get(i).solidArea))
        {
            if (target.get(i) != entity)
            {
                entity.collisionOn = true;
                index = i;
            }
        }
        return index;
    }

    public int getObjectIndex(Entity entity, boolean player, int index, int i)
    {
        if (entity.solidArea.intersects(gp.objects.get(i).solidArea))
        {
            if (gp.objects.get(i).collision)
            {
                entity.collisionOn = true;
            }
            if (player)
            {
                index = i;
            }
        }
        return index;
    }

    public void setCollisionOn(Entity entity, int tileNumber1, int tileNumber2)
    {
        if (gp.tileManager.tile[tileNumber1].collision || gp.tileManager.tile[tileNumber2].collision)
        {
            entity.collisionOn = true;
        }
    }

    public int setTileNumber(int entityLeftCol, int entityBottomRow)
    {
        return gp.tileManager.mapTileNumber[entityLeftCol][entityBottomRow];
    }
}
