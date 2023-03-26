package assets.entities.interactivetiles;

import application.GamePanel;
import assets.entities.InteractiveTile;

import static assets.EntityType.AXE;
import static assets.EntityType.TREE;
import static utilities.sound.Sound.TREE_CHOP;

public class Tree extends InteractiveTile
{
    public Tree(GamePanel gp)
    {
        super(gp);
        type = TREE;
        interactSound = TREE_CHOP;
        toolForInteraction = AXE;
        isHittingTileWithCollision = true;
    }
}
