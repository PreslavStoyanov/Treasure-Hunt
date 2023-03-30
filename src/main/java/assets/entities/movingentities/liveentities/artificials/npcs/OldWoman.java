package assets.entities.movingentities.liveentities.artificials.npcs;

import application.GamePanel;
import assets.entities.movingentities.liveentities.artificials.Npc;

import java.util.List;

import static application.GamePanel.TILE_SIZE;
import static assets.EntityType.OLD_WOMAN;

public class OldWoman extends Npc
{


    public OldWoman(GamePanel gp, int x, int y)
    {
        super(gp);
        this.setWorldLocation(x * TILE_SIZE, y * TILE_SIZE);
        setSolidAreaAndDefaultLocation(8, 16, 30, 30);
        type = OLD_WOMAN;
        movingSpeed = 1;
        sprites = setSprites("src/main/resources/npc/old_woman_sprites.yaml");
        dialogues = List.of(
                "Hello, Hello!",
                "Don't trust anybody!",
                "I'm a wizard as this old-man down \nthere!",
                "I was better than the old-man!",
                "So good luck!");
    }
}
