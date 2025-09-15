package puzzle;

import frame.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class PuzzleOne {

    private final GamePanel gp;
    private boolean puzzleStarted;
    private boolean puzzleSolved;

    private final Robot robot;
    private final CookieManager cookieManager;
    private final Server server;


    public PuzzleOne(GamePanel gp){
        this.gp = gp;
        robot = new Robot(gp);
        cookieManager = new CookieManager(gp);
        server = new Server(gp);
    }

    public void draw(Graphics2D g2){
        g2.fillRect(0,0,gp.getTileSize()*gp.getMaxCol(),
                gp.getTileSize()*gp.getMaxRow());
        robot.draw(g2);
        server.draw(g2);
        cookieManager.draw(g2);

    }

    public void update(){
        robot.update();
        server.update();
        cookieManager.update();

    }

    public void setShield(boolean shieldUp){
        robot.setShieldUp(shieldUp);
    }

    public void createCookie(){
        cookieManager.createCookie();
    }

    public Rectangle robotShieldArea(){
        return robot.getShieldSolidArea();
    }

    public Rectangle robotArea(){
        return robot.getSolidArea();
    }

    public boolean isShieldUp(){
        return robot.isShieldUp();
    }

    public int getServerX(){
        return server.getServerX();
    }

    public int getServerY(){
        return server.getServerY();
    }



}

class Robot {

    GamePanel gp;

    private final int x;
    private final int y;
    private final int width;
    private final int height;

    private BufferedImage robotImage,shieldOne,shieldTwo,shieldThree,shieldFour;

    /*PUZZLE LOGIC VARIABLES*/
    private int spriteCounter = 1;
    private int spriteNum = 1;
    private final int maxCookies = 6;
    private int cookiesRemaining = maxCookies;
    private int cookieRechargeTimer = 0;
    private int throwTimer = 0;
    private boolean canThrow;
    private boolean shieldUp = false;
    private final Rectangle solidArea;

    /*SHIELD VARIABLES*/
    private final int shieldX;
    private final int shieldY;
    private final int shieldWidth;
    private final int shieldHeight;
    private final Rectangle shieldSolidArea;




    public Robot(GamePanel gp){
        this.gp = gp;
        this.x = gp.getTileSize() * (gp.getMaxCol() - 4);
        this.y = gp.getTileSize() * (gp.getMaxRow() - 4) - 24;
        this.width = gp.getTileSize() * 4;
        this.height = gp.getTileSize() * 4;
        this.solidArea = new Rectangle(x + gp.getTileSize(),y + gp.getTileSize(),width,height);

        this.shieldX = x - gp.getTileSize();
        this.shieldY = y - gp.getTileSize();
        this.shieldWidth = width + gp.getTileSize()/3;
        this.shieldHeight = height + gp.getTileSize()/3;

        shieldSolidArea = new Rectangle(shieldX + gp.getTileSize(), shieldY + gp.getTileSize()
                ,width,height);

        initImage();
    }

    public void draw(Graphics2D g2){
        g2.drawImage(robotImage,x,y,width,height,null);
        if(shieldUp){
            BufferedImage currentImage = null;
            switch (spriteNum){
                case 1 -> currentImage = shieldOne;
                case 2 -> currentImage = shieldTwo;
                case 3 -> currentImage = shieldThree;
                case 4 -> currentImage = shieldFour;
            }
            g2.drawImage(currentImage, shieldX, shieldY,shieldWidth, shieldHeight,null);
        }
    }

