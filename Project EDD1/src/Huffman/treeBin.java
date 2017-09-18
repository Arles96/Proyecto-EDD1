package Huffman;

import java.io.Serializable;

public class treeBin implements Serializable{
    //Variables
    private treeBin left;
    private treeBin right;
    private Object value;
    private int label;
    
    public treeBin(){
        value = null;
    }

    public treeBin(Object value) {
        this.value = value;
    }

    public treeBin(Object value, int label) {
        this.value = value;
        this.label = label;
    }

    public treeBin(treeBin left, treeBin right, Object value, int label) {
        this.left = left;
        this.right = right;
        this.value = value;
        this.label = label;
    }
    
    public treeBin getLeft() {
        return left;
    }

    public void setLeft(treeBin left) {
        this.left = left;
    }

    public treeBin getRight() {
        return right;
    }

    public void setRight(treeBin right) {
        this.right = right;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }
    
    public void insertNode(treeBin n){
        if(n.getLabel() <= label){
            if(left == null){
                left = n;
            }else{
                left.insertNode(n);
            }
        }else if(n.getLabel() > label){
            if(right == null){
                right = n;
            }else{
                right.insertNode(n);
            }
        }
    }

    public Object getTree(int t){
        treeBin pTree = this;
        while(t != 1 || t != 0){
            if(t % 2 == 0){
                pTree = pTree.getLeft();
            }else{
                pTree = pTree.getRight();
            }
        }
        return pTree;
    }
    
    public Object getTree(boolean[] bArr){
        treeBin pTree = this;
        for(int i = 0; i < bArr.length; i++){
            if(pTree == null)
                break;
            if(!bArr[i]){
                pTree = pTree.getLeft();
            }else{
                pTree = pTree.getRight();
            }
        }
        return pTree;
    }
    
    public boolean hasChildren(){
        return (left != null || right != null);
    }
    
    @Override
    public String toString() {
        return "treeBin{" + "left=" + left + ", right=" + right + ", value=" + value + ", label=" + label + '}';
    }
}
