package assets.entities.movingentities.liveentities.artificials.npcs;

import application.GamePanel;
import assets.entities.movingentities.liveentities.artificials.Npc;

import static application.GamePanel.tileSize;
import static assets.EntityType.OLD_MAN;
import static utilities.drawers.DialogueWindowDrawer.currentDialogue;

public class OldMan extends Npc
{
    int dialogueIndex;

    public OldMan(GamePanel gp, int x, int y)
    {
        super(gp);
        this.setWorldLocation(x * tileSize, y * tileSize);
        setSolidAreaAndDefaultLocation(8, 16, 30, 30);
        type = OLD_MAN;
        movingSpeed = 1;
        sprites = setSprites("src/main/resources/npc/old_man_sprites.yaml");
        setDialogue();
    }

    @Override
    public void faceUpPlayer()
    {
        super.faceUpPlayer();
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
