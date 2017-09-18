package math;

import java.util.ArrayList;

/**
 *
 * @author CJ
 */
public class Tree {

    protected ArrayList<Tree> subtrees;
    protected Tree father;
    protected Object content;
    public final int PRE = 0;
    public final int POS = 1;
    public final int SIMETRIC = 2;
    private int order = 2;
    private boolean seen = false;

    public Tree(Tree father) {
        this.subtrees = new ArrayList();
        this.father = father;
        this.content = null;
    }

    public Tree(Tree father, Object content) {
        this.subtrees = new ArrayList();
        this.father = father;
        this.content = content;
    }

    public Tree(Tree father, ArrayList<Tree> subtrees) {
        if (subtrees != null) {
            this.subtrees = subtrees;
        } else {
            this.subtrees = new ArrayList();
        }
        this.father = father;
        this.content = null;
    }

    public Tree(Tree father, Object content, ArrayList<Tree> subtrees) {
        if (subtrees != null) {
            this.subtrees = subtrees;
        } else {
            this.subtrees = new ArrayList();
        }
        this.father = father;
        this.content = content;
    }

    public void create(Tree[] nodes) {
        for (int i = 0; i < nodes.length; i++) {
//            nodes[i].setFather(this);
            subtrees.add(nodes[i]);
        }
    }

    public int indexOf(Object object) {
        if (object instanceof Tree) {
            return subtrees.indexOf((Tree) object);
        } else {
            for (int i = 0; i < subtrees.size(); i++) {
                if (this.subtrees.get(i).content == object) {
                    return i;
                }
            }
        }
        return -1;
    }

    public void add(Object object) {
        if (object instanceof Tree) {
            this.subtrees.add((Tree) object);
        } else {
            this.subtrees.add(new Tree(this, object, null));
        }
    }

    public void remove(Object object) {
        int i = indexOf(object);
        if (i > 0) {
            subtrees.remove(i);
        }
    }

    public void setFather(Tree tree) {
        this.father = tree;
    }

    public Tree getFather() {
        return father;
    }

    public void setContain(Object object) {
        this.content = object;
    }

    public Object getContain() {
        return this.content;
    }

    public void setSeen(boolean bol) {
        this.seen = bol;
    }

    public boolean isSeen() {
        return this.seen;
    }

    public Tree next() {
        if (!subtrees.isEmpty()) {
            switch (order) {
                case PRE: {
                    for (int i = subtrees.size() - 1; i >= 0; i--) {
                        if (!subtrees.get(i).isSeen()) {
                            return subtrees.get(i).next();
                        }
                    }
                }
                case POS: {
                    for (int i = subtrees.size() - 1; i >= 0; i--) {
                        if (!subtrees.get(i).isSeen()) {
                            return subtrees.get(i).next();
                        }
                    }
                }
                case SIMETRIC: {
                    for (int i = subtrees.size() - 1; i >= 0; i--) {
                        if (!subtrees.get(i).isSeen()) {
                            return subtrees.get(i).next();
                        }
                    }
                }
            }
        }
        setSeen(true);
        //System.out.println(this.getContain());
        return this;
    }

    public void clear() {
        this.subtrees = new ArrayList();
        this.content = null;
    }

    public boolean isEmpty() {
        return (subtrees.size() == 0) && (content == null);
    }

    public void setOrder(int i) {
        this.order = i;
    }

    @Override
    public String toString() {
        String data = "";
        //System.out.println(content);
        data += content + " { ";
        for (int i = 0; i < subtrees.size(); i++) {
            data += subtrees.get(i).toString() + ", ";
        }
        data += " } ";
        return data;
    }

}
