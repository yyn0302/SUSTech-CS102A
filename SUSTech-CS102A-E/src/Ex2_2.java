import java.util.Scanner;


public class Ex2_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        float a, h;
        System.out.print("Enter the width of a rectangle: ");
        a = sc.nextFloat();
        System.out.print("Enter the height of a rectangle: ");
        h = sc.nextFloat();

        System.out.println("The area is " + (a * h));
        System.out.println("The perimeter is " + (a + h) * 2);

        sc.close();
    }
}