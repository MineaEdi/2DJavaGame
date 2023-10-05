package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Random;

public class NPC_2 extends Entity
{
    public NPC_2(GamePanel gp)
    {
        super(gp);

        type = 1;

        direction = "right";
        speed = 2;

        // NPC_2 STATUS
        maxLife = 3;
        life = maxLife;

        getNPC2Image();
        setDialogue();
    }

    public void getNPC2Image()
    {
        try
        {
            left1 = ImageIO.read(getClass().getResourceAsStream("/npc1/NPC1_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/npc1/NPC1_left_2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/npc1/NPC1_left_3.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/npc1/NPC1_left_4.png"));
            left5 = ImageIO.read(getClass().getResourceAsStream("/npc1/NPC1_left_5.png"));
            left6 = ImageIO.read(getClass().getResourceAsStream("/npc1/NPC1_left_6.png"));
            left7 = ImageIO.read(getClass().getResourceAsStream("/npc1/NPC1_left_7.png"));
            left8 = ImageIO.read(getClass().getResourceAsStream("/npc1/NPC1_left_8.png"));
            left9 = ImageIO.read(getClass().getResourceAsStream("/npc1/NPC1_left_9.png"));

            right1 = ImageIO.read(getClass().getResourceAsStream("/npc1/NPC1_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/npc1/NPC1_right_2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/npc1/NPC1_right_3.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/npc1/NPC1_right_4.png"));
            right5 = ImageIO.read(getClass().getResourceAsStream("/npc1/NPC1_right_5.png"));
            right6 = ImageIO.read(getClass().getResourceAsStream("/npc1/NPC1_right_6.png"));
            right7 = ImageIO.read(getClass().getResourceAsStream("/npc1/NPC1_right_7.png"));
            right8 = ImageIO.read(getClass().getResourceAsStream("/npc1/NPC1_right_8.png"));
            right9 = ImageIO.read(getClass().getResourceAsStream("/npc1/NPC1_right_9.png"));

            npc2_dead1 = ImageIO.read(getClass().getResourceAsStream("/npc1/NPC1_dying1.png"));
            npc2_dead2 = ImageIO.read(getClass().getResourceAsStream("/npc1/NPC1_dying2.png"));
            npc2_dead3 = ImageIO.read(getClass().getResourceAsStream("/npc1/NPC1_dying3.png"));
            npc2_dead4 = ImageIO.read(getClass().getResourceAsStream("/npc1/NPC1_dying4.png"));
            npc2_dead5 = ImageIO.read(getClass().getResourceAsStream("/npc1/NPC1_dying5.png"));
            npc2_dead6 = ImageIO.read(getClass().getResourceAsStream("/npc1/NPC1_dying6.png"));

        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void setDialogue()
    {
        dialogues[0] = "Trăiesc să te slujesc, împărate!";
        dialogues[1] = "Nu îi vom lăsa să ne învingă!";
        dialogues[2] = "Le arătăm noi adevăratul imperiu!";
        dialogues[3] = "Nu am auzit de acești indivizi..";

    }

    public void setAction() {

        actionLockCounter++;

        if(actionLockCounter == 120)
        {
            Random random = new Random();
            int i = random.nextInt(100) + 1; // alegem un nr 1-100

            if(i <= 50){
                direction = "left";
            } else {
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }

    public void speak() {
        super.speak();
    }
}
