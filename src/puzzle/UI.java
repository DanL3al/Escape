package puzzle;

import frame.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class UI {

    private final GamePanel gp;

    /*PUZZLE ONE VARIABLES*/
    private final BufferedImage cookieImage;
    private final BufferedImage shieldImage;
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
        init();
    }

    public void draw(Graphics2D g2){
        BufferedImage currentImage = null;
        BufferedImage numberImage = null;
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
        g2.drawImage(currentImage,puzzleOneX,puzzleOneY,puzzleOneWidth * 2,puzzleOneHeight * 2,null);
        g2.drawImage(cookieImage,puzzleOneX + gp.getTileSize() * 2,puzzleOneY + 24,puzzleOneWidth,puzzleOneHeight,null);
        g2.drawImage(numberImage,puzzleOneX + gp.getTileSize() * 2 + 30, puzzleOneY + 24,puzzleOneWidth,puzzleOneHeight,null);

        g2.drawImage(space, puzzleOneX, puzzleOneY + gp.getTileSize(),puzzleOneWidth * 2,puzzleOneHeight * 2,null);
        g2.drawImage(shieldImage, puzzleOneX + gp.getTileSize() * 2, puzzleOneY + gp.getTileSize() + 26,puzzleOneWidth,puzzleOneHeight,null);
    }

    public void update(){
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

    private void init(){
        try{
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
