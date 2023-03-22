package assets.entities.movingentities.liveentities.artificials;

import application.GamePanel;
import assets.entities.movingentities.liveentities.Artificial;

import java.util.LinkedList;
import java.util.List;

import static assets.entities.MovingEntity.Direction.*;

public class Npc extends Artificial
{
    public List<String> dialogues = new LinkedList<>();

    public Npc(GamePanel gp)
    {
        super(gp);
    }

    public void speak()
    {
        switch (gp.player.direction)
        {
            case UP -> this.direction = DOWN;
            case DOWN -> this.direction = UP;
            case LEFT -> this.direction = RIGHT;
            case RIGHT -> this.direction = LEFT;
        }
    }
}