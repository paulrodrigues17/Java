import java.util.*;
import java.io.*;

class Letter implements Comparable<Letter> {
    public char letter;
    public int pos;
    public int count;

    Letter (char ch) {
        letter = ch;
        count = 0;
    }
    
    public int compareTo (Letter other) {
        if (count < other.count) return +1;
        if (count > other.count) return -1;
        if (pos < other.pos) return -1;
        if (pos > other.pos) return +1;
        return 0;
    }
}

class DAA009 {
    static String s;
    public static void main(String[] args) {
        
        FastScanner in = new FastScanner(System.in);
        String adn = in.next();
        Letter[] array = new Letter[26];
        char ch = 'A';
        
        for (int i=0; i < 26; i++) {
            array[i] = new Letter(ch);
            ch++;
        }

        for (int i=0; i < adn.length(); i++) {
            int pos = adn.charAt(i) - 'A';
            array[pos].count++;

            if (array[pos].count == 1) array[pos].pos = i;
        }

        Arrays.sort(array);

        for (int i=0; i < 26; i++) {
            if (array[i].count > 0) 
                System.out.println(array[i].letter + " " + array[i].count);
        }

    }
}