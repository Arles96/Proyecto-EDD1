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
public abstract class AbstractGraph {
    
    public abstract void remove(Object object);
    
    public abstract void clear();
    
    public abstract boolean isEmpty();
    
    public abstract boolean exists(Object object);
    
    public abstract void insert(Object object);
        
}
