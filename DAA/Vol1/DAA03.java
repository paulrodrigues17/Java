import java.util.*;

class DAA03 {
    static int T; 

    static int geracao (String m) {
        if (Character.isLetter(m.charAt(0)) && Character.isDigit(m.charAt(5))) return 1;     // XX-NN-NN

        else if (Character.isDigit(m.charAt(0)) && Character.isDigit(m.charAt(5)))           // NN-XX-NN
            return 3;                                                                  

        else if (Character.isLetter(m.charAt(0)) && Character.isLetter(m.charAt(5)))         // XX-NN-XX
            return 4;                                                                 

        else                                                                                 // NN-NN-XX
            return 2;
    }

    static int value (char ch) {
        
        if(ch < 'K')
            return ch - 'A';
        else if(ch > 'K' && ch < 'W')
            return ch - 'B';
        else if(ch == 'X')
            return ch - 'C';
        else
            return ch - 'D';
    }

    static int matricula (String m) {
        int result = 0;
        char ch;
        int n;

        if (geracao(m) == 1) {
            ch = m.charAt(0);
            n = value(ch);
            result += n * 10000 * 23;

            ch = m.charAt(1);
            n = value(ch);
            result += n * 10000;

            n = m.charAt(2)-'0';
            result += n * 1000;

            n = m.charAt(3)-'0';
            result += n * 100;

            n = m.charAt(4)-'0';
            result += n * 10;

            n = m.charAt(5)-'0';
            result += n;
        }

        else if (geracao(m) == 2) {
            n = m.charAt(0)-'0';
            result += n * 1000;

            n = m.charAt(1)-'0';
            result += n * 100;

            n = m.charAt(2)-'0';
            result += n * 10;

            n = m.charAt(3)-'0';
            result += n;
            
            ch = m.charAt(4);
            n = value(ch);
            result += n * 10000 * 23;

            ch = m.charAt(5);
            n = value(ch);
            result += n *10000;
            
            result += 5290000;		
        }

        else if (geracao(m) == 3) {
            n = m.charAt(0)-'0';
            result += n * 1000;

            n = m.charAt(1)-'0';
            result += n * 100;
            
            ch = m.charAt(2);
            n = value(ch);
            result += n * 23* 10000;

            ch = m.charAt(3);
            n = value(ch);
            result += n * 10000;

            n = m.charAt(4)-'0';
            result += n * 10;

            n = m.charAt(5)-'0';
            result += n;

            result += 10580000;		
        }

        else if (geracao(m) == 4) {
            ch = m.charAt(0);
            n = value(ch);
            result += n * 23 * 23 * 23 * 100;

            ch = m.charAt(1);
            n = value(ch);
            result += n * 23 * 23 * 100;

            n = m.charAt(2)-'0';
            result += n * 10;

            n = m.charAt(3)-'0';
            result += n;
            
            ch = m.charAt(4);
            n = value(ch);
            result += n * 23 * 100;

            ch = m.charAt(5);
            n = value(ch);
            result += n * 100;

			result += 15870000;    
        }

        return result;
    }

    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        
        T = in.nextInt();

        for (int i=0; i < T; i++) {
            String m1 = in.next();
            String m2 = in.next();
            
            m1 = m1.replace("-", "");
            m2 = m2.replace("-", "");

            int aux = matricula(m1) - matricula(m2);
            System.out.println(Math.abs(aux));
        }
    }
}
