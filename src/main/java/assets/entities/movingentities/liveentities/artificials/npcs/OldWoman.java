package assets.entities.movingentities.liveentities.artificials.npcs;

import application.GamePanel;
import assets.entities.movingentities.liveentities.artificials.Npc;

import static application.GamePanel.tileSize;
import static assets.EntityType.OLD_WOMAN;
import static utilities.drawers.DialogueWindowDrawer.currentDialogue;

public class OldWoman extends Npc
{
    int dialogueIndex;

    public OldWoman(GamePanel gp, int x, int y)
    {
        super(gp);
        this.setWorldLocation(x * tileSize, y * tileSize);
        setSolidAreaAndDefaultLocation(8, 16, 30, 30);
        type = OLD_WOMAN;
        movingSpeed = 1;
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
