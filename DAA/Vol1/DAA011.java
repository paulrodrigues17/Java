import java.util.*;

class DAA011 {
    static int n;
    static int[] di;
    static int td;
    static int p;
    static int[] ki;

    static boolean condition (int mid, int k, int[] v) {
        int part = 0;
        int sum = 0;

        for (int i=0; i < v.length; i++) {
            sum += v[i];
            if (sum > mid) {
                part++;
                sum = 0; 
                i--;
            }

            if (part == k) return false;
        }
        
        return true;
    }

    static int bsearch (int low, int high, int key, int[] v) {
        while (low < high) {
            int middle = low + (high - low) / 2;
            if (condition(middle, key, v)) high = middle;
            else                                   low = middle + 1;
        }

        return low;
    }
    public static void main(String[] args) {
        FastScanner in = new FastScanner(System.in);

        n = in.nextInt();
        di = new int[n];
        for (int i=0; i < n; i++) {
            di[i] = in.nextInt();
            td += di[i];
        }

        p = in.nextInt();
        ki = new int[p];
        for (int i=0; i < p; i++) {
            ki[i] = in.nextInt();
            FastPrint.out.println(bsearch(0, td, ki[i], di));
        }
        FastPrint.out.close();
    }
}
