package assets.entities.movingentities.liveentities;

import application.GamePanel;
import assets.Entity;
import assets.entities.InteractiveTile;
import assets.entities.Object;
import assets.entities.movingentities.AliveEntity;
import assets.entities.movingentities.liveentities.artificials.Monster;
import assets.entities.movingentities.projectiles.Fireball;
import assets.entities.movingentities.sprites.AttackingSprite;
import assets.entities.movingentities.sprites.AttackingSprites;
import assets.entities.objects.StorableObject;
import assets.entities.objects.collectables.equppables.DefenseObject;
import assets.entities.objects.collectables.equppables.Weapon;
import assets.interfaces.Damageable;
import utilities.keyboard.KeyboardHandler;
import utilities.keyboard.PlayScreenKeyboardHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static application.GamePanel.*;
import static assets.EntityType.*;
import static assets.entities.MovingEntity.Direction.*;
import static utilities.GameState.GAME_LOSE_STATE;
import static utilities.drawers.InventoryWindowDrawer.*;
import static utilities.drawers.MessageDrawer.addMessage;
import static utilities.keyboard.PlayScreenKeyboardHandler.*;
import static utilities.sound.Sound.*;

public class Player extends AliveEntity implements Damageable
{
    public List<StorableObject> inventory = new ArrayList<>();
    public int inventoryCapacity;
    public Optional<Weapon> currentWeapon = Optional.empty();
    public Optional<DefenseObject> currentShield = Optional.empty();
    public int coins;
    public int level;
    public int strength;
    public int agility;
    public int swingTimer = 0;
    public int energy;
    public int maxEnergy;
    public int maxExp;
    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyboardHandler keyboardHandler)
    {
        super(gp);
        screenX = screenWidth / 2 - halfTileSize;
        screenY = screenHeight / 2 - halfTileSize;
        setSolidAreaAndDefaultLocation(8, 16, 30, 30);
        setWorldLocation(tileSize * 24, tileSize * 24);
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
        inventoryCapacity = 20;
        projectile = new Fireball(gp);
        sprites = setSprites("src/main/resources/player/player_sprites.yaml");
    }

    public static int getInventoryItemIndex()
    {
        return inventorySlotCursorCol + inventorySlotCursorRow * INVENTORY_COLS;
    }

    public int calculateAttack()
    {
        return currentWeapon.map(weapon -> strength * weapon.value).orElse(0);
    }

    public int calculateDefense()
    {
        return currentShield.map(defenseObject -> agility * defenseObject.value).orElse(0);
    }

    @Override
    public void update()
    {
        if (isSwingButtonPressed && currentWeapon.isPresent())
        {
            swing(currentWeapon.get());
        }
        else
        {
            changeMovingDirection();
            interactWithEntities();
            handleProjectileAction();
        }
        setInvincibleTime(60);
        if (life <= 0)
        {
            endGame();
        }
    }

    private void endGame()
    {
        gp.soundHandler.stop();
        gp.soundHandler.playSoundEffect(GAMEOVER_SOUND);
        gp.setGameState(GAME_LOSE_STATE);
    }

    private void handleProjectileAction()
    {
        if (isShootProjectileButtonPressed
                && energy >= projectile.castEnergyNeeded)
        {
            projectile.shoot(this);
            decreaseEnergy(projectile.castEnergyNeeded);
        }
        if (isEnergyButtonPressed)
        {
            increaseEnergy(1);
        }
    }

    @Override
    public void draw(Graphics2D g2)
    {
        BufferedImage image;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        if (isSwingButtonPressed && currentWeapon.isPresent())
        {
            switch (direction)
            {
                case UP -> tempScreenY -= tileSize;
                case LEFT -> tempScreenX -= tileSize;
            }
            image = switchAttackingSpritesByDirection(currentWeapon.get());
        }
        else
        {
            image = switchWalkingSpritesByDirection();
        }

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, isInvincible ? 0.5F : 1F));
        g2.drawImage(image, Math.min(tempScreenX, worldX), Math.min(tempScreenY, worldY), null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F));
    }

    private BufferedImage switchAttackingSpritesByDirection(Weapon weapon)
    {
        AttackingSprites attackingSprites = sprites.getAttackingSprites().get(weapon.type);
        List<AttackingSprite> attackingSpritesByDirection;
        switch (direction)
        {
            case UP -> attackingSpritesByDirection = attackingSprites.getUpSprites();
            case DOWN -> attackingSpritesByDirection = attackingSprites.getDownSprites();
            case LEFT -> attackingSpritesByDirection = attackingSprites.getLeftSprites();
            case RIGHT -> attackingSpritesByDirection = attackingSprites.getRightSprites();
            default -> throw new IllegalStateException("Unexpected value: " + direction);
        }
        return changeAttackingSprite(attackingSpritesByDirection);
    }

    @Override
    public void interactWithEntities()
    {
        isHittingTileWithCollision = gp.collisionChecker.isHittingCollisionTile(this);
        Optional<InteractiveTile> interactiveTile = gp.interactiveTiles.stream()
                .filter(tile -> gp.collisionChecker.isEntityColliding(this, tile))
                .findFirst();
        gp.monsters.stream().filter(monster -> gp.collisionChecker.isEntityColliding(this, monster))
                .findFirst()
                .ifPresent(monster -> {
                    if (!monster.isDying && !isInvincible)
                    {
                        takeDamage(monster.attackValue);
                        reactToDamage();
                    }
                });
        gp.objects.stream().filter(object -> gp.collisionChecker.isEntityColliding(this, object))
                .findFirst()
                .ifPresent(Object::interact);

        if (interactiveTile.isEmpty() && !isHittingTileWithCollision && isWalkingButtonPressed())
        {
            handleMoving();
        }

        gp.npcs.stream().filter(npc -> gp.collisionChecker.isEntityColliding(this, npc))
                .findFirst().ifPresent(npc -> {
                    if (isTalkButtonPressed)
                    {
                        npc.speak();
                    }
                });
    }

    @Override
    public void changeMovingDirection()
    {
        if (isUpButtonPressed)
        {
            direction = UP;
        }
        else if (isDownButtonPressed)
        {
            direction = DOWN;
        }
        else if (isLeftButtonPressed)
        {
            direction = LEFT;
        }
        else if (isRightButtonPressed)
        {
            direction = RIGHT;
        }
    }

    @Override
    public void takeDamage(int damage)
    {
        gp.soundHandler.playSoundEffect(RECEIVE_DAMAGE);
        super.takeDamage(damage);
    }

    @Override
    public void reactToDamage()
    {
        isInvincible = true;
    }

    public void damageMonster(Monster monster, int damage)
    {
        if (!monster.isInvincible)
        {
            monster.takeDamage(damage);
            if (monster.isDying)
            {
                addMessage(String.format("%d exp gained from killing %s", monster.exp, monster.name));
                collectExp(monster.exp);
            }
        }
    }

    public void increaseEnergy(int value)
    {
        energy = Math.min(energy + value, maxEnergy);
    }

    public void decreaseEnergy(int value)
    {
        energy = Math.max(energy - value, 0);
    }

    public void collectExp(int collectedExp)
    {
        exp += collectedExp;
        if (exp >= maxExp)
        {
            levelUp();
        }
    }

    private boolean isWalkingButtonPressed()
    {
        return isUpButtonPressed || isDownButtonPressed || isLeftButtonPressed || isRightButtonPressed;
    }

    private void swing(Weapon weapon)
    {
        swingTimer++;
        if (swingTimer == 2)
        {
            gp.soundHandler.playSoundEffect(weapon.swingSound);
        }
        else if (swingTimer <= 5)
        {
            spriteNumber = 1;
        }
        else if (swingTimer == 6)
        {
            getOptionalEntityCollidingWithWeaponSwing(weapon).ifPresent(entity ->
            {
                if (MONSTER_TYPES.contains(entity.type))
                {
                    damageMonster((Monster) entity, attackValue);
                }
                else if (INTERACTIVE_TILES.contains(entity.type))
                {
                    ((InteractiveTile) entity).interact();
                }
            });
        }
        else if (swingTimer <= 25)
        {
            spriteNumber = 2;
        }
        else
        {
            spriteNumber = 1;
            swingTimer = 0;
            PlayScreenKeyboardHandler.isSwingButtonPressed = false;
        }
    }

    private Optional<? extends Entity> getOptionalEntityCollidingWithWeaponSwing(Weapon weapon)
    {
        //save current worldX, worldY, solidArea
        int worldXBeforeAttack = worldX;
        int worldYBeforeAttack = worldY;
        int solidAreaWidthBeforeAttack = solidArea.width;
        int solidAreaHeightBeforeAttack = solidArea.height;

        adjustPlayerCoordinatesForAttackArea();
        solidArea.setSize(weapon.attackArea.width, weapon.attackArea.height);

        Optional<? extends Entity> result = gp.monsters.stream()
                .filter(m -> gp.collisionChecker.isEntityColliding(this, m))
                .findFirst();

        if (result.isEmpty())
        {
            result = gp.interactiveTiles.stream()
                    .filter(tile -> gp.collisionChecker.isEntityColliding(this, tile))
                    .findFirst();
        }

        //restore original position
        setWorldLocation(worldXBeforeAttack, worldYBeforeAttack);
        solidArea.setSize(solidAreaWidthBeforeAttack, solidAreaHeightBeforeAttack);

        return result;
    }

    private void levelUp()
    {
        level++;
        maxLife = Math.min(maxLife + 2, 12);
        maxEnergy = Math.min(maxEnergy + level * 5, 120);
        this.increaseLife(2);
        strength++;
        agility++;
        exp = 0;
        maxExp += level;

        attackValue = calculateAttack();
        defense = calculateDefense();
        gp.soundHandler.playSoundEffect(LEVEL_UP);
        addMessage(String.format("You are level %d now! You feel stronger!", level));
    }

    private void adjustPlayerCoordinatesForAttackArea()
    {
        switch (direction)
        {
            case UP -> worldY -= currentWeapon.get().attackArea.height;
            case DOWN -> worldY += currentWeapon.get().attackArea.height;
            case LEFT -> worldX -= currentWeapon.get().attackArea.width;
            case RIGHT -> worldX += currentWeapon.get().attackArea.width;
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

    private void takeDamageFromMonsterIfAvailable(Monster monster)
    {

    }
}
