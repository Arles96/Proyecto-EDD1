package BicolorableGraph;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;

import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class Nodo implements Serializable{
    protected String id ;
    protected String text;
    protected int posX;
    protected int posY;
    protected int width;
    protected int height;
    protected Font font;
    protected Color color;
    protected Color fontColor;
    protected boolean selected;
    
    protected ArrayList<Nodo> nextNode = new ArrayList();
    
    protected final int MIN_HEIGHT = 25;
    protected final int MIN_WIDTH = 40;
    
    protected final int OFF_WIDTH = 30;
    protected final int OFF_HEIGHT = 15;

    public Nodo() {
        this.posX = 0;
        this.posY = 0;
        this.color = Color.BLUE;
        this.fontColor = Color.BLACK;
        this.font = new Font("Tahoma", Font.PLAIN, 11);
        this.text = "";
        this.selected = false;
        adjustSize();
    }
    
    public Nodo(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        this.color = Color.BLUE;
        this.fontColor = Color.BLACK;
        this.font = new Font("Tahoma", Font.PLAIN, 11);
        this.text = "";
        this.selected = false;
        adjustSize();
    }
    
    public Nodo(Color color, int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        this.color = color;
        this.font = new Font("Tahoma", Font.PLAIN, 11);
        this.text = "";
        this.selected = false;
        adjustSize();
    }
    
    public Nodo(Color color, Font font, int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        this.color = color;
        this.fontColor = Color.BLACK;
        this.font = font;
        this.text = "";
        this.selected = false;
        adjustSize();
    }
    
    public Nodo(Color color, Font font, Color fontColor, int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        this.color = color;
        this.fontColor = fontColor;
        this.font = font;
        this.text = "";
        this.selected = false;
        adjustSize();
    }

    public Nodo(String text, int posX, int posY, Color color) {
        this.text = text;
        this.posX = posX;
        this.posY = posY;
        this.font = new Font("Tahoma", 1, 11);
        this.color = color;
        this.fontColor = Color.BLACK;
        this.selected = false;
        adjustSize();
    }
    
    public Nodo(String id, String text, int posX, int posY, int width, int height, Font font, Color color) {
        //auto generate id
        this.id = id;
        this.text = text;
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.font = font;
        this.color = color;
        this.fontColor = Color.BLACK;
        this.selected = false;
        adjustSize();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        adjustSize();
        checkText();
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
        adjustSize();
    }

    public Color getFontColor() {
        return fontColor;
    }

    public void setFontColor(Color fontColor) {
        this.fontColor = fontColor;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public ArrayList<Nodo> getNextNode() {
        return nextNode;
    }

    public Nodo getNextNode(int i) {
        return nextNode.get(i);
    }
    
    public void setNextNode(ArrayList<Nodo> nextNode) {
        this.nextNode = nextNode;
    }

    public void setNextNode(Nodo nextNode, int i) {
        this.nextNode.set(i, nextNode);
    }

    public void addNextNode(Nodo nextNode) {
        if(this.nextNode.indexOf(nextNode) < 0){
            this.nextNode.add(nextNode);
            System.out.println("Agregado");
        }
    }
    
    public int getMIN_HEIGHT() {
        return MIN_HEIGHT;
    }

    public int getMIN_WIDTH() {
        return MIN_WIDTH;
    }

    public int getOFF_WIDTH() {
        return OFF_WIDTH;
    }

    public int getOFF_HEIGHT() {
        return OFF_HEIGHT;
    }
    
    public Rectangle getBounds(){
        return new Rectangle(posX, posY, width, height);
    }
    
    public abstract Polygon getPolygon();
    
    protected void adjustSize(){
        if(!this.text.equals("")){
            this.width = getFontSize()[0] + OFF_WIDTH;
            this.height = getFontSize()[1] + OFF_HEIGHT;
        }else{
            this.width = MIN_WIDTH;
            this.height = MIN_HEIGHT;
        }
    }
    
    public int[] getFontSize(){
        int[] size = new int[2];
        AffineTransform affinetransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affinetransform,true,true); 
        
        size[0] = (int)font.getStringBounds(text, frc).getWidth();
        size[1] = (int)font.getStringBounds(text, frc).getHeight();
        
        return size;
    }
    
    public abstract void checkText();
    
    public Point getCenterPoint(){
        return new Point(this.posX + this.width / 2, this.posY + this.height / 2);
    }

    @Override
    public String toString() {
        if(nextNode == null){
            return ("Nodo{" + "id=" + id + ", text=" + text + ", posX=" + posX + ", posY=" + posY + ", width=" + width + ", height=" + height + ", font=" + font + ", color=" + color + ", fontColor=" + fontColor + ", selected=" + selected + ", nextNode.id=" + "null" + ", MIN_HEIGHT=" + MIN_HEIGHT + ", MIN_WIDTH=" + MIN_WIDTH + ", OFF_WIDTH=" + OFF_WIDTH + ", OFF_HEIGHT=" + OFF_HEIGHT + '}').replace(",","\n");
        }else{
            return ("Nodo{" + "id=" + id + ", text=" + text + ", posX=" + posX + ", posY=" + posY + ", width=" + width + ", height=" + height + ", font=" + font + ", color=" + color + ", fontColor=" + fontColor + ", selected=" + selected + ", nextNode=" + nextNode + ", MIN_HEIGHT=" + MIN_HEIGHT + ", MIN_WIDTH=" + MIN_WIDTH + ", OFF_WIDTH=" + OFF_WIDTH + ", OFF_HEIGHT=" + OFF_HEIGHT + '}').replace(",","\n");
        }
    }
}
