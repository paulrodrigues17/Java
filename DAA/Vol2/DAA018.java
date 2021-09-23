import java.util.*;

class DAA018 {
    static int n; //número de tipos de moeda
    static int[] ti; //valores de cada moeda
    static int p; //número de perguntas
    static int qi; //quantia - trocos
    static int[] coins;
    static int[] use;
    static int aux;
    static int aux1;

    public static void main(String[] args) {
        FastScanner in = new FastScanner(System.in);

        n = in.nextInt();
        ti = new int[n];
        for (int i=0; i < n; i++)
            ti[i] = in.nextInt();
        
        p = in.nextInt();
        for (int i=0; i < p; i++) {
            qi = in.nextInt();
            aux = qi;
            coins = new int[qi+1];
            use = new int[qi+1];
            int count = 0;

            for (int x=1; x <= qi; x++) 
                coins[x] = 10000;

            coins[0] = 0;
            use[0] = 0;
            for (int j=1; j <= qi; j++) {
                for (int k=0; k < n; k++) {
                    if (ti[k] <= j && 1 + coins[j - ti[k]] < coins[j]) {
                        coins[j] = 1 + coins[j - ti[k]];
                        use[j] = ti[k];
                    }
                }
            }
            int ans=0;
            aux = qi;
            FastPrint.out.print(qi + ":" + " [" + coins[qi] + "] ");
            for (int t=0; t < coins[qi]; t++) {
                if (t < coins[qi]-1) FastPrint.out.print(use[qi] + " "); 
                else FastPrint.out.println(use[qi]);
                ans = aux - use[aux];
                use[qi] = use[ans]; 
                aux -= use[ans];
            }
        }
        FastPrint.out.close();
    }
}