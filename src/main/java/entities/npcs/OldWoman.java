package entities.npcs;

import View.GamePanel;
import entities.types.Npc;

import static entities.types.EntityType.OLD_WOMAN;
import static utilities.drawers.DialogueWindowDrawer.currentDialogue;

public class OldWoman extends Npc
{
    int dialogueIndex;

    public OldWoman(GamePanel gp, int x, int y)
    {
        super(gp);
        this.worldX = x * GamePanel.tileSize;
        this.worldY = y * GamePanel.tileSize;
        type = OLD_WOMAN;
        speed = 1;
        sprites = setSprites("src/main/resources/old_woman_sprites.yaml");
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
        dialogues.add("Hello, Hello!");
        dialogues.add("Don't trust anybody!");
        dialogues.add("I'm a wizard as this old-man down \nthere!");
        dialogues.add("I was better than the old-man!");
        dialogues.add("So good luck!");
    }
}
