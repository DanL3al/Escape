package dialogue;

import frame.GamePanel;

import java.util.ArrayList;

public class Dialogue {

    private ArrayList<String> dialogues = new ArrayList<>();
    private int currentDialogue = 0;
    private final GamePanel gp;

    public Dialogue(GamePanel gp){
        this.gp = gp;
        initDialogues();
    }

    private void initDialogues(){
        this.dialogues.addFirst("Não vou conseguir escapar nesse computador,preciso de algo que se movimente");
        this.dialogues.addLast("Para descriptografar a porta preciso de 4 chaves");
    }

    public int getCurrentDialogue() {
        return currentDialogue;
    }
    public String getCurrentDialogueText(){
        return this.dialogues.get(currentDialogue);
    }


    public ArrayList<String> getDialogues() {
        return dialogues;
    }

    public void setCurrentDialogue() {
        this.currentDialogue++;
    }
}
