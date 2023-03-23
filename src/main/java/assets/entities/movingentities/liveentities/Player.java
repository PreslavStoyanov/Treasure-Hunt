package assets.entities.movingentities.liveentities;

import application.GamePanel;
import assets.EntityType;
import assets.entities.Object;
import assets.entities.movingentities.AliveEntity;
import assets.entities.movingentities.liveentities.artificials.Monster;
import assets.entities.movingentities.liveentities.artificials.Npc;
import assets.entities.movingentities.projectiles.Fireball;
import assets.entities.movingentities.sprites.AttackingSprite;
import assets.entities.objects.CollectableObject;
import assets.entities.objects.collectables.DefenseObject;
import assets.entities.objects.collectables.Weapon;
import assets.entities.objects.collectables.defenseobjects.Shield;
import assets.entities.objects.collectables.weapons.Sword;
import utilities.keyboard.KeyboardHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static application.GamePanel.*;
import static assets.EntityType.*;
import static assets.entities.MovingEntity.Direction.*;
import static utilities.GameState.*;
import static utilities.drawers.InventoryWindowDrawer.*;
import static utilities.drawers.MessageDrawer.addMessage;
import static utilities.sound.Sound.*;

public class Player extends AliveEntity
{
    private final KeyboardHandler keyboardHandler;
    public List<CollectableObject> inventory = new ArrayList<>();
    public int inventoryCapacity;
    public Weapon currentWeapon;
    public DefenseObject currentShield;
    public int coins;
    public int level;
    public int strength;
    public int agility;
    public boolean isAttacking;
    public int attackActionTimer = 0;
    public int energy;
    public int maxEnergy;
    public int maxExp;
    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyboardHandler keyboardHandler)
    {
        super(gp);
        this.keyboardHandler = keyboardHandler;
        screenX = screenWidth / 2 - halfTileSize;
        screenY = screenHeight / 2 - halfTileSize;
        setSolidAreaAndDefaultLocation(8, 16, 30, 30);
        setWorldLocation(tileSize * 28, tileSize * 28);
        movingSpeed = 4;
        level = 1;
        maxLife = 6;
        life = maxLife;
        strength = 1;
        agility = 1;
        exp = 0;
        maxExp = 5;
        energy = 0;
        maxEnergy = 50;
        coins = 0;
        type = PLAYER;
        isAttacking = false;
        currentWeapon = new Sword(gp);
        currentShield = new Shield(gp);
        projectile = new Fireball(gp);
        inventory.add(currentShield);
        inventory.add(currentWeapon);
        inventoryCapacity = 20;
        attackValue = calculateAttack();
        defense = calculateDefense();
        sprites = setSprites("src/main/resources/player/player_sprites.yaml");
    }

    public static int getInventoryItemIndex()
    {
        return inventorySlotCursorCol + inventorySlotCursorRow * INVENTORY_COLS;
    }

    public int calculateAttack()
    {
        return strength * currentWeapon.attackValue;
    }

    public int calculateDefense()
    {
        return agility * currentShield.defenseValue;
    }

    @Override
    public void update()
    {
        if (isAttacking)
        {
            attack();
        }
        else if (isActionButtonPressed())
        {
            changeMovingDirection();
            hasCollision = gp.collisionChecker.isTileColliding(this);
            interactWithEntities();

            if (!hasCollision && !keyboardHandler.playScreenKeyboardHandler.isTalkButtonPressed)
            {
                handleMoving();
            }
        }
        else if (keyboardHandler.playScreenKeyboardHandler.isShootProjectileButtonPressed
                && energy >= projectile.castEnergyNeeded)
        {
            shootProjectile();
            decreaseEnergy(projectile.castEnergyNeeded);
        }
        else if (keyboardHandler.playScreenKeyboardHandler.isEnergyButtonPressed)
        {
            increaseEnergy(1);
        }
        setInvincibleTime(60);
        if (life <= 0)
        {
            gp.soundHandler.stop();
            gp.soundHandler.playSoundEffect(GAMEOVER_SOUND);
            gp.setGameState(GAME_LOSE_STATE);
        }
    }

    private void shootProjectile()
    {

        projectile.shoot(this);
        gp.projectiles.add(projectile);
        gp.soundHandler.playSoundEffect(FIREBALL_SOUND);
    }

    private boolean isActionButtonPressed()
    {
        return keyboardHandler.playScreenKeyboardHandler.isUpPressed || keyboardHandler.playScreenKeyboardHandler.isDownPressed
                || keyboardHandler.playScreenKeyboardHandler.isLeftPressed || keyboardHandler.playScreenKeyboardHandler.isRightPressed
                || keyboardHandler.playScreenKeyboardHandler.isInventoryButtonPressed || keyboardHandler.playScreenKeyboardHandler.isTalkButtonPressed;
    }

    @Override
    public void draw(Graphics2D g2)
    {
        BufferedImage image;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        if (isAttacking)
        {
            switch (direction)
            {
                case UP ->
                {
                    image = changeAttackingSprite(sprites.getAttackingSprites().get(currentWeapon.type).getUpSprites());
                    tempScreenY -= tileSize;
                }
                case DOWN ->
                        image = changeAttackingSprite(sprites.getAttackingSprites().get(currentWeapon.type).getDownSprites());
                case LEFT ->
                {
                    image = changeAttackingSprite(sprites.getAttackingSprites().get(currentWeapon.type).getLeftSprites());
                    tempScreenX -= tileSize;
                }
                case RIGHT ->
                        image = changeAttackingSprite(sprites.getAttackingSprites().get(currentWeapon.type).getRightSprites());
                default -> throw new IllegalStateException("Unexpected value: " + direction);
            }
        }
        else
        {
            image = switchWalkingSpritesByDirection();
        }


        int x = Math.min(tempScreenX, worldX);
        int y = Math.min(tempScreenY, worldY);

        if (isInvincible)
        {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        }

        g2.drawImage(image, x, y, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

    @Override
    public void interactWithEntities()
    {
        gp.objects.stream()
                .filter(object -> gp.collisionChecker.isObjectColliding(this, object))
                .findFirst().ifPresent(Object::interact);

        gp.npcs.stream()
                .filter(npc -> gp.collisionChecker.isLiveEntityColliding(this, npc))
                .findFirst().ifPresent(npc ->
                {
                    if (keyboardHandler.playScreenKeyboardHandler.isTalkButtonPressed)
                    {
                        interactWithNpc(npc);
                    }
                });

        gp.monsters.stream()
                .filter(monster -> gp.collisionChecker.isLiveEntityColliding(this, monster))
                .findFirst().ifPresent(monster ->
                {
                    if (!monster.isDying && !isInvincible)
                    {
                        takeDamage(monster.attackValue);
                    }
                });
    }

    @Override
    public void changeMovingDirection()
    {
        if (keyboardHandler.playScreenKeyboardHandler.isUpPressed)
        {
            direction = UP;
        }
        else if (keyboardHandler.playScreenKeyboardHandler.isDownPressed)
        {
            direction = DOWN;
        }
        else if (keyboardHandler.playScreenKeyboardHandler.isLeftPressed)
        {
            direction = LEFT;
        }
        else if (keyboardHandler.playScreenKeyboardHandler.isRightPressed)
        {
            direction = RIGHT;
        }
    }

    private void interactWithNpc(Npc npc)
    {
        gp.setGameState(DIALOGUE_STATE);
        gp.soundHandler.playSoundEffect(GOSSIP);
        npc.speak();
        keyboardHandler.playScreenKeyboardHandler.isTalkButtonPressed = false;
    }

    @Override
    public void takeDamage(int damage)
    {
        gp.soundHandler.playSoundEffect(RECEIVE_DAMAGE);
        super.takeDamage(damage);
    }

    public void collectExpAndCheckForLevelingUp(int collectedExp)
    {
        exp += collectedExp;
        if (exp >= maxExp)
        {
            levelUp();
            exp = 0;
            maxExp += level;
        }
    }

    private void levelUp()
    {
        level++;
        maxLife = Math.min(maxLife + 2, 12);
        maxEnergy = Math.min(maxEnergy + level * 5, 120);
        this.increaseLife(2);
        strength++;
        agility++;

        attackValue = calculateAttack();
        defense = calculateDefense();
        gp.soundHandler.playSoundEffect(LEVEL_UP);
        addMessage(String.format("You are level %d now! You feel stronger!", level));
    }

    private void attack()
    {
        attackActionTimer++;
        if (attackActionTimer == 2)
        {
            switch (currentWeapon.type)
            {
                case SWORD -> gp.soundHandler.playSoundEffect(SWING_SWORD);
                case AXE -> gp.soundHandler.playSoundEffect(SWING_AXE);
            }
        }
        else if (attackActionTimer <= 5)
        {
            spriteNumber = 1;
        }
        else if (attackActionTimer <= 25)
        {
            spriteNumber = 2;
            getOptionalMonsterCollidingWithAttack().ifPresent(monster -> damageMonster(monster, attackValue));
        }
        else
        {
            spriteNumber = 1;
            attackActionTimer = 0;
            isAttacking = false;
        }
    }

    public void damageMonster(Monster monster, int damage)
    {
        if (!monster.isInvincible)
        {
            monster.takeDamage(damage);
            if (monster.isDying)
            {
                addMessage(String.format("%d exp gained from killing %s", monster.exp, monster.name));
                collectExpAndCheckForLevelingUp(monster.exp);
            }
        }
    }

    private Optional<Monster> getOptionalMonsterCollidingWithAttack()
    {
        //save current worldX, worldY, solidArea
        int worldXBeforeAttack = worldX;
        int worldYBeforeAttack = worldY;
        int solidAreaWidthBeforeAttack = solidArea.width;
        int solidAreaHeightBeforeAttack = solidArea.height;

        adjustPlayerCoordinatesForAttackArea();
        solidArea.setSize(currentWeapon.attackArea.width, currentWeapon.attackArea.height);

        Optional<Monster> monster = gp.monsters.stream()
                .filter(m -> gp.collisionChecker.isLiveEntityColliding(this, m))
                .findFirst();

        //restore original position
        setWorldLocation(worldXBeforeAttack, worldYBeforeAttack);
        solidArea.setSize(solidAreaWidthBeforeAttack, solidAreaHeightBeforeAttack);

        return monster;
    }

    private void adjustPlayerCoordinatesForAttackArea()
    {
        switch (direction)
        {
            case UP -> worldY -= currentWeapon.attackArea.height;
            case DOWN -> worldY += currentWeapon.attackArea.height;
            case LEFT -> worldX -= currentWeapon.attackArea.width;
            case RIGHT -> worldX += currentWeapon.attackArea.width;
        }
    }

    private BufferedImage changeAttackingSprite(List<AttackingSprite> attackingSprites)
    {
        if (spriteNumber == 1)
        {
            return attackingSprites.get(0).getImage();
        }
        return attackingSprites.get(1).getImage();
    }

    public void increaseEnergy(int value)
    {
        energy = Math.min(energy + value, maxEnergy);
    }

    public void decreaseEnergy(int value)
    {
        energy = Math.max(energy - value, 0);
    }
}
