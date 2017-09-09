/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PerformanceEvaluation;

import abstractClass.AbstractTree;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author arles96
 */
public class Tree extends AbstractTree{
    
    private Employee root;
    private ArrayList <Tree> tree = new ArrayList();
    
    //constructor

    public Tree(Employee root) {
        this.root = root;
    }
    
    // getter and setter

    public Employee getRoot() {
        return root;
    }

    public void setRoot(Employee root) {
        this.root = root;
    }

    public ArrayList<Tree> getTree() {
        return tree;
    }

    public void setTree(ArrayList<Tree> tree) {
        this.tree = tree;
    }
    
    //Administration methods
    
    @Override
    public boolean isEmpty(){
        return root == null;
    }
    
    @Override
    public void clear(){
        root = null;
        tree.clear();
    }
    
    @Override
    public boolean isLeaf(){
        return tree.isEmpty();
    }
    
    
    public void grades(){
        if (!isLeaf()) {
            int accumulator = 0;
            for (Tree t : tree) {
                if (t.isLeaf()) {
                    accumulator+= t.getRoot().getValue();
                }else {
                    t.grades();
                    accumulator += t.getRoot().getGrade();
                }
            }
            root.setGrade(accumulator/tree.size());
        }
    }
    
   private String printRoot(){
       return ">" + root.getName() + "_" + root.getGrade() + "\n";
   }
   
   private String printChild(String space){
       String employees = "";
       for (Tree t : tree) {
           employees += space + ">" + t.getRoot().getName() + "_" +
                   t.getRoot().getGrade() + "\n";
           if (!t.isLeaf()) {
               employees += t.printChild(space + "    ");
           }           
       }
       return employees;
   }
   
    @Override
   public String print(){
       String employees = "";
       employees += printRoot();
       employees += printChild("    ");
       return employees;
   }

    @Override
    public void insertRoot(Object object) {
        if (object instanceof Employee) 
            root = (Employee) object;
    }

    @Override
    public boolean exists(Object object) {
        if (object instanceof Integer) {
            int id = (Integer) object;
            if (id==root.getId()) {
                return true;
            }else {
                if (tree.stream().anyMatch((t) -> (t.exists(object)))) {
                    return true;
                }
            }
            return false;
        }else {
            return false;
        }
    }

    @Override
    public void remove(Object object) {
        if (object instanceof Employee) {
            if (root.equals(object)) {
                clear();
            }else {
                for (int i = 0; i < tree.size(); i++) {
                    tree.get(i).remove(object);
                    if (tree.get(i).isEmpty()) {
                        tree.remove(i);
                    }
                }
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Error, no es un empleado");
        }
    }

    @Override
    public void insertLeaf(Object father, Object son) {
        if (father instanceof Employee && son instanceof Employee && root!=null) {
            if (root.equals((Employee)father)) {
                tree.add(new Tree((Employee)son));
            }else {
                tree.forEach((t) -> {
                    t.insertLeaf(father, son);
                });
            }
        }else {
            JOptionPane.showMessageDialog(null, "Error al agregar");
        }
    }
    
    public void changeEmployee(String name, String area, int value, int id, Employee employee){
        if (root.equals(employee)) {
            if (!name.equals(root.getName())) {
                root.setName(name);
                employee.setName(name);
                JOptionPane.showMessageDialog(null, "Se ha modificado el nombre.");
            }
            if (!area.equals(root.getArea())) {
                root.setArea(area);
                employee.setArea(area);
                JOptionPane.showMessageDialog(null, "Se ha modificado el area.");
            }
            if (id!=root.getId()) {
                root.setId(id);
                employee.setId(id);
                JOptionPane.showMessageDialog(null, "Se ha modificado el id.");
            }
            if (isLeaf()) {
                if (value!=root.getValue()) {
                    root.setValue(value);
                    employee.setValue(value);
                }
            }else {
                JOptionPane.showMessageDialog(null, "Error");
            }
        }else {
            tree.forEach((t) -> {
                t.changeEmployee(name, area, value, id, employee);
            });
        }
    }
    
}
