import java.util.Scanner;

public class Ex8_5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("s1: ");
        String s1 = sc.nextLine();
        System.out.print("s2: ");
        String s2 = sc.nextLine();
        int index = 0;
        int flag = 0;
        while (s1.indexOf(s2, index) >= 0) {
            index = s1.indexOf(s2, index);
            System.out.println("Found at index: " + index);
            index++;
            flag++;
        }
        System.out.println("Total occurrences:" + flag);
    }
}
