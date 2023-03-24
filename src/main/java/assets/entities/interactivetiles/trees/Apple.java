package assets.entities.interactivetiles.trees;

import application.GamePanel;
import assets.entities.interactivetiles.Tree;

import static application.Application.interactiveTilesUrls;
import static utilities.images.ImageUtils.setupDefaultSizeImage;

public class Apple extends Tree
{
    public Apple(GamePanel gp)
    {
        super(gp);
        name = "Apple tree";
        endurance = 3;
        defaultImage = setupDefaultSizeImage(interactiveTilesUrls.get("apple-tree"));
        afterInteractionImage = setupDefaultSizeImage(interactiveTilesUrls.get("apple-tree-stump"));
    }
}
