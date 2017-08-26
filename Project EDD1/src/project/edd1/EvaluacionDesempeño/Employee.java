/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.edd1.EvaluacionDesempeño;

import java.util.Objects;

/**
 *
 * @author arles96
 */
public class Employee {    
    
    //atribute

    private int value;
    private String name;
    
    //constructor

    public Employee(int value, String name) {
        this.value = value;
        this.name= name;
    }
    
    //getter and setter

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

    // administration methods

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Employee other = (Employee) obj;
        if (this.value != other.value) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Empleado: " + this.name + "Nota: " + value;
    }
        
}
