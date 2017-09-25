/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BicolorableGraph;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Dario
 */
public class CanvasPanel extends javax.swing.JPanel {

    /**
     * Creates new form CanvasPanel
     */
    public CanvasPanel() {
        initComponents();
        diagrama = new ArrayList();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupNodes = new javax.swing.JPopupMenu();
        popItemCreateConection = new javax.swing.JMenuItem();
        popItemTextNode = new javax.swing.JMenuItem();

        popItemCreateConection.setText("Conectar con...");
        popItemCreateConection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popItemCreateConectionActionPerformed(evt);
            }
        });
        popupNodes.add(popItemCreateConection);

        popItemTextNode.setText("Editar texto");
        popItemTextNode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popItemTextNodeActionPerformed(evt);
            }
        });
        popupNodes.add(popItemTextNode);

        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
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
            .addGap(0, 591, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 396, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    ///////////////////////////////////
    ///////////////////////////////////
    ///////////////////////////////////
    ///////////////////////////////////
    ///////////////////////////////////

    //////////////////////////////////////////////////
    //////////////////////////////////////////////////
    //Eventos
    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        nodeCollision = firstCollision(evt.getX(), evt.getY());

        //Seleccion multiple
        if (!evt.isControlDown() || nodeCollision == null) {
            unselectAll();
        }

        //Mostrar popup menu
        if (evt.isMetaDown() && nodeCollision != null) {
            if(getSelectedNodes() <= 1 && connect && nodeCollision != sendingNode){
                connect = false;
                sendingNode.addNextNode(nodeCollision);
            }else{
                popItemCreateConection.setVisible(getSelectedNodes() <= 1);
                popItemTextNode.setVisible(true);
                //popItemConectHere.setVisible(getSelectedNodes() <= 1 && connect && nodeCollision != sendingNode);

                nodeCollision.setSelected(true);

                popupNodes.show(this, evt.getX(), evt.getY());
            }
        }

        //Seleccionar un nodo
        if (evt.getButton() == 1 && nodeCollision != null) {
            nodeCollision.setSelected(!nodeCollision.isSelected());
        }

        repaint();
    }//GEN-LAST:event_formMouseClicked

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        nodeCollision = firstCollision(evt.getX(), evt.getY());
        if (evt.getButton() == 1 && nodeCollision != null) {
            click = true;
        }
        repaint();
    }//GEN-LAST:event_formMousePressed

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        if (evt.getButton() == 1) {
            click = false;
            nodeCollision = null;
        }
        repaint();
    }//GEN-LAST:event_formMouseReleased

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        if (!this.contains(evt.getX(), evt.getY())) {
            click = false;
        }

        if (click && nodeCollision != null) {
            //(Optional)Drag groups
            nodeCollision.setPosX(evt.getX() + offX);
            nodeCollision.setPosY(evt.getY() + offY);
        }

        repaint();
    }//GEN-LAST:event_formMouseDragged

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        if (!this.contains(evt.getX(), evt.getY())) {
            click = false;
        }
        
        repaint();
    }//GEN-LAST:event_formMouseMoved

    private void popItemCreateConectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popItemCreateConectionActionPerformed
        connect = true;
        sendingNode = nodeCollision;
    }//GEN-LAST:event_popItemCreateConectionActionPerformed

    private void popItemTextNodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popItemTextNodeActionPerformed
        //Editar texto de nodos seleccionados
        String newText = JOptionPane.showInputDialog(this, "Ingrese el valor:");
        if (newText != null) {
            applyToSelected(newText);
        }
        repaint();
    }//GEN-LAST:event_popItemTextNodeActionPerformed

    /////////////////////////////////////////////
    /////////////////////////////////////////////
    //Metodos de administracion
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Nodo nodo : diagrama) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", 0, 11));
            //Draw Lines
            if(!nodo.getNextNode().isEmpty()){
                for (Nodo nodo2 : nodo.getNextNode()) {
                    if(!(nodo2 instanceof Terminal)){
                        double val = Math.pow(Math.pow(nodo.getCenterPoint().x - nodo2.getCenterPoint().x, 2) 
                                + Math.pow(nodo.getCenterPoint().y - nodo2.getCenterPoint().y, 2), 0.5);

                        g.drawLine(nodo.getCenterPoint().x, nodo.getCenterPoint().y, 
                           nodo2.getCenterPoint().x, nodo2.getCenterPoint().y);

                        if(gDraw != null){
                            val = gDraw.getMatrix(diagrama.indexOf(nodo), nodo.getNextNode().indexOf(nodo2));
                            g.drawString(String.valueOf((int)(val)), 
                                    (nodo.getCenterPoint().x + nodo2.getCenterPoint().x)/2,
                                    (nodo.getCenterPoint().y + nodo2.getCenterPoint().y)/2);
                        }else{
                            g.drawString(String.valueOf((int)(val)),
                                    (nodo.getCenterPoint().x + nodo2.getCenterPoint().x)/2,
                                    (nodo.getCenterPoint().y + nodo2.getCenterPoint().y)/2);
                        }
                    }
                }
            }
            
            
            //Draw terminals
            if (nodo instanceof Terminal) {
                //Draw node
                g.setColor(nodo.getColor());
                g.fillRoundRect(nodo.getPosX(), nodo.getPosY(), nodo.getWidth(), nodo.getHeight(), (int) (nodo.getWidth() * 0.30), (int) (nodo.getWidth() * 0.30));

                //Draw text
                g.setColor(nodo.getFontColor());
                g.setFont(nodo.getFont());
                g.drawString(nodo.getText(), nodo.getPosX() + 15, nodo.getPosY() + ((int) nodo.getHeight()) / 2 + nodo.getFontSize()[1] / 2);

                //Draw selection outline
                if (nodo.isSelected()) {
                    g.setColor(SELECTION_COLOR);
                    g.drawRoundRect(nodo.getPosX(), nodo.getPosY(), nodo.getWidth(), nodo.getHeight(), (int) (nodo.getWidth() * 0.30), (int) (nodo.getWidth() * 0.30));
                }
            } else {
                //Draw node
                g.setColor(nodo.getColor());
                g.fillPolygon(nodo.getPolygon());

                //Draw text
                g.setColor(nodo.getFontColor());
                g.setFont(nodo.getFont());
                g.drawString(nodo.getText(), nodo.getPosX() + nodo.getOFF_WIDTH() / 2, nodo.getPosY() + ((int) nodo.getHeight()) / 2 + nodo.getFontSize()[1] / 2);

                //Draw selection outline
                if (nodo.isSelected()) {
                    g.setColor(SELECTION_COLOR);
                    g.drawPolygon(nodo.getPolygon());
                }
            }
        }
    }

    public void drawGraph(Graph graph, Color color){
        for (int i = 0; i < graph.getSize(); i++) {
            Nodo newNode = new Proceso(String.valueOf(i), this.getWidth()/2, this.getHeight()/2, color);
            this.addNode(newNode);
            for (int j = 0; j < graph.getSize(); j++) {
                this.diagrama.get(i).addNextNode(new Terminal());
            }
            System.out.println(this.diagrama.get(i).getNextNode().size());
        }
        
        for (int i = 0; i < graph.getSize(); i++) {
            for (int j = 0; j < graph.getSize(); j++) {
                if(graph.getMatrix(i, j) > 0 && graph.getMatrix(i, j) < 5000){
                    diagrama.get(i).setNextNode(diagrama.get(j), j);
                }
            }
        }
        gDraw = graph;
        repaint();
    }
    
    public String getChartName() {
        return chartName;
    }

    public void setChartName(String chartName) {
        this.chartName = chartName;
    }
    
    public void clean(){
        diagrama = new ArrayList();
        gDraw = null;
        repaint();
    }
    /*public int addNode(Nodo node) throws myException {
        int cTerminal = getTerminals();

        if (node instanceof Terminal) {
            if (cTerminal < 2) {
                boolean inicioExists = false;
                if (cTerminal == 1) {
                    for (Nodo nodo : diagrama) {
                        if (nodo instanceof Terminal) {
                            inicioExists = ((Terminal) nodo).isInicio();
                            break;
                        }
                    }
                }
                if (cTerminal == 0) {
                    inicioExists = false;
                }

                ((Terminal) node).setInicio(!inicioExists);
                node.setText((!inicioExists) ? "Inicio" : "Fin");
            } else {
                throw new myException("Solo se puede tener dos terminales");
            }
        }
        this.diagrama.add(node);

        return cTerminal;
    }*///Viejo

    public int addNode(Nodo node) {
        int cTerminal = getTerminals();
        
        node.setId(generateIdNode(node));

        this.diagrama.add(node);

        return cTerminal;
    }

    public ArrayList<Nodo> getDiagrama() {
        return diagrama;
    }
    
    public void setDiagrama(ArrayList<Nodo> diagrama) {
        this.diagrama = diagrama;
    }

    private String generateIdNode(Nodo node) {
        String id = chartName + node.getClass().getSimpleName();
        int cName = 0;
        for (int i = 0; i < diagrama.size(); i++) {
            String nodeName = diagrama.get(i).getId();
            if (id.concat("" + cName).equals(nodeName)) {
                cName++;
                i = -1;
            }
        }
        id += cName;
        return id;
    }

    //get the first collisioned node
    private Nodo firstCollision(int x, int y) {
        Rectangle mouse = new Rectangle(x, y, 1, 1);
        for (Nodo nodo : diagrama) {
            if (nodo instanceof Terminal) {
                if (mouse.intersects(nodo.getBounds())) {
                    offX = nodo.getPosX() - x;
                    offY = nodo.getPosY() - y;
                    return nodo;
                }
            } else if (nodo.getPolygon().contains(x, y))/*(mouse.intersects(nodo.getBounds()))*/ {
                offX = nodo.getPosX() - x;
                offY = nodo.getPosY() - y;
                return nodo;
            }
        }
        return null;
    }

    //unselect all nodes
    private void unselectAll() {
        for (Nodo nodo : diagrama) {
            nodo.setSelected(false);
        }
    }

    //Change atributes to all selected nodes
    public void applyToSelected(Color color) {
        for (Nodo nodo : diagrama) {
            if (nodo.isSelected()) {
                nodo.setColor(color);
            }
        }
    }

    public void applyToAll(Color color) {
        for (Nodo nodo : diagrama) {
            nodo.setColor(color);
        }
    }
    
    public void applyToSelected(Font font) {
        for (Nodo nodo : diagrama) {
            if (nodo.isSelected()) {
                nodo.setFont(font);
            }
        }
    }

    public void applyToSelected(Color color, Font font) {
        for (Nodo nodo : diagrama) {
            if (nodo.isSelected()) {
                nodo.setColor(color);
                nodo.setFont(font);
            }
        }
    }

    public void applyToSelected(Font font, Color fontColor) {
        for (Nodo nodo : diagrama) {
            if (nodo.isSelected()) {
                nodo.setFont(font);
                nodo.setFontColor(fontColor);
            }
        }
    }

    public void applyToSelected(Color color, Font font, Color fontColor) {
        for (Nodo nodo : diagrama) {
            if (nodo.isSelected()) {
                nodo.setColor(color);
                nodo.setFont(font);
                nodo.setFontColor(fontColor);
            }
        }
    }

    public void applyToSelected(String text) {
        for (Nodo nodo : diagrama) {
            if (nodo.isSelected()) {
                nodo.setText(text);
            }
        }
    }

    public int getTerminals() {
        int cTerminal = 0;
        for (Nodo nodo : diagrama) {
            if (nodo instanceof Terminal) {
                cTerminal++;
            }
        }
        return cTerminal;
    }
    
    public int getSelectedNodes(){
        int c = 0;
        for (Nodo nodo : diagrama) {
            if(nodo.isSelected())
                c++;
        }
        return c;
    }
    
    ///////////////////////////////////
    ///////////////////////////////////
    ///////////////////////////////////
    ///////////////////////////////////
    ///////////////////////////////////
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem popItemCreateConection;
    private javax.swing.JMenuItem popItemTextNode;
    private javax.swing.JPopupMenu popupNodes;
    // End of variables declaration//GEN-END:variables

    //Uso externo
    private ArrayList<Nodo> diagrama;
    private String chartName;
    public Graph gDraw = null;

    //Uso interno
    private boolean click;
    private int offX;
    private int offY;
    private Nodo nodeCollision;
    private Nodo sendingNode;
    private boolean connect;

    //Constantes
    public final int DESIGN_MODE = 0;
    public final int LINES_MODE = 1;
    private final Color SELECTION_COLOR = new Color(51, 153, 255);
}
