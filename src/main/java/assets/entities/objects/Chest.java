package assets.entities.objects;

import application.GamePanel;
import assets.EntityType;
import assets.entities.Object;
import assets.entities.objects.interfaces.Interactable;

import static application.Application.defaultImagesUrls;
import static application.GamePanel.tileSize;
import static assets.EntityType.CHEST;
import static utilities.GameState.GAME_WIN_STATE;
import static utilities.images.ImageUtils.setupDefaultSizeImage;
import static utilities.sound.Sound.WIN_SOUND;

public class Chest extends Object implements Interactable
{

    public Chest(GamePanel gp, int x, int y)
    {
        super(gp);
        this.setWorldLocation(x * tileSize, y * tileSize);
        name = "Chest";
        type = CHEST;
        interactSound = WIN_SOUND;
        defaultImage = setupDefaultSizeImage(defaultImagesUrls.get("chest"));
    }

    @Override
    public void interact()
    {
        gp.soundHandler.stop();
        gp.soundHandler.playSoundEffect(interactSound);
        gp.setGameState(GAME_WIN_STATE);
    }
}
