package puzzle.labyrinth;

import frame.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Labyrinth {

    private final GamePanel gp;
    private boolean started = false;
    private boolean gameFinished = false;

    private final int maxCol = 20;
    private final int maxRow = 20;
    private final int nodeSize = 20;
    private final int screenWidth = maxCol * nodeSize;
    private final int screenHeight = maxRow * nodeSize;


    Node[][] node = new Node[maxCol][maxRow];
    Node startNode, goalNode, currentNode;

    ArrayList<Node> openList = new ArrayList<>();
    ArrayList<Node> checkedList = new ArrayList<>();
    ArrayList<Node> parentNodes;
    ArrayList<Node> currentParents = new ArrayList<>();

    boolean goalReached = false;

    public Labyrinth(GamePanel gp){
        this.gp = gp;

        int col = 0;
        int row = 0;
        while (col < maxCol && row < maxRow){
            node[col][row] = new Node(col,row);
            col++;
            if(col == maxCol){
                col = 0;
                row++;
            }
        }

        setSolidPatterns();

        while(true){
            int colStart = (int) (Math.random() * maxCol - 1);
            int rowStart = 19;
            if(!node[colStart][rowStart].solid){
                setStartNode(colStart,rowStart);
                break;
            }
        }

        while (true) {
            int colEnd = (int) (Math.random() * maxCol - 1);
            int endRow = 0;
            if(!node[colEnd][endRow].solid){
                setGoalNode(colEnd,endRow);
                break;
            }
        }
        autoSearch();

    }

    public void update(){
        if(currentParents.size() == parentNodes.size()){
            gameFinished = true;
            gp.setKeys();
            gp.setGameState(gp.getControllingRobot());
        }
    }

    public void draw(Graphics2D g2){
        g2.fillRect(0,0,gp.getWidth(),gp.getHeight());
        g2.setColor(Color.green);

        int x = gp.getTileSize() * maxCol / 2 - screenWidth;
        int y = gp.getTileSize() * maxRow / 2 - screenHeight;

        g2.drawRect(x, y, screenWidth,screenHeight);
        for(Node[] nodes : node){
            for(Node node : nodes){
                if(node.start){
                    g2.setColor(Color.BLUE);
                    g2.fillRect(x + nodeSize * node.col, y + nodeSize * node.row, nodeSize,nodeSize);
                    g2.setColor(Color.gray);
                    g2.drawRect(x + nodeSize * node.col, y + nodeSize * node.row, nodeSize,nodeSize);
                }else if(node.goal){
                    g2.setColor(Color.YELLOW);
                    g2.fillRect(x + nodeSize * node.col, y + nodeSize * node.row, nodeSize,nodeSize);
                    g2.setColor(Color.white);
                    g2.drawString("end",(x + nodeSize * node.col), (y + nodeSize * node.row));
                    g2.setColor(Color.gray);
                    g2.drawRect(x + nodeSize * node.col, y + nodeSize * node.row, nodeSize,nodeSize);
                }else{
                    if(node.clicked){
                        if(!parentNodes.contains(node)){
                            g2.setColor(Color.red);
                        }else{
                            g2.setColor(Color.green);
                        }
                    }else{
                        g2.setColor(Color.black);
                    }
                    g2.fillRect(x + nodeSize * node.col, y + nodeSize * node.row, nodeSize,nodeSize);
                    g2.setColor(Color.gray);
                    g2.drawRect(x + nodeSize * node.col, y + nodeSize * node.row, nodeSize,nodeSize);
                }
            }
        }
    }

    private void autoSearch(){
        while(!goalReached){
            int col = currentNode.col;
            int row = currentNode.row;
            currentNode.setAsChecked();
            checkedList.add(currentNode);
            openList.remove(currentNode);

            if(row - 1 >= 0){
                openNode(node[col][row-1]);
            }
            if(col - 1 >= 0){
                openNode(node[col-1][row]);
            }
            if(row + 1 < maxRow){
                openNode(node[col][row+1]);
            }
            if(col + 1 < maxCol){
                openNode(node[col+1][row]);
            }

            int bestNodeIndex = 0;
            int bestNodeFCost = 999;
            for(int i = 0; i < openList.size(); i++){
                Node current = openList.get(i);
                if(current.fCost < bestNodeFCost){
                    bestNodeIndex = i;
                    bestNodeFCost = current.fCost;
                }else if(current.fCost == bestNodeFCost){
                    if(current.gCost < openList.get(bestNodeIndex).gCost){
                        bestNodeIndex = i;
                    }
                }
            }
            currentNode = openList.get(bestNodeIndex);
            if(currentNode == goalNode){
                goalReached = true;
                trackThePath();
                parentNodes = buildPath();
                for (Node n : parentNodes){
                    n.partOfThePath = true;
                }
            }
        }
    }

    public void setNodeClicked(int col, int row){
        if(!node[col][row].start || !node[col][row].goal){
            if(!node[col][row].clicked){
                node[col][row].clicked = true;
                if(parentNodes.contains(node[col][row])){
                    currentParents.add(node[col][row]);
                }
            }
        }
    }


    private void setSolidPatterns(){
        int random = (int) (Math.random() * 3);
        if(random == 0){
            setSolidNode(0,2);
            setSolidNode(2,2);
            setSolidNode(5,4);
            setSolidNode(7,5);
            setSolidNode(1,2);
            setSolidNode(4,3);
            setSolidNode(4,0);
            setSolidNode(4,1);
            setSolidNode(4,2);
            setSolidNode(6,7);
        }else if(random == 1){
            setSolidNode(2,1);
            setSolidNode(2,2);
            setSolidNode(2,3);
            setSolidNode(3,1);
            setSolidNode(3,0);
            setSolidNode(4,3);
            setSolidNode(5,3);
            setSolidNode(5,4);
            setSolidNode(5,5);
            setSolidNode(5,6);
            setSolidNode(6,6);
            setSolidNode(7,6);
        }else if (random == 2){
            setSolidNode(1,1);
            setSolidNode(1,2);
            setSolidNode(1,3);
            setSolidNode(2,1);
            setSolidNode(3,1);
            setSolidNode(4,1);
            setSolidNode(5,1);
        }
    }

    private void setSolidNode(int col, int row){
        node[col][row].setAsSolid();
    }

    private void setStartNode(int col, int row){
        node[col][row].setAsStart();
        startNode = node[col][row];
        currentNode = startNode;
    }

    private void setGoalNode(int col, int row){
        node[col][row].setAsGoal();
        goalNode = node[col][row];
    }

    private void openNode(Node node){
        if(!node.open && !node.checked && !node.solid){
            node.setAsOpen();
            node.parent = currentNode;
            openList.add(node);
        }
    }

    private void trackThePath(){
        Node current = goalNode;
        while (current != startNode){
            current = current.parent;
            if(current != startNode){
                current.setAsPath();
            }
        }
    }

    private ArrayList<Node> buildPath(){
        ArrayList<Node> path = new ArrayList<>();
        Node current = goalNode;
        while(current != null){
            if(!current.goal && !current.start){
                path.add(current);
            }
            if(current == startNode){
                break;
            }
            current = current.parent;
        }
        return path;
    }

    public boolean isGameFinished() {
        return gameFinished;
    }

}


class Node extends JButton implements ActionListener {

    Node parent;
    int col;
    int row;
    int gCost;
    int hCost;
    int fCost;
    boolean start;
    boolean goal;
    boolean solid;
    boolean open;
    boolean checked;
    boolean partOfThePath;
    boolean clicked;

    public Node(int col, int row){
        this.col = col;
        this.row = row;
        setBackground(Color.white);
        setForeground(Color.black);
        addActionListener(this);
    }


    public void setAsStart(){
        setBackground(Color.blue);
        setForeground(Color.white);
        setText("Start");
        start = true;
    }
    public void setAsGoal(){
        setBackground(Color.yellow);
        setForeground(Color.black);
        setText("Goal");
        goal = true;
    }

    public void setAsOpen(){
        open = true;
    }

    public void setAsChecked(){
        if(!start && !goal) {
            setBackground(Color.orange);
            setForeground(Color.black);
        }
        checked = true;
    }

    public void setAsPath(){
        setBackground(Color.green);
        setForeground(Color.black);
    }

    public void setAsSolid(){
        setBackground(Color.black);
        setForeground(Color.black);
        solid = true;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        setBackground(Color.orange);
    }
}
