/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import BicolorableGraph.BicoloreablePanel;
import Dijkstra.PanelDijkstra;
import Huffman.HuffManPanel;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import PerformanceEvaluation.PanelEvaluation;
import java.awt.Graphics;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import math.Interfase;
/**
 *
 * @author arles96
 */
public class MainMenu extends javax.swing.JFrame {
    
    //Atributes    
    
    private PanelEvaluation panel_evaluation;
    private PanelDijkstra panel_dijkstra;
    private HuffManPanel panel_huffman;
    private BicoloreablePanel panel_bicorolable;
    private Interfase panel_math;
    
    
    //Constructor
    
    public MainMenu() {
        initComponents();
        configurations();
    }
    
    // administration method
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        if (panel_dijkstra!=null) {
            panel_dijkstra.setSize(this.getWidth(), this.getHeight());
        }
        if (panel_bicorolable!=null) {
            panel_bicorolable.setSize(this.getWidth(), this.getHeight());
        }
        if (panel_evaluation!=null) {
            panel_evaluation.setSize(this.getWidth(), this.getHeight());
        }if (panel_huffman!=null) {
            panel_huffman.setSize(this.getWidth(), this.getHeight());
        }
        if (panel_math!=null) {
            panel_math.setSize(this.getWidth(), this.getHeight());
        }
        mainPanel.setSize(this.getWidth(), this.getHeight());
    }
    
    private void configurations(){
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("../img/logo.jpg")).getImage());    
        try {
            BackgroundBorder border = new 
        BackgroundBorder(ImageIO.read(getClass().getResource("/img/background.png")));
            mainPanel.setBorder(border);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, e.toString());
        }
    }
    
    
    public void addPanel(JPanel panel){
        panel.setSize(this.getWidth(), this.getHeight());
        panel.setLocation(0, 0);
        contentPanel.removeAll();
        contentPanel.add(panel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
        jMenuItemComeback.setEnabled(true);        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contentPanel = new javax.swing.JPanel();
        mainPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        bicolorableGraphButton = new javax.swing.JButton();
        huffmanButton = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        performanceEvaluationButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemComeback = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1261, 638));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("MENU PRINCIPAL");

        jButton1.setText("Menor costo un origen");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        bicolorableGraphButton.setText("grafo bi-coloreable, Floyd, Kruskal y Prim");
        bicolorableGraphButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bicolorableGraphButtonActionPerformed(evt);
            }
        });

        huffmanButton.setText("Compresor de Archivos de Texto");
        huffmanButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                huffmanButtonActionPerformed(evt);
            }
        });

        jButton5.setText("Expresiones Matemáticas");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        performanceEvaluationButton.setText("Cálculo de Desempeño");
        performanceEvaluationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                performanceEvaluationButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(330, 330, 330)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(performanceEvaluationButton, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(328, 328, 328)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bicolorableGraphButton, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 960, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(huffmanButton, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(403, 403, 403))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(performanceEvaluationButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(huffmanButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bicolorableGraphButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(210, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout contentPanelLayout = new javax.swing.GroupLayout(contentPanel);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1261, Short.MAX_VALUE)
        );
        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jMenu1.setText("Archivo");

        jMenuItemComeback.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemComeback.setText("Regresar");
        jMenuItemComeback.setEnabled(false);
        jMenuItemComeback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemComebackActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemComeback);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void performanceEvaluationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_performanceEvaluationButtonActionPerformed
        panel_evaluation = new PanelEvaluation();
        addPanel(panel_evaluation);
    }//GEN-LAST:event_performanceEvaluationButtonActionPerformed

    private void jMenuItemComebackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemComebackActionPerformed
        if (panel_evaluation!=null) {
            panel_evaluation = null;
        }
        if (panel_dijkstra!=null) {
            panel_dijkstra = null;
        }
        if (panel_huffman!=null) {
            panel_huffman = null;
        }
        if (panel_bicorolable!=null) {
            panel_bicorolable = null;
        }
        addPanel(mainPanel);
        jMenuItemComeback.setEnabled(false);
    }//GEN-LAST:event_jMenuItemComebackActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        panel_dijkstra = new PanelDijkstra();
        addPanel(panel_dijkstra);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void huffmanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_huffmanButtonActionPerformed
        panel_huffman = new HuffManPanel();
        addPanel(panel_huffman);
    }//GEN-LAST:event_huffmanButtonActionPerformed

    private void bicolorableGraphButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bicolorableGraphButtonActionPerformed
        panel_bicorolable = new BicoloreablePanel();
        addPanel(panel_bicorolable);
    }//GEN-LAST:event_bicolorableGraphButtonActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        panel_math = new Interfase();
        addPanel(panel_math);
    }//GEN-LAST:event_jButton5ActionPerformed

    /**
     * @param args the command line arguments
     */
    /*public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        /*try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        /*java.awt.EventQueue.invokeLater(() -> {
            new MainMenu().setVisible(true);
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bicolorableGraphButton;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JButton huffmanButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItemComeback;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton performanceEvaluationButton;
    // End of variables declaration//GEN-END:variables

}
