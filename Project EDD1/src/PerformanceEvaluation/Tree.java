/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PerformanceEvaluation;

import abstractClass.AbastractTree;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

/**
 *
 * @author arles96
 */
public class Tree extends AbastractTree{
    
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
    
    @Override
    public boolean isEmpty(){
        return rooth == null;
    }
    
    @Override
    public void clear(){
        rooth = null;
        tree.clear();
    }
    
    public void remove(int id){
        
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
                    accumulator+= t.getRooth().getValue();
                }else {
                    t.grades();
                    accumulator += t.getRooth().getGrade();
                }
            }
            rooth.setGrade(accumulator/tree.size());
        }
    }
    
   private String printRooth(){
       return ">" + rooth.getName() + "_" + rooth.getGrade() + "\n";
   }
   
   private String printChild(String space){
       String employees = "";
       for (Tree t : tree) {
           employees += space + ">" + t.getRooth().getName() + "_" +
                   t.getRooth().getGrade() + "\n";
           if (!t.isLeaf()) {
               employees += t.printChild(space + "    ");
           }           
       }
       return employees;
   }
   
    @Override
   public String print(){
       String employees = "";
       employees += printRooth();
       employees += printChild("    ");
       return employees;
   }

    @Override
    public void insertRooth(Object object) {
        if (object instanceof Employee) 
            rooth = (Employee) object;
    }

    @Override
    public boolean exists(Object object) {
        if (object instanceof Integer) {
            int id = (Integer) object;
            if (id==rooth.getId()) {
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
            if (rooth.equals(object)) {
                rooth = null;
                tree.clear();
            }else {
                for (Tree t : tree) {
                    t.remove(object);
                    if (t.getRooth()==null) {
                        tree.remove(t);
                        break;
                    }
                }
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Error no es un empleado");
        }
    }

    @Override
    public void insertLeaf(Object father, Object son) {
        if (father instanceof Employee && son instanceof Employee && rooth!=null) {
            if (rooth.equals((Employee)father)) {
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
        if (rooth.equals(employee)) {
            if (!name.equals(rooth.getName())) {
                rooth.setName(name);
                employee.setName(name);
                JOptionPane.showMessageDialog(null, "Se ha modificado el nombre.");
            }
            if (!area.equals(rooth.getArea())) {
                rooth.setArea(area);
                employee.setArea(area);
                JOptionPane.showMessageDialog(null, "Se ha modificado el area.");
            }
            if (id!=rooth.getId()) {
                rooth.setId(id);
                employee.setId(id);
                JOptionPane.showMessageDialog(null, "Se ha modificado el id.");
            }
            if (isLeaf()) {
                if (value!=rooth.getValue()) {
                    rooth.setValue(value);
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
