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
    private int counter2;
    private int size;
    private int counter;
    
    //Constructor

    public GraphDijkstra(int size) {
        this.size = size;
        counter = 0;
        nodes = new Node[this.size];
        solution = new Node[this.size];
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
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public Node[] getSolution() {
        return solution;
    }

    public void setSolution(Node[] solution) {
        this.solution = solution;
    }

    public int getCounter2() {
        return counter2;
    }

    public void setCounter2(int counter2) {
        this.counter2 = counter2;
    }
       
    //Administration methods

    @Override
    public void remove(Object object) {
        
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
    
    public void makeConnection(Node node1, Node node2){
        for (int i = 0; i < counter; i++) {
            if (nodes[i].equals(node1)) {
                nodes[i].addConnection(node2);
                break;
            }
        }
    }
    
    
    //TODO: Terminar este algoritmo
    private void path(Node[] list){
        Node temporal = null;
        for (int i = 0; i < size; i++) {
            int less = 0;    
            int index = -1;
            if (temporal==null) {
                for (int j = 0; j < list.length; j++) {
                    if (list[j].getConnection(j)!= null && less==0) {
                        less = list[j].getConnection(j).getAccumulator();
                        temporal = list[i].getConnection(j);
                        index = j;
                    }
                    if (less!=0 && less>list[j].getAccumulator()) {
                        less = list[j].getAccumulator();
                        temporal = list[j].getConnection(j);
                        index = j;
                    }
                }
            }else {
                for (int j = 0; j < size; j++) {
                    if (list[j]==null && solution[i-1].getConnection(j)!=null) {
                        solution[i-1].getConnection(j).setAccumulator(solution[i-1].getAccumulator() + 
                                solution[i-1].getConnection(j).getAccumulator());
                        list[j] = solution[i-1].getConnection(j);
                    }
                    if (list[j].getConnection(j)!= null && less==0 && !list[j].isSolution()) {
                        less = list[j].getConnection(j).getAccumulator();
                        temporal = list[i].getConnection(j);
                        index = j;
                    }
                    if (less!=0 && less>list[j].getAccumulator() && !list[i].isSolution()) {
                        less = list[j].getAccumulator();
                        temporal = list[j].getConnection(j);
                        index = j;
                    }
                    
                }
            }
            if (temporal!=null && index!=-1) {
                list[index].setSolution(true);
                //temporal.setSolution(true);
                solution[i] = temporal;
            } 
        }      
    }
    
    public void dijkstra(Node node){
        Node [] list = node.getConnections();
        path(list);
    }
    
    
    
    
}
