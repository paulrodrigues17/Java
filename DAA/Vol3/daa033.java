import java.util.*;
import java.util.TreeMap;
import java.util.Map;

// Classe que representa uma aresta
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
    double max = Float.MAX_VALUE;
    
    Graph(int n) {
	this.n = n;
	nodes = new Node[n+1];  // +1 se os nos comecam em 1 ao inves de 0
	for (int i=1; i<=n; i++)
	    nodes[i] = new Node();
    }
    
    void addLink(int a, int b, double c) {
    nodes[a].adj.add(new Edge(b, c));
    nodes[b].adj.add(new Edge(a,c));
    }

    // Algoritmo de Dijkstra
    void dijkstra(int s) {
	
        //Inicializar nos como nao visitados e com distancia infinita
        for (int i=1; i<=n; i++) {
            nodes[i].distance = Integer.MAX_VALUE;
            nodes[i].visited  = false;
        }
        
        // Inicializar "fila" com no origem
        nodes[s].distance = 0;
        TreeSet<NodeQ> q = new TreeSet<>();
        q.add(new NodeQ(0.0, s)); // Criar um par (dist=0, no=s)

        // Ciclo principal do Dijkstra
        while (!q.isEmpty()) {
        
            // Retirar no com menor distancia (o "primeiro" do set, que e uma BST)
            NodeQ nq = q.pollFirst();
            int  u = nq.node;
            nodes[u].visited = true;
            //System.out.println(u + " [dist=" + nodes[u].distance + "]");
            
            // Relaxar arestas do no retirado
            for (Edge e : nodes[u].adj) {
                int v = e.to;
                double cost = e.weight;
                //System.out.println(u + " " + nodes[u].distance);
                if (!nodes[v].visited && nodes[u].distance + cost < nodes[v].distance) {
                    q.remove(new NodeQ(nodes[v].distance, v)); // Apagar do set
                    nodes[v].distance = nodes[u].distance + cost;
                    q.add(new NodeQ(nodes[v].distance, v));    // Inserir com nova (e menor) distancia
                }
            }
        }
        int aux = nodes.length-1;
        System.out.printf("%.1f", nodes[aux].distance);
        System.out.println();
        
    }
};

class daa033 {
    static int n;
    static int e;
    public static void main(String[] args) {
        FastScanner in = new FastScanner(System.in);
        TreeMap<String, Integer> map = new TreeMap<>();

        n = in.nextInt();
        e = in.nextInt();
        Graph g = new Graph(n);
        String line1 = in.nextLine();
        String[] name = line1.split(" ");
        map.put(name[0], 1); //start
        map.put(name[1], n); //end
        int count = 2;

        for (int i=0; i < e; i++) {
            String start = in.next();
            String end   = in.next();
            double dist  = in.nextDouble();
            Integer j    = map.get(start);
            Integer k    = map.get(end);
            if (j == null) {
                map.put(start, count);
                count++;
            } 
            if (k == null) {
                map.put(end, count);
                count++;
            }
            //System.out.println(map.get(start) + " -> " + map.get(end) + " => " + dist);
            g.addLink(map.get(start), 
                      map.get(end),
                      dist);
        }
        g.dijkstra(1);
        //System.out.println(g.max);
    }
}