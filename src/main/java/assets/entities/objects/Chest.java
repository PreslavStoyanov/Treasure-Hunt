package assets.entities.objects;

import application.GamePanel;
import assets.entities.Object;
import assets.interfaces.Interactive;

import static application.Application.objectsImagesUrls;
import static application.GamePanel.TILE_SIZE;
import static assets.EntityType.CHEST;
import static utilities.GameState.GAME_WIN_STATE;
import static utilities.images.ImageUtils.setupDefaultSizeImage;
import static utilities.sound.Sound.WIN_SOUND;

public class Chest extends Object implements Interactive
{

    public Chest(GamePanel gp, int x, int y)
    {
        super(gp);
        this.setWorldLocation(x * TILE_SIZE, y * TILE_SIZE);
        name = "Chest";
        type = CHEST;
        interactSound = WIN_SOUND;
        defaultImage = setupDefaultSizeImage(objectsImagesUrls.get("chest"));
    }

    @Override
    public void interact()
    {
        gp.soundEffectsHandler.stop();
        gp.soundEffectsHandler.playSoundEffect(interactSound);
        gp.setGameState(GAME_WIN_STATE);
    }
}
