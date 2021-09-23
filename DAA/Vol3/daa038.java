import java.util.*;


class Edge {
    int to; 
    int weight; 

    Edge(int t, int w) {
        to = t;
        weight = w;
    }
}


class Node {
    public LinkedList<Edge> adj; 
    public boolean visited;
    public int distance; 

    Node() {
        adj = new LinkedList<>();
    }
}

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


class Graph {
    int n; 
    Node[] nodes; 
    int[] aux;

    Graph(int n) {
        this.n = n;
        nodes = new Node[n + 1]; 
        for (int i = 1; i <= n; i++)
            nodes[i] = new Node();
    }

    void addLink(int a, int b, int c) {
        nodes[a].adj.add(new Edge(b, c));
        nodes[b].adj.add(new Edge(a, c));
    }

   
    void prim(int s) {
        for (int i = 1; i <= n; i++) {
            nodes[i].distance = Integer.MAX_VALUE;
            nodes[i].visited = false;
        }

        nodes[s].distance = 0;
        TreeSet<NodeQ> q = new TreeSet<>();
        q.add(new NodeQ(0, s)); 

        int index = 0;
        int total = 0;

        while (!q.isEmpty()) {
            NodeQ nq = q.pollFirst();
            int u = nq.node;
            nodes[u].visited = true;

            total += nodes[u].distance;             

            if(nodes[u].distance != 0)
                aux[index++] = nodes[u].distance;  

            for (Edge e : nodes[u].adj) {
                int v = e.to;
                int cost = e.weight;
                if (!nodes[v].visited && cost < nodes[v].distance) {
                    q.remove(new NodeQ(nodes[v].distance, v)); 
                    nodes[v].distance = cost;
                    q.add(new NodeQ(nodes[v].distance, v)); 
                }
            }
        }

        FastPrint.out.println(total);
    }
}

class daa038 {
    static int a;
    static int b;
    static int count;
    public static void main(String args[]) {
        FastScanner in = new FastScanner(System.in);
        a = in.nextInt();
        b = in.nextInt();
        Graph g = new Graph(a + b);
        g.aux = new int[b];
        count = 0;

        int[] array = new int[a];

        for (int i = 1; i <= a + b; i++) {
            if (i <= a) 
                array[i-1] = i;
        }
        
        for (int i = 0; i < a-1; i++) 
            g.addLink(array[i], array[i+1], 0);
        
        
        int c = in.nextInt();

        for (int i = 0; i < c; i++)
            g.addLink(in.nextInt(), in.nextInt(), in.nextInt());

        g.prim(1);

        Arrays.sort(g.aux);

        for (int i = 0; i < b; i++) {
            if (count == 0) {
                FastPrint.out.print(g.aux[i]);
                count++;
            } 
            else
                FastPrint.out.print(" " + g.aux[i]);
        }
        FastPrint.out.println();
        FastPrint.out.close();
    }
}
