package Floyd;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author CJ
 */
public class Floyd {

    private int size;
    private Graph graph;
    private int[][] costs;
    private int[][] ways;

    public Floyd(Graph graph) {
        this.graph = graph;
        costs = graph.getCosts();
        ways = new int[costs.length][costs[0].length];
        this.size = costs.length;
        calculate();
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    public int[][] getWays() {
        return ways;
    }

    public int[][] getCosts() {
        return costs;
    }

    public void setWays(int[][] ways) {
        this.ways = ways;
    }

    private void calculate() {
        int[][] temp = costs;
        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (temp[i][k] + temp[k][j] < temp[i][j]) {
                        temp[i][j] = temp[i][k] + temp[k][j];
                    }
                }
            }
        }
        ways = temp;
    }

}
