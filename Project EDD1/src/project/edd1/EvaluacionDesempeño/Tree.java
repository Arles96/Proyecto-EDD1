/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.edd1.EvaluacionDesempeño;

/**
 *
 * @author arles96
 */
public class Tree {
    
    private Employee rooth;
    private Employee[] employee;
    private Tree tree;
    
    //constructor

    public Tree(Employee rooth, int limit) {
        this.rooth = rooth;
        this.employee = new Employee[limit];
    }
    
    // getter and setter

    public Employee getRooth() {
        return rooth;
    }

    public void setRooth(Employee rooth) {
        this.rooth = rooth;
    }

    public Employee[] getEmployee() {
        return employee;
    }

    public void setEmployee(Employee[] employee) {
        this.employee = employee;
    }

    public Tree getTree() {
        return tree;
    }

    public void setTree(Tree tree) {
        this.tree = tree;
    }
    
    //Administration methods
    
    public void insert(Employee person){
        
    }  
    
}
