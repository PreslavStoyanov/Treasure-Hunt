package assets.entities;

import application.GamePanel;
import assets.Entity;
import assets.EntityType;
import assets.interfaces.Interactive;
import utilities.sound.Sound;

import java.awt.image.BufferedImage;

import static utilities.drawers.MessageDrawer.addMessage;
import static utilities.sound.Sound.TREE_CHOP;

public abstract class InteractiveTile extends Entity implements Interactive
{
    public BufferedImage afterInteractionImage;
    public Sound interactSound;
    public int endurance;
    public EntityType toolForInteraction;

    public InteractiveTile(GamePanel gp)
    {
        super(gp);
    }

    @Override
    public void interact()
    {
        if (gp.player.currentWeapon.get().type.equals(toolForInteraction))
        {
            gp.soundEffectsHandler.playSoundEffect(TREE_CHOP);
            decreaseEndurance(1);
            if (endurance == 0)
            {
                defaultImage = afterInteractionImage;
                isHittingTileWithCollision = false;
            }
        }
        else
        {
            addMessage(String.format("You need %s", toolForInteraction));
        }
    }

    private void decreaseEndurance(int value)
    {
        endurance = Math.max(endurance - value, 0);
    }
}
