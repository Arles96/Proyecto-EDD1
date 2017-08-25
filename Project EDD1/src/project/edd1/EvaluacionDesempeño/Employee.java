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
public class Employee {    

    private int value;
    private String name;
    private Employee boss = null;

    public Employee(int value, String name) {
        this.value = value;
        this.name= name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDepartment() {
        return name;
    }

    public void setDepartment(String name) {
        this.name = name;
    }

    public Employee getEmployee() {
        return boss;
    }

    public void setEmployee(Employee employee) {
        this.boss = employee;
    }

    
    
    
    
}
