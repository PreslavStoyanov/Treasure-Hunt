package assets.entities.interactivetiles.trees;

import application.GamePanel;
import assets.entities.interactivetiles.Tree;

import static application.Application.interactiveTilesUrls;
import static utilities.images.ImageUtils.setupDefaultSizeImage;

public class Oak extends Tree
{
    public Oak(GamePanel gp)
    {
        super(gp);
        name = "Oak tree";
        endurance = 5;
        defaultImage = setupDefaultSizeImage(interactiveTilesUrls.get("oak-tree"));
        afterInteractionImage = setupDefaultSizeImage(interactiveTilesUrls.get("oak-tree-stump"));
    }
}
