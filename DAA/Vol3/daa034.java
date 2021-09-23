import java.util.*;

// Classe que representa uma aresta
class Edge {
    int to;     // No destino
    int weight; // Peso da aresta
    
    Edge(int t, int w) {
	to = t;
	weight = w;
    }
}

// Classe que representa um no
class Node {
    public LinkedList<Edge> adj; // Lista de adjacencias
    public boolean visited;      // No ja foi visitado?
    public int distance;         // Distancia ao no origem da pesquisa
    
    Node() {
	adj = new LinkedList<>();
    } 
};

// Classe que representa um no para ficar na fila de prioridade
class NodeQ implements Comparable<NodeQ> {
    public int cost;
    public int node;

    NodeQ(int c, int n) {
	cost = c;
	node = n;
    }

    @Override
    public int compareTo(NodeQ nq) { 
        if (cost < nq.cost) return -1; 
        if (cost > nq.cost) return +1;
	    if (node < nq.node) return -1; 
	    if (node > nq.node) return +1;
        return 0; 
    } 
}

// Classe que representa um grafo
class Graph {
    int n;          // Numero de nos do grafo
    Node[] nodes;   // Array para conter os nos
    
    Graph(int n) {
	this.n = n;
	nodes = new Node[n];  // +1 se os nos comecam em 1 ao inves de 0
	for (int i=0; i<n; i++)
	    nodes[i] = new Node();
    }
    
    void addLink(int a, int b, int c) {
	nodes[a].adj.add(new Edge(b, c));
    }

    // Algoritmo de Dijkstra
    String dijkstra(int s) {
	
        for (int i=0; i<n; i++) 
            nodes[i].distance = Integer.MAX_VALUE/2;
        
        nodes[s].distance = 0;

        for (int i=1; i < n-1; i++) {
            for (int j=0; j < n; j++) {  
                for (Edge e : nodes[j].adj) {
                    if (nodes[j].distance + e.weight < nodes[e.to].distance) {
                        nodes[e.to].distance = nodes[j].distance + e.weight;
                    }
                }
            }
        }

        /*for (int i=0; i < n; i++) 
            System.out.println("nodes[" + i +"] = " + nodes[i].distance);*/

        for (int i=0; i < n; i++) {
            for (Edge e : nodes[i].adj) {
                if (nodes[i].distance + e.weight < nodes[e.to].distance) {
                    //int x = nodes[i].distance + e.weight;
                    //System.out.println("nodes[" + e.to + "] = " + x);
                    return "possivel";
                }
            }
        }
        return "impossivel";
    }
};

class daa034 {
    static int c;
    static int n;
    static int m;
    public static void main(String[] args) {
        FastScanner in = new FastScanner(System.in);
        c = in.nextInt();

        for (int i=0; i < c; i++) {
            n = in.nextInt();
            m = in.nextInt();
            Graph g = new Graph(n);
            for (int j=0; j < m; j++) {
                int a = in.nextInt();
                int b = in.nextInt();
                int t = in.nextInt();
                g.addLink(a,b,t);
                //System.out.print("j= " + j + " ");
            }
            System.out.println(g.dijkstra(0)); 
        }
    }
}