import java.util.*;

class Piramide {
    long value;
    boolean deterioradas;

    Piramide (long v, boolean d) {
        value = v;
        deterioradas = d;
    }
}

class DAA017 {
    static int n; //número de pedras e nível
    static int d; //número de pedras em falta ou deterioradas

    public static void main(String[] args) {
        FastScanner in = new FastScanner(System.in);

        n = in.nextInt();
        d = in.nextInt();
        Piramide[][] count = new Piramide[n+1][n+1];
        long m = 0;

        for (int i=n; i > 0; i--)
            for (int j=1; j <= n; j++)
                count[i][j] = new Piramide(0, true);

        count[n][1].value = 1;

        for (int i=0; i < d; i++) {
            count[in.nextInt()][in.nextInt()] = new Piramide(0, false); 
        }

        if (count[n][1].deterioradas == false) System.out.println(m); //provar se o topo da pirâmide é deteriorada

        for (int i=n-1; i > 0; i--) {
            for (int j=1; j <= n+1-i; j++) {
                if (j == 1) {
                    if (count[i][1].deterioradas == true) count[i][1].value = count[i+1][1].value;
                }
                else if (j + i == n+1) {
                    if (count[i][j].deterioradas == true) count[i][j].value = count[i+1][j-1].value;
                }
                else {
                    if (count[i][j].deterioradas == true) count[i][j].value = count[i+1][j-1].value + count[i+1][j].value;
                }
            }
        }

        for (int i=1; i <= n; i++) 
            m += count[1][i].value;

        FastPrint.out.println(m);
        FastPrint.out.close();
    }
}