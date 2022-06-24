package Model.Entity;

import View.GamePanel;

import java.util.Random;

public class OldMan extends Entity {
    int dialogueIndex;
    public OldMan(GamePanel gp) {
        super(gp);
        direction = "down";
        speed = 1;
        getOldManImage();
        setDialogue();
    }

    public void getOldManImage() {
        upSprites.add(setup("/npc/oldMan/up1.png", gp.tileSize, gp.tileSize));
        upSprites.add(setup("/npc/oldMan/up2.png", gp.tileSize, gp.tileSize));
        downSprites.add(setup("/npc/oldMan/down1.png", gp.tileSize, gp.tileSize));
        downSprites.add(setup("/npc/oldMan/down2.png", gp.tileSize, gp.tileSize));
        leftSprites.add(setup("/npc/oldMan/left1.png", gp.tileSize, gp.tileSize));
        leftSprites.add(setup("/npc/oldMan/left2.png", gp.tileSize, gp.tileSize));
        rightSprites.add(setup("/npc/oldMan/right1.png", gp.tileSize, gp.tileSize));
        rightSprites.add(setup("/npc/oldMan/right2.png", gp.tileSize, gp.tileSize));
    }

    public void setAction() {
        actionLockCounter++;

        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = 1 + random.nextInt(100);
            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75) {
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }


    @Override
    public void speak() {
        super.speak();
        gp.ui.currentDialogue = dialogues.get(dialogueIndex);
        dialogueIndex++;
        if (dialogueIndex == dialogues.size()) {
            dialogueIndex = 0;
        }
    }

    public void setDialogue() {
        dialogues.add("Hello, buddy!");
        dialogues.add("Be aware of the monkey!");
        dialogues.add("Once I was a wizard but now... I was \ntrowing a fire balls. Now I'm just \nold-man");
        dialogues.add("Good luck!");
    }
}
