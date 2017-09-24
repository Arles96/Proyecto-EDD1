package BicolorableGraph;

import java.awt.Color;
import java.awt.Font;
import java.awt.Polygon;
import java.util.ArrayList;

public class Terminal extends Nodo {
    private boolean inicio;
    private ArrayList<Nodo> nextNode;

    public Terminal() {
        super();
    }

    public Terminal(boolean inicio) {
        this.inicio = inicio;
    }

    public Terminal(int posX, int posY) {
        super(posX, posY);
    }

    public Terminal(Color color, int posX, int posY) {
        super(color, posX, posY);
    }

    public Terminal(Color color, Font font, int posX, int posY) {
        super(color, font, posX, posY);
    }

    public Terminal(Color color, Font font, Color fontColor, int posX, int posY) {
        super(color, font, fontColor, posX, posY);
    }

    public Terminal(String id, String text, int posX, int posY, int width, int height, Font font, Color color) {
        super(id, text, posX, posY, width, height, font, color);
    }

    public boolean isInicio() {
        return inicio;
    }

    public boolean isFinal() {
        return !inicio;
    }

    public void setInicio(boolean inicio) {
        this.inicio = inicio;
    }

    @Override
    public void setText(String text) {
        if (this.text.isEmpty()) {
            this.text = text;
        }
        
        adjustSize();
        checkText();
    }

    @Override
    public Polygon getPolygon() {
        return null;
    }

    @Override
    public void checkText() {
        
    }
}
