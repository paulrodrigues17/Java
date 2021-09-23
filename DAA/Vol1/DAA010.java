import java.util.*;
import java.io.*;

class DAA010 {
    static int N; //tamanho do conjunto
    static int[] Si; //array de números
    static int Q; //perguntas
    static int[] Pi; //array para número para cada pergunta
    static int aux; //variável para a soma 
    static int[] allSum; //todas as somas possíveis
    static int K; //tamanho do array de todas as somas possíveis

    static int bsearch (int[] v, int low, int high, int key) {
        while (low <= high) {
            int middle = low + (high - low) / 2;
            if (key == v[middle]) return middle;
            else if (key < v[middle]) high = middle - 1;
            else low = middle + 1;
        }

        return -1; 
    }

    static int combinations (int n) {
        return (n*(n-1))/2;
    }
    public static void main(String[] args) {
        FastScanner in = new FastScanner(System.in);

        N = in.nextInt(); 
        Si = new int[N]; 
        K = combinations(N); aux = 0; 
        allSum = new int[K];
        
        for (int i=0; i < N; i++)
            Si[i] = in.nextInt();

        for (int i=0; i < N-1; i++) {
            for (int j=i+1; j < N; j++) {
                allSum[aux] = Si[i] + Si[j];
                aux++;
            }
        }
        Arrays.sort(allSum);

        int min = allSum[0];
        int max = allSum[K-1];
    
        Q = in.nextInt();
        Pi = new int[Q];
        for (int i=0; i < Q; i++) {
            Pi[i] = in.nextInt();

            if (bsearch(allSum, 0, K-1, Pi[i]) == -1) {
                int afterQ = Pi[i];
                int beforeQ = Pi[i];

                while (bsearch(allSum, 0, K-1, beforeQ) == -1 && beforeQ >= min)
                    beforeQ--;
                while (bsearch(allSum, 0, K-1, afterQ) == -1 && afterQ <= max) 
                    afterQ++;
                
                if (beforeQ < min) FastPrint.out.println(afterQ);
                else if (afterQ > max) FastPrint.out.println(beforeQ);
                else if (Pi[i] - beforeQ < afterQ - Pi[i]) FastPrint.out.println(beforeQ);
                else if (Pi[i] - beforeQ > afterQ - Pi[i]) FastPrint.out.println(afterQ);
                else                                       FastPrint.out.println(beforeQ + " " + afterQ);
            }
            
            else
                FastPrint.out.println(Pi[i]);
        }      

        FastPrint.out.close();
    }
}