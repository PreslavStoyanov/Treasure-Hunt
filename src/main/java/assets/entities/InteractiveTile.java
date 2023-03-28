package assets.entities;

import application.GamePanel;
import assets.Entity;
import assets.EntityType;
import assets.entities.objects.StorableObject;
import assets.interfaces.Interactive;
import utilities.sound.Sound;

import java.awt.image.BufferedImage;
import java.util.Optional;

import static assets.EntityType.BOAT_PADDLE;
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
        Optional<StorableObject> interactionTool = gp.player.inventory.stream()
                .filter(obj -> obj.type.equals(toolForInteraction))
                .findFirst();

        if (interactionTool.isPresent())
        {
            gp.soundEffectsHandler.playSoundEffect(interactSound);
            decreaseEndurance(1);
            if (endurance == 0)
            {
                defaultImage = afterInteractionImage;
                doAction();
            }
        }
        else
        {
            addMessage(String.format("You need %s", toolForInteraction));
        }
    }

    public abstract void doAction();

    private void decreaseEndurance(int value)
    {
        endurance = Math.max(endurance - value, 0);
    }
}
