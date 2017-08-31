/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.edd1.PerformanceEvaluation;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author arles96
 */
public class Tree {
    
    private Employee rooth;
    private ArrayList <Tree> tree = new ArrayList();
    
    //constructor

    public Tree(Employee rooth) {
        this.rooth = rooth;
    }
    
    // getter and setter

    public Employee getRooth() {
        return rooth;
    }

    public void setRooth(Employee rooth) {
        this.rooth = rooth;
    }

    public ArrayList<Tree> getTree() {
        return tree;
    }

    public void setTree(ArrayList<Tree> tree) {
        this.tree = tree;
    }
    
    //Administration methods
    
    public void insert(Employee person, int id){
        if (rooth.getId()==id) {
            tree.add(new Tree(person));
        }else {
            for (Tree t : tree) {
                if (t.getRooth().getId()==id) {
                    t.getTree().add(new Tree(person));
                    break;
                }
            }
        }
    }
    
    public boolean isEmpty(){
        return rooth == null;
    }
    
    public void clear(){
        rooth = null;
        tree.clear();
    }
    
    public void remove(int id){
        for (Tree t : tree) {
            if (t.getRooth().getId()==id) {
                tree.remove(t);
                break;
            }
        }
    }
    
    public boolean isLeaf(){
        return tree.size()==-1;
    }
    
    
    public void grades(){
        if (!isLeaf()) {
            int accumulator = 0;
            for (Tree t : tree) {
                if (t.isLeaf()) {
                    accumulator+=t.getRooth().getValue();
                }else {
                    t.grades();
                    accumulator += t.getRooth().getGrade();
                }
            }
            rooth.setGrade(accumulator);
        }
    }
    
    public boolean isExists(int id){
        if (rooth.getId()==id) {
            return true;
        }
        else return tree.stream().anyMatch((t) -> (t.getRooth().getId()==id));        
    }
    
}
