package assets.entities.objects.collectables;

import application.GamePanel;
import assets.entities.objects.StorableObject;

import static application.Application.objectsImagesUrls;
import static assets.EntityType.KEY;
import static utilities.drawers.MessageDrawer.addMessage;
import static utilities.images.ImageUtils.setupDefaultSizeImage;
import static utilities.sound.Sound.TAKE_KEY;

public class Key extends StorableObject
{

    public Key(GamePanel gp)
    {
        super(gp);
        this.name = "Key";
        this.type = KEY;
        this.interactSound = TAKE_KEY;
        this.description = String.format("""
                [%s]
                It can open doors!""", name);
        this.defaultImage = setupDefaultSizeImage(objectsImagesUrls.get("key"));
    }

    @Override
    public void useItem()
    {
        addMessage("It open doors");
    }
}
