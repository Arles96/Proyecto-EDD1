/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import javax.swing.border.Border;

/**
 *
 * @author arles96
 */
public class BackgroundBorder implements Border{
    
    //Atributes
    
    private BufferedImage image = null;
    
    //Contructor 

    public BackgroundBorder(BufferedImage image) {
        this.image = image;
    }
    
    // administratio methods    

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        if (image!=null) {
            g.drawImage(image, 0, 0, width, height, null);
        }
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(0, 0, 0, 0);
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }
    
}
