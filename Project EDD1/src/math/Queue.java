package math;

import java.util.ArrayList;

/**
 *
 * @author CJ
 */
public class Queue {

    private int pos;
    private ArrayList<Object> list;

    public Queue() {
        this.list = new ArrayList();
        pos = 0;
    }

    public Object next() {
        if (list.isEmpty()) {
            return null;
        }
        Object temp = list.get(0);
        list.remove(0);
        return temp;
    }
    
    public Object getCurrent() {
        if (list.isEmpty()) {
            return null;
        }
        if (list.size() > pos) {
            Object temp = list.get(pos);
            list.remove(pos);
            return temp;
        } else {
            return null;
        }
    }

    public Object see() {
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public Object seeNext() {
        if (list.isEmpty()) {
            return null;
        }
        if (list.size() > pos) {
            return list.get(pos);
        } else {
            return null;
        }
    }

    public void add(Object object) {
        list.add(object);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }

    public ArrayList<Object> getQueue() {
        return list;
    }

    public void setQueue(ArrayList<Object> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return list.toString();
    }

}
