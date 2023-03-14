package entities.types;

import View.GamePanel;

import java.util.LinkedList;
import java.util.List;

import static entities.Direction.*;

public class Npc extends LiveEntity
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
