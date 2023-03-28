package assets.entities.interactivetiles.trees;

import application.GamePanel;
import assets.entities.interactivetiles.Tree;

import static application.Application.breakableTilesUrls;
import static utilities.images.ImageUtils.setupDefaultSizeImage;

public class Oak extends Tree
{
    public Oak(GamePanel gp)
    {
        super(gp);
        name = "Oak tree";
        endurance = 5;
        defaultImage = setupDefaultSizeImage(breakableTilesUrls.get("oak-tree"));
        brokenImage = setupDefaultSizeImage(breakableTilesUrls.get("oak-tree-stump"));
    }
}
