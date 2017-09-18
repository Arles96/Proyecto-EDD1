package BicolorableGraph;

import java.util.ArrayList;
import java.util.Arrays;

public class Minimal {
    
    public static void main(String[] args){
        Graph graph = new Graph(7, true);
        Graph graph2 = new Graph(4, true);
        
        graph.setNode("A", 0);
        graph.setNode("B", 1);
        graph.setNode("C", 2);
        graph.setNode("D", 3);
        graph.setNode("E", 4);
        graph.setNode("F", 5);
        graph.setNode("G", 6);
        
        graph.setNodeConection(0, 1, 2);
        graph.setNodeConection(0, 2, 3);
        graph.setNodeConection(0, 3, 3);
        //graph.setNodeConection(0, 4, 1000000);
        //graph.setNodeConection(0, 5, 1000000);
        //graph.setNodeConection(0, 6, 1000000);
        graph.setNodeConection(1, 2, 4);
        //graph.setNodeConection(1, 3, 1000000);
        graph.setNodeConection(1, 4, 3);
        //graph.setNodeConection(1, 5, 1000000);
        //graph.setNodeConection(1, 6, 1000000);
        //graph.setNodeConection(2, 3, 1000000);
        graph.setNodeConection(2, 4, 1);
        graph.setNodeConection(2, 5, 6);
        //graph.setNodeConection(2, 6, 1000000);
        //graph.setNodeConection(3, 4, 1000000);
        graph.setNodeConection(3, 5, 7);
        //graph.setNodeConection(3, 6, 1000000);
        graph.setNodeConection(4, 5, 8);
        //graph.setNodeConection(4, 6, 1000000);
        graph.setNodeConection(5, 6, 9);
        
        graph2.setNode("A", 0);
        graph2.setNode("B", 1);
        graph2.setNode("C", 2);
        graph2.setNode("D", 3);
        
        graph2.setNodeConection(0, 1, 1);
        graph2.setNodeConection(0, 3, 2);
        graph2.setNodeConection(1, 2, 4);
        graph2.setNodeConection(1, 3, 3);
        graph2.setNodeConection(2, 3, 5);
        
        
        System.out.println("Kruskal:");
        printTree(Kruskal(graph), "");
        printTree(Kruskal(graph2), "");
        System.out.println(graph);
        System.out.println(graph2);
        
        
        System.out.println("Prim:");
        printTree(Prim(graph), "");
        printTree(Prim(graph2), "");
        System.out.println(graph);
        System.out.println(graph2);
    }
    
    public static void printList(ArrayList<int[]> list){
        for (int i = 0; i < list.size(); i++) {
            System.out.println(Arrays.toString(list.get(i)));
        }
    }
    
    public static Tree Kruskal(Graph graph){
        //Hacer lista de conecciones
        ArrayList<int[]> listCon = new ArrayList();
        for (int i = 0; i < graph.getSize() - 1; i++) {
            for (int j = i + 1; j < graph.getSize(); j++) {
                if(graph.getMatrix(i, j) > 0){
                    listCon.add(new int[]{i, j, graph.getMatrix(i, j)});
                }
            }
        }
        
        //Ordenar con base a valor
        ArrayList<int[]> sortedList = new ArrayList();
        getFromHeap(heapSort(listCon, 2), sortedList);
        
        
        //Eliminar  nodos que causan ciclos
        ArrayList<int[]> solution = new ArrayList();
        solution.add(sortedList.get(0));
        for (int i = 1; i < sortedList.size(); i++) {
            boolean found = false;
            for (int j = 0; j < solution.size(); j++) 
                if(sortedList.get(i)[1] == solution.get(j)[1])
                    found = true;
            
            if(!found)
                solution.add(sortedList.get(i));
        }
        
        ArrayList<Tree> msp = new ArrayList();
        //TODO:
        for (int i = 0; i < graph.getSize(); i++) {
            msp.add(new Tree(graph.getObject(i), graph.getObject(i)));
        }
        
        for (int i = 0; i < solution.size(); i++) {
            int[] vals = solution.get(i);
            
            msp.get(vals[0]).addChildren(msp.get(vals[1]));
        }
        
        return msp.get(0);
    }
    
    public static Tree Prim(Graph graph){
        //Hacer arbol
        ArrayList<Tree> msp = new ArrayList();
        for (int i = 0; i < graph.getSize(); i++) {
            msp.add(new Tree(graph.getObject(i), graph.getObject(i)));
        }
        
        //Aplicar Prim
        ArrayList<Object> solution = new ArrayList();
        solution.add(0);
        while(solution.size() < graph.getSize()){
            int[] least = new int[]{0, 0, 1000000};
            for (Object sol : solution) {
                for (int i = (int)sol + 1; i < graph.getSize(); i++) {
                    if (graph.getMatrix((int)sol, i) < least[2] && solution.indexOf(i) < 0){
                        least[0] = (int)sol;
                        least[1] = i;
                        least[2] = graph.getMatrix((int)sol, i);
                    }
                }
            }
            msp.get(least[0]).addChildren(msp.get(least[1]));
            solution.add(least[1]);
        }
        
        return msp.get(0);
    }
    
    public static void getFromHeap(treeBin heap, ArrayList sortedList)
    {
        if(heap.getLeft() != null)
            getFromHeap(heap.getLeft(), sortedList);
        
        sortedList.add(heap.getValue());
        
        if(heap.getRight() != null)
            getFromHeap(heap.getRight(), sortedList);
    }
    
    public static treeBin heapSort(ArrayList<int[]> list, int conpVal){
        treeBin heap = new treeBin(list.get(0), list.get(0)[2]);
        
        for(int i = 1; i < list.size(); i++){
            heap.insertNode(new treeBin(list.get(i), list.get(i)[conpVal]));
        }
        
        return heap;
    }
    
    public static void printTree(Tree tree, String s){
        System.out.println(s + tree.getLabel() + " value: " + tree.getValue());
        
        if(tree.hasChildren());
        for (int i = 0; i < tree.getChildren().size(); i++) {
            printTree(tree.getChildren().get(i), s + "\t");
        }
    }
}
