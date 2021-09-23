import java.util.*;

class DAA01 {
    static int N;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        int count = 0;

        for (int i=0; i < N; i++) {
            int aux = in.nextInt();
            if (aux == 42) count ++;
        }
        System.out.println(count);
    }
}