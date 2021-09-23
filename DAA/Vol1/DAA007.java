import java.util.*;

class DAA007 {
    static int n;
    static int[] v;
    static int maxSoFar;
    static int sum;
    public static void main(String[] args) {

        FastScanner in = new FastScanner(System.in);
        n = in.nextInt();
        v = new int[n];
        sum = 0;
        
        for (int i=0; i < n; i++) {
            v[i] = in.nextInt();
            maxSoFar = v[0];
        }

        for (int i=0; i < n; i++) { 
            sum += v[i];
            if (sum > maxSoFar) maxSoFar = sum;
            if (sum < 0) sum = 0;
        }
        FastPrint.out.println(maxSoFar);
        FastPrint.out.close();
    }
}