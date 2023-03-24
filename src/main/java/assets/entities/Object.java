package assets.entities;

import application.GamePanel;
import assets.Entity;
import assets.interfaces.Interactive;
import utilities.sound.Sound;

public abstract class Object extends Entity implements Interactive
{
    public String description;
    public Sound interactSound;

    public Object(GamePanel gp)
    {
        super(gp);
    }

    @Override
    public void interact()
    {
        gp.objects.remove(this);
        gp.soundHandler.playSoundEffect(interactSound);
    }
}
