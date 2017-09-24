package BicolorableGraph;

import java.awt.Color;
import java.util.ArrayList;

public class Bicoloreable {
    public static boolean isBicoloreable(Graph graph){
        ArrayList visited = new ArrayList();
        
        for (int i = 0; i < graph.getSize(); i++) {
            graph.setList(new Color[graph.getSize()]);
            boolean isNotBicolorable = R_Prof(graph, i, visited, (Color[])graph.getList(), true);
            if(isNotBicolorable){
                return false;
            }
        }
        return true;
    }
    
    public static boolean R_Prof(Graph graph, int i, ArrayList visited, Color[] colors, boolean color){
        /*if(prev == i)
            return false;*/
        
        if(colors[i] == null){
            colors[i] = (color)?Color.RED:Color.BLUE;
        }else{
            return (colors[i] != ((color)?Color.RED:Color.BLUE));
        }
        
        for (int j = 0; j < visited.size(); j++) {
            if(i == (int)visited.get(j))
                return false;
        }
        
        visited.add(i);
        
        /*if(i > graph.getSize() - 1){
            return false;
        }*/
        
        for (int next = 0; next < graph.getSize(); next++) {
            if(i == next)
                continue;
           
            if(graph.getMatrix(i, next) > 0){
                System.out.println("[" + i + ", " + next + "]"  
                        + graph.getMatrix(i, next) + ", use: " + color + ";" + " sends: " + !color);
                //System.out.println(Arrays.toString(colors));
                System.out.println();
                if(R_Prof(graph, next, visited, colors, !color))
                    return true;
            }
        }
        
        return false;
    }
}
