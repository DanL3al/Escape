package puzzle;

import frame.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class HorrorPuzzle {

    private final GamePanel gp;
    private BufferedImage robot, up ,eye, shift, light, lightTwo, lightThree,robotOn;
    private BufferedImage antivirus,antivirusTwo,antivirusThree, antivirusFour, antivirusFive;
    private BufferedImage key;

    private boolean gameWon = false;
    private boolean gameLost = false;

    private boolean flashlightOn = false;
    private int flashLightSprite = 1;
    private int flashLightSpriteCounter = 0;
    private int currentSprite = 1;
    private int spriteCounter = 0;
    private int teste = 0;
    private int jumpscareTimer = 0;
    private int actionTimer = 0;
    private int endingTimer = 0;

    private boolean foundRobot = false;
    private boolean walking = false;
    private boolean canWatch = true;

    private int state;

    private boolean startDelay = false;
    private int delay = 0;
    private int watchingTimer = 0;

    private final int playing = 1;
    private final int aboutToGetJumpscared = 2;
    private final int jumpscare = 4;
    private final int foundTheKey = 3;


    private int distance = 0;

    private final String puzzleDescription = "Ache a chave\nNão seja pego";
    private final Rectangle solidArea;


    private final Color color = new Color(0,0,0,210);

    public HorrorPuzzle(GamePanel gp){
        this.gp = gp;
        initImages();
        state = playing;
        solidArea = new Rectangle(gp.getTileSize() * 10,gp.getTileSize() * 2,gp.getTileSize(),gp.getTileSize());
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.black);
        g2.fillRect(0,0,gp.getWidth(),gp.getHeight());
        if(state == playing || state == aboutToGetJumpscared){
            if(flashlightOn){
                BufferedImage current = null;
                switch (flashLightSprite){
                    case 1 -> current = light;
                    case 2 -> current = lightTwo;
                    case 3 -> current = lightThree;
                }
                g2.drawImage(current,80, gp.getHeight() - gp.getTileSize()*11 + 16, gp.getTileSize()*8, gp.getTileSize()*8,null);
                g2.drawImage(robotOn,0,gp.getHeight() - gp.getTileSize()*4 + 10, gp.getTileSize() * 4, gp.getTileSize() * 4, null);
            }else{
                g2.setColor(color);
                g2.fillRect(0,gp.getHeight() - gp.getTileSize()*4 + 10, gp.getTileSize() * 2, gp.getTileSize() * 2);
                g2.drawImage(robot,0,gp.getHeight() - gp.getTileSize()*4 + 10, gp.getTileSize() * 4, gp.getTileSize() * 4, null);
            }

            if(foundRobot || startDelay){
                g2.drawImage(eye,gp.getTileSize() * 6 - 12, gp.getTileSize() * 4   - 12, gp.getTileSize(),gp.getTileSize(),null);
            }

            g2.drawImage(up,gp.getTileSize() * 3, gp.getTileSize() * 11, gp.getTileSize(),gp.getTileSize(),null);
        }else if(state == jumpscare){
            BufferedImage t = null;
            switch (currentSprite){
                case 1 -> t = antivirus;
                case 2 -> t = antivirusTwo;
                case 3 -> t = antivirusThree;
                case 4 -> t = antivirusFour;
                case 5 -> t = antivirusFive;
            }
            g2.drawImage(t,80, gp.getHeight() - gp.getTileSize() * 9 + 16, gp.getTileSize()*8, gp.getTileSize()*8,null);
        }else if(state == foundTheKey){
            g2.drawImage(key,gp.getTileSize() * 6 - 12, gp.getTileSize() * 4   - 12, gp.getTileSize(),gp.getTileSize(),null);
        }
    }

    public void drawRectangle(Graphics2D g2){
        g2.setColor(Color.gray);
        g2.fillRect(solidArea.x, solidArea.y, solidArea.width, solidArea.height);
    }

    public void update(){
        if(distance >= 600){
            state = foundTheKey;
        }
        if(state == foundTheKey){
            endingTimer++;
            if(endingTimer >= 120){
                gameWon = true;
                gp.setGameState(gp.getControllingRobot());
            }
        }
        if(state == playing){
            if(canWatch){
                actionTimer++;
            }
            if(actionTimer >= 150){
                int random = (int) (Math.random() * 10);
                if(random >= 4){
                    startDelay = true;
                }
                actionTimer = 0;
            }
            if(startDelay){
                delay++;
                if(delay >= 120){
                    startDelay = false;
                    foundRobot = true;
                    delay = 0;
                }
            }
            if(foundRobot){
                watchingTimer++;
                if(watchingTimer >= 120){
                    foundRobot = false;
                    canWatch = false;
                    watchingTimer = 0;
                }
                if(walking || flashlightOn){
                    state = aboutToGetJumpscared;
                }
            }else{
                if(!canWatch){
                    watchingTimer++;
                    if(watchingTimer >= 80){
                        canWatch = true;
                    }
                }
            }
        }

        if(state == aboutToGetJumpscared){
            jumpscareTimer++;
            if(jumpscareTimer >= 60){
                state = jumpscare;
            }
        }

        if(state == jumpscare){
            endingTimer++;
            if(endingTimer >= 90){
                gameLost = true;
                gp.setGameState(gp.getControllingRobot());
                resetGame();
            }
            spriteCounter++;
            if(spriteCounter >= 4){
                if(currentSprite == 1){
                    currentSprite = 2;
                }else if(currentSprite == 2){
                    currentSprite = 3;
                }else if(currentSprite == 3){
                    currentSprite = 4;
                }else if(currentSprite == 4){
                    currentSprite = 5;
                }else{
                    currentSprite = 1;
                }
                spriteCounter = 0;
            }
        }

        flashLightSpriteCounter++;
        if(flashLightSpriteCounter >= 6){
            if(flashLightSprite == 1){
                flashLightSprite = 2;
            }if(flashLightSprite == 2){
                flashLightSprite = 3;
            }else{
                flashLightSprite = 1;
            }
            flashLightSpriteCounter = 0;
        }

    }

    private void resetGame(){
        foundRobot = false;
        walking = false;
        canWatch = true;
        startDelay = false;
        state = playing;
    }


    private void initImages(){
        try{
            robot = ImageIO.read(getClass().getClassLoader().getResourceAsStream("horrorPuzzleAssets/flashlight.png"));
            robotOn = ImageIO.read(getClass().getClassLoader().getResourceAsStream("horrorPuzzleAssets/flashlight-on.png"));
            antivirus = ImageIO.read(getClass().getClassLoader().getResourceAsStream("horrorPuzzleAssets/ant.png"));
            antivirusTwo = ImageIO.read(getClass().getClassLoader().getResourceAsStream("horrorPuzzleAssets/antivirus-3.png"));
            antivirusThree = ImageIO.read(getClass().getClassLoader().getResourceAsStream("horrorPuzzleAssets/antivirus-3.png"));
            antivirusFour = ImageIO.read(getClass().getClassLoader().getResourceAsStream("horrorPuzzleAssets/antivirus-4.png"));
            antivirusFive = ImageIO.read(getClass().getClassLoader().getResourceAsStream("horrorPuzzleAssets/antivirus-5.png"));
            light = ImageIO.read(getClass().getClassLoader().getResourceAsStream("horrorPuzzleAssets/light.png"));
            lightTwo = ImageIO.read(getClass().getClassLoader().getResourceAsStream("horrorPuzzleAssets/light-two.png"));
            lightThree = ImageIO.read(getClass().getClassLoader().getResourceAsStream("horrorPuzzleAssets/light-three.png"));
            up = ImageIO.read(getClass().getClassLoader().getResourceAsStream("horrorPuzzleAssets/up.png"));
            key = ImageIO.read(getClass().getClassLoader().getResourceAsStream("horrorPuzzleAssets/key.png"));
            eye = ImageIO.read(getClass().getClassLoader().getResourceAsStream("horrorPuzzleAssets/eyes.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public boolean isFlashlightOn() {
        return flashlightOn;
    }

    public void setFlashlightOn(boolean flashlightOn) {
        this.flashlightOn = flashlightOn;
    }


    public void setWalking(boolean walking) {
        this.walking = walking;
        this.distance += 1;
    }

    public String getPuzzleDescription() {
        return puzzleDescription;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public boolean isGameLost() {
        return gameLost;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }
}
