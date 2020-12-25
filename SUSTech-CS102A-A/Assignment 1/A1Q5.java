import java.util.Scanner;

public class A1Q5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int totalMoney = sc.nextInt(), availableSC;
        sc.close();

        int[] SC = {823543, 117649, 16807, 2401, 343, 49, 7, 1};
        
        for (int i : SC) {
            availableSC = totalMoney / i;
            System.out.print(availableSC);
            totalMoney -= availableSC * i;
        }
    }
}