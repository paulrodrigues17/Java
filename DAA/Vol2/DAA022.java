// Codigo inicial para o problema [DAA 022] Arvores Red-Black
// Pedro Ribeiro (DCC/FCUP)

import java.util.*;

// Estrutura para representar um no da arvore
class Node {
    boolean isBlack;  // No preto?
    boolean isNull;   // No nulo?
    int value;        // Valor
    Node left, right; // Filhos

    Node(int v) {
	isNull  = (v==0);
	isBlack = (v>=0);
	value   = Math.abs(v);
    }
}

class DAA022 {

    // Ler input em preorder
    static Node readPreOrder(Scanner in) {
	int v = in.nextInt();
	Node aux = new Node(v);
	if (v!=0) {
	    aux.left  = readPreOrder(in);
	    aux.right = readPreOrder(in);
	}
	return aux;
    }

    // Menor valor da arvore
    static int minimum(Node t) {
	if (t.isNull) return Integer.MAX_VALUE;
	int minLeft  = minimum(t.left);
	int minRight = minimum(t.right);
	return Math.min(t.value, Math.min(minLeft, minRight));
    }

    // Maior valor da arvore
    static int maximum(Node t) {
	if (t.isNull) return Integer.MIN_VALUE;
	int minLeft  = maximum(t.left);
	int minRight = maximum(t.right);
	return Math.max(t.value, Math.max(minLeft, minRight));
    }

    // Quantidade de nos (internos)
    static int size(Node t) {
	if (t.isNull) return 0;
	return 1 + size(t.left) + size(t.right);
    }

    static boolean binarySearchTree(Node t, int min, int max) {
        if (t.value == 0) return true;
        if (t.value > min && t.value < max && 
            binarySearchTree(t.left, min, t.value) &&
            binarySearchTree(t.right, t.value, max))
            return true;
        return false;
    }

    static boolean rootProperty(Node t) {
        if (t.isBlack) return true;
        return false;
    }

    static boolean redProperty (Node t) {
        if (t.value == 0) return true;
        if (!t.isBlack) {
            if (!t.left.isBlack || !t.right.isBlack) return false;
        }
        return redProperty(t.left) && redProperty(t.right);
    }

    static int blackProperty (Node t) {
        if (t.value == 0) return 1;
        else {
            int left  = blackProperty(t.left) + (t.left.isBlack ? 1 : 0);
            int right = blackProperty(t.right) + (t.right.isBlack ? 1 : 0);
            if (left != right) return 0;
            return left;
        }
    }

    // ---------------------------------------------------
    
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        
        int n =  in.nextInt();
        for (int i=0; i<n; i++) {
            Node root = readPreOrder(in);
            if (rootProperty(root) && binarySearchTree(root, Integer.MIN_VALUE, Integer.MAX_VALUE) 
                && redProperty(root) && blackProperty(root) != 0)
                System.out.println("SIM");
            
            else 
                System.out.println("NAO");
        }	
    }
}