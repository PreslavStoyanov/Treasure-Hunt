package assets.entities.objects.collectables;

import application.GamePanel;
import assets.entities.objects.CollectableObject;

import static application.Application.defaultImagesUrls;
import static assets.EntityType.KEY;
import static utilities.drawers.MessageDrawer.addMessage;
import static utilities.images.ImageUtils.setupDefaultSizeImage;
import static utilities.sound.Sound.TAKE_COIN;

public class Key extends CollectableObject
{

    public Key(GamePanel gp)
    {
        super(gp);
        this.name = "Key";
        this.type = KEY;
        this.interactSound = TAKE_COIN; //TODO add TAKE_KEY sound
        this.description = String.format("""
                [%s]
                It can open doors!""", name);
        this.defaultImage = setupDefaultSizeImage(defaultImagesUrls.get("key"));
    }

    @Override
    public void useItem()
    {
        addMessage("It open doors");
    }
}
