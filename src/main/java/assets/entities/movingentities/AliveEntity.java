package assets.entities.movingentities;

import application.GamePanel;
import assets.entities.MovingEntity;

import java.awt.*;

import static application.GamePanel.tileSize;

public class AliveEntity extends MovingEntity
{
    public boolean isInvincible = false;
    public int invincibleCounter = 60;
    public int maxLife;
    public int life;
    public int defense;
    public int exp;
    public int attack;

    public AliveEntity(GamePanel gp)
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
            g2.drawImage(switchWalkingSpritesByDirection(), screenX, screenY, tileSize, tileSize, null);
        }
    }

    @Override
    public void update()
    {
        super.update();
        setInvincibleTime(40);
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

    public void increaseLife(int value)
    {
        life = Math.min(life + value, maxLife);
    }

    public void decreaseLife(int value)
    {
        life = Math.max(life - value, 0);
    }
}
