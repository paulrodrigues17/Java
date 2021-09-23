import java.util.*;

class DAA006 {
    static int N;  //número de casos a considerar
    static float qx; 
    static float qy;
    static float ql; //comprimento do lado
    static float cx; 
    static float cy;
    static float cr; //raio

    static float dist(float qx, float qy, float ql, float cx, float cy, float cr) {
        return ((cx - qx) * (cx - qx)) + ((cy - qy) * (cy - qy));
    }

    static boolean circleInsideSquare(float qx, float qy, float ql, float cx, float cy, float cr) {
        
        if (cx-cr > qx && cx+cr < qx+ql && cy-cr > qy && cy+cr < qy+ql) 
            return true;
        return false;
    }

    static boolean squareInsideCircle(float qx, float qy, float ql, float cx, float cy, float cr) {
        if (dist(qx, qy, ql, cx, cy, cr) < cr*cr
            && dist(qx+ql, qy, ql, cx, cy, cr) < cr*cr
            && dist(qx, qy+ql, ql, cx, cy, cr) < cr*cr
            && dist(qx+ql, qy+ql, ql, cx, cy, cr) < cr*cr)
                return true;
        return false;
    }

    static boolean check (float qx, float qy, float ql, float cx, float cy, float cr) {
        float testX = cx;
        float testY = cy;

        if (cx < qx)         testX = qx;        // left edge
        else if (cx > qx+ql) testX = qx+ql;     // right edge

        if (cy < qy)         testY = qy;        // top edge
        else if (cy > qy+ql) testY = qy+ql;     // bottom edge
    
        float distX = cx-testX;
        float distY = cy-testY;
        float distance = (float)(Math.sqrt((distX*distX) + (distY*distY)));

        if (distance <= cr) {
            return true;
        }
        return false;
    }

    static double intersect(float qx, float qy, float ql, float cx, float cy, float cr) {
        float ans = 0; 
        if (check(qx, qy, ql, cx, cy, cr)) {
            
            if (circleInsideSquare(qx, qy, ql, cx, cy, cr))
                ans = (float)Math.PI * cr * cr;
            
            else if (squareInsideCircle(qx, qy, ql, cx, cy, cr))
                        ans = ql * ql;
            
            else {
                if ((ql/2) > 0.0001) {
                    ans = (float)(intersect (qx, qy, ql/2, cx, cy, cr) +
                    intersect (qx+ql/2, qy, ql/2, cx, cy, cr) +
                    intersect (qx, qy+ql/2, ql/2, cx, cy, cr) +
                    intersect (qx+ql/2, qy+ql/2, ql/2, cx, cy, cr));
                }
            }
        }
        return ans;
    }
    public static void main (String[] args) {
        FastScanner in = new FastScanner(System.in);

        N = in.nextInt();

        for (int i=0; i < N; i++) {
            qx = in.nextInt();
            qy = in.nextInt();
            ql = in.nextInt();
            cx = in.nextInt();
            cy = in.nextInt();
            cr = in.nextInt();
            FastPrint.out.println(intersect(qx,qy,ql,cx,cy,cr));
        }

        FastPrint.out.close();
    }
}