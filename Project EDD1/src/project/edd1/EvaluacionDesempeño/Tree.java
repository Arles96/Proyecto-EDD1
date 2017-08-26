/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.edd1.EvaluacionDesempeño;

import javax.swing.JOptionPane;

/**
 *
 * @author arles96
 */
public class Tree {
    
    private Employee rooth;
    private int limit = 0;
    private Tree[] tree;
    private  int counter = 0;
    
    //constructor

    public Tree(Employee rooth, int limit) {
        this.rooth = rooth;
        this.limit = limit;
        this.tree = new Tree[this.limit];
    }
    
    // getter and setter

    public Employee getRooth() {
        return rooth;
    }

    public void setRooth(Employee rooth) {
        this.rooth = rooth;
    }

    public int getLimit() {
        return limit;
    }

    public Tree[] getTree() {
        return tree;
    }

    public void setTree(Tree[] tree) {
        this.tree = tree;
    }

    public int getCounter() {
        return counter;
    }
    
    
    //Administration methods
    
    public void insert(Employee person){
        if (counter<limit) {
            tree[counter]= new Tree(person, limit);
            counter++;
        }else {
            JOptionPane.showMessageDialog(null, "Error: El numero de empleados esta a limite.");
        }
    }  
    
    public boolean isEmpty(){
        return rooth==null && counter==0;
    }
    
    public void clear(){
        rooth = null;
        counter = 0;
    }
    
    public void remove(Employee employee){
        if (isEmpty()) {
            JOptionPane.showMessageDialog(null, "El arbol esta vacio.");
        }else {
            if (rooth.equals(employee)) {
                rooth = null;
            }else {
                for (int i = 0; i < counter; i++) {
                    remove(tree[counter].getRooth());
                }
            }
        }
    }
    
    public Employee find(){
        return null;
    }
    
}
