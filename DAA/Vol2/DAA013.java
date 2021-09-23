import java.util.*;
import java.util.Arrays;

class Segment implements Comparable<Segment> {
    public int left;
    public int right;
    public int size;

    Segment (int l, int r) {
        left = l;
        right = r;
        size = r - l;
    }

    public int compareTo (Segment other) {
        if (left < other.left) return -1;
        if (left > other.left) return +1;
        if (size > other.size) return -1;
        if (size < other.size) return +1;
        return 0;
    }
}

class DAA013 {
    static int m;
    static int n;
    static int li;
    static int ri;
    public static void main(String[] args) {
        FastScanner in = new FastScanner(System.in);

        m = in.nextInt();
        n = in.nextInt();
        Segment[] seg = new Segment[n]; 

        for (int i=0; i < n; i++) {
            li = in.nextInt();
            ri = in.nextInt();
            seg[i] = new Segment(li, ri);
        }

        Arrays.sort(seg);
        int end = seg[0].right;
        int count = 1;
        int max = 0;

        while (end < m) {
            for (int i=1; i < n; i++) {
                if (seg[i].left != 0 && seg[i].left <= end) {
                    if (max < seg[i].right) {
                        max = seg[i].right;
                    }
                }
            }
            count++;
            end = max;
        }

        FastPrint.out.println(count);
        FastPrint.out.close();
    }
}