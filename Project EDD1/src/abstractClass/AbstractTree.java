/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abstractClass;

/**
 *
 * @author arles96
 */
public abstract class AbstractTree {
    
    public abstract void insertRoot(Object object);
    
    public abstract void insertLeaf(Object father, Object son);
    
    public abstract boolean exists(Object object);
    
    public abstract boolean isLeaf();
    
    public abstract String print();
    
    public abstract void clear();
    
    public abstract void remove(Object object);    
    
    public abstract boolean isEmpty();
    
}
