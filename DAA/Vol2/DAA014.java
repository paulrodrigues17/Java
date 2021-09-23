import java.util.*;
import java.util.Arrays;

class Sapato implements Comparable<Sapato> {
    float d;  //duração
    float c;  //coima
    int pos; //posição
    float r; //coima / duração

    Sapato (int duration, int coima, int k) {
        d = duration;
        c = coima;
        pos = k;
        r = (float)(c/d);
    }

    public int compareTo (Sapato other) {
        if (r < other.r) return +1;
        if (r > other.r) return -1;
        if (pos < other.pos) return -1;
        if (pos > other.pos) return +1;
        return 0;
    }
}

class DAA014 {
    static int n;
    public static void main(String[] args) {
        FastScanner in = new FastScanner(System.in);

        n = in.nextInt();
        Sapato[] s = new Sapato[n];
        for (int i=0; i < n; i++) {
            s[i] = new Sapato(in.nextInt(), in.nextInt(), i+1);
        }
        Arrays.sort(s);
        
        for (int i=0; i < n; i++) {
            FastPrint.out.print(s[i].pos);
            if (i < n-1) FastPrint.out.print(" ");
            else         FastPrint.out.println();
        }
        FastPrint.out.close();
    }
}