package entities.types;

import View.GamePanel;
import entities.Entity;
import entities.objects.Shield;
import entities.objects.Sword;
import entities.sprites.AttackingSprite;
import entities.sprites.WalkingSprite;
import utilities.keyboard.KeyboardHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

import static View.GamePanel.*;
import static entities.types.EntityType.*;
import static utilities.GameState.DIALOGUE_STATE;
import static utilities.GameState.END_STATE;
import static utilities.drawers.DialogueWindowDrawer.currentDialogue;
import static utilities.drawers.MessageDrawer.addMessage;
import static utilities.images.ImageUtils.setupDefaultImage;
import static utilities.sound.Sound.*;

public class Player extends Entity
{
    private final KeyboardHandler keyboardHandler;
    private int keyCount;
    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyboardHandler keyboardHandler)
    {
        super(gp);
        this.keyboardHandler = keyboardHandler;

        screenX = screenWidth / 2 - (tileSize / 2);
        screenY = screenHeight / 2 - (tileSize / 2);

        solidArea = new Rectangle(8, 16, 30, 30);
        solidAreaDefaultX = 8;
        solidAreaDefaultY = 16;

        attackArea.width = 36;
        attackArea.height = 36;

        setDefaultValues();
        this.sprites = setSprites("src/main/resources/player_sprites.yaml");
    }

    public int calculateAttack()
    {
        return attack = strength * currentWeapon.attackValue;
    }

    public int calculateDefense()
    {
        return defense = agility * currentShield.defenseValue;
    }

    public void setDefaultValues()
    {
        thumbUp = setupDefaultImage("/player/thumbUp.png");
        worldX = tileSize * 28;
        worldY = tileSize * 28;
        speed = 4;
        keyCount = 0;
        direction = "thumbUp";
        level = 1;
        maxLife = 6;
        life = maxLife;
        strength = 1;
        agility = 1;
        exp = 0;
        nextLevelExp = 5;
        coins = 0;
        type = PLAYER;
        currentWeapon = new Sword(gp);
        currentShield = new Shield(gp);
        attack = calculateAttack();
        defense = calculateDefense();
    }

    public void update()
    {
        if (isAttacking)
        {
            attack();
        }
        else if (keyboardHandler.isWPressed || keyboardHandler.isSPressed
                || keyboardHandler.isAPressed || keyboardHandler.isDPressed
                || keyboardHandler.isQPressed || keyboardHandler.isEPressed)
        {
            setDirection();
            hasCollision = gp.collisionChecker.isCollisionTile(this);
            interactWithEntities();
            if (!hasCollision && !keyboardHandler.isEPressed)
            {
                handleMoving();
            }

            keyboardHandler.isEPressed = false;
            changeSpriteNumber(sprites.getWalkingUpSprites().size(), 5);
        }
        else
        {
            spriteNumber = 1;
        }
        setInvincibleTime(60);
    }

    public void draw(Graphics2D g2)
    {
        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch (direction)
        {
            case "up" ->
            {
                image = changeSprite(sprites.getWalkingUpSprites(), sprites.getAttackingUpSprites(), spriteNumber);
                if (isAttacking)
                {
                    tempScreenY -= tileSize;
                }
            }
            case "down" -> image = changeSprite(sprites.getWalkingDownSprites(), sprites.getAttackingDownSprites(), spriteNumber);
            case "left" ->
            {
                image = changeSprite(sprites.getWalkingLeftSprites(), sprites.getAttackingLeftSprites(), spriteNumber);
                if (isAttacking)
                {
                    tempScreenX -= tileSize;
                }
            }
            case "right" -> image = changeSprite(sprites.getWalkingRightSprites(), sprites.getAttackingRightSprites(), spriteNumber);
            case "thumbUp" -> image = thumbUp;
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
        int objectIndex = gp.collisionChecker.checkObjectsForCollisions(this, gp.objects);
        if (objectIndex != -1)
        {
            pickUpObject(objectIndex);
        }

        int npcIndex = gp.collisionChecker.checkLiveAssetsForCollision(this, gp.npcs);
        if (npcIndex != -1)
        {
            interactNpc(npcIndex);
        }

        int monsterIndex = gp.collisionChecker.checkLiveAssetsForCollision(this, gp.monsters);
        if (monsterIndex != -1)
        {
            contactMonster(monsterIndex);
        }
    }

    private void handleMoving()
    {
        switch (direction)
        {
            case "up" -> worldY -= speed;
            case "down" -> worldY += speed;
            case "left" -> worldX -= speed;
            case "right" -> worldX += speed;
        }
    }

    private void setDirection()
    {
        if (keyboardHandler.isWPressed)
        {
            direction = "up";
        }
        else if (keyboardHandler.isSPressed)
        {
            direction = "down";
        }
        else if (keyboardHandler.isAPressed)
        {
            direction = "left";
        }
        else if (keyboardHandler.isDPressed)
        {
            direction = "right";
        }
        else if (keyboardHandler.isQPressed)
        {
            direction = "thumbUp";
        }
    }

    private void pickUpObject(int i)
    {
        EntityType entityType = gp.objects.get(i).type;
        if (objectTypes.contains(entityType))
        {
            switch (entityType)
            {
                case MONKEY -> interactWithMonkey(i);
                case KEY -> interactWithKey(i);
                case DOOR -> interactWithDoor(i);
                case BOOTS -> interactWithBoots(i);
                case CHEST -> interactWithChest();
            }
        }
    }

    private void interactWithChest()
    {
        gp.setGameState(END_STATE);
        gp.stopMusic();
        gp.playSoundEffect(WIN);
    }

    private void interactWithBoots(int i)
    {
        gp.playSoundEffect(POWER_UP);
        speed += 2;
        gp.objects.remove(i);
        addMessage("You got boots!");
    }

    private void interactWithDoor(int i)
    {
        if (keyCount > 0)
        {
            gp.playSoundEffect(UNLOCK);
            keyCount--;
            gp.objects.remove(i);
            addMessage("Door opened!");
        }
        else
        {
            addMessage("You need a key!");
        }
    }

    private void interactWithKey(int i)
    {
        gp.playSoundEffect(COIN);
        keyCount++;
        gp.objects.remove(i);
        addMessage("You got a key!");
    }

    private void interactWithMonkey(int i)
    {
        if (keyCount == 0)
        {
            addMessage("You have nothing for me!");
        }
        else
        {
            keyCount--;
            gp.objects.remove(i);
            addMessage("The monkey robbed you and ran out!");
        }
    }

    private void interactNpc(int i)
    {
        if (keyboardHandler.isEPressed)
        {
            gp.setGameState(DIALOGUE_STATE);
            gp.npcs.get(i).speak();
        }
        keyboardHandler.isEPressed = false;
    }

    private void contactMonster(int i)
    {
        if (isInvincible)
        {
            return;
        }
        if (monstersTypes.contains(gp.monsters.get(i).type))
        {
            gp.playSoundEffect(RECEIVE_DAMAGE);
            life -= calculateDamage(i);
            isInvincible = true;
        }
    }

    private int calculateDamage(int i)
    {
        int damage = gp.monsters.get(i).attack - defense;
        return Math.max(damage, 0);
    }

    private void damageMonster(int i)
    {
        if (!gp.monsters.get(i).isInvincible)
        {
            gp.playSoundEffect(HIT);

            int damage = causeDamage(i);
            gp.monsters.get(i).life -= damage;

            addMessage(damage + " damage!");

            gp.monsters.get(i).isInvincible = true;

            gp.monsters.get(i).damageReaction();
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
            gp.playSoundEffect(LEVEL_UP);
            gp.setGameState(DIALOGUE_STATE);
            currentDialogue = "You are level " + level + " now!\n" + "You feel stronger!";
        }
    }

    private int causeDamage(int i)
    {
        int damage = attack - gp.monsters.get(i).defense;
        if (damage < 0)
        {
            damage = 0;
        }
        return damage;
    }

    private void attack()
    {
        spriteCounter++;
        if (spriteCounter == 2)
        {
            gp.playSoundEffect(SWING_WEAPON);
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
        int currentWorldX = worldX;
        int currentWorldY = worldY;
        int solidAreaWidth = solidArea.width;
        int solidAreaHeight = solidArea.height;

        adjustPlayerCoordinatesForAttackArea();

        setSolidAreaToAttackArea();

        int monsterIndex = gp.collisionChecker.checkLiveAssetsForCollision(this, gp.monsters);
        if (monsterIndex != -1)
        {
            damageMonster(monsterIndex);
        }

        restoreOriginalPosition(currentWorldX, currentWorldY, solidAreaWidth, solidAreaHeight);
    }

    private void setSolidAreaToAttackArea()
    {
        solidArea.width = attackArea.width;
        solidArea.height = attackArea.height;
    }

    private void adjustPlayerCoordinatesForAttackArea()
    {
        switch (direction)
        {
            case "up" -> worldY -= attackArea.height;
            case "down" -> worldY += attackArea.height;
            case "left" -> worldX -= attackArea.width;
            case "right" -> worldX += attackArea.width;
        }
    }

    private void restoreOriginalPosition(int currentWorldX, int currentWorldY,
                                         int solidAreaWidth, int solidAreaHeight)
    {
        worldX = currentWorldX;
        worldY = currentWorldY;
        solidArea.width = solidAreaWidth;
        solidArea.height = solidAreaHeight;
    }

    private BufferedImage changeSprite(List<WalkingSprite> walkingSprites,
                                      List<AttackingSprite> attackingSprites,
                                      int spriteNumber)
    {
        if (isAttacking)
        {
            if (spriteNumber == 1)
            {
                return attackingSprites.get(0).getImage();
            }
            return attackingSprites.get(1).getImage();
        }

        for (int i = 0; i < walkingSprites.size(); i++)
        {
            if (spriteNumber == i)
            {
                return walkingSprites.get(spriteNumber).getImage();
            }
        }
        return walkingSprites.get(0).getImage();
    }
}
