package assets.entities.movingentities;

import application.GamePanel;
import assets.entities.MovingEntity;

public abstract class AliveEntity extends MovingEntity
{
    public boolean isInvincible = false;
    public int invincibleCounter = 60;
    public int maxLife;
    public int life;
    public int maxMana;
    public int mana;
    public int defense;
    public int exp;
    public int attackValue;
    public Projectile projectile;

    public AliveEntity(GamePanel gp)
    {
        super(gp);
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

    public void takeDamage(int damage)
    {
        decreaseLife(damage - defense);
        isInvincible = true;
    }

    public void increaseLife(int value)
    {
        life = Math.min(life + value, maxLife);
    }

    private void decreaseLife(int value)
    {
        life = Math.max(life - value, 0);
    }
}
