import java.util.*;

class Point {
    double x;
    double y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

class Edge {
    int to;     // No destino
    double weight; // Peso da aresta
    
    Edge(int t, double w) {
	    to = t;
	    weight = w;
    }
}

// Classe que representa um no
class Node {
    public LinkedList<Edge> adj; // Lista de adjacencias
    public boolean visited;      // No ja foi visitado?
    public double distance;         // Distancia ao no origem da pesquisa
    
    Node() {
	    adj = new LinkedList<>();
    } 
};

// Classe que representa um no para ficar na fila de prioridade
class NodeQ implements Comparable<NodeQ> {
    public double cost;
    public int node;

    NodeQ(double c, int n) {
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
        nodes = new Node[n+1];  // +1 se os nos comecam em 1 ao inves de 0
        for (int i=1; i<=n; i++)
            nodes[i] = new Node();
    }
    
    void addLink(int a, int b, double c) {
        nodes[a].adj.add(new Edge(b, c));
        nodes[b].adj.add(new Edge(a, c));
    }

    void prim(int r) {
        for (int i=1; i <= n; i++) {
            nodes[i].distance = Integer.MAX_VALUE;
            nodes[i].visited = false;
        }

        nodes[r].distance = 0;
        TreeSet<NodeQ> q = new TreeSet<>();
        q.add(new NodeQ(0, r));
        double total = 0;

        while (!q.isEmpty()) {
            NodeQ nq = q.pollFirst();
            int u = nq.node;
            nodes[u].visited = true;
            total += nodes[u].distance;
            
            for (Edge e : nodes[u].adj) {
                int v = e.to;
                double cost = e.weight;

                if (!nodes[v].visited && cost < nodes[v].distance) {
                    q.remove(new NodeQ(nodes[v].distance, v));
                    nodes[v].distance = cost;
                    //System.out.println(v + " " + nodes[v].distance);
                    q.add(new NodeQ(nodes[v].distance, v));
                }
            }
        }
        FastPrint.out.printf("%.3f", total);
    }
};

class daa037 {
    static int n;

    public static double dist(Point a, Point b) {   
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }
    public static void main(String[] args) {
        FastScanner in = new FastScanner(System.in);
        n = in.nextInt();
        Graph g = new Graph(n);
        Point[] pontos = new Point[n+1];
        
        for (int i = 1; i <= n; i++) {
            double x = in.nextDouble();
            double y = in.nextDouble();
            pontos[i] = new Point(x, y);
        }

        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                g.addLink(i, j , dist(pontos[i], pontos[j]));
            }
        }
        g.prim(1);

        FastPrint.out.println();
        FastPrint.out.close();
    }
}