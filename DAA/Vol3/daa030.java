import java.util.*;

class Node {
    public LinkedList<Integer> adj; // Lista de adjacencias
    public boolean visited;         // Valor booleano que indica se foi visitado numa pesquisa
    public int distance;            // Distancia ao no origem da pesquisa

    Node() {
    adj = new LinkedList<Integer>();
    }
}

// Classe que representa um grafo
class Graph {
    int n;           // Numero de nos do grafo
    Node nodes[];    // Array para conter os nos
    int max = Integer.MIN_VALUE;
    int min = Integer.MAX_VALUE;
    int[][] matrix;
    int[] maxDist;

    Graph(int n) {
        this.n = n;
        nodes  = new Node[n+1]; // +1 se nos comecam em 1 ao inves de 0
        for (int i=1; i<=n; i++)
            nodes[i] = new Node();
        matrix = new int[n+1][n+1];
        maxDist = new int[n+1];
    }

    public void addLink(int a, int b) {
    nodes[a].adj.add(b);
    nodes[b].adj.add(a);
    }

    // Algoritmo de pesquisa em largura
    public void bfs(int v) {
        LinkedList<Integer> q = new LinkedList<Integer>();
        for (int i=1; i<=n; i++) nodes[i].visited = false;
    
        q.add(v);
        nodes[v].visited = true;
        nodes[v].distance = 0;

        matrix[v][v] = 0;
        maxDist[v] = 0;
    
        while (q.size() > 0) {
            int u = q.removeFirst();
            //System.out.println(u + " [dist=" + nodes[u].distance + "]");
            for (int w : nodes[u].adj)
            if (!nodes[w].visited) {
                q.add(w);
                nodes[w].visited  = true;
                nodes[w].distance = nodes[u].distance + 1;
                matrix[v][w] = nodes[w].distance; 

                if (maxDist[v] < nodes[w].distance) {
                    maxDist[v] = nodes[w].distance;
                    //System.out.println(maxDist[v]);
                }
            }	    
        }

        if (max < maxDist[v]) max = maxDist[v];
        if (min > maxDist[v]) min = maxDist[v];
    }
}

public class daa030 {
    static int n;
    static int e;
    static int count;
    
    public static void main(String[] args) {
        FastScanner in = new FastScanner(System.in);
        
        n = in.nextInt();
        Graph g = new Graph(n);
        e = in.nextInt();
        int count = 0;
        for (int i=0; i < e; i++) 
            g.addLink(in.nextInt(), in.nextInt());

        for (int i=1; i <= n; i++) {
            g.bfs(i);
        }

        System.out.println(g.max);
        System.out.println(g.min);

        for (int i = 1; i <= n; i++) { 
            if (g.maxDist[i] == g.min && count == 0) {
                System.out.print(i);
                count++;
            }

            else if (g.maxDist[i] == g.min)
                System.out.print(" " + i);

            else if (i == n && g.maxDist[i] == g.min)
                System.out.print(i);   
        }

        System.out.println();
        count = 0;

        for (int i = 1; i <= n; i++) { 
            if (g.maxDist[i] == g.max && count == 0) {
                System.out.print(i);
                count++;
            } 
            else if (g.maxDist[i] == g.max)
                System.out.print(" " + i);

            else if (i == n && g.maxDist[i] == g.min)
                System.out.print(i);
        }

        System.out.println();
        /*for (int i=1; i <= n; i++) { //ler matriz
            for (int j=1; j <= n; j++)
                System.out.print(g.matrix[i][j] + " ");
            System.out.println();
        }*/
    }
}