package assets.entities;

import application.GamePanel;
import assets.Entity;
import assets.EntityType;
import assets.interfaces.Interactive;
import utilities.sound.Sound;

import java.awt.image.BufferedImage;

import static utilities.drawers.MessageDrawer.addMessage;

public abstract class BreakableTile extends Entity implements Interactive
{
    public BufferedImage brokenImage;
    public Sound interactSound;
    public int endurance;
    public EntityType breakTool;

    public BreakableTile(GamePanel gp)
    {
        super(gp);
    }

    @Override
    public void interact()
    {
        if (gp.player.getRequiredToolForInteraction(breakTool).isPresent())
        {
            gp.soundEffectsHandler.playSoundEffect(interactSound);
            decreaseEndurance(1);
            if (endurance == 0)
            {
                defaultImage = brokenImage;
                isTransitional = false;
            }
        }
        else
        {
            addMessage(String.format("You need %s", breakTool));
        }
    }

    private void decreaseEndurance(int value)
    {
        endurance = Math.max(endurance - value, 0);
    }
}
