package assets.entities.objects.storables.potions;

import application.GamePanel;
import assets.entities.objects.storables.Potion;
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
        this.price = 5;
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
