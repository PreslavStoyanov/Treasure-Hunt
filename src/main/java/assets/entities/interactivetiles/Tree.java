package assets.entities.interactivetiles;

import application.GamePanel;
import assets.entities.InteractiveTile;

import static assets.EntityType.AXE;
import static assets.EntityType.TREE;
import static utilities.sound.Sound.TREE_CHOP;
import static utilities.statehandlers.PlayStateHandler.isSwingButtonPressed;

public class Tree extends InteractiveTile
{
    public Tree(GamePanel gp)
    {
        super(gp);
        type = TREE;
        interactSound = TREE_CHOP;
        toolForInteraction = AXE;
        isTransitional = true;
    }

    @Override
    public void interact()
    {
        if (isSwingButtonPressed &&
                gp.player.currentWeapon.isPresent() &&
                gp.player.currentWeapon.get().type.equals(toolForInteraction))
        {
            super.interact();
            isTransitional = false;
        }
    }

    @Override
    public void doAction()
    {

    }
}
