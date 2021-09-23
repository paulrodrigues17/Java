import java.util.*;

class DAA02 {
    static int[] Ni;
    static int[] Ki;
    static int[] ans;
    static int T;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        T = in.nextInt();
        Ni = new int[T];
        Ki = new int[T];

        for (int i=0; i < T; i++) {
            Ni[i] = in.nextInt(); Ni[i]++;
            Ki[i] = in.nextInt();
        }

        int i=0;

        while (i < T) {
            int aux = 0;
            int number = Ni[i];
            while (number > 0) {
                aux += number % 10;
                number /= 10;
            }
            if (aux != Ki[i]) Ni[i]++;
            else { i++; }           
        }

        for (int j=0; j < T; j++)
            System.out.println(Ni[j]);
    }
}
