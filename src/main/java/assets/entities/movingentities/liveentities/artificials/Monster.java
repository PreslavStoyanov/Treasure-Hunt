package assets.entities.movingentities.liveentities.artificials;

import application.GamePanel;
import assets.entities.movingentities.liveentities.Artificial;
import assets.interfaces.Damageable;
import assets.interfaces.ItemDroppable;

import java.awt.*;
import java.util.Random;

import static application.GamePanel.TILE_SIZE;
import static assets.ObjectType.*;
import static utilities.sound.Sound.HIT_MONSTER;

public class Monster extends Artificial implements Damageable, ItemDroppable
{

    public boolean isAlive = true;
    public int dyingCounter = 0;
    public boolean isDying = false;
    public boolean isHpBarOn = false;
    public int hpBarCounter = 0;


    public Monster(GamePanel gp)
    {
        super(gp);
    }

    @Override
    public void draw(Graphics2D g2)
    {
        int screenX = worldX + Math.min(gp.player.screenX - gp.player.worldX, 0);
        int screenY = worldY + Math.min(gp.player.screenY - gp.player.worldY, 0);

        if (isHpBarOn)
        {
            drawHpBar(g2, screenX, screenY);
        }

        if (isInvincible)
        {
            isHpBarOn = true;
            hpBarCounter = 0;
            changeAlpha(g2, 0.5F);
        }

        if (isDying)
        {
            dyingAnimation(g2);
        }
        super.draw(g2);
        changeAlpha(g2, 1F);
    }

    @Override
    public void update()
    {
        super.update();
        if (!gp.player.isInvincible && isContactingPlayer)
        {
            gp.player.takeDamage(attackValue);
            gp.player.reactToDamage();
        }
    }

    @Override
    public void takeDamage(int damage)
    {
        gp.soundEffectsHandler.playSoundEffect(HIT_MONSTER);
        super.takeDamage(damage);
        reactToDamage();
        isDying = life <= 0;
    }

    public void reactToDamage()
    {
        isInvincible = true;
    }

    @Override
    public void dropItem()
    {
        switch (new Random().nextInt(20))
        {
            case 1, 2 -> gp.entitySetter.addObject(HEALTH_POTION, worldX, worldY);
            case 3, 4 -> gp.entitySetter.addObject(ENERGY_POTION, worldX, worldY);
            case 5 -> gp.entitySetter.addObject(EXP_POTION, worldX, worldY);
            case 6, 7, 8 -> gp.entitySetter.addObject(ENERGY, worldX, worldY);
            case 9, 10, 11 -> gp.entitySetter.addObject(HEART, worldX, worldY);
            default -> gp.entitySetter.addObject(COIN, worldX, worldY);
        }
    }

    private void drawHpBar(Graphics2D g2, int screenX, int screenY)
    {
        drawFillingBar(g2, screenX, screenY);

        hpBarCounter++;
        if (hpBarCounter > 300)
        {
            hpBarCounter = 0;
            isHpBarOn = false;
        }
    }

    /**
     * There is the same method in DrawerUtils, but it's not drawing.
     * Probably the problem is in Graphics2D
     */
    private void drawFillingBar(Graphics2D g2, int screenX, int screenY)
    {
        double oneScale = (double) TILE_SIZE / maxLife;
        double hpBarValue = oneScale * life;

        g2.setColor(new Color(35, 35, 35));
        g2.fillRoundRect(screenX, screenY - TILE_SIZE / 3, TILE_SIZE, TILE_SIZE / 4, 10, 10);

        g2.setColor(new Color(255, 0, 30));
        g2.fillRoundRect(screenX, screenY - TILE_SIZE / 3, (int) hpBarValue, TILE_SIZE / 4, 10, 10);

        g2.setColor(new Color(255, 255, 255));
        g2.setStroke(new BasicStroke(1));
        g2.drawRoundRect(screenX, screenY - TILE_SIZE / 3, TILE_SIZE, TILE_SIZE / 4, 10, 10);
    }

    private void dyingAnimation(Graphics2D g2)
    {
        dyingCounter++;
        int framesPerFade = 5;
        int frameIndex = (dyingCounter - 1) / framesPerFade;

        if (frameIndex % 2 == 0)
        {
            changeAlpha(g2, 0f);
        }
        else
        {
            changeAlpha(g2, 1f);
        }

        int numDyingFrames = 8;
        if (frameIndex >= numDyingFrames)
        {
            isAlive = false;
        }
    }

    private void changeAlpha(Graphics2D g2, float alpha)
    {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
    }
}
