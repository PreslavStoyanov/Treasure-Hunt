package assets.entities.interactivetiles;

import application.GamePanel;
import assets.entities.BreakableTile;

import static assets.EntityType.AXE;
import static assets.EntityType.TREE;
import static utilities.sound.Sound.TREE_CHOP;

public class Tree extends BreakableTile
{
    public Tree(GamePanel gp)
    {
        super(gp);
        type = TREE;
        interactSound = TREE_CHOP;
        breakTool = AXE;
        isTransitional = true;
    }
}
