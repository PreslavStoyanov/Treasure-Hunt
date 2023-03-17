package assets.entities.liveentities;

import application.GamePanel;
import assets.EntityType;
import assets.entities.LiveEntity;
import assets.entities.Object;
import assets.entities.objects.DefenseObject;
import assets.entities.objects.Weapon;
import assets.entities.objects.defenseobjects.Shield;
import assets.entities.objects.weapons.Sword;
import assets.entities.sprites.AttackingSprite;
import utilities.keyboard.KeyboardHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static application.GamePanel.*;
import static assets.EntityType.*;
import static assets.entities.LiveEntity.Direction.*;
import static utilities.GameState.*;
import static utilities.drawers.DialogueWindowDrawer.currentDialogue;
import static utilities.drawers.InventoryWindowDrawer.*;
import static utilities.drawers.MessageDrawer.addMessage;
import static utilities.images.ImageUtils.setupDefaultImage;
import static utilities.sound.Sound.*;

public class Player extends LiveEntity
{
    private final KeyboardHandler keyboardHandler;
    private int keyCount;
    public List<Object> inventory = new ArrayList<>();
    public int inventoryCapacity;
    public Weapon currentWeapon;
    public DefenseObject currentShield;
    public int coins;
    public int nextLevelExp;
    public int level;
    public int strength;
    public int agility;
    public boolean isAttacking;
    public final int screenX;
    public final int screenY;


