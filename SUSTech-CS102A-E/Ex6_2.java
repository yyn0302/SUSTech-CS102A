import java.util.*;
import java.lang.*;

public class Ex6_2 {
    public static double area(double a, double b, double c) {
        double p = (a+b+c)/2;
        return Math.sqrt(p * (p-a) * (p-b) * (p-c));
    }

    public static double perimeter(double a, double b, double c) {
        return a + b + c;
    }

    public static boolean isValid(double a, double b, double c) {
        double sum = a + b + c;
        if (c < sum/2 && b < sum/2 && a < sum/2) return true;
        else return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for(;;) {
            double a = sc.nextDouble();
            if (a== -1) {
                System.out.println("Bye");
                return;
            }
            double b = sc.nextDouble();
            double c = sc.nextDouble();
            if (!isValid(a, b, c)) {
                System.out.println("The input is invalid.");
                continue;
            }
            System.out.printf("The area is %.3f\n", area(a, b, c));
            System.out.printf("The perimeter is %.3f\n", perimeter(a, b, c));
        }
    }
}