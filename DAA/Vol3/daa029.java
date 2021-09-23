import java.util.*;

class daa029 {
    static int n;
    static boolean[][] adj;
    static boolean[] visited;
    static boolean[] exist;
    static String[] p;
    static LinkedList<Character> order;

    static int size(int fstSize, int secSize) {
        if (fstSize >= secSize) return secSize;
        return fstSize;
    }

    static void dfs(int v) {
        visited[v] = true;
        for (int i=0; i < 26; i++) {
            if (adj[v][i] && !visited[i]) {
                dfs(i);
            }
        }
        order.addFirst((char)(v+'A'));
    }
    public static void main(String[] args) {
        FastScanner in = new FastScanner(System.in);
        n = in.nextInt();

        order = new LinkedList<>();
        adj = new boolean[26][26];
        visited = new boolean[26];
        exist = new boolean[26];
        p = new String[n];

        //input
        for (int i=0; i < n; i++) {
            p[i] = in.nextLine();
            //System.out.println(p[i]);
        }

        for (int i=0; i < n-1; i++) {
            int aux = size(p[i].length(), p[i+1].length());
            for (int j=0; j < aux; j++) {
                if (p[i].charAt(j) != p[i+1].charAt(j)) {   
                    System.out.println(p[i].charAt(j) + " -> " + p[i+1].charAt(j));
                    adj[p[i].charAt(j) - 'A'][p[i+1].charAt(j) - 'A'] = true;
                    exist[p[i].charAt(j) - 'A'] = exist[p[i+1].charAt(j) - 'A'] = true;
                    break;
                }
            }
        }

        for (int i=0; i < 26; i++) 
            if (!visited[i])
                dfs(i);
        

        for (char c : order)
            if (exist[c - 'A'])
                System.out.print(c);
        System.out.println();
    }
}