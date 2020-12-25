import java.util.Scanner;
import java.util.Arrays;

public class Ex4_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        float[] grade = new float[10];
        for (int i = 0; i < 10; i++) {
            grade[i] = sc.nextFloat();
        }
        Arrays.sort(grade);
        float avg = 0;
        for (int i = 1; i < 9; i++) {
            avg += grade[i];
        }
        System.out.println(avg / 8);
    }
}