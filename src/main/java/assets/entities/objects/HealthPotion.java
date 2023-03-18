package assets.entities.objects;

import application.GamePanel;
import assets.entities.Object;

import static assets.EntityType.HEALTH_POTION;
import static utilities.drawers.MessageDrawer.addMessage;
import static utilities.images.ImageUtils.setupDefaultImage;
import static utilities.sound.Sound.POWER_UP;

public class HealthPotion extends Object
{
    private final int value = 4;

    public HealthPotion(GamePanel gp)
    {
        super(gp);
        this.solidArea.setSize(36, 36);
        this.name = "Health Potion";
        this.type = HEALTH_POTION;
        this.image = setupDefaultImage("/objects/health_potion.png");
        this.description = String.format("[%s]\nGives you 2 hearts!", name);
    }

    @Override
    public void useItem()
    {
        gp.player.increaseLife(value);
        gp.player.inventory.remove(this);
        gp.soundHandler.playSoundEffect(POWER_UP);
        addMessage("You gained 2 hearts!");
    }
}
