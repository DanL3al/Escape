package tile;

import frame.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    private final GamePanel gp;
    private Tile[] tiles = new Tile[3];
    private int[][] mapTileNum;

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

        while(col < gp.getMaxCol() && row < gp.getMaxRow()){
            g2.drawImage(tiles[mapTileNum[col][row]].getImage(),x,y,gp.getTileSize(),gp.getTileSize(),null);
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
        try{
            tiles[0] = new Tile(true,true,true);
            tiles[0].setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("tileAssets/escape-door.png")));

            tiles[1] = new Tile(false,false,false);
            tiles[1].setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("tileAssets/ground.png")));

            tiles[2] = new Tile(true,true,false);
            tiles[2].setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("tileAssets/puzzle-door.png")));
        }catch (IOException e){
            e.printStackTrace();
        }
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


}
