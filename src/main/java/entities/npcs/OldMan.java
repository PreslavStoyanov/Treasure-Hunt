package entities.npcs;

import View.GamePanel;
import entities.types.Npc;

import static entities.types.EntityType.OLD_MAN;
import static utilities.drawers.DialogueWindowDrawer.currentDialogue;

public class OldMan extends Npc
{
    int dialogueIndex;

    public OldMan(GamePanel gp, int x, int y)
    {
        super(gp);
        this.worldX = x * GamePanel.tileSize;
        this.worldY = y * GamePanel.tileSize;
        direction = "down";
        type = OLD_MAN;
        speed = 1;
        sprites = setSprites("src/main/resources/old_man_sprites.yaml");
        setDialogue();
    }

    @Override
    public void speak()
    {
        super.speak();
        currentDialogue = dialogues.get(dialogueIndex);
        dialogueIndex++;
        if (dialogueIndex == dialogues.size())
        {
            dialogueIndex = 0;
        }
    }

    public void setDialogue()
    {
        dialogues.add("Hello, buddy!");
        dialogues.add("Be aware of the monkey!");
        dialogues.add("Once I was a wizard but now... I was \ntrowing a fire balls. Now I'm just \nold-man");
        dialogues.add("Good luck!");
    }
}