    public Player(GamePanel gp, KeyboardHandler keyboardHandler)
    {
        super(gp);
        this.keyboardHandler = keyboardHandler;

        screenX = screenWidth / 2 - halfTileSize;
        screenY = screenHeight / 2 - halfTileSize;

        solidArea = new Rectangle(8, 16, 30, 30);
        solidAreaDefaultX = 8;
        solidAreaDefaultY = 16;

        setDefaultValues();
        this.sprites = setSprites("src/main/resources/player_sprites.yaml");
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
        thumbUp = setupDefaultImage("/player/thumbUp.png");
        this.worldX = tileSize * 28;
        this.worldY = tileSize * 28;
        this.speed = 4;
        this.keyCount = 0;
        this.direction = DOWN;
        this.level = 1;
        this.maxLife = 6;
        this.life = maxLife;
        this.strength = 1;
        this.agility = 1;
        this.exp = 0;
        this.nextLevelExp = 5;
        this.coins = 0;
        this.type = PLAYER;
        this.isAttacking = false;
        this.currentWeapon = new Sword(gp);
        this.currentShield = new Shield(gp);
        this.inventory.add(currentShield);
        this.inventory.add(currentWeapon);
        this.inventoryCapacity = 20;
        this.attack = calculateAttack();
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
            setDirection();
            hasCollision = gp.collisionChecker.isTileColliding(this);
            interactWithEntities();

            if (!hasCollision)
            {
                handleMoving();
            }

            keyboardHandler.isEPressed = false;
            changeSpriteNumber(sprites.getWalkingUpSprites().size(), 5);
        }
        setInvincibleTime(60);
        if (life <= 0)
        {
            gp.setGameState(GAME_LOSE_STATE);
        }
    }

    private boolean isActionButtonPressed()
    {
        return keyboardHandler.isWPressed || keyboardHandler.isSPressed
                || keyboardHandler.isAPressed || keyboardHandler.isDPressed
                || keyboardHandler.isQPressed || keyboardHandler.isEPressed;
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
                case DOWN -> image = changeAttackingSprite(sprites.getAttackingSprites().get(currentWeapon.type).getDownSprites());
                case LEFT ->
                {
                    image = changeAttackingSprite(sprites.getAttackingSprites().get(currentWeapon.type).getLeftSprites());
                    tempScreenX -= tileSize;
                }
                case RIGHT -> image = changeAttackingSprite(sprites.getAttackingSprites().get(currentWeapon.type).getRightSprites());
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

    private void interactWithEntities()
    {
        //index == -1 == false (no interaction)
        //index == 0+ == true (has interaction and the index of entity to interact with)
        int objectIndex = gp.collisionChecker.areObjectsColliding(this, gp.objects);
        if (objectIndex != -1)
        {
            interactWithObject(gp.objects.get(objectIndex));
        }

        int npcIndex = gp.collisionChecker.areLiveEntitiesColliding(this, gp.npcs);
        if (npcIndex != -1 && keyboardHandler.isEPressed)
        {
            interactWithNpc(gp.npcs.get(npcIndex));
        }

        int monsterIndex = gp.collisionChecker.areLiveEntitiesColliding(this, gp.monsters);
        if (monsterIndex != -1)
        {
            contactMonster(gp.monsters.get(monsterIndex));
        }
    }


    private void setDirection()
    {
        if (keyboardHandler.isWPressed)
        {
            direction = UP;
        }
        else if (keyboardHandler.isSPressed)
        {
            direction = DOWN;
        }
        else if (keyboardHandler.isAPressed)
        {
            direction = LEFT;
        }
        else if (keyboardHandler.isDPressed)
        {
            direction = RIGHT;
        }
    }

    private void interactWithObject(Object object)
    {
        EntityType objectType = object.type;
        if (!objectTypes.contains(objectType))
        {
            return;
        }

        if (collectableObjects.contains(objectType))
        {
            if (inventory.size() == inventoryCapacity)
            {
                addMessage("Inventory is full!");
                return;
            }

            collectObject(object);
        }
        else if (staticObjects.contains(objectType))
        {
            interactWithStaticObject(object, objectType);
        }
    }

    private void interactWithStaticObject(Object object, EntityType objectType)
    {
        switch (objectType)
        {
            case DOOR -> interactWithDoor(object);
            case MONKEY -> interactWithMonkey(object);
            case CHEST -> interactWithChest();
        }
    }

    private void collectObject(Object object)
    {
        switch (object.type)
        {
            case AXE -> interactWithAxe(object);
            case KEY -> interactWithKey(object);
            case BOOTS -> interactWithBoots(object);
        }
    }

    private void interactWithAxe(Object object)
    {
        inventory.add(object);
        gp.objects.remove(object);
        addMessage("You got an axe!");
    }

    private void interactWithChest()
    {
        gp.setGameState(GAME_WIN_STATE);
        gp.soundHandler.stop();
        gp.soundHandler.playSoundEffect(WIN);
    }

    private void interactWithBoots(Object object)
    {
        gp.soundHandler.playSoundEffect(POWER_UP);
        inventory.add(object);
        speed += 2;
        gp.objects.remove(object);
        addMessage("You got boots!");
    }

    private void interactWithDoor(Object object)
    {
        if (keyCount > 0)
        {
            gp.soundHandler.playSoundEffect(UNLOCK);
            keyCount--;
            gp.objects.remove(object);
            addMessage("Door opened!");
        }
        else
        {
            addMessage("You need a key!");
        }
    }

    private void interactWithKey(Object object)
    {
        gp.soundHandler.playSoundEffect(COIN);
        inventory.add(object);
        keyCount++;
        gp.objects.remove(object);
        addMessage("You got a key!");
    }

    private void interactWithMonkey(Object object)
    {
        if (keyCount == 0)
        {
            addMessage("You have nothing for me!");
        }
        else
        {
            keyCount--;
            gp.objects.remove(object);
            addMessage("The monkey robbed you and ran out!");
        }
    }

    private void interactWithNpc(Npc npc)
    {
        gp.setGameState(DIALOGUE_STATE);
        npc.speak();
        keyboardHandler.isEPressed = false;
    }

    private void contactMonster(Monster monster)
    {
        if (isInvincible)
        {
            return;
        }
        if (monstersTypes.contains(monster.type))
        {
            gp.soundHandler.playSoundEffect(RECEIVE_DAMAGE);
            life -= calculateDamage(monster.attack);
            isInvincible = true;
        }
    }

    private int calculateDamage(int monsterAttack)
    {
        int damage = monsterAttack - defense;
        return Math.max(damage, 0);
    }

    private void damageMonster(int i)
    {
        if (!gp.monsters.get(i).isInvincible)
        {
            gp.soundHandler.playSoundEffect(HIT);

            gp.monsters.get(i).life -= Math.max(attack - gp.monsters.get(i).defense, 0);

            gp.monsters.get(i).isInvincible = true;

            gp.monsters.get(i).reactToDamage();
            if (gp.monsters.get(i).life <= 0)
            {
                gp.monsters.get(i).isDying = true;
                addMessage(gp.monsters.get(i).name + " killed!");
                addMessage(gp.monsters.get(i).exp + " exp  gained!");
                exp += gp.monsters.get(i).exp;
                checkLevelUp();
            }
        }
    }

    private void checkLevelUp()
    {
        if (exp >= nextLevelExp)
        {
            level++;
            nextLevelExp += 5;
            maxLife += 2;
            life += 2;
            if (maxLife >= 12)
            {
                maxLife = 12;
                if (life >= 12)
                {
                    life = 12;
                }
            }

            strength++;
            agility++;
            attack = calculateAttack();
            defense = calculateDefense();
            gp.soundHandler.playSoundEffect(LEVEL_UP);
            gp.setGameState(DIALOGUE_STATE);
            currentDialogue = "You are level " + level + " now!\n" + "You feel stronger!";
        }
    }

    private void attack()
    {
        spriteCounter++;
        if (spriteCounter == 2)
        {
            switch (currentWeapon.type)
            {
                case SWORD -> gp.soundHandler.playSoundEffect(SWING_SWORD);
                case AXE -> gp.soundHandler.playSoundEffect(SWING_AXE);
            }
        }
        if (spriteCounter <= 5)
        {
            spriteNumber = 1;
        }
        if (spriteCounter > 5 && spriteCounter <= 25)
        {
            spriteNumber = 2;
            checkAttackMonsterCollision();
        }
        if (spriteCounter > 25)
        {
            spriteNumber = 1;
            spriteCounter = 0;
            isAttacking = false;
        }

    }

    private void checkAttackMonsterCollision()
    {
        //save current worldX, worldY, solidArea
        int worldXBeforeAttack = worldX;
        int worldYBeforeAttack = worldY;
        int solidAreaWidthBeforeAttack = solidArea.width;
        int solidAreaHeightBeforeAttack = solidArea.height;

        adjustPlayerCoordinatesForAttackArea();
        solidArea.setSize(currentWeapon.attackArea.width, currentWeapon.attackArea.height);

        int monsterIndex = gp.collisionChecker.areLiveEntitiesColliding(this, gp.monsters);
        if (monsterIndex != -1)
        {
            damageMonster(monsterIndex);
        }

        //restore original position
        setWorldLocation(worldXBeforeAttack, worldYBeforeAttack);
        solidArea.setSize(solidAreaWidthBeforeAttack, solidAreaHeightBeforeAttack);
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
