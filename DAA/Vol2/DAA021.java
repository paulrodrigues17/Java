import java.util.*;
import java.util.TreeSet;
import java.util.TreeMap;
import java.util.Map;

class DAA021 {
    static int a;
    static int r;
    public static void main(String[] args) {
        FastScanner in = new FastScanner(System.in);

        a = in.nextInt();
        r = in.nextInt();
        TreeMap<Integer, Integer> bakugans = new TreeMap<Integer, Integer>();

        for (int i=0; i < a+r; i++) {
            String line = in.nextLine();
            String[] names = line.split(" ");
            if (names[0].equals("BAK")) {
                Integer j = bakugans.get(Integer.parseInt(names[1]));
                if (j == null) bakugans.put(Integer.parseInt(names[1]), 1);
                else           bakugans.put(Integer.parseInt(names[1]), j+1);
            }        
            else {
                if (names[0].equals("MAX")) {
                    Integer j = bakugans.get(bakugans.lastKey());
                    if (j > 1) {
                        FastPrint.out.println(bakugans.lastKey());
                        bakugans.put(bakugans.lastKey(), j-1);
                    }
                    else {
                        FastPrint.out.println(bakugans.lastKey());
                        bakugans.pollLastEntry();
                    }
                }
                else {
                    Integer j = bakugans.get(bakugans.firstKey());
                    if (j > 1) {
                        FastPrint.out.println(bakugans.firstKey());
                        bakugans.put(bakugans.firstKey(), j-1);
                    }
                    else {
                        FastPrint.out.println(bakugans.firstKey());
                        bakugans.pollFirstEntry();
                    }   
                }
            }     
        }
        FastPrint.out.close();
    }
}