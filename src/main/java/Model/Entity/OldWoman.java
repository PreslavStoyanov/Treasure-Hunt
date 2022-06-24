package Model.Entity;

import View.GamePanel;

import java.util.Random;

public class OldWoman extends Entity {
    int dialogueIndex;
    public OldWoman(GamePanel gp) {
        super(gp);
        direction = "down";
        speed = 1;
        getOldWomanImage();
        setDialogue();
    }

    public void getOldWomanImage() {
        upSprites.add(setup("/npc/oldWoman/up1.png", gp.tileSize, gp.tileSize));
        upSprites.add(setup("/npc/oldWoman/up2.png", gp.tileSize, gp.tileSize));
        downSprites.add(setup("/npc/oldWoman/down1.png", gp.tileSize, gp.tileSize));
        downSprites.add(setup("/npc/oldWoman/down2.png", gp.tileSize, gp.tileSize));
        leftSprites.add(setup("/npc/oldWoman/left1.png", gp.tileSize, gp.tileSize));
        leftSprites.add(setup("/npc/oldWoman/left2.png", gp.tileSize, gp.tileSize));
        rightSprites.add(setup("/npc/oldWoman/right1.png", gp.tileSize, gp.tileSize));
        rightSprites.add(setup("/npc/oldWoman/right2.png", gp.tileSize, gp.tileSize));
    }

    public void setAction() {
        actionLockCounter++;

        if (actionLockCounter == 120){
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
        dialogues.add("Hello, Hello!");
        dialogues.add("Don't trust anybody!");
        dialogues.add("I'm a wizard as this old-man down \nthere!");
        dialogues.add("I was better than the old-man!");
        dialogues.add("So good luck!");
    }
}
