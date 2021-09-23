import java.util.*;

// Classe que representa um no

class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class daa031 {
    static int l;
    static int c;
    static char[][] matrix;
    static int num_nuvens;
    static boolean[][] visited;
    static int[][] distance;

    public static void bfs(int x, int y) {
        LinkedList<Point> q = new LinkedList<Point>();
        //FastPrint.out.println(x + " " + y);

        for (int i=0; i < l; i++) 
            for (int j=0; j < c; j++) {
                if (matrix[i][j] == '#') { 
                    //FastPrint.out.println(i + " " + j);
                    Point p = new Point(i,j);
                    q.add(p);
                    distance[i][j] = 0;
                    visited[i][j] = true;
                }
            }
        
        while (q.size() > 0) {
            Point aux = q.removeFirst();
            int abc = aux.x;
            int ord = aux.y;
            //System.out.println(abc + " " + ord);
            if (abc + 1 < l && !visited[abc+1][ord]) { //deslocamento para baixo
                //int ans = abc+1;
                //FastPrint.out.println("x = " + abc + " y = " + ord);
                distance[abc+1][ord] = distance[abc][ord] + 1;
                //FastPrint.out.println("pra baixo, x = " + ans + " y = " + ord + " dist = " + distance[ans][ord]);
                visited[abc+1][ord]  = true;
                Point novo       = new Point(abc+1, ord);
                q.add(novo); 
            }
            if (abc - 1 >= 0 && !visited[abc-1][ord]) { //deslocamento para cima
                distance[abc-1][ord] = distance[abc][ord] + 1;
                visited[abc-1][ord]  = true;
                Point novo = new Point(abc-1, ord);
                q.add(novo); 
            }
            if (ord + 1 < c && !visited[abc][ord+1]) { //deslocamento direita
                int ans = ord+1;
                //FastPrint.out.println("x = " + abc + " y = " + ord);
                distance[abc][ord+1] = distance[abc][ord] + 1;
                visited[abc][ord+1]  = true;
                Point novo       = new Point(abc, ord+1);
                q.add(novo);
            } 
            if (ord - 1 >= 0 && !visited[abc][ord-1]) { //deslocamento esquerda
                distance[abc][ord-1] = distance[abc][ord] + 1;
                visited[abc][ord-1]  = true;
                Point novo       = new Point(abc, ord-1);
                q.add(novo);
            }
        }
    }
    public static void main(String[] args){
        FastScanner in = new FastScanner(System.in);

        l = in.nextInt();
        c = in.nextInt();
        matrix = new char[l+1][c+1];
        visited = new boolean[l+1][c+1];
        distance = new int[l+1][c+1];
        int fstCloud = 0;
        int row = 0;
        int col = 0;

        for (int i=0; i < l; i++) 
            matrix[i] = in.next().toCharArray();
        
        for(int i=0; i < l; i++) {
            for(int j=0; j < c; j++) {
                if (fstCloud == 0 && matrix[i][j] == '#') {
                    bfs(i,j);
                    fstCloud = 1;
                    break;
                }
               // FastPrint.out.print(matrix[i][j]);
            }
            //FastPrint.out.println();
        }
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int ans;
        for (int i=0; i < l; i++)
            for (int j=0; j < c; j++) {
                if (matrix[i][j] == 'A') {
                    ans = distance[i][j];
                    if (ans > max) max = ans;
                    if (ans < min) min = ans;
                }
            }
        FastPrint.out.println(min + " " + max);
        FastPrint.out.close();
    }   
}