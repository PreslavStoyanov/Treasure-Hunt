package assets.entities.movingentities.liveentities.artificials.npcs;

import application.GamePanel;
import assets.entities.movingentities.liveentities.artificials.Npc;

import java.util.List;

import static application.GamePanel.TILE_SIZE;
import static assets.EntityType.OLD_MAN;

public class OldMan extends Npc
{

    public OldMan(GamePanel gp, int x, int y)
    {
        super(gp);
        this.setWorldLocation(x * TILE_SIZE, y * TILE_SIZE);
        setSolidAreaAndDefaultLocation(8, 16, 30, 30);
        type = OLD_MAN;
        movingSpeed = 1;
        sprites = setSprites("src/main/resources/npc/old_man_sprites.yaml");
        dialogues = List.of(
                "Hello, buddy!",
                "Be aware of the monkey!",
                "Once I was a wizard but now... I was \ntrowing a fire balls. Now I'm just \nold-man",
                "Good luck!");
    }
}
