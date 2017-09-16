/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dijkstra;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author arles96
 */

public class Node {
    
    //Atributes
    private int vertex;
    private int x;
    private int y;
    private int distance;
    private int size;
    private int accumulator;
    private boolean solution;
    private Node [] connections;
    private int counter = 0;
    public static final int DIAMETER = 40;

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

    public Node(int vertex, int x, int y, int size) {
        this.vertex = vertex;
        this.x = x;
        this.y = y;
        this.size = size;
        this.connections = new Node[this.size];
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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
    
    //administration methods
    
    public void addConnection(Node node, int distance){
        if (connections[node.getVertex()-1]==null) {
            node.setDistance(distance);
            connections[node.getVertex()-1] = node;
            System.out.println(connections[node.getVertex()-1].getVertex());
            counter++;
        }else {
            JOptionPane.showMessageDialog(null, "Ya existe una conexión.");
        }
    }
    
    public void removeConnection(Node node){
        if (connections[node.getVertex()-1]!=null) {
            connections[node.getVertex()-1] = null;
            counter--;
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
    
    private void paintRedNode(Graphics g){
        g.setColor(Color.red);
        g.fillOval(x-DIAMETER/2, y-DIAMETER/2, DIAMETER, DIAMETER);
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, 12));
        g.drawString("" + vertex, x, y);
    }
    
    private void paintRedArrow(Graphics g, int i){
        double ang, angSep;
        double tx,ty;
        int dist;
        int x2,y2;
        int x3, y3;
        int xs, ys;
        int r = DIAMETER/2;
        if (x+r<getConnection(i).getX() && y+r<getConnection(i).getY()) {
            x2 = getConnection(i).getX()-r;
            y2 = getConnection(i).getY() - r/2;
            x3 = x + r;
            y3 = y + r/2;
            xs = (x2-x3)/2  + x3;
            ys = (y2-y3)/2 + y3;
        }else if (x-r>getConnection(i).getX() && y+r>getConnection(i).getY()) {
            x2 = getConnection(i).getX() + r ;
            y2 = getConnection(i).getY() + r/2;
            x3 = x - r;
            y3 = y - r/2;
            xs = (x3-x2)/2  + x2;
            ys = (y3-y2)/2 + y2;
        }else if (x-r>getConnection(i).getX() && y+r<getConnection(i).getY()) {
            x2 = getConnection(i).getX() + r;
            y2 = getConnection(i).getY() - r/2;
            x3 = x - r;
            y3 = y + r/2;
            xs = (x3-x2)/2 + x2;
            ys = (y2-y3)/2 + y3;
        }else if (x+r<getConnection(i).getX() && y-r>getConnection(i).getY()) {
            x2 = getConnection(i).getX() - r;
            y2 = getConnection(i).getY() + r/2;
            x3 = x + r;
            y3 = y - r/2;
            xs = (x2-x3)/2 + x3;
            ys = (y3-y2)/2 + y2;
        }else if (x+r>getConnection(i).getX() && x-r<getConnection(i).getX()
                && y+r<getConnection(i).getY()) {
            x2 = getConnection(i).getX();
            y2 = getConnection(i).getY() - r;
            x3 = x;
            y3 = y + r;
            xs = x3 - 15;
            ys = (y2-y3)/2 + y3;
        }else if (x+r>getConnection(i).getX() && x-r<getConnection(i).getX()
                && y-r>getConnection(i).getY()) {
            x2 = getConnection(i).getX();
            y2 = getConnection(i).getY() + r;
            x3 = x;
            y3 = y - r;
            xs = x2 + 15;
            ys = (y3-y2)/2 + y2;
        }else if (y+r>getConnection(i).getY() && y-r<getConnection(i).getY()
                && x+r<getConnection(i).getX()) {
            y2 = getConnection(i).getY();
            x2 = getConnection(i).getX() - r;
            y3 = y;
            x3 = x + r;
            xs = (x2-x3)/2 + x3;
            ys = y3 + 10;
        }else {
            y2 = getConnection(i).getY();
            x2 = getConnection(i).getX() + r;
            y3 = y;
            x3 = x - r;
            xs = (x3-x2)/2 + x2;
            ys = y2 - 10;
        }
        Point point1,point2;
        point1=new Point(x3,y3);
        point2=new Point(x2,y2);
        dist=15;
        ty=-(point1.y-point2.y)*1.0;
        tx=(point1.x-point2.x)*1.0;
        ang=Math.atan (ty/tx);
        if(tx<0)
        {// si tx es negativo aumentar 180 grados
           ang+=Math.PI;
        }
        //puntos de control para la punta
        //p1 y p2 son los puntos de salida
        Point p1=new Point(),p2=new Point(),point=point2;
        //angulo de separacion
        angSep=25.0;
        p1.x=(int)(point.x+dist*Math.cos (ang-Math.toRadians (angSep)));
        p1.y=(int)(point.y-dist*Math.sin (ang-Math.toRadians (angSep)));
        p2.x=(int)(point.x+dist*Math.cos (ang+Math.toRadians (angSep)));
        p2.y=(int)(point.y-dist*Math.sin (ang+Math.toRadians (angSep)));
        Graphics2D g2D=(Graphics2D)g;
        //dale color a la linea
        g.setColor (Color.red);
        // grosor de la linea
        g2D.setStroke (new BasicStroke(2.5f));
        //dibuja la linea de extremo a extremo
        g.drawLine (point1.x,point1.y,point.x,point.y);
        //dibujar la punta
        g.drawLine (p1.x,p1.y,point.x,point.y);
        g.drawLine (p2.x,p2.y,point.x,point.y);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", 2,18));
        g.drawString("" + getConnection(i).getDistance(), xs, ys);
    }
    
    public void paint(Graphics g){
        if (counter==0) {
            paintRedNode(g);
        }else {
            paintRedNode(g);
            for (int i = 0; i < size; i++) {
                if (getConnection(i)!=null) {
                    paintRedArrow(g, i);
                }
            }
        }
    }
    
}
