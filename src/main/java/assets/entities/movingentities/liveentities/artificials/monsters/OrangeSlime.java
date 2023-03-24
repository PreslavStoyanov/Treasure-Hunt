package assets.entities.movingentities.liveentities.artificials.monsters;

import application.GamePanel;
import assets.EntityType;
import assets.entities.movingentities.liveentities.artificials.Monster;

import static application.GamePanel.tileSize;

public class OrangeSlime extends Monster
{
    GamePanel gp;

    public OrangeSlime(GamePanel gp, int x, int y)
    {
        super(gp);
        this.setWorldLocation(x * tileSize, y * tileSize);
        setSolidAreaAndDefaultLocation(3, 18, 42, 30);
        this.gp = gp;
        type = EntityType.SLIME;
        name = "Orange slime";
        movingSpeed = 1;
        maxLife = 3;
        life = maxLife;
        attackValue = 2;
        defense = 0;
        exp = 3;
        sprites = setSprites("src/main/resources/monster/orange_slime_sprites.yaml");
    }

    public void reactToDamage()
    {
        super.reactToDamage();
        direction = gp.player.direction;
    }
}
