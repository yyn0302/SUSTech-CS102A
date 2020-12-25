import java.util.Scanner;

public class A1Q3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int h = sc.nextInt(), m = sc.nextInt();
        sc.close();
        
        int deltaH, deltaM;
        if (h < 13) {
            if (m == 0) {
                deltaM = 0;
                deltaH = 13 - h;
            }
            else {
                deltaM = 60 - m;
                deltaH = 12 - h;
            }
        }
        else {
            if (m == 0) {
                deltaM = 0;
                deltaH = 13 - h + 24;
            }
            else {
                deltaM = 60 - m;
                deltaH = 12 - h + 24;
            }
        }
        if (h == 13 && m == 0) {
            deltaH = 0;
        }
        
        if (deltaH != 0) {
            System.out.print(deltaH + " ");
        }
        System.out.print(deltaM);
    }
}
