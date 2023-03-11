package entities.Entity;

import utilities.drawers.DialogueWindowDrawer;
import utilities.keyboard.KeyboardHandler;
import View.GamePanel;
import entities.Objects.Shield;
import entities.Objects.Sword;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

import static utilities.GameState.DIALOGUE_STATE;
import static utilities.drawers.DialogueWindowDrawer.*;
import static utilities.drawers.MessageDrawer.addMessage;
import static utilities.images.ImageUtils.setupDefaultImage;
import static utilities.images.ImageUtils.setupImage;
import static utilities.sound.Sound.*;

public class Player extends Entity
{
    KeyboardHandler keyboardHandler;
    public final int screenX;
    public final int screenY;
    public int keyCount;

    public Player(GamePanel gamePanel, KeyboardHandler keyboardHandler)
    {
        super(gamePanel);
        this.keyboardHandler = keyboardHandler;

        screenX = gamePanel.screenWidth / 2 - (gamePanel.tileSize / 2);
        screenY = gamePanel.screenHeight / 2 - (gamePanel.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 30;
        solidArea.height = 30;

        attackArea.width = 36;
        attackArea.height = 36;

        setDefaultValues();
        setPlayerImages();
        setPlayerAttackImages();
    }

    public int getAttack()
    {
        return attack = strength * currentWeapon.attackValue;
    }

    public int getDefence()
    {
        return defense = agility * currentShield.defenseValue;
    }

    public void setDefaultValues()
    {
        worldX = gp.tileSize * 28;
        worldY = gp.tileSize * 28;
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
        currentWeapon = new Sword(gp);
        currentShield = new Shield(gp);
        attack = getAttack();
        defense = getDefence();
    }

    public void setPlayerImages()
    {
        upSprites.add(setupDefaultImage("/player/up/up0.png"));
        upSprites.add(setupDefaultImage("/player/up/up1.png"));
        upSprites.add(setupDefaultImage("/player/up/up2.png"));
        upSprites.add(setupDefaultImage("/player/up/up3.png"));
        upSprites.add(setupDefaultImage("/player/up/up4.png"));
        upSprites.add(setupDefaultImage("/player/up/up5.png"));

        downSprites.add(setupDefaultImage("/player/down/down0.png"));
        downSprites.add(setupDefaultImage("/player/down/down1.png"));
        downSprites.add(setupDefaultImage("/player/down/down2.png"));
        downSprites.add(setupDefaultImage("/player/down/down3.png"));
        downSprites.add(setupDefaultImage("/player/down/down4.png"));
        downSprites.add(setupDefaultImage("/player/down/down5.png"));

        leftSprites.add(setupDefaultImage("/player/left/left0.png"));
        leftSprites.add(setupDefaultImage("/player/left/left1.png"));
        leftSprites.add(setupDefaultImage("/player/left/left2.png"));
        leftSprites.add(setupDefaultImage("/player/left/left3.png"));
        leftSprites.add(setupDefaultImage("/player/left/left4.png"));
        leftSprites.add(setupDefaultImage("/player/left/left5.png"));

        rightSprites.add(setupDefaultImage("/player/right/right0.png"));
        rightSprites.add(setupDefaultImage("/player/right/right1.png"));
        rightSprites.add(setupDefaultImage("/player/right/right2.png"));
        rightSprites.add(setupDefaultImage("/player/right/right3.png"));
        rightSprites.add(setupDefaultImage("/player/right/right4.png"));
        rightSprites.add(setupDefaultImage("/player/right/right5.png"));

        thumbUp = setupDefaultImage("/player/thumbUp.png");
    }

    public void setPlayerAttackImages()
    {
        upAttackSprites.add(setupImage("/player/attack/up1.png", gp.tileSize, gp.tileSize * 2));
        upAttackSprites.add(setupImage("/player/attack/up2.png", gp.tileSize, gp.tileSize * 2));
        downAttackSprites.add(setupImage("/player/attack/down1.png", gp.tileSize, gp.tileSize * 2));
        downAttackSprites.add(setupImage("/player/attack/down2.png", gp.tileSize, gp.tileSize * 2));
        leftAttackSprites.add(setupImage("/player/attack/left1.png", gp.tileSize * 2, gp.tileSize));
        leftAttackSprites.add(setupImage("/player/attack/left2.png", gp.tileSize * 2, gp.tileSize));
        rightAttackSprites.add(setupImage("/player/attack/right1.png", gp.tileSize * 2, gp.tileSize));
        rightAttackSprites.add(setupImage("/player/attack/right2.png", gp.tileSize * 2, gp.tileSize));
    }

    public void update()
    {
        if (attacking)
        {
            attacking();
        } else if (keyboardHandler.upPressed || keyboardHandler.downPressed
                || keyboardHandler.leftPressed || keyboardHandler.rightPressed
                || keyboardHandler.thumbUpPressed || keyboardHandler.ePressed)
        {

            if (keyboardHandler.upPressed)
            {
                direction = "up";
            } else if (keyboardHandler.downPressed)
            {
                direction = "down";
            } else if (keyboardHandler.leftPressed)
            {
                direction = "left";
            } else if (keyboardHandler.rightPressed)
            {
                direction = "right";
            } else if (keyboardHandler.thumbUpPressed)
            {
                direction = "thumbUp";
            }

            collisionOn = false;
            gp.collisionChecker.checkTile(this);

            //index == -1 -> false (no interaction)
            //index == 0+ -> true (interaction and the index of entity to interact with)
            int objectIndex = gp.collisionChecker.checkObjectsForCollisions(this, gp.objects, true);
            if (objectIndex != -1)
            {
                pickUpObject(objectIndex);
            }

            int npcIndex = gp.collisionChecker.checkEntitiesForCollision(this, gp.npcs);
            if (npcIndex != -1)
            {
                interactNpc(npcIndex);
            }

            int monsterIndex = gp.collisionChecker.checkEntitiesForCollision(this, gp.monsters);
            if (monsterIndex != -1)
            {
                contactMonster(monsterIndex);
            }

            if (!collisionOn && !keyboardHandler.ePressed)
            {
                switch (direction)
                {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
            }
            keyboardHandler.ePressed = false;
            spriteNumberChanger(upSprites.size(), 5);
        } else
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
                image = changeSprite(image, upSprites, upAttackSprites, spriteNumber);
                if (attacking)
                {
                    tempScreenY -= gp.tileSize;
                }
            }
            case "down" -> image = changeSprite(image, downSprites, downAttackSprites, spriteNumber);
            case "left" ->
            {
                image = changeSprite(image, leftSprites, leftAttackSprites, spriteNumber);
                if (attacking)
                {
                    tempScreenX -= gp.tileSize;
                }
            }
            case "right" -> image = changeSprite(image, rightSprites, rightAttackSprites, spriteNumber);
            case "thumbUp" -> image = thumbUp;
        }

        int x = Math.min(tempScreenX, worldX);
        int y = Math.min(tempScreenY, worldY);

        if (invincible)
        {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        }

        g2.drawImage(image, x, y, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

    public void pickUpObject(int i)
    {
        String objectName = gp.objects.get(i).name;
        switch (objectName)
        {
            case "Monkey" ->
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
            case "Key" ->
            {
                gp.playSoundEffect(COIN);
                keyCount++;
                gp.objects.remove(i);
                addMessage("You got a key!");
            }
            case "Door" ->
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
            case "Boots" ->
            {
                gp.playSoundEffect(POWER_UP);
                speed += 2;
                gp.objects.remove(i);
                addMessage("You got boots!");
            }
            case "Chest" ->
            {
                gp.ui.gameFinished = true;
                gp.stopMusic();
                gp.playSoundEffect(WIN);
            }
        }
    }

    public void interactNpc(int i)
    {
        if (keyboardHandler.ePressed)
        {
            gp.setGameState(DIALOGUE_STATE);
            gp.npcs.get(i).speak();
        }
        keyboardHandler.ePressed = false;
    }

    public void contactMonster(int i)
    {
        if (!invincible)
        {
            if (gp.monsters.get(i).type == 2)
            {
                gp.playSoundEffect(RECEIVE_DAMAGE);
                life -= takeDamage(i);
                invincible = true;
            }
            else if (gp.monsters.get(i).type == 3)
            {
                gp.playSoundEffect(RECEIVE_DAMAGE);
                life -= takeDamage(i);
                invincible = true;
            }
        }
    }

    private int takeDamage(int i)
    {
        int damage = gp.monsters.get(i).attack - defense;
        if (damage < 0)
        {
            damage = 0;
        }
        return damage;
    }

    public void damageMonster(int i)
    {
        if (!gp.monsters.get(i).invincible)
        {
            gp.playSoundEffect(HIT);

            int damage = causeDamage(i);
            gp.monsters.get(i).life -= damage;

            addMessage(damage + " damage!");

            gp.monsters.get(i).invincible = true;

            gp.monsters.get(i).damageReaction();
            if (gp.monsters.get(i).life <= 0)
            {
                gp.monsters.get(i).dying = true;
                addMessage(gp.monsters.get(i).name + " killed!");
                addMessage(gp.monsters.get(i).exp + " exp  gained!");
                exp += gp.monsters.get(i).exp;
                checkLevelUp();
            }
        }
    }

    public void checkLevelUp()
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
            attack = getAttack();
            defense = getDefence();
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

    public void attacking()
    {
        if (attacking)
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
                attacking = false;
            }
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

        int monsterIndex = gp.collisionChecker.checkEntitiesForCollision(this, gp.monsters);
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

    public BufferedImage changeSprite(BufferedImage image, List<BufferedImage> sprites, List<BufferedImage> attackingSprites, int spriteNumber)
    {
        if (!attacking)
        {
            for (int i = 0; i < sprites.size(); i++)
            {
                if (spriteNumber == i)
                {
                    image = sprites.get(spriteNumber);
                    break;
                }
            }
        } else
        {
            if (spriteNumber == 1)
            {
                image = attackingSprites.get(0);
            }
            if (spriteNumber == 2)
            {
                image = attackingSprites.get(1);
            }
        }
        return image;
    }
}
