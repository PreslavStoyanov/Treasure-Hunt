package assets.entities.objects.storables;

import application.GamePanel;
import assets.entities.objects.StorableObject;
import assets.interfaces.Consumable;

import static assets.EntityType.POTION;
import static utilities.sound.Sound.DRINK_POTION;
import static utilities.sound.Sound.TAKE_POTION;

public abstract class Potion extends StorableObject implements Consumable
{
    public int value;

    public Potion(GamePanel gp)
    {
        super(gp);
        this.solidArea.setSize(36, 36);
        this.type = POTION;
        this.interactSound = TAKE_POTION;
    }

    @Override
    public void useItem()
    {
        consume();
    }

    @Override
    public void consume()
    {
        gp.player.inventory.getStorage().remove(this);
        gp.soundEffectsHandler.playSoundEffect(DRINK_POTION);
    }
}
