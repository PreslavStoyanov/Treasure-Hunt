package assets.entities.movingentities.liveentities;

import application.GamePanel;
import assets.EntityType;
import assets.entities.Object;
import assets.entities.movingentities.AliveEntity;
import assets.entities.movingentities.liveentities.artificials.Monster;
import assets.entities.movingentities.liveentities.artificials.Npc;
import assets.entities.movingentities.projectiles.Fireball;
import assets.entities.movingentities.sprites.AttackingSprite;
import assets.entities.objects.UsableObject;
import assets.entities.objects.usableobjects.DefenseObject;
import assets.entities.objects.usableobjects.Weapon;
import assets.entities.objects.usableobjects.defenseobjects.Shield;
import assets.entities.objects.usableobjects.weapons.Sword;
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
    public List<UsableObject> inventory = new ArrayList<>();
    public int inventoryCapacity;
    public Weapon currentWeapon;
    public DefenseObject currentShield;
    public int coins;
    public int level;
    public int strength;
    public int agility;
    public boolean isAttacking;
    public int attackActionTimer = 0;
    public int energy = 0;
    public int maxExp = 5;
    public final int screenX;
    public final int screenY;


    public Player(GamePanel gp, KeyboardHandler keyboardHandler)
    {
        super(gp);
        this.keyboardHandler = keyboardHandler;
        screenX = screenWidth / 2 - halfTileSize;
        screenY = screenHeight / 2 - halfTileSize;
        setSolidAreaAndDefaultLocation(8, 16, 30, 30);
        setDefaultValues();
        this.sprites = setSprites("src/main/resources/player/player_sprites.yaml");
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

    public void setDefaultValues()
    {
        this.setWorldLocation(tileSize * 28, tileSize * 28);
        this.movingSpeed = 4;
        this.level = 1;
        this.maxLife = 6;
        this.life = maxLife;
        this.strength = 1;
        this.agility = 1;
        this.exp = 0;
        this.coins = 0;
        this.type = PLAYER;
        this.isAttacking = false;
        this.currentWeapon = new Sword(gp);
        this.currentShield = new Shield(gp);
        this.projectile = new Fireball(gp);
        this.inventory.add(currentShield);
        this.inventory.add(currentWeapon);
        this.inventoryCapacity = 20;
        this.attackValue = calculateAttack();
        this.defense = calculateDefense();
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
                && energy == projectile.castEnergyNeeded)
        {
            shootProjectile();
            energy = 0;
        }
        else if (keyboardHandler.playScreenKeyboardHandler.isEnergyButtonPressed)
        {
            energy = Math.min(++energy, projectile.castEnergyNeeded);
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
                .findFirst().ifPresent(this::interactWithObject);

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

    private void interactWithObject(Object object)
    {
        EntityType objectType = object.type;
        if (!OBJECT_TYPES.contains(objectType))
        {
            return;
        }

        if (USABLE_OBJECTS.contains(objectType))
        {
            if (inventory.size() == inventoryCapacity)
            {
                addMessage("Inventory is full!");
                return;
            }

            collectObject((UsableObject) object);
        }
        else if (STATIC_OBJECTS.contains(objectType))
        {
            interactWithStaticObject(object);
        }
    }

    private void collectObject(UsableObject object)
    {
        inventory.add(object);
        gp.objects.remove(object);
        addMessage(String.format("You got %s", object.name));
        switch (object.type)
        {
            case AXE -> gp.soundHandler.playSoundEffect(TAKE_AXE);
            case HEALTH_POTION -> gp.soundHandler.playSoundEffect(TAKE_POTION);
            case KEY -> gp.soundHandler.playSoundEffect(TAKE_KEY);
            case BOOTS -> interactWithBoots();
        }
    }

    private void interactWithStaticObject(Object object)
    {
        switch (object.type)
        {
            case DOOR -> interactWithDoor(object);
            case MONKEY -> interactWithMonkey(object);
            case CHEST -> interactWithChest();
        }
    }

    private void interactWithBoots()
    {
        gp.soundHandler.playSoundEffect(POWER_UP);
        movingSpeed += 2;
    }

    private void interactWithDoor(Object door)
    {
        Optional<UsableObject> keyToRemove = gp.player.inventory.stream()
                .filter(obj -> obj.type.equals(KEY))
                .findFirst();

        if (keyToRemove.isPresent())
        {
            gp.player.inventory.remove(keyToRemove.get());
            gp.soundHandler.playSoundEffect(OPEN_DOOR);
            gp.objects.remove(door);
            addMessage("Door opened!");
        }
        else
        {
            addMessage("You need a key!");
        }
    }

    private void interactWithMonkey(Object monkey)
    {
        Optional<UsableObject> keyToRemove = gp.player.inventory.stream()
                .filter(obj -> obj.type.equals(KEY))
                .findFirst();

        if (keyToRemove.isPresent())
        {
            gp.player.inventory.remove(keyToRemove.get());
            gp.objects.remove(monkey);
            gp.soundHandler.playSoundEffect(MONKEY_LAUGH);
            addMessage("The monkey robbed you and ran out!");
        }
        else
        {
            addMessage("You have nothing for me!");
        }
    }

    private void interactWithChest()
    {
        gp.soundHandler.stop();
        gp.soundHandler.playSoundEffect(WIN_SOUND);
        gp.setGameState(GAME_WIN_STATE);
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
}
