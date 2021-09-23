import java.util.*;

class daa026 {
    static int n;
    static int rows;
    static int cols;
    static char[][] matrix;
    static boolean[][] visited;
    static int aux;
    static int max;

    static int dfs(int y, int x) {
        if (y<0 || y>=rows || x<0 || x>=cols) return 0; 
        if (visited[y][x]) return 0;
        if (matrix[y][x] == '.') return 0;
        visited[y][x] = true;
        int count = 1;
        count += dfs(y-1, x);
        count += dfs(y+1, x);
        count += dfs(y, x+1);
        count += dfs(y, x-1); 
        count += dfs(y+1, x+1);
        count += dfs(y-1, x-1);
        count += dfs(y+1, x-1);
        count += dfs(y-1, x+1);
        return count;        
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        n = in.nextInt();

        for (int k=0; k < n; k++) {
            rows = in.nextInt(); cols = in.nextInt();
            matrix = new char[rows][cols];
            visited = new boolean[rows][cols];
            max = Integer.MIN_VALUE;
            aux = 0;
            for (int i=0; i < rows; i++) {
                matrix[i] = in.next().toCharArray();
            }
            for (int j=0; j < rows; j++) {
                for (int w=0; w < cols; w++) {
                    aux = dfs(j,w);
                    if (aux >= max) max = aux;
                }
            }
            System.out.println(max);
        }
    }
}