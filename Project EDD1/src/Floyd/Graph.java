package Floyd;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author CJ
 */
public class Graph {

    protected ArrayList<Node> nodes;
    protected int bass;
    protected int[][] costs;

    public Graph() {
    }

    public Graph(ArrayList<Node> nodes) {
        this.nodes = nodes;
        costs = new int[nodes.size()][];
        fillCost();
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public Node getNode(int i) {
        if (i < nodes.size()) {
            return nodes.get(i);
        }
        return null;
    }

    public void setNodes(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }

    public int getBass() {
        return bass;
    }

    public void setBass(int bass) {
        this.bass = bass;
    }

    public void fillCost() {
        for (int i = 0; i < nodes.size(); i++) {
            costs[i] = nodes.get(i).getCosts();
        }
    }

    public int[][] getCosts() {
        return costs;
    }

    public void setCosts(int[][] costs) {
        this.costs = costs;
    }

    public void convert(BicolorableGraph.Graph g, int n) {
        costs = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    costs[i][j] = 0;
                } else {
                    costs[i][j] = g.getMatrix(i, j);
                }
            }
        }
        this.nodes = null;
    }
}

/*
GraphStream
 */
