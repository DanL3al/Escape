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
    private boolean gameOver;

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
        if(server.getHeat() >= server.getMaxHeat()){
            this.puzzleSolved = true;
            gp.setGameState(gp.getControllingRobot());
        }
        if(gp.getHealth() == 0){
            this.puzzleSolved = false;
            this.puzzleStarted = false;
            gp.setGameState(gp.getControllingRobot());
        }
        robot.update();
        server.update();
        cookieManager.update();

    }

    public void setShield(boolean shieldUp){
        robot.setShieldUp(shieldUp);
    }

    public int getCookiesRemaining(){
        return robot.getCookiesRemaining();
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

    public BufferedImage getShieldImage(){
        return robot.getShieldImage();
    }

    public BufferedImage getCookieImage(){
        return cookieManager.getCookieImage();
    }

    public boolean getRobotCanThrow(){
        return robot.isCanThrow();
    }

    public void setRobotCookiesRemaining(){
        robot.setCookiesRemaining();
    }

    public int getServerHeat(){
        return server.getHeat();
    }

    public void setServerHeat(){
        server.setHeat();
    }

    public void setHealth(){
        robot.setHealth();
    }

    public int getHealth(){
        return robot.getHealth();
    }

    public BufferedImage getHeart(){
        return robot.getHeart();
    }

    public BufferedImage getCurrentHeatImage(){
        return server.getCurrentHeat();
    }

}

class Robot {

    GamePanel gp;

    private final int x;
    private final int y;
    private final int width;
    private final int height;

    private BufferedImage robotImage,shieldOne,shieldTwo,shieldThree,shieldFour;
    private BufferedImage heart;

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
    private int health = 3;

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
            if(cookieRechargeTimer % 58 == 0){
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
            heart = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("puzzleOneAssets/heart.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setCookiesRemaining(){
        this.cookiesRemaining--;
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

    public BufferedImage getShieldImage(){
        return shieldOne;
    }

    public int getCookiesRemaining() {
        return cookiesRemaining;
    }

    public boolean isCanThrow(){
        return canThrow;
    }

    public void setHealth(){
        health--;
    }

    public int getHealth(){
        return health;
    }

    public BufferedImage getHeart() {
        return heart;
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
            gp.setServerHeat();
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
    private BufferedImage currentHeat, heatZero,heatOne,heatTwo,heatThree,heatFour;

    /*SERVER LOGIC VARIABLES*/
    private final Attack attack;
    private int attackTimer = 0;
    private int spriteCounter = 1;
    private int spriteNum = 1;
    private boolean preAttack = false;
    private boolean attacking = false;
    private int heat = 0;
    private final int maxHeat = 400;

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
        if(heat < 80){
            currentHeat = heatZero;
        }else if(heat < 200){
            currentHeat = heatOne;
        }else if(heat < 280){
            currentHeat = heatTwo;
        }else if(heat < 360){
            currentHeat = heatThree;
        }else{
            currentHeat = heatFour;
        }

        attackTimer ++;
        if(attackTimer == attack.getAttackCooldown() - 60){
            preAttack = true;
        }
        if(attackTimer == attack.getAttackCooldown()){
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
            heatZero = ImageIO.read(getClass().getClassLoader().getResourceAsStream("puzzleOneAssets/heat-zero.png"));
            heatOne = ImageIO.read(getClass().getClassLoader().getResourceAsStream("puzzleOneAssets/heat-one.png"));
            heatTwo = ImageIO.read(getClass().getClassLoader().getResourceAsStream("puzzleOneAssets/heat-two.png"));
            heatThree = ImageIO.read(getClass().getClassLoader().getResourceAsStream("puzzleOneAssets/heat-three.png"));
            heatFour = ImageIO.read(getClass().getClassLoader().getResourceAsStream("puzzleOneAssets/heat-four.png"));
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

    public int getHeat() {
        return heat;
    }

    public void setHeat() {
        this.heat += (int) (Math.random() * 10);
    }

    public int getMaxHeat(){
        return maxHeat;
    }

    public BufferedImage getCurrentHeat() {
        return currentHeat;
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
    private int attackCooldown;
    private final int[] cooldowns = new int[]{180,68,120,80,90,140};


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
            x += 8;
            y += 8;
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

        attackCooldown = cooldowns[(int)(Math.random() * cooldowns.length)];

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
            if(solidArea.intersects(gp.getRobotArea())){
                this.attackDone = true;
                gp.setHealth();
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

    public int getAttackCooldown() {
        return attackCooldown;
    }
}
