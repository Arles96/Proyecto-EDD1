/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dijkstra;

import java.util.ArrayList;
import java.util.Objects;
import javax.swing.JOptionPane;

/**
 *
 * @author arles96
 */
public class Node {
    
    //Atributes
    private String vertex;
    private int distance;
    private ArrayList<Node> connections = new ArrayList();

    //Constructor
    public Node(String vertex, int distance) {
        this.vertex = vertex;
        this.distance = distance;
    }

    public Node(String vertex) {
        this.vertex = vertex;
    }
    
    //getter and setter

    public String getVertex() {
        return vertex;
    }

    public void setVertex(String vertex) {
        this.vertex = vertex;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public ArrayList<Node> getConnections() {
        return connections;
    }

    public void setConnections(ArrayList<Node> connections) {
        this.connections = connections;
    }
    
    //administration methods
    
    public void addConnection(Node node){
        boolean checker = false;
        for (Node connection : connections) {
            if (connection.equals(node)) {
                checker = true;
            }
        }
        if (checker==true) {
            JOptionPane.showMessageDialog(null, "Es un elemento repetido.");
        }else {
            connections.add(node);
            JOptionPane.showMessageDialog(null, "Se ha realizado una conexión.");
        }
    }
    
    public void removeConnection(int index){
        connections.remove(index);
    }
    
    public Node getConnection(int index){
        return connections.get(index);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.vertex);
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
        return Objects.equals(this.vertex, other.vertex);
    }
    
    
    
}
