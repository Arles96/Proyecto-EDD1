package BicolorableGraph;

import abstractClass.AbstractTree;
import java.util.ArrayList;


public class Tree extends AbstractTree{
    Object value;
    ArrayList<Tree> children;
    Object label;
    String to;
    Tree father;
    
    public Tree() {
    }

    public Tree(Object value) {
        this.value = value;
        this.label = null;
        children = new ArrayList();
    }
    
    public Tree(Object value, Object label) {
        this.value = value;
        this.label = label;
        children = new ArrayList();
    }
    
    public Tree(Object value, ArrayList<Tree> children, int label) {
        this.value = value;
        this.children = children;
        this.label = label;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public ArrayList<Tree> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Tree> children) {
        this.children = children;
    }
    
    public void addChildren(Tree tree){
        this.children.add(tree);
    }

    public Object getLabel() {
        return label;
    }

    public void setLabel(Object label) {
        this.label = label;
    }

    public boolean hasChildren(){
        return (!children.isEmpty());
    }

    @Override
    public String toString() {
        return "Tree{" + "value=" + value + ", children=" + children + ", label=" + label + '}';
    }

    @Override
    public void insertRoot(Object object) {
        value = object;
    }

    @Override
    public void insertLeaf(Object father, Object son) {
        Tree newTree = new Tree();
        newTree.father = (Tree)father;
        newTree.addChildren((Tree)son);
        this.children.add(newTree);
    }

    @Override
    public boolean exists(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isLeaf() {
        return !hasChildren();
    }

    @Override
    public String print() {
        return "";
    }

    @Override
    public void clear() {
        children = new ArrayList();
    }

    @Override
    public void remove(Object object) {
        for (int i = 0; i < children.size(); i++) {
            if(children.get(i) == (Tree)object){
                children.remove(i);
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return (children.isEmpty());
    }
}