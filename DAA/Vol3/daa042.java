// Exemplo de implementaÃ§Ã£o de algoritmo de Edmonds-Karp
// usando lista de adjacencias + matriz de capacidades
// (implementacao com vector)

import java.util.*;

// Classe para no da fila do BFS: pares (no, capacidade)
class NodeQ {
    public int node;
    public int flow;

    NodeQ(int n, int f) {
        node = n;
        flow = f;
    }
}

// Classe que representa um grafo
class Graph {
    int n; // Numero de nos do grafo
    Vector<Vector<Integer>> adj; // Lista de adjacencias
    int cap[][]; // Matriz de capacidades

    Graph(int n) {
        this.n = n;
        adj = new Vector<>();
        for (int i = 0; i <= n; i++)
            adj.add(new Vector<Integer>());
        cap = new int[n][n]; // +1 se os nos comecam em 1 ao inves de 0
    }

    void addLink(int a, int b, int c) {
        // adjacencias do grafo nao dirigido, porque podemos ter de andar no sentido
        // contrario ao procurarmos caminhos de aumento
        adj.get(a).add(b);
        adj.get(b).add(a);
        cap[a][b] = c;
    }

    // BFS para encontrar caminho de aumento
    // devolve valor do fluxo nesse caminho
    int bfs(int s, int t, int[] parent) {
        for (int i = 0; i < n+n+2; i++)
            parent[i] = -1;

        parent[s] = -2;

        Queue<NodeQ> q = new LinkedList<>(); // fila do BFS

        // inicializar com no origem e capacidade infinita
        q.add(new NodeQ(s, Integer.MAX_VALUE));

        while (!q.isEmpty()) {
            // returar primeiro no da fila
            int cur = q.peek().node;
            int flow = q.peek().flow;
            //System.out.println("c->" + cur);
            q.poll();

            // percorrer nos adjacentes ao no atual (cur)
            for (int next : adj.get(cur)) {
                //System.out.println("n=" + next + " c=" + cur);
                // se o vizinho ainda nao foi visitado (parent==-1)
                // e a aresta respetiva ainda tem capacidade para passar fluxo
                //System.out.println("-->parent[" + next + "] = " + cur);
                if (parent[next] == -1 && cap[cur][next] > 0) {
                    parent[next] = cur; // atualizar pai
                    //System.out.println("parent[" + next + "] = " + cur);
                    int new_flow = Math.min(flow, cap[cur][next]); // atualizar fluxo
                    if (next == t) return new_flow; // chegamos ao final?
                    q.add(new NodeQ(next, new_flow)); // adicionar a fila
                }
            }
        }
        return 0;
    }

    // Algoritmo de Edmonds-Karp para fluxo maximo entre s e t
    // devolve valor do fluxo maximo (cap[][] fica com grafo residual)
    int maxFlow(int s, int t) {
        int flow = 0; // fluxo a calcular
        int[] parent = new int[n+n+2]; // array de pais (permite reconstruir caminho)

        while (true) {
            int new_flow = bfs(s, t, parent); // fluxo de um caminho de aumento
            //System.out.println(new_flow);
            if (new_flow == 0) break; // se nao existir, terminar

             // imprimir fluxo e caminho de aumento
             System.out.print("Caminho de aumento: fluxo " + new_flow + " | " + t);

            flow += new_flow; // aumentar fluxo total com fluxo deste caminho
            int cur = t;
            while (cur != s) { // percorrer caminho de aumento e alterar arestas
                int prev = parent[cur];
                cap[prev][cur] -= new_flow;
                cap[cur][prev] += new_flow;
                cur = prev;
                System.out.print(" <- " + cur); // imprimir proximo no do caminho
            }
            System.out.println();
        }

        return flow;
    }
}


class daa042 {
    public static void main(String[] args) {
        FastScanner in = new FastScanner(System.in);

        int c = in.nextInt(); //casos
        for (int i=0; i < c; i++) {
            int n = in.nextInt(); //pessoas
            int superOri  = n*2;
            int superDest = n*2+1;
            int nos = n+n+2; 
            Graph g = new Graph(nos);
            int m = in.nextInt(); //declarações

            for (int j=0; j < m; j++) {
               int a = in.nextInt();
               int b = in.nextInt();
               g.addLink(a, b+n, 1);
               g.addLink(superOri, a, 1);
               g.addLink(b+n, superDest, 1);
            }
            int flow = g.maxFlow(superOri, superDest);
            System.out.println("flow = " + flow);
            if (flow == n) FastPrint.out.println("YES");
            else FastPrint.out.println("NO");
        }
        FastPrint.out.close();
    }    
}