import java.util.*;

class Matrix {
    Scanner sc = new Scanner(System.in);
    public int raw = sc.nextInt(), col = sc.nextInt();
    public int[][] Serpentine = new int[raw][col];
    public int nowRaw = 0, nowCol = col - 1;
    boolean isRightUp, hasMoved = false;

    public void moveLeftOrDown() {
        if (nowCol == col-1 && nowRaw != raw-1) {
            nowRaw++;
            isRightUp = true;
            hasMoved = true;
            tracker++;
            return;
        }
        if (nowCol == 0) {
            nowRaw++;
            isRightUp = false;
            hasMoved = true;
            tracker++;
            return;
        }
        if (nowRaw == 0 && nowCol != 0) {
            nowCol--;
            isRightUp = false;
            hasMoved = true;
            tracker++;
            return;
        }
        if (nowRaw == raw-1 && nowCol != 0) {
            nowCol--;
            isRightUp = true;
            hasMoved = true;
            tracker++;
            return;
        }
    }

    public void moveIncline() {
        if (isRightUp) { nowRaw--; nowCol--; }
        else           { nowRaw++; nowCol++; }
    }

    int tracker = 0;
    public void fill() {
        for (int i = 1; i <= raw*col; i++) {
            hasMoved = false;
            try{
                Serpentine[nowRaw][nowCol] = i;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("ArrayIndexOutOfBoundsException");
            }
            if (tracker == 0) moveLeftOrDown();
            if (!hasMoved) {
                try {
                    moveIncline();
                    Serpentine[nowRaw][nowCol] = -1;
                } catch (ArrayIndexOutOfBoundsException e) {
                    isRightUp = !isRightUp;
                    moveIncline();
                }
                tracker = 0;
            }
        }
    }
}

public class OJ_20 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // temporary fix
        Matrix mt = new Matrix();
        if (mt.raw == 1) {
            for (int i = mt.col; i >= 1; i--) {
                System.out.printf("%3d",i);
            }
        }
        else if (mt.col == 1) {
            for (int i = 1; i <= mt.raw; i++) {
                System.out.printf("%3d\n",i);
            }
        }
        else {
            mt.fill();
            for (int i = 0; i < mt.raw; i++) {
                for (int j : mt.Serpentine[i]) {
                    System.out.printf("%3d", j);
                }
                System.out.println();
            }
        }
    }
}
