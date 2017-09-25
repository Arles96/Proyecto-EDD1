package Floyd;

import java.util.ArrayList;

/**
 *
 * @author CJ
 */
public class Node {

    protected ArrayList<Integer> cost;
    protected Object content;
    protected int current;
    protected int value;
    protected int size;
    private boolean seen;
    private String tag;

    public Node(int value, String tag, Object content, int[] cost) {
        this.cost = new ArrayList();
        for (int i = 0; i < cost.length; i++) {
            this.cost.add(cost[i]);
        }
        this.content = content;
        this.value = value;
        this.tag = tag;
        this.seen = false;
        this.current = -1;
    }

    public Node(int value, String tag, Object content, String[] cost) {
        this.cost = new ArrayList();
        for (int i = 0; i < cost.length; i++) {
            this.cost.add(Integer.parseInt(cost[i]));
        }
        this.content = content;
        this.value = value;
        this.tag = tag;
        this.seen = false;
        this.current = -1;
    }

    public void add(int way) {
        this.cost.add(way);
    }

    public void create(int[] cost) {
        for (int i = 0; i < cost.length; i++) {
            this.cost.add(cost[i]);
        }
    }

    public int next() {
        if (current + 1 < cost.size()) {
            current++;
            return cost.get(current);
        }
        return Integer.MAX_VALUE;
    }

    public int[] getCosts() {
        int[] temp = new int[cost.size()];
        for (int i = 0; i < cost.size(); i++) {
            temp[i] = cost.get(i);
        }
        return temp;
    }

    public void setCosts(ArrayList<Integer> cost) {
        this.cost = cost;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public int getCurrent() {
        return current + 1;
    }

    public void resetCurrent() {
        this.current = -1;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getSize() {
        return cost.size() + 1;
    }

    @Override
    public String toString() {
        return cost.toString();
    }

}
