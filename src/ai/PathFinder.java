package ai;

import main.GamePanel;

import java.util.ArrayList;

public class PathFinder {

    GamePanel gp;
    Node[][] node;
    ArrayList<Node> openList = new ArrayList<>();
    public ArrayList<Node> pathList = new ArrayList<>(); // cu aceasta lista bosii pot urmari drumul
    Node startNode, goalNode, currentNode;
    boolean goalReached = false;
    int step = 0;

    public PathFinder(GamePanel gp){
        this.gp = gp;
        instantiateNode();
    }

    public void instantiateNode(){
        node = new Node[gp.maxScreenCol][gp.maxScreenRow];

        int col = 0;
        int row = 0;

        // creaam nod pentru fiecale tile de pe mapa
        while(col < gp.maxScreenCol && row < gp.maxScreenRow){
            node[col][row] = new Node(col, row);

            col++;
            if(col == gp.maxScreenCol){
                col = 0;
                row++;
            }
        }
    }

    public void resetNodes() {
        int col = 0;
        int row = 0;

        while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
            // resetam open, checked si solid state
            node[col][row].open = false;
            node[col][row].checked = false;
            node[col][row].solid = false;

            col++;
            if(col == gp.maxScreenCol){
                col = 0;
                row++;
            }
        }

        // resetam alte setari
        openList.clear();
        pathList.clear();
        goalReached = false;
        step = 0;
    }

    public void setNodes(int startCol, int startRow, int goalCol, int goalRow) {
        resetNodes();

        // aici atribuim setarile nodului curent
        // setam Start si Goal node
        startNode = node[startCol][startRow];
        currentNode = startNode;
        goalNode = node[goalCol][goalRow];
        openList.add(currentNode);

        int col = 0;
        int row = 0;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow){
            // trebuie sa stim ce noduri ar trebui sa fie solide
            // SET SOLID NODE
            // verificam  tile-urile
            int tileNum = gp.tileM.mapTileNum[gp.currentMap][col][row];
            if(gp.tileM.tile[tileNum].collision == true){
                node[col][row].solid = true;
            }
            // SET COST
            getCost(node[col][row]);

            col++;
            if(col == gp.maxScreenCol){
                col = 0;
                row++;
            }
        }
    }

    public void getCost(Node node){

        // G COST
        int xDistance = Math.abs(node.col - startNode.col);
        int yDistance = Math.abs(node.row - startNode.row);
        node.gCost = xDistance + yDistance;

        // H COST
        xDistance = Math.abs(node.col - goalNode.col);
        yDistance = Math.abs(node.row - goalNode.row);
        node.hCost = xDistance + yDistance;

        // F COST
        node.fCost = node.gCost + node.hCost;
    }

    public boolean seach(){

        while(goalReached == false && step < 500) {

            int col = currentNode.col;
            int row = currentNode.row;

            // verificam nodul curent
            currentNode.checked = true;
            openList.remove(currentNode);

            // deschidem nodul "Up"
            if(row - 1 >= 0){
                openNode(node[col][row - 1]);
            }

            // deschidem nodul "Left"
            if(col - 1 >= 0){
                openNode(node[col - 1][row]);
            }

            // deschidem nodul "Down"
            if(row + 1 < gp.maxScreenRow){
                openNode(node[col][row + 1]);
            }

            // deschidem nodul "Right"
            if(row + 1 < gp.maxScreenCol){
                openNode(node[col + 1][row]);
            }

            // cautam nodul cel mai bun
            int bestNodeIndex = 0;
            int bestNodefCost = 999;

            for(int i = 0; i < openList.size(); i++){

                // verificam daca F cost-ul acestui nod e mai bun
                if(openList.get(i).fCost < bestNodefCost){
                    bestNodeIndex = i;
                    bestNodefCost = openList.get(i).fCost;
                }

                // daca F costurile sunt egale, verificam G Cost
                else if (openList.get(i).fCost == bestNodefCost) {
                    if(openList.get(i).gCost < openList.get(bestNodeIndex).gCost){
                        bestNodeIndex = i;
                    }
                }
            }

            // daca nu e niciun nod in openList, terminam bucla
            if(openList.size() == 0){
                break;
            }

            // dupa bucla, openList[bestNodeIndex] este urmatorul pas (= currentNode)
            currentNode = openList.get(bestNodeIndex);

            if(currentNode == goalNode){
                goalReached = true;
                trackThePath();
            }
            step++;
        }

        return goalReached;
    }

    public void openNode(Node node){

        if(node.open == false && node.checked == false && node.solid == false){

            node.open = true;
            node.parent = currentNode;
            openList.add(node);
        }
    }

    public void trackThePath(){

        Node current = goalNode;

        // mergem de la goal node la start node (backtrack / mers inapoi)
        while(current != startNode){

            pathList.add(0, current);
            current = current.parent;
        }
    }
}
