package assets.entities.movingentities.liveentities.artificials;

import application.GamePanel;
import assets.entities.movingentities.liveentities.Artificial;

import java.util.LinkedList;
import java.util.List;

import static assets.entities.MovingEntity.Direction.*;
import static utilities.GameState.DIALOGUE_STATE;
import static utilities.keyboard.PlayScreenKeyboardHandler.isTalkButtonPressed;
import static utilities.sound.Sound.GOSSIP;

public class Npc extends Artificial
{
    public List<String> dialogues = new LinkedList<>();

    public Npc(GamePanel gp)
    {
        super(gp);
    }

    public void faceUpPlayer()
    {
        switch (gp.player.direction)
        {
            case UP -> this.direction = DOWN;
            case DOWN -> this.direction = UP;
            case LEFT -> this.direction = RIGHT;
            case RIGHT -> this.direction = LEFT;
        }
    }

    public void speak()
    {
        gp.setGameState(DIALOGUE_STATE);
        gp.soundEffectsHandler.playSoundEffect(GOSSIP);
        faceUpPlayer();
        isTalkButtonPressed = false;
    }
}
