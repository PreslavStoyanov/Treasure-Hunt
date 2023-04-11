package assets.entities.movingentities.liveentities.artificials.npcs;

import application.GamePanel;
import assets.Inventory;
import assets.entities.movingentities.liveentities.artificials.Npc;
import assets.entities.objects.storables.BoatPaddle;
import assets.entities.objects.storables.Key;
import assets.entities.objects.storables.potions.EnergyPotion;
import assets.entities.objects.storables.potions.ExpPotion;
import assets.entities.objects.storables.potions.HealthPotion;

import java.util.List;

import static application.GamePanel.TILE_SIZE;
import static assets.EntityType.MERCHANT_GIRL;

public class MerchantGirl extends Npc
{
    public MerchantGirl(GamePanel gp, int x, int y)
    {
        super(gp);
        this.setWorldLocation(x * TILE_SIZE, y * TILE_SIZE);
        setSolidAreaAndDefaultLocation(8, 16, 30, 30);
        type = MERCHANT_GIRL;
        movingSpeed = 1;
        sprites = setSprites("src/main/resources/npc/merchant_girl_sprites.yaml");
        dialogues = List.of("Hello!", "What do you want?");
        inventory = new Inventory(List.of(new Key(gp), new HealthPotion(gp), new ExpPotion(gp), new EnergyPotion(gp)));
    }
}
