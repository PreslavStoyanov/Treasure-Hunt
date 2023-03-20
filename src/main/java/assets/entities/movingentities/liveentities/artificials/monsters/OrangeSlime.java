package assets.entities.movingentities.liveentities.artificials.monsters;

import application.GamePanel;
import assets.EntityType;
import assets.entities.movingentities.liveentities.artificials.Monster;

public class OrangeSlime extends Monster
{
    GamePanel gp;

    public OrangeSlime(GamePanel gp, int x, int y)
    {
        super(gp);
        this.worldX = x * GamePanel.tileSize;
        this.worldY = y * GamePanel.tileSize;
        this.gp = gp;
        type = EntityType.SLIME;
        name = "Orange slime";
        movingSpeed = 1;
        maxLife = 3;
        life = maxLife;
        attack = 2;
        defense = 0;
        exp = 3;

        setSolidAreaAndDefaultLocation(3, 18, 42, 30);
        sprites = setSprites("src/main/resources/orange_slime_sprites.yaml");
    }

    public void reactToDamage()
    {
        actionLockCounter = 0;
        direction = gp.player.direction;
    }
}
