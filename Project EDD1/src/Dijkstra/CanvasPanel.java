/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dijkstra;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Arles Cerrato
 */
public class CanvasPanel extends javax.swing.JPanel {

    //Atributes
    
    private int counter = 1;
    private GraphDijkstra graph;
    private int size = 0;
    private boolean useCanvas = false; 
    private DefaultListModel model = new DefaultListModel();
    private boolean pressed = false;
    
    //Constructor
    
    public CanvasPanel() {
        initComponents();
    }
    
    //Getter and setter

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public GraphDijkstra getGraph() {
        return graph;
    }

    public void setGraph(GraphDijkstra graph) {
        this.graph = graph;
    }

    public int getSizeGraph() {
        return size;
    }

    public void setSizeGraph(int size) {
        if (this.size==0) {
            this.size = size;
            graph = new GraphDijkstra(size);
        }else {
            this.size = size;
            graph.setSize(size);
        }
    }

    public boolean isUseCanvas() {
        return useCanvas;
    }

    public void setUseCanvas(boolean useCanvas) {
        this.useCanvas = useCanvas;
    }

    public DefaultListModel getModel() {
        return model;
    }

    public void setModel(DefaultListModel model) {
        this.model = model;
    }

    public boolean isPressed() {
        return pressed;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }
    
    //Administration Methods
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        if (graph!=null) {
            for (int i = 0; i < graph.getSize(); i++) {
                for (int j = 0; j < 10; j++) {
                    if (graph.getNode(i)!=null) {
                        graph.getNode(i).paint(g);
                    }
                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(java.awt.Color.white);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 667, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 455, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        if (!useCanvas) {
            JOptionPane.showMessageDialog(this, "Presione el boton de habilitar " +
                    "para agregar nodos.");
        }else{
            if (counter-1==size && size>0) {
                JOptionPane.showMessageDialog(this, "Se ha llegado al limite de nodos.");
                return;
            }
            if (size==0) {
                JOptionPane.showMessageDialog(this, "Ingrese un tamaño de nodos");
                return;
            }
            if (evt.getButton() == MouseEvent.BUTTON1) {
                if (!graph.isOccupied(evt.getX(), evt.getY())) {
                    Node node = new Node(counter, evt.getX(), evt.getY(), size);
                    graph.insert(node);
                    model.addElement(node);
                    counter++;
                    repaint();
                }else {
                    JOptionPane.showMessageDialog(this, "Es lugar ya esta ocupado.");
                }                
            }
        }
    }//GEN-LAST:event_formMouseClicked

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        pressed = true;
    }//GEN-LAST:event_formMousePressed

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        pressed = false;
    }//GEN-LAST:event_formMouseReleased

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        if (graph.getGraphicNode(evt.getX(),evt.getY())!=null && evt.getX()-Node.DIAMETER/2>0 && 
                evt.getX()+Node.DIAMETER/2<getWidth() && evt.getY()-Node.DIAMETER/2>0 && 
                evt.getY()+Node.DIAMETER/2<getHeight()) {
            graph.getGraphicNode(evt.getX(),evt.getY()).setX(evt.getX());
            graph.getGraphicNode(evt.getX(),evt.getY()).setY(evt.getY());
            repaint();
        }
    }//GEN-LAST:event_formMouseDragged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
