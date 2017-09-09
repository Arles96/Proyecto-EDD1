/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dijkstra;

import abstractClass.AbstractGraph;
import java.util.ArrayList;

/**
 *
 * @author arles96
 */
public class GraphDijkstra extends AbstractGraph {
    
    
    
    //Atributos
    private ArrayList<Node> nodes = new ArrayList();
    
    //Constructor

    public GraphDijkstra() {
    }
    
    //getter ande setter

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }
    
    //Administration methods

    @Override
    public void remove(Object object) {
        if (object instanceof Node) {
            nodes.remove((Node)object);
            for (Node node : nodes) {
                for ( int i=0; i<node.getConnections().size(); i++) {
                    if (node.getConnection(i).equals(object)) {
                        node.removeConnection(i);
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void clear() {
        nodes.clear();
    }

    @Override
    public boolean isEmpty() {
        return nodes.isEmpty();
    }

    @Override
    public boolean exists(Object object) {
        return nodes.stream().anyMatch((node) -> (node.equals(object)));
    }

    @Override
    public void insert(Object object) {
        nodes.add((Node)object);
    }
    
    public void makeConnection(Node node1, Node node2){
        nodes.forEach((node) -> {
            if (node.equals(node1)) {
                node.addConnection(node);
            }else if (node.equals(node2)) {
                node.addConnection(node1);
            }
        });
    }
    
    /*private void lowerCost(ArrayList<Node> list, Node node1, Node node2, int accumulator){
        for (Node node : nodes) {
            if (node.equals(node1) && accumulator==0) {
                for (Node connection :  node.getConnections()) {
                    if (connection.equals(node2)) {
                        list.add(connection);
                        break;
                    }
                    
                }
            }
        }
    }
    
    public ArrayList<Node> dijkstra(Node node1, Node node2){
        ArrayList<Node> list = new ArrayList();
        lowerCost(list, node1, node2, 0);
        return list;
    }*/
    
}
