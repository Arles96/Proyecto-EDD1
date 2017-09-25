/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dijkstra;

import abstractClass.AbstractGraph;
import javax.swing.JOptionPane;

/**
 *
 * @author arles96
 */
public class GraphDijkstra extends AbstractGraph {
    
    //Atributos
    private Node[] nodes;
    private Node[] solution;
    private int size;
    private int counter;
    
    //Constructor

    public GraphDijkstra(int size) {
        this.size = size;
        counter = 0;
        nodes = new Node[this.size];
        //solution = new Node[this.size];
    }
    
    //getter ande setter

    public Node[] getNodes() {
        return nodes;
    }

    public void setNodes(Node[] nodes) {
        this.nodes = nodes;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
        Node [] temporal = nodes;
        nodes = new Node[this.size];
        for (int i = 0; i < counter; i++) {
            nodes[i] = temporal[i];
            if (nodes[i]!=null) {
                nodes[i].setSize(size);
            }
        }
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void setSolution(Node[] solution) {
        this.solution = solution;
    }
    
    public Node getNode(int index){
        return nodes[index];
    }
       
    //Administration methods

    @Override
    public void remove(Object object) {
        if (object instanceof Node) {
            for (int i = 0; i < size; i++) {
                if (nodes[i]!=null) {
                    nodes[i].removeConnection((Node)object);
                    if (nodes[i].equals(object)) {
                        nodes[i] = null;
                        counter--;
                    }
                }                
            }
        }else{
            JOptionPane.showMessageDialog(null, "Error al eliminar");
        }
    }

    @Override
    public void clear() {
        nodes = new Node[size];
    }

    @Override
    public boolean isEmpty() {
        return counter==0;
    }

    @Override
    public boolean exists(Object object) {
        if (object instanceof Node) {
            for (int i = 0; i < counter; i++) {
                if (nodes[i].equals(object)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void insert(Object object) {
        if (counter==size) {
            JOptionPane.showMessageDialog(null, "Error, la cantidad de nodes" + 
                    "es igual al maximo de nodos.");
        }else {
            nodes[counter] = (Node) object;
            counter++;
        }
    }
    
    public void makeConnection(Node node1, Node node2, int distance){
        if (nodes[node1.getVertex()-1]!=null) {
            nodes[node1.getVertex()-1].addConnection(node2, distance);
        }else {
            JOptionPane.showMessageDialog(null, "El nodo no existe.");
        }
    }
    
    // private method for dijkstra
    private void path(Node[] list, Node node){        
        for (int i = 0; i < counter; i++) {
            if (list[i]!=null) {
                list[i].setAccumulator(node.getDistance(i));
            }
        }
        for (int i = 1; i < counter; i++) {
            int less = 0;
            int index = -1;
            boolean checker = false;
            for (int j = 0; j < counter; j++) {
                if (list[j]!=null && j!=node.getVertex()-1) {
                    if (!checker && !list[j].isSolution()) {
                        less = list[j].getAccumulator();
                        index = j;
                        checker = true;
                    }
                    if (list[j].getAccumulator()!=0) {
                        if (less>list[j].getAccumulator() && !list[j].isSolution()) {
                            less = list[j].getAccumulator();
                            index = j;
                        }
                    }
                    if (i>1) {
                        if (solution[i-1].getConnection(j)!=null) {
                            int accumulator = solution[i-1].getAccumulator() + solution[i-1].getDistance(j);
                            if (accumulator<list[j].getAccumulator()) {
                                list[j] = solution[i-1].getConnection(j);
                                list[j].setAccumulator(accumulator);
                                if (less>list[j].getAccumulator()) {
                                    less = list[j].getAccumulator();
                                    index = j;
                                }
                            }
                        }
                    }
                }else {
                    if (i>1 && node.getVertex()-1!=j) {
                        if (solution[i-1].getConnection(j)!=null) {
                            list[j] = solution[i-1].getConnection(j);
                            int accumulator = solution[i-1].getAccumulator() + solution[i-1].getDistance(j);
                            list[j].setAccumulator(accumulator);
                            if (!checker) {
                                less = list[j].getAccumulator();
                                checker = true;
                                index = j;
                            }else {
                                if (list[j].getAccumulator()!=0) {
                                    if (less>list[j].getAccumulator() && !list[j].isSolution()) {
                                        less=list[j].getAccumulator();
                                        index = j;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (index>-1) {
                list[index].setSolution(true);
                solution[i] = list[index];
            }else {
                break;
            }
        }
    }
    
    public Node[] dijkstra(Node node){
        Node [] list = node.getConnections().clone();
        solution = new Node[size];
        solution[0] = node;
        solution[0].setAccumulator(0);
        path(list,node);
        return solution;
    }
    
    public boolean isOccupied(int x, int y){
        for (int i = 0; i < counter; i++) {
            if (x>nodes[i].getX()-Node.DIAMETER && x<nodes[i].getX()+Node.DIAMETER 
                    && y>nodes[i].getY()-Node.DIAMETER && y<nodes[i].getY() + Node.DIAMETER) {
                return true;
            }
        }
        return false;
    }
    
    public Node getGraphicNode(int x, int y){
        for (int i = 0; i < counter; i++) {
             if (x>nodes[i].getX()-Node.DIAMETER && x<nodes[i].getX()+Node.DIAMETER 
                    && y>nodes[i].getY()-Node.DIAMETER && y<nodes[i].getY() + Node.DIAMETER) {
                return nodes[i];
            }
        }
        return null;
    }
    
    public boolean existConnection(Node node1, Node node2){
        return !(node1.getConnection(node2.getVertex()-1)==null && 
                node2.getConnection(node1.getVertex()-1)==null);
    }
    
}
