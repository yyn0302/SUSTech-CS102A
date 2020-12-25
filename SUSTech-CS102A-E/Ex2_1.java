import java.util.Scanner;

public class Ex2_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String name;
        int birth;
        float weight;
        char grade;

        System.out.print("Enter your name: ");
        name = sc.next();
        System.out.print("Enter your birth year: ");
        birth = sc.nextInt();
        System.out.print("Enter your weight: ");
        weight = sc.nextFloat();
        System.out.print("Enter your grade: ");
        // grade = sc.next().charAt(0).toUpperCase();
        grade = sc.next().charAt(0);

        System.out.printf("Name: %s\n" +
                "Age: %d\n" +
                "Weight: %.2f\n" +
                "Grade: %c\n\n",
                name, 2020 - birth, weight, grade);

        sc.close();
    }
}
