package entities.types;

import View.GamePanel;
import entities.Entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

import static View.GamePanel.tileSize;

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
            case "up" -> this.direction = "down";
            case "down" -> this.direction = "up";
            case "left" -> this.direction = "right";
            case "right" -> this.direction = "left";
        }
    }
}
