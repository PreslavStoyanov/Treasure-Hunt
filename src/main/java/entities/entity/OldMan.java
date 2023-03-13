package entities.entity;

import View.GamePanel;

import static utilities.drawers.DialogueWindowDrawer.currentDialogue;
import static utilities.images.ImageUtils.setupDefaultImage;

public class OldMan extends Entity
{
    int dialogueIndex;

    public OldMan(GamePanel gp, int x, int y)
    {
        super(gp);
        this.worldX = x * gp.tileSize;
        this.worldY = y * gp.tileSize;
        direction = "down";
        speed = 1;
        getOldManImage();
        setDialogue();
    }

    public void getOldManImage()
    {
        upSprites.add(setupDefaultImage("/npc/oldMan/up1.png"));
        upSprites.add(setupDefaultImage("/npc/oldMan/up2.png"));
        downSprites.add(setupDefaultImage("/npc/oldMan/down1.png"));
        downSprites.add(setupDefaultImage("/npc/oldMan/down2.png"));
        leftSprites.add(setupDefaultImage("/npc/oldMan/left1.png"));
        leftSprites.add(setupDefaultImage("/npc/oldMan/left2.png"));
        rightSprites.add(setupDefaultImage("/npc/oldMan/right1.png"));
        rightSprites.add(setupDefaultImage("/npc/oldMan/right2.png"));
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
