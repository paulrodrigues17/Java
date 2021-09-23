import java.util.*;

class daa025 {
    static int n;
    static int l;
    static boolean adj[][];    // Matriz de adjacencias
    static boolean visited[];  // Que nos ja foram visitados?
    static int count;

    static void dfs(int v) {
        //System.out.print(v + " ");
        visited[v] = true;
        for (int i=1; i<=n; i++)
            if (adj[v][i] && !visited[i]) {
                dfs(i);
            }
    }
    
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        
        n = in.nextInt();
        adj     = new boolean[n+1][n+1];
        visited = new boolean[n+1];	
        l = in.nextInt();	
        count = 0;

        for (int i=0; i < l; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            adj[a][b] = adj[b][a] = true;
        }
        for (int i=1; i <= n; i++) {
            if (!visited[i]) {
                count++;
                dfs(i);
            }
        }
        
        System.out.println(count);
    }
}