    public void update(){
        if(!canThrow){
            throwTimer++;
            if(throwTimer >= 8){
                canThrow = true;
            }
        }
        if(cookiesRemaining < maxCookies){
            cookieRechargeTimer++;
            if(cookieRechargeTimer % 72 == 0){
                cookiesRemaining++;
            }
        }
        spriteCounter++;
        if(spriteCounter > 6){
            if(spriteNum == 1){
                spriteNum = 2;
            }else if(spriteNum == 2){
                spriteNum = 3;
            }else if(spriteNum == 3){
                spriteNum = 4;
            }else{
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }


    private void initImage(){
        try {
            robotImage = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("puzzleOneAssets/robot.png")));
            shieldOne = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("puzzleOneAssets/shield.png")));
            shieldTwo = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("puzzleOneAssets/shield-2.png")));
            shieldThree = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("puzzleOneAssets/shield-3.png")));
            shieldFour = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("puzzleOneAssets/shield-4.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void setShieldUp(boolean shieldUp) {
        this.shieldUp = shieldUp;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public Rectangle getShieldSolidArea() {
        return shieldSolidArea;
    }

    public boolean isShieldUp(){
        return shieldUp;
    }
}

class CookieManager{

    private final GamePanel gp;

    private final ArrayList<Cookie> cookies = new ArrayList<>();
    private final BufferedImage[] cookieImages = new BufferedImage[6];

    public CookieManager(GamePanel gp){
        initImages();
        this.gp = gp;
    }

    public void update(){
        checkDeleted();
        for (Cookie cookie : cookies){
            cookie.update();
        }
    }

    public void draw(Graphics2D g2){
        for(Cookie cookie : cookies){
            cookie.draw(g2);
        }
    }

    public void createCookie(){
        int random = (int) (Math.random() * cookieImages.length);
        Cookie cookie = new Cookie(cookieImages[random], gp);
        cookies.add(cookie);
    }

    private void checkDeleted(){
        ArrayList<Cookie> deleted = new ArrayList<>();
        for (Cookie cookie : cookies){
            if(cookie.isDeleted()){
                deleted.add(cookie);
            }
        }
        for (Cookie d : deleted){
            cookies.remove(d);
        }
    }

