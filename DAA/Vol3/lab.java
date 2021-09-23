import java.util.*;

class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class lab {
    static int l;
    static int c;
    static char[][] matrix;
    static boolean[][] visited;
    static int[][] distance;

    public static void bfs(int x, int y) {
        LinkedList<Point> q = new LinkedList<Point>();

        q.add(new Point(x,y));

        for (int i=0; i < l; i++) 
            for (int j=0; j < c; j++) 
                if (matrix[i][j] == '#') 
                    visited[i][j] = true;

        while (q.size() > 0) {
            Point p = q.removeFirst();
            int abc = p.x;
            int ord = p.y;
            if (abc + 1 < l && !visited[abc+1][ord]) {
                //FastPrint.out.println(abc + " " + ord);
                distance[abc+1][ord] = distance[abc][ord] + 1;
                visited[abc+1][ord]  = true; 
                Point aux = new Point(abc+1, ord);
                q.add(aux);
            }
            if (abc - 1 >= 0 && !visited[abc-1][ord]) {
                distance[abc-1][ord] = distance[abc][ord] + 1;
                visited[abc-1][ord]  = true;
                Point aux = new Point(abc-1, ord);
                q.add(aux);
            }
            if (ord + 1 < c && !visited[abc][ord+1]) {
                //FastPrint.out.println(abc + " " + ord);
                distance[abc][ord+1] = distance[abc][ord] + 1;
                visited[abc][ord+1]  = true;
                Point aux = new Point(abc, ord+1);
                q.add(aux);
            }
            if (ord - 1 >= 0 && !visited[abc][ord-1]) {
                distance[abc][ord-1] = distance[abc][ord] + 1;
                visited[abc][ord-1]  = true;
                Point aux = new Point(abc, ord-1);
                q.add(aux);
            }
        }
    }
    public static void main(String[] args) {
        FastScanner in = new FastScanner(System.in);
        l = in.nextInt();
        c = in.nextInt();
        matrix = new char[l+1][c+1];
        visited = new boolean[l+1][c+1];
        distance = new int[l+1][c+1];
        int row = 0;
        int col = 0;
        for (int i=0; i < l; i++) 
            matrix[i] = in.next().toCharArray();
        
        for (int i=0; i < l; i++) {
            for (int j=0; j < c; j++) 
                if (matrix[i][j] == 'P') {
                    row = i;
                    col = j; 
                    distance[i][j] = 0;
                }         
        }
        bfs(row, col);
        for (int i=0; i < l; i++)
            for (int j=0; j < c; j++) {
                if (matrix[i][j] == 'C')
                    FastPrint.out.println(distance[i][j]);
            }
        FastPrint.out.close();
    }
}