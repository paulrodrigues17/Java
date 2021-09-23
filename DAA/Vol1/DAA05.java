import java.util.*;

class DAA05 {
    static int N; //número de bakugans
    static int[] Ei; //energias da sequência de bakugans
    static int F; //número de bakugans
    static int Ai; //posição inicial
    static int Bi; //posição final
    static int[] sum;
    static int aux;

    public static void main (String[] args) {
        FastScanner in = new FastScanner(System.in);

        N = in.nextInt();
        Ei = new int[N+1];
        sum = new int[N+1];
        
        for (int i=1; i <= N ; i++) {
            Ei[i] = in.nextInt();
            sum[i] += Ei[i]+aux;
            aux = sum[i];  
        }

        F = in.nextInt();
        for (int i=0; i < F; i++) {
            Ai = in.nextInt(); 
            Bi = in.nextInt(); 
            FastPrint.out.println(sum[Bi]-sum[Ai-1]);
        }

        FastPrint.out.close();
    }
}