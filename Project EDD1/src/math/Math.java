package math;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author CJ
 */
public final class Math {

    private String answer;
    private String expression;
    private Tree tree;
    private Stack stack;
    private Queue formatted;

    public Math(String expression) {
        this.expression = expression;
        tree = new Tree(null);
        stack = new Stack();
        formatted = new Queue();
    }

    /**
     * Resuelve toda la expresión DEBE_SER O(n) HASTA_AHORA O(n)
     *
     * @throws mathException
     */
    public String solve() throws mathException {
        if (!swinged()) // n
        {
            throw new mathException("Error en simbolo agrupador.");
        }
        format(); // n
        fillTree();
        answer = calculate();

        return answer;
    }

    private void end(int i) throws mathException {
        if ((i + 1) >= (expression.length())) {
            throw new mathException("Error en la expresión.");
        } else if (Character.isDigit(expression.charAt(i)) && !Character.isDigit(expression.charAt(i + 1))) {
            throw new mathException("Error en la expresión.");
        }
    }

    /**
     * Extrae los enteros de la expresión O(n)
     *
     * @param i
     * @return
     */
    public int[] extract(int i) {
        char current;
        String number = "";
        while (i < expression.length() && Character.isDigit(current = expression.charAt(i))) {
            number += current;
            i++;
        }
        try {
            return new int[]{i - 1, Integer.parseInt(number)};
        } catch (Exception e) {
            //System.err.println("LINEA 68");
            e.printStackTrace();
            return new int[]{i - 1, 0};
        }
    }

    /**
     * Comprueba que los agrupadores están balanceados.
     *
     * @see O(n)
     */
    private boolean swinged() {
        stack.clear();
        char c;
        for (int i = 0; i < expression.length(); i++) {
            switch (c = expression.charAt(i)) {
                case '(':
                case '[':
                case '{': {
                    stack.push(c);
                    break;
                }
                case ')': {
                    if (stack.isEmpty() || (char) stack.pull() != '(') {
                        return false;
                    }
                    break;
                }
                case ']': {
                    if (stack.isEmpty() || (char) stack.pull() != '[') {
                        return false;
                    }
                    break;
                }
                case '}': {
                    if (stack.isEmpty() || (char) stack.pull() != '{') {
                        return false;
                    }
                    break;
                }
            }
        }
        return stack.isEmpty();
    }

    /**
     * Formatea a orden posfijo.
     *
     * @see O(n)
     */
    private void format() throws mathException {
        stack.clear();
        String current;
        int flag = 1;
        int internal = 1;
        for (int i = 0; i < expression.length(); i++) {
            switch (current = expression.charAt(i) + "") {
                case "-":
                    flag *= -1;
                    current = "+";
                case "+":
                case "*":
                case "x":
                case "X":
                case "/":
                case "^":
                case "(":
                case "[":
                case "{": {
                    //System.out.println("INI");
                    if (priority(current.charAt(0))[1] == 0) {
                        stack.push(current);
                        if (flag < 0) {
                            flag *= -1;
                            internal *= -1;
                        }
                    }
                    //System.out.println("\nSTACK\t" + stack.getStack().toString() + "\nSALID\t" + formatted.toString());
                    //System.out.println("WHILE");
                    while ((priority(current.charAt(0))[0] < priority((stack.pop() + "").charAt(0))[1])) {
                        formatted.add(stack.pull());
                        //System.out.println("\nSTACK\t" + stack.getStack().toString() + "\nSALID\t" + formatted.toString());
                    }
                    //System.out.println("WHILE_FIN");
                    if (priority(current.charAt(0))[1] > 0) {
                        stack.push(current);
                    }
                    //System.out.println("\nSTACK\t" + stack.getStack().toString() + "\nSALID\t" + formatted.toString());
                    //System.out.println("INI_Fin");
                    break;
                }
                case ")":
                case "]":
                case "}": {
                    String c;
                    //System.out.println("FIN");
                    while ('(' != (c = stack.pull() + "").charAt(0) && c.charAt(0) != '[' && c.charAt(0) != '{') {
                        formatted.add(c);
                        //System.out.println("\nSTACK\t" + stack.getStack().toString() + "\nSALID\t" + formatted.toString());
                    }
                    if (flag < 0) {
                        internal *= -1;
                    }
                    //System.out.println("\nSTACK\t" + stack.getStack().toString() + "\nSALID\t" + formatted.toString());
                    break;
                }
                case "\n":
                case " ": {
                    break;
                }
                default:
                    //System.out.println("NUMBER");
                    if (Character.isDigit(current.charAt(0))) {
                        int[] temp = extract(i);
                        i = temp[0];
                        //System.err.println(temp[1]);
                        formatted.add(temp[1] * flag * internal);
                        if (flag < 0) {
                            flag *= -1;
                        }
                        break;
                    } else {
                        throw new mathException("No se permiten letras.");
                    }
            }
        }
        try {
            while (0 < stack.getStack().size()) {
                formatted.add(stack.pull());
            }
        } catch (Exception e) {

        }
        //System.out.println("\nSTACK\t" + stack.getStack().toString() + "\nSALID\t" + formatted.toString());
    }

    /**
     * Asigna un valor de prioridad de pila y de flujo.
     *
     * @see O(1)
     */
    private int[] priority(char current) {
        switch (current) {
            case '+':
            case '-': {
                return new int[]{1, 1};
            }
            case '*':
            case 'x':
            case '/': {
                return new int[]{2, 2};
            }
            case '^': {
                return new int[]{4, 3};
            }
            case '(':
            case '[':
            case '{': {
                return new int[]{4, 0};
            }
        }
        return new int[]{0, 0};
    }

    private void fillTree() throws mathException {
        //System.out.println("FILTREE____________________________________________________________________-");
        stack.clear();
        Stack trees = new Stack();
        int med = 0;
        Object current = null;
        while ((current = formatted.next()) != null || (!stack.isEmpty() && trees.size() == 1)) {
            //System.out.println(current);
            switch (current + "") {
                case "+":
                case "*":
                case "x":
                case "X":
                case "/":
                case "^": {
                    if (med >= 2) {
                        Tree node = new Tree(null, current, null);
                        Object secund = stack.pull();
                        node.create(new Tree[]{new Tree(node, stack.pull(), null), new Tree(node, secund, null)});
                        node.setOrder(tree.SIMETRIC);
                        trees.push(node);
                        //System.out.println(node.toString());

                    } else if (med == 1) {
                        Tree node = new Tree(null, current);
                        node.create(new Tree[]{(Tree) trees.pull(), new Tree(node, stack.pull(), null)});
                        node.setOrder(tree.SIMETRIC);
                        trees.push(node);
                        //System.out.println(node.toString());
                    } else if (med == 0) {
                        if (stack.size() > 0) {
                            if (trees.size() > 1) {
                                Tree node = new Tree(null, current, null);
                                Object secund = trees.pull();
                                node.create(new Tree[]{(Tree) trees.pull(), (Tree) secund});
                                node.setOrder(tree.SIMETRIC);
                                trees.push(node);
                                //System.out.println(node.toString());
                            } else {
                                Tree node = new Tree(null, current, null);
                                node.create(new Tree[]{new Tree(node, stack.pull(), null), (Tree) trees.pull()});
                                node.setOrder(tree.SIMETRIC);
                                trees.push(node);
                                //System.out.println(node.toString());
                            }
                        } else {
                            if (trees.size() > 1) {
                                Tree node = new Tree(null, current, null);
                                Object secund = trees.pull();
                                node.create(new Tree[]{(Tree) trees.pull(), (Tree) secund});
                                node.setOrder(tree.SIMETRIC);
                                trees.push(node);
                                //System.out.println(node.toString());
                            }
                        }
                    }
                    med = 0;
                    break;
                }
                default: {
                    stack.push(current);
                    med++;
                    break;
                }
            }
        }
        try {
            tree = (Tree) trees.pull();
            tree.setOrder(tree.SIMETRIC);
        } catch (Exception e) {
            throw new mathException("Error sintáctico.");
        }
        //System.out.println(tree.toString());
    }

    private String calculate() throws mathException {
        //System.out.println("CALCULATE________________________________________");
        stack.clear();
        Tree t;
        while ((t = tree.next()) != null) {
            //System.out.println("\nSTACK\t" + stack.getStack().toString());
            double temp = 0;
            t.setOrder(tree.SIMETRIC);
            switch (t.getContain().toString()) {
                case "+": {
                    stack.push((Double.parseDouble(stack.pull() + "") + Double.parseDouble(stack.pull() + "")) + "");
                    //System.out.println(stack.pop() + "WWW");
                    break;
                }
                case "*":
                case "x":
                case "X": {
                    stack.push((Double.parseDouble(stack.pull() + "") * Double.parseDouble(stack.pull() + "")) + "");
                    break;
                }
                case "/": {
                    stack.push((Double.parseDouble(stack.pull() + "") / Double.parseDouble(stack.pull() + "")) + "");
                    break;
                }
                case "^": {
                    double pot = Double.parseDouble(stack.pull() + "");
                    temp = Double.parseDouble(stack.pull() + "");
                    int total = 1;
                    for (int i = 0; i < temp; i++) {
                        total *= pot;
                    }
                    stack.push(total + "");
                    break;
                }
                default: {
                    stack.push(t.getContain());
                    break;
                }
            }
            if (t == tree) {
                break;
            }
            ////System.out.println(t.getContain());
        }
        return stack.pull() + "";
    }

    @Override
    public String toString() {
        return expression + " = " + answer;
    }

}
/* 

############################################################
Turing: Todo algoritmo es reducible a un ... Turing
Vertex cover
Complejidad computacional
Grafo k coloreable
Thomas Cook
############################################################

 */


 /*
/* Agrega el primer signo que encuentra. 
stack.push(current);

/* Verifica si termina con signo.
end(i);
i++;
current = Character.toLowerCase(expression.charAt(i));

/* Si continua otro signo. 
if (!Character.isDigit(current)) {
    int[] temp = extract(i);
    i = temp[0];
    //System.err.println(i);
    if (temp[1] < 0) {
        formatted += temp[1];
    }
    //else
    //formatted += "+" + temp[1];

} /* Si sigue un numero. 
else if (Character.isDigit((char) current)) {*/
// }
