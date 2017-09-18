package BicolorableGraph;

import abstractClass.AbstractGraph;

public class Graph extends AbstractGraph{
    private int[][] matrix;
    private Object[] list;
    
    public Graph(){
        matrix = new int[5][5];
        list = new Object[5];
    }
    
    public Graph(int n){
        matrix = new int[n][n];
        list = new Object[n];
        
        for (int i = 0; i < list.length; i++) {
            list[i] = null;
        }
    }
    
    public Graph(int n, boolean infinite){
        matrix = new int[n][n];
        list = new Object[n];
        
        for (int i = 0; i < list.length; i++) {
            list[i] = i;
        }
        
        if(infinite)
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = 0x0fffffff;
                }
            }
    }
    
    public void setNodeConection(int node1, int node2){
        matrix[node1][node2] = 1;
        matrix[node2][node1] = 1;
    }
    
    public void setNodeConection(int node1, int node2, int value){
        matrix[node1][node2] = value;
        matrix[node2][node1] = value;
    }
    
    public void setNode(Object obj, int index){
        list[index] = obj;
    }
    
    public int getMatrix(int i, int j){
        return matrix[i][j];
    }
    
    public int getSize(){
        return list.length;
    }
    
    public void setList(Object[] list){
        this.list = list;
    }
    
    public Object[] getList(){
        return list;
    }
    
    public Object getObject(int i){
        return list[i];
    }
    
    /*public void setSize(){
        
    }*/

    @Override
    public String toString() {
        String to = "";
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if(matrix[i][j] == 0x0fffffff){
                    to += 'I';
                }else{
                    to += matrix[i][j];
                }
                to += " ";
            }
            to += "\t[" + list[i] + "]\n";
        }
        
        return to;
    }

    @Override
    public void remove(Object object) {
        for (int i = 0; i < list.length; i++) {
            if(list[i] == object){
                list[i] = null;
            }
        }
    }

    @Override
    public void clear() {
        matrix = null;
        list = null;
    }

    @Override
    public boolean isEmpty() {
        return (list == null);
    }

    @Override
    public boolean exists(Object object) {
        for (Object list1 : list) {
            if (object == list1) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void insert(Object object) {
        boolean change = false;
        for (int i = 0; i < list.length; i++) {
            if(list[i] == null){
                list[i] = object;
                change = true;
            }
        }
        if(change){
            list[list.length - 1] = object;
        }
    }
}
