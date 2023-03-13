package entities.npcs;

import View.GamePanel;
import entities.types.Npc;

import static entities.types.EntityType.OLD_WOMAN;
import static utilities.drawers.DialogueWindowDrawer.currentDialogue;
import static utilities.images.ImageUtils.setupDefaultImage;

public class OldWoman extends Npc
{
    int dialogueIndex;

    public OldWoman(GamePanel gp, int x, int y)
    {
        super(gp);
        this.worldX = x * GamePanel.tileSize;
        this.worldY = y * GamePanel.tileSize;
        direction = "down";
        type = OLD_WOMAN;
        speed = 1;
        getOldWomanImage();
        setDialogue();
    }

    public void getOldWomanImage()
    {
        upSprites.add(setupDefaultImage("/npc/oldWoman/up1.png"));
        upSprites.add(setupDefaultImage("/npc/oldWoman/up2.png"));
        downSprites.add(setupDefaultImage("/npc/oldWoman/down1.png"));
        downSprites.add(setupDefaultImage("/npc/oldWoman/down2.png"));
        leftSprites.add(setupDefaultImage("/npc/oldWoman/left1.png"));
        leftSprites.add(setupDefaultImage("/npc/oldWoman/left2.png"));
        rightSprites.add(setupDefaultImage("/npc/oldWoman/right1.png"));
        rightSprites.add(setupDefaultImage("/npc/oldWoman/right2.png"));
    }

    public void setAction()
    {

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
