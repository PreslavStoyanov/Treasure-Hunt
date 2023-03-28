package assets.entities.objects.collectables.potions;

import application.GamePanel;
import assets.entities.objects.collectables.Potion;
import assets.interfaces.Consumable;

import static application.Application.objectsImagesUrls;
import static utilities.drawers.MessageDrawer.addMessage;
import static utilities.images.ImageUtils.setupDefaultSizeImage;

public class ExpPotion extends Potion implements Consumable
{

    public ExpPotion(GamePanel gp)
    {
        super(gp);

        this.value = 10;
        this.name = "Exp Potion";
        this.description = String.format("""
                [%s]
                Gives you %d exp!""", name, value);
        this.defaultImage = setupDefaultSizeImage(objectsImagesUrls.get("exp-potion"));
    }

    @Override
    public void consume()
    {
        gp.player.collectExp(value);
        super.consume();
        addMessage(String.format("You gained %d exp!", value));
    }
}
