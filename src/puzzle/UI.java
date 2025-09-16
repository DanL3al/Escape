package puzzle;

import frame.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class UI {

    private final GamePanel gp;

    //DIALOGUES VARIABLES
    private BufferedImage talkIconOne, talkIconPc;

    private final Color opaqueBlack = new Color(0,0,0,220);
    private final Color borderColor = new Color(255,255,255);



    /*INITIAL BLACK SCREEN BEFORE PUZZLE VARIABLES*/
    String text = "Sobrecarregue o servidor com cookies indesejados"; //TODO: CHANGE IT TO GET FROM PUZZLE
    StringBuilder current = new StringBuilder();
    int i = 0;
    int timer = 0;
    int changeStateTimer = 0;

    /*ROBOT VS SERVER VARIABLES*/
    private final BufferedImage cookieImage;
    private final BufferedImage shieldImage;
    private final BufferedImage heartImage;
    private BufferedImage heatImage;
    private final int puzzleOneX;
    private final int puzzleOneY;
    private final int puzzleOneWidth;
    private final int puzzleOneHeight;
    private BufferedImage space,wOne,wTwo,wThree,zero,one,two,three,four,five,six;
    int spriteNum = 1;
    int spriteCounter = 0;



    public UI(GamePanel gp){
        this.gp = gp;
        this.cookieImage = gp.getCookieImage();
        this.shieldImage = gp.getShieldImage();
        this.puzzleOneX = gp.getTileSize();
        this.puzzleOneY = gp.getTileSize() * 7;
        this.puzzleOneWidth = gp.getTileSize();
        this.puzzleOneHeight = gp.getTileSize();
        this.heartImage = gp.getHeartImage();
        this.heatImage = gp.getCurrentHeatImage();
        init();
    }

    public void robotServerBlackScreenDraw(Graphics2D g2){
        g2.setColor(Color.black);
        g2.fillRect(0,0,gp.getWidth(),gp.getHeight());
        g2.setColor(Color.WHITE);
        g2.drawString(String.valueOf(current), gp.getTileSize() * 3,gp.getMaxCol() * gp.getTileSize() / 2);
    }

    public void draw(Graphics2D g2){
        BufferedImage currentImage = null;
        BufferedImage numberImage = null;
        heatImage = gp.getCurrentHeatImage();
        switch (spriteNum){
            case  1 -> currentImage = wOne;
            case  2 -> currentImage = wTwo;
            case  3 -> currentImage = wThree;
        }
        switch (gp.getCookiesRemaining()){
            case 0 -> numberImage = zero;
            case 1 -> numberImage = one;
            case 2 -> numberImage = two;
            case 3 -> numberImage = three;
            case 4 -> numberImage = four;
            case 5 -> numberImage = five;
            case 6 -> numberImage = six;
        }

        g2.drawImage(heatImage, gp.getTileSize() * 4, gp.getTileSize(), puzzleOneWidth * 2,puzzleOneHeight * 2,null);

        g2.drawImage(currentImage,puzzleOneX,puzzleOneY,puzzleOneWidth * 2,puzzleOneHeight * 2,null);
        g2.drawImage(cookieImage,puzzleOneX + gp.getTileSize() * 2,puzzleOneY + 24,puzzleOneWidth,puzzleOneHeight,null);
        g2.drawImage(numberImage,puzzleOneX + gp.getTileSize() * 2 + 30, puzzleOneY + 24,puzzleOneWidth,puzzleOneHeight,null);

        g2.drawImage(space, puzzleOneX, puzzleOneY + gp.getTileSize(),puzzleOneWidth * 2,puzzleOneHeight * 2,null);
        g2.drawImage(shieldImage, puzzleOneX + gp.getTileSize() * 2, puzzleOneY + gp.getTileSize() + 26,puzzleOneWidth,puzzleOneHeight,null);

        for(int i = 0; i < gp.getHealth(); i++){
            g2.drawImage(heartImage, gp.getTileSize() * 6 +(i*20), gp.getHeight() - gp.getTileSize() * 2, puzzleOneWidth,puzzleOneHeight,null);
        }


    }

    public void update(){
        if(gp.getGameState() == gp.getShowingPuzzleObjective()){
            if(i < text.length()){
                timer++;
            }else{
                changeStateTimer++;
                if(changeStateTimer == 80){
                    gp.setGameState(gp.getSolvingPuzzle());
                }
            }
            if(timer % 7 == 0){
                if(i < text.length()){
                    if(!current.isEmpty()){
                        current.deleteCharAt(i);
                    }
                    current.append(text.charAt(i));
                    current.append("_");
                    i++;
                }
            }
        }else{
            spriteCounter++;
            if(spriteCounter > 12){
                if(spriteNum == 1){
                    spriteNum = 2;
                }else if(spriteNum == 2){
                    spriteNum = 3;
                } else{
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }

    }

    public void drawDialogue(Graphics2D g2){

        int opaqueScreenX = 0;
        int opaqueScreenY = gp.getHeight() - gp.getTileSize() * 4 + 8;
        int opaqueScreenWidth = gp.getWidth() - gp.getTileSize() + (gp.getTileSize() / 3 * 2);
        int opaqueScreenHeight = gp.getHeight() - (gp.getHeight() - gp.getTileSize() * 4) - gp.getTileSize();

        int borderX = 0;
        int borderY = gp.getHeight() - gp.getTileSize() * 4 + 8;
        int borderWidth = gp.getWidth() - gp.getTileSize() + (gp.getTileSize() / 3 * 2);
        int borderHeight = gp.getHeight() - (gp.getHeight() - gp.getTileSize() * 4) - gp.getTileSize();

        g2.setColor(opaqueBlack);
        g2.fillRoundRect(opaqueScreenX,opaqueScreenY,opaqueScreenWidth,opaqueScreenHeight,35,35);

        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(6));
        g2.drawRoundRect(borderX,borderY,borderWidth,borderHeight,25,25);

        int iconX = 10;
        int iconY = (opaqueScreenY + opaqueScreenHeight) / 2 + gp.getTileSize() * 3 + (gp.getTileSize() / 3);
        int iconWidth = gp.getTileSize();
        int iconHeight = gp.getTileSize();

        int stringX = 72;
        int stringY = gp.getHeight() - gp.getTileSize() * 3;


        g2.drawImage(talkIconPc,iconX,iconY,iconWidth,iconHeight,null);
        String teste = modifyString(gp.getCurrentDialogue());
        for(String line : teste.split("\n")){
            g2.drawString(line,stringX,stringY);
            stringY += 18;
        }

        g2.drawString("aperte t", gp.getHeight() - gp.getTileSize() * 2, gp.getWidth() - gp.getTileSize());


    }

    private String modifyString(String s){
        if(s.length() < 72){
            return s;
        }
        StringBuilder formattedString = new StringBuilder();
        for (int j = 72; j < s.length(); j += 72) {
            String firstPart = s.substring(0,j);
            String breakLine = "\n";
            String lastPart = s.substring(72);
            formattedString.append(firstPart);
            formattedString.append(breakLine);
            formattedString.append(lastPart);
        }
        return String.valueOf(formattedString);
    }

    private void init(){
        try{
            //TALK ICON IMAGES
            talkIconOne = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("talkIconAssets/talk-icon-1.png")));
            talkIconPc = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("talkIconAssets/talk-icon-pc.png")));

            //SERVER PUZZLE IMAGES
            space = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("puzzleOneAssets/space.png")));
            wOne = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("puzzleOneAssets/w-1.png")));
            wTwo = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("puzzleOneAssets/w-2.png")));
            wThree = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("puzzleOneAssets/w-3.png")));
            zero = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("puzzleOneAssets/zero.png")));
            one = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("puzzleOneAssets/one.png")));
            two = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("puzzleOneAssets/two.png")));
            three = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("puzzleOneAssets/three.png")));
            four = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("puzzleOneAssets/four.png")));
            five = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("puzzleOneAssets/five.png")));
            six = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("puzzleOneAssets/six.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
