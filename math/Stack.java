package math;

import java.util.ArrayList;

/**
 *
 * @author CJ
 */
public class Stack {
    private ArrayList<Object> list;

    public Stack() {
        this.list = new ArrayList();
    }

    public ArrayList<Object> getStack() {
        return list;
    }

    public void setStack(ArrayList<Object> list) {
        this.list = list;
    }
    
    public void push(Object object) {
        list.add(object);
    }
    
    public Object pull() {
        if(list.size() > 0) {
            Object temp = list.get(list.size() - 1);
            list.remove(list.size() - 1);
            return temp;
        }
        return null;
    }
    
    public Object pop() {
        if(list.size() > 0)
            return list.get(list.size() - 1);
        return null;
    }
    
    public void clear() {
        this.list = new ArrayList();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
    
    public int size() {
        return list.size();
    }
    @Override
    public String toString() {
        String s = "";
        for (int i = list.size() - 1; i >= 0; i--)
            s += list.get(i);
        clear();
        return s;
    }
    
    public String popString() {
        String s = "";
        for (int i = list.size() - 1; i >= 0; i--)
            s += list.get(i);
        return s;
    }
    
}
