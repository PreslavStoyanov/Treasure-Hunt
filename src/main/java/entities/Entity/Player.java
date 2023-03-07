package entities.Entity;

import utilities.keboard.KeyboardHandler;
import View.GamePanel;
import entities.Objects.Shield;
import entities.Objects.Sword;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

import static utilities.GameState.DIALOGUE_STATE;

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
        getPlayerImage();
        getPlayerAttackImages();
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

    public void getPlayerImage()
    {
        upSprites.add(setup("/player/up/up0.png", gp.tileSize, gp.tileSize));
        upSprites.add(setup("/player/up/up1.png", gp.tileSize, gp.tileSize));
        upSprites.add(setup("/player/up/up2.png", gp.tileSize, gp.tileSize));
        upSprites.add(setup("/player/up/up3.png", gp.tileSize, gp.tileSize));
        upSprites.add(setup("/player/up/up4.png", gp.tileSize, gp.tileSize));
        upSprites.add(setup("/player/up/up5.png", gp.tileSize, gp.tileSize));

        downSprites.add(setup("/player/down/down0.png", gp.tileSize, gp.tileSize));
        downSprites.add(setup("/player/down/down1.png", gp.tileSize, gp.tileSize));
        downSprites.add(setup("/player/down/down2.png", gp.tileSize, gp.tileSize));
        downSprites.add(setup("/player/down/down3.png", gp.tileSize, gp.tileSize));
        downSprites.add(setup("/player/down/down4.png", gp.tileSize, gp.tileSize));
        downSprites.add(setup("/player/down/down5.png", gp.tileSize, gp.tileSize));

        leftSprites.add(setup("/player/left/left0.png", gp.tileSize, gp.tileSize));
        leftSprites.add(setup("/player/left/left1.png", gp.tileSize, gp.tileSize));
        leftSprites.add(setup("/player/left/left2.png", gp.tileSize, gp.tileSize));
        leftSprites.add(setup("/player/left/left3.png", gp.tileSize, gp.tileSize));
        leftSprites.add(setup("/player/left/left4.png", gp.tileSize, gp.tileSize));
        leftSprites.add(setup("/player/left/left5.png", gp.tileSize, gp.tileSize));

        rightSprites.add(setup("/player/right/right0.png", gp.tileSize, gp.tileSize));
        rightSprites.add(setup("/player/right/right1.png", gp.tileSize, gp.tileSize));
        rightSprites.add(setup("/player/right/right2.png", gp.tileSize, gp.tileSize));
        rightSprites.add(setup("/player/right/right3.png", gp.tileSize, gp.tileSize));
        rightSprites.add(setup("/player/right/right4.png", gp.tileSize, gp.tileSize));
        rightSprites.add(setup("/player/right/right5.png", gp.tileSize, gp.tileSize));

        thumbUp = setup("/player/thumbUp.png", gp.tileSize, gp.tileSize);
    }

    public void getPlayerAttackImages()
    {
        upAttackSprites.add(setup("/player/attack/up1.png", gp.tileSize, gp.tileSize * 2));
        upAttackSprites.add(setup("/player/attack/up2.png", gp.tileSize, gp.tileSize * 2));
        downAttackSprites.add(setup("/player/attack/down1.png", gp.tileSize, gp.tileSize * 2));
        downAttackSprites.add(setup("/player/attack/down2.png", gp.tileSize, gp.tileSize * 2));
        leftAttackSprites.add(setup("/player/attack/left1.png", gp.tileSize * 2, gp.tileSize));
        leftAttackSprites.add(setup("/player/attack/left2.png", gp.tileSize * 2, gp.tileSize));
        rightAttackSprites.add(setup("/player/attack/right1.png", gp.tileSize * 2, gp.tileSize));
        rightAttackSprites.add(setup("/player/attack/right2.png", gp.tileSize * 2, gp.tileSize));
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

            int objectIndex = gp.collisionChecker.checkObject(this, true);
            pickUpObject(objectIndex);

            int npcIndex = gp.collisionChecker.checkEntity(this, gp.npc);
            interactNpc(npcIndex);

            int monsterIndex = gp.collisionChecker.checkEntity(this, gp.monsters);
            contactMonster(monsterIndex);

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
        if (i != 999)
        {
            String objectName = gp.objects.get(i).name;
            switch (objectName)
            {
                case "Monkey" ->
                {
                    if (keyCount == 0)
                    {
                        gp.ui.addMessage("You have nothing for me!");
                    } else
                    {
                        keyCount--;
                        gp.objects.remove(i);
                        gp.ui.addMessage("The monkey robbed you and ran out!");
                    }
                }
                case "Key" ->
                {
                    gp.playSoundEffect(1);
                    keyCount++;
                    gp.objects.remove(i);
                    gp.ui.addMessage("You got a key!");
                }
                case "Door" ->
                {
                    if (keyCount > 0)
                    {
                        gp.playSoundEffect(3);
                        keyCount--;
                        gp.objects.remove(i);
                        gp.ui.addMessage("Door opened!");
                    } else
                    {
                        gp.ui.addMessage("You need a key!");
                    }
                }
                case "Boots" ->
                {
                    gp.playSoundEffect(2);
                    speed += 2;
                    gp.objects.remove(i);
                    gp.ui.addMessage("You got boots!");
                }
                case "Chest" ->
                {
                    gp.ui.gameFinished = true;
                    gp.stopMusic();
                    gp.playSoundEffect(4);
                }
            }

        }
    }

    public void interactNpc(int i)
    {
        if (i != 999)
        {
            if (keyboardHandler.ePressed)
            {
                gp.setGameState(DIALOGUE_STATE);
                gp.npc.get(i).speak();
            }
            keyboardHandler.ePressed = false;
        }
    }

    public void contactMonster(int i)
    {
        if (i != 999)
        {
            if (!invincible)
            {
                if (gp.monsters.get(i).type == 2)
                {
                    gp.playSoundEffect(6);
                    life -= takeDamage(i);
                    invincible = true;
                } else if (gp.monsters.get(i).type == 3)
                {
                    gp.playSoundEffect(6);
                    life -= takeDamage(i);
                    invincible = true;
                }
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
        if (i != 999)
        {
            if (!gp.monsters.get(i).invincible)
            {
                gp.playSoundEffect(5);

                int damage = causeDamage(i);
                gp.monsters.get(i).life -= damage;

                gp.ui.addMessage(damage + " damage!");

                gp.monsters.get(i).invincible = true;

                gp.monsters.get(i).damageReaction();
                if (gp.monsters.get(i).life <= 0)
                {
                    gp.monsters.get(i).dying = true;
                    gp.ui.addMessage(gp.monsters.get(i).name + " killed!");
                    gp.ui.addMessage(gp.monsters.get(i).exp + " exp  gained!");
                    exp += gp.monsters.get(i).exp;
                    checkLevelUp();
                }
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
            gp.playSoundEffect(8);
            gp.setGameState(DIALOGUE_STATE);
            gp.ui.currentDialogue = "You are level " + level + " now!\n" + "You feel stronger!";
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
                gp.playSoundEffect(7);
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
        //adjust player worldX/Y for attackArea
        switch (direction)
        {
            case "up" -> worldY -= attackArea.height;
            case "down" -> worldY += attackArea.height;
            case "left" -> worldX -= attackArea.width;
            case "right" -> worldX += attackArea.width;
        }
        //attackArea become solidArea
        solidArea.width = attackArea.width;
        solidArea.height = attackArea.height;
        //check monster collision with updated worldX, worldY, solidArea
        int monsterIndex = gp.collisionChecker.checkEntity(this, gp.monsters);
        damageMonster(monsterIndex);
        //restore original position
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
