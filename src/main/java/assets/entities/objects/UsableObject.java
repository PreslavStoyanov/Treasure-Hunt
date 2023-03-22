package assets.entities.objects;

import application.GamePanel;
import assets.entities.Object;

public abstract class UsableObject extends Object
{

    public UsableObject(GamePanel gp)
    {
        super(gp);
    }

    public abstract void useItem();
}