    private void initImages(){
        for (int i = 0; i < cookieImages.length; i++) {
            try{
                cookieImages[i] = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("puzzleOneAssets/cookie-" + (i + 1) + ".png")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public BufferedImage getCookieImage(){
        return cookieImages[4];
    }
}

class Cookie{

    private final GamePanel gp;
    private final BufferedImage image;
    private int x;
    private int y;
    private final int width;
    private final int height;
    private boolean deleted = false;

    public Cookie(BufferedImage image, GamePanel gp){
        this.gp = gp;
        this.x = gp.getTileSize() * (gp.getMaxCol() - 4);
        this.y = gp.getTileSize() * (gp.getMaxCol() - 4);
        this.width = gp.getTileSize() + 12;
        this.height = gp.getTileSize() + 12;
        this.image = image;

    }

    public void update(){
        if(this.x > gp.getServerX() && this.y > gp.getServerY()){
            y-=2;
            x-=2;
        }else{
            deleted = true;
        }
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void draw(Graphics2D g2){
        g2.drawImage(image,x,y,width,height,null);
    }
}

class Server{
    private final GamePanel gp;

    private int x,y,width,height;
    private BufferedImage serverOne,serverTwo,serverThree,serverFour;

    /*SERVER LOGIC VARIABLES*/
    private final Attack attack;
    private int attackTimer = 0;
    private int spriteCounter = 1;
    private int spriteNum = 1;
    private boolean preAttack = false;
    private boolean attacking = false;

    public Server(GamePanel gp){
        this.x = 0;
        this.y = 0;
        this.gp = gp;
        this.width = gp.getTileSize() * 3;
        this.height = gp.getTileSize() * 4;
        attack = new Attack(gp);
        startImages();
    }

    public void draw(Graphics2D g2){
        BufferedImage currentImage = null;
        switch (spriteNum){
            case 1 -> currentImage = serverOne;
            case 2 -> currentImage = serverTwo;
            case 3 -> currentImage = serverThree;
            case 4 -> currentImage = serverFour;
        }
        g2.drawImage(currentImage,x,y,width,height,null);
        if(attacking){
            attack.draw(g2);
        }
    }

    public void update(){
        attackTimer ++;
        if(attackTimer == 120){
            preAttack = true;
        }
        if(attackTimer == 180){
            preAttack = false;
            attacking = true;
            attack.resetCoordinates();
        }

        if(preAttack){
            spriteCounter += 1;
            if(spriteCounter > 10){
                if(spriteNum == 1){
                    spriteNum = 2;
                }else if(spriteNum == 2){
                    spriteNum = 3;
                }else if(spriteNum == 3){
                    spriteNum = 4;
                }
                spriteCounter = 0;
            }
        }else{
            spriteNum = 1;
        }
        if(attacking){
            attack.update();
            if(attack.attackDone){
                attacking = false;
                attack.attackDone = false;
                this.attackTimer = 0;
            }
        }
    }


    private void startImages(){
        try{
            serverOne = ImageIO.read(getClass().getClassLoader().getResourceAsStream("puzzleOneAssets/server-1.png"));
            serverTwo = ImageIO.read(getClass().getClassLoader().getResourceAsStream("puzzleOneAssets/server-2.png"));
            serverThree = ImageIO.read(getClass().getClassLoader().getResourceAsStream("puzzleOneAssets/server-3.png"));
            serverFour = ImageIO.read(getClass().getClassLoader().getResourceAsStream("puzzleOneAssets/server-4.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public int getServerX(){
        return x + width - gp.getTileSize() * 2;
    }

    public int getServerY(){
        return y + height - gp.getTileSize() * 2;
    }
}

class Attack{

    private final GamePanel gp;

    private final int initialX;
    private final int initialY;
    private int x,y,width,heigth;
    private BufferedImage attackOne,attackTwo,attackThree;
    private BufferedImage currentImage = null;
    boolean attackDone = false;
    private final Rectangle solidArea;
    private int defaultSolidAreaX, defaultSolidAreaY;


    public Attack(GamePanel gp){
        this.gp = gp;
        initialX = gp.getTileSize();
        initialY = gp.getTileSize();
        this.width = gp.getTileSize()*3;
        this.heigth = gp.getTileSize()*3;
        solidArea = new Rectangle(0,0,width+gp.getTileSize(),heigth-gp.getTileSize());
        init();
        resetCoordinates();
    }

    public void update(){
        checkCollision();
        if(x < 600 && y < 600){
            x += 6;
            y += 6;
        }else{
            attackDone = true;
        }
    }

    public void draw(Graphics2D g2){
        g2.drawImage(currentImage,x,y,width,heigth,null);
    }


    public void resetCoordinates(){
        x = initialX;
        y = initialY;
        attackDone = false;
        int random = (int) (Math.random()*3);
        if(random == 0){
            currentImage = attackOne;
        }else if(random == 1){
            currentImage = attackTwo;
        }else{
            currentImage = attackThree;
        }
    }

    public void checkCollision(){
        this.solidArea.x = defaultSolidAreaX + x;
        this.solidArea.y = defaultSolidAreaY + y;

        if(gp.getShieldUp()){
            if(this.solidArea.intersects(gp.getRobotShieldArea())){
                this.attackDone=true;
            }
        }
        else{
            //TODO: IMPLEMENT ROBOT-ATTACK COLLISION
            if(solidArea.intersects(gp.getRobotArea())){
                this.attackDone = true;
            }
        }

        this.solidArea.x = defaultSolidAreaX;
        this.solidArea.y = defaultSolidAreaY;
    }


    private void init(){
        try{
            attackOne = ImageIO.read(getClass().getClassLoader().getResourceAsStream("puzzleOneAssets/ataque-1.png"));
            attackTwo = ImageIO.read(getClass().getClassLoader().getResourceAsStream("puzzleOneAssets/ataque-2.png"));
            attackThree = ImageIO.read(getClass().getClassLoader().getResourceAsStream("puzzleOneAssets/ataque-3.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
