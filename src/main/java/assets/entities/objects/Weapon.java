package assets.entities.objects;

import application.GamePanel;
import assets.entities.Object;

import java.awt.*;

public class Weapon extends Object
{
    public int attackValue;
    public Rectangle attackArea;

    public Weapon(GamePanel gp)
    {
        super(gp);
    }
}
