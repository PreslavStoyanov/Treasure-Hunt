package assets.entities.movingentities.liveentities.artificials;

import application.GamePanel;
import assets.Inventory;
import assets.entities.movingentities.liveentities.Artificial;

import java.util.List;

import static assets.entities.MovingEntity.Direction.*;
import static utilities.GameState.DIALOGUE_STATE;
import static utilities.drawers.DialogueWindowDrawer.currentDialogue;
import static utilities.statehandlers.PlayStateHandler.isTalkButtonPressed;
import static utilities.statehandlers.TradeStateHandler.inventoryToBuyFrom;

public class Npc extends Artificial
{
    public Inventory inventory = new Inventory();
    public int dialogueIndex;
    public List<String> dialogues;

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

    private void talk()
    {
        currentDialogue = dialogues.get(dialogueIndex);
        dialogueIndex++;
        if (dialogueIndex == dialogues.size())
        {
            dialogueIndex = 0;
        }
    }

    public void speak()
    {
        gp.setGameState(DIALOGUE_STATE);
//        gp.soundEffectsHandler.playSoundEffect(GOSSIP);
        faceUpPlayer();
        talk();
        isTalkButtonPressed = false;
        inventoryToBuyFrom = inventory;
    }
}
