package entities.types;

import View.GamePanel;

import java.awt.*;

import static View.GamePanel.tileSize;
import static entities.types.EntityType.monstersTypes;

public class Monster extends LiveEntity
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

        if (monstersTypes.contains(type) && isHpBarOn)
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

    private void drawHpBar(Graphics2D g2, int screenX, int screenY)
    {
        double oneScale = (double) tileSize / maxLife;
        double hpBarValue = oneScale * life;

        g2.setColor(new Color(35, 35, 35));
        g2.fillRect(screenX - 1, screenY - 16, tileSize + 2, 12);

        g2.setColor(new Color(255, 0, 30));
        g2.fillRect(screenX, screenY - 15, (int) hpBarValue, 10);

        hpBarCounter++;
        if (hpBarCounter > 300)
        {
            hpBarCounter = 0;
            isHpBarOn = false;
        }
    }

    private void dyingAnimation(Graphics2D g2)
    {
        dyingCounter++;
        int i = 5;
        if (dyingCounter <= i) changeAlpha(g2, 0f);
        if (dyingCounter > i && dyingCounter <= i * 2) changeAlpha(g2, 1f);
        if (dyingCounter > i * 2 && dyingCounter <= i * 3) changeAlpha(g2, 0f);
        if (dyingCounter > i * 3 && dyingCounter <= i * 4) changeAlpha(g2, 1f);
        if (dyingCounter > i * 4 && dyingCounter <= i * 5) changeAlpha(g2, 0f);
        if (dyingCounter > i * 5 && dyingCounter <= i * 6) changeAlpha(g2, 1f);
        if (dyingCounter > i * 6 && dyingCounter <= i * 7) changeAlpha(g2, 0f);
        if (dyingCounter > i * 7 && dyingCounter <= i * 8) changeAlpha(g2, 1f);
        if (dyingCounter >= i * 8)
        {
            isDying = false;
            isAlive = false;
        }
    }

    private void changeAlpha(Graphics2D g2, float alpha)
    {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
    }
}
