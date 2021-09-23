import java.util.*;

class daa027 {
    static int n;
    static int v;
    static int e;
    static boolean[] visited;
    static boolean[][] adj;
    static String[] color;
    static int aux;

    static void dfs(int w) {
        visited[w] = true;
        for (int i=1; i<=v; i++) {
            if (adj[w][i] && !visited[i]) {
                if (color[w].equals("Green")) {
                    color[i] = "Red";
                    dfs(i);
                }
                else if (color[w].equals("Red")) {
                    color[i] = "Green";
                    dfs(i);
                }
            }
            if (visited[i] && adj[w][i]) {
                if (color[w].equals(color[i])) {
                    aux = -1;
                }
                if (color[w].equals("Black")) {
                    if (color[i].equals("Green")) color[w] = "Red";
                    else                          color[w] = "Green";
                }
            }
        }
    }     
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        n = in.nextInt();

        for (int i=0; i < n; i++) {
            v = in.nextInt();
            e = in.nextInt();
            visited = new boolean[v+1];
            adj = new boolean[v+1][v+1];
            color = new String[v+1];
            aux = 0;
            for (int j=1; j <= v; j++)
                color[j] = "Black";
            color[1] = "Red";
            for (int j=1; j <= e; j++) {
                int a = in.nextInt();
                int b = in.nextInt();
                adj[a][b] = adj[b][a] = true;
            }
            for (int k=1; k <= v; k++) {
                if (!visited[k]) {
                    dfs(k);
                }
            }
            if (aux < 0) System.out.println("nao");
            else         System.out.println("sim");
        }
    }
}