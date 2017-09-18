package BicolorableGraph;

import java.awt.Color;
import java.awt.Font;
import java.awt.Polygon;


public class Proceso extends Nodo{
    
    public Proceso() {
        super();
    }

    public Proceso(int posX, int posY) {
        super(posX, posY);
    }

    public Proceso(Color color, int posX, int posY) {
        super(color, posX, posY);
    }

    public Proceso(Color color, Font font, int posX, int posY) {
        super(color, font, posX, posY);
    }

    public Proceso(Color color, Font font, Color fontColor, int posX, int posY) {
        super(color, font, fontColor, posX, posY);
    }
    
    public Proceso(String text, int posX, int posY, Color color) {
        super(text, posX, posY, color);
    }
    
    
    public Polygon getPolygon(){
        int[] xS = new int[]{posX, posX + width, posX + width, posX, posX};
        int[] yS = new int[]{posY, posY, posY + height, posY + height, posY};
        Polygon newPolygon = new Polygon(xS, yS, 5);
        return newPolygon;
    }

    //Check syntax errors in text
    @Override
    public void checkText() {
        
    }
}
