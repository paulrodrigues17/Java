import java.util.*;

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
    boolean[][] matrix;
    static String[] city;
    
    Graph(int n) {
        this.n = n;
        nodes = new Node[n+1];  // +1 se os nos comecam em 1 ao inves de 0
        for (int i=1; i<=n; i++)
            nodes[i] = new Node();
        matrix = new boolean[n+1][n+1];
        city = new String[n+1];
    }
    
    void addLink(int a, int b, int c) {
        nodes[a].adj.add(new Edge(b, c));
        nodes[a].adj.add(new Edge(a, c));
    }

    void floydWarshall() {
        for (int i=1; i <= n; i++) 
            matrix[i][i] = true;

        for (int i=1; i <= n; i++) 
            for (Edge e : nodes[i].adj) 
                matrix[i][e.to] = true;

        for (int k=1; k <= n; k++) 
            for (int i=1; i <= n; i++)
                for (int j=1; j <= n; j++)
                    if (matrix[i][k] && matrix[k][j]) 
                        matrix[i][j] = true;
        
        char count = 65;
        System.out.print("  ");
        for (int i=1; i <= n; i++) {
            city[i] = String.valueOf(count);
            if (i == n) System.out.print(city[i]);
            else        System.out.print(city[i] + " ");
            count++;
        }
        System.out.println();

        for (int i=1; i <= n; i++){
            System.out.print(city[i] + " ");
            for (int j=1; j <= n; j++) {
                if (matrix[i][j]) { 
                    if (j == n) System.out.print("1");
                    else System.out.print("1 ");
                }
                else {
                    if (j == n) System.out.print("0");
                    else System.out.print("0 ");
                }
            }
            System.out.println();
        }
    }
};

class daa035 {
    static int n;
    public static void main(String[] args) {
        FastScanner in = new FastScanner(System.in);
        n = in.nextInt();
        Graph g = new Graph(n);

        for (int i=1; i <= n; i++) {
            String cidade_origem = in.next();
            int num_destinos = in.nextInt(); 
            for (int j=0; j < num_destinos; j++) {
                String destino = in.next();
                int x = cidade_origem.charAt(0) - 'A' + 1;
                int y = destino.charAt(0) - 'A' + 1;
                g.addLink(x, y, 1);
            }
        }
        g.floydWarshall();
    }
}