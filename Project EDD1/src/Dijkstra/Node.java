/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dijkstra;

/**
 *
 * @author arles96
 */

public class Node {
    
    //Atributes
    private int vertex;
    private int distance;
    private int size;
    private int accumulator;
    private boolean solution;
    private Node [] connections;
    private Node brother;

    //Constructor

    public Node(int vertex, int size) {
        this.vertex = vertex;
        this.size = size;
        connections = new Node[this.size];
        this.solution = false;
        accumulator = 0;
    }

    public Node(int vertex) {
        this.vertex = vertex;
        this.solution = false;
        accumulator = 0;
    }    
    
    //getter and setter

    public int getVertex() {
        return vertex;
    }

    public void setVertex(int vertex) {
        this.vertex = vertex;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
        if (accumulator==0) {
            setAccumulator(distance);
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Node[] getConnections() {
        return connections;
    }

    public void setConnections(Node[] connections) {
        this.connections = connections;
    }   

    public int getAccumulator() {
        return accumulator;
    }

    public void setAccumulator(int accumulator) {
        this.accumulator = accumulator;
    }

    public boolean isSolution() {
        return solution;
    }

    public void setSolution(boolean visited) {
        this.solution = visited;
    }

    public Node getBrother() {
        return brother;
    }

    public void setBrother(Node brother) {
        this.brother = brother;
    }
    
    //administration methods
    
    public void addConnection(Node node){
        for (int i = 0; i < size; i++) {
            if (i+1==node.getVertex()) {
                connections[i]= node;
                break;
            }
        }
    }
    
    public void removeConnection(Node node){
        for (int i = 0; i < size; i++) {
            if (i+1==node.getVertex()) {
                connections[i] = null;
                break;
            }
        }
    }
    
    public Node getConnection(int index){
        return connections[index];
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.vertex;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Node other = (Node) obj;
        return this.vertex == other.vertex;
    }

    @Override
    public String toString() {
        return "" + vertex;
    }
    
    

}
