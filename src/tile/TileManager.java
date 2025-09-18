package tile;

import frame.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {

    private final GamePanel gp;
    private final Tile[] doorClosedControlling = new Tile[144];
    private final Tile[] doorClosedStealth = new Tile[144];
    private final Tile[] doorOpenControlling = new Tile[144];
    private final int[][] mapTileNum;

    public TileManager(GamePanel gp){
        this.gp = gp;
        mapTileNum = new int[gp.getMaxCol()][gp.getMaxRow()];
        startImages();
        loadMap();
    }

    public void draw(Graphics2D g2){
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;


        Tile[] current;

        if(gp.isDoorOpen()){
            current = doorOpenControlling;
        }else{
            if(gp.getGameState() == gp.getStealth()){
                current = doorClosedStealth;
            }else{
                current = doorClosedControlling;
            }
        }


        while(col < gp.getMaxCol() && row < gp.getMaxRow()){
            g2.drawImage(current[mapTileNum[col][row]].getImage(),x,y,gp.getTileSize(),gp.getTileSize(),null);
            col++;
            x+=gp.getTileSize();

            if(col == gp.getMaxCol()){
                col = 0;
                x = 0;
                row++;
                y += gp.getTileSize();
            }
        }
    }

    public void startImages(){
        for (int i = 0; i < doorClosedControlling.length; i++) {
            try {
                doorClosedControlling[i] = new Tile();
                doorClosedControlling[i].setImage(ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tileAssets/doorClosedControlling/" + (i + 1) + ".png"))));
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        setCollisionOn(doorClosedControlling);

        for (int i = 0; i < doorClosedStealth.length; i++) {
            try {
                doorClosedStealth[i] = new Tile();
                doorClosedStealth[i].setImage(ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tileAssets/doorClosedStealth/" + (i + 1) + ".png"))));
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        setCollisionOn(doorClosedStealth);

        for (int i = 0; i < doorOpenControlling.length; i++) {
            try {
                doorOpenControlling[i] = new Tile();
                doorOpenControlling[i].setImage(ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tileAssets/doorOpenControlling/" + (i + 1) + ".png"))));
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        setCollisionOn(doorOpenControlling);
    }

    public void loadMap(){
        try{
            InputStream is = getClass().getResourceAsStream("/maps/sala-1.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;
            while(col < gp.getMaxCol() && row < gp.getMaxRow()){
                String line = br.readLine();
                while(col < gp.getMaxCol()){
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.getMaxCol()){
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setCollisionOn(Tile[] tiles){
        for (int i = 0; i < 24; i++) {
            tiles[i].setCollision(true);
        }

        for(int i = 0; i < 132; i += 12){
            tiles[i].setCollision(true);
        }

        for(int i = 11; i < 144; i += 12){
            tiles[i].setCollision(true);
        }
    }

    public boolean getDoorClosedControlling(int tilNum) {
        return doorClosedControlling[tilNum].isCollision();
    }

    public int getMapTileNum(int col, int row){
        return mapTileNum[col][row];
    }
}
