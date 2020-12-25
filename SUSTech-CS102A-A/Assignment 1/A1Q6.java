import java.util.Scanner;
import java.lang.Math;

public class A1Q6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int daysToCourse = sc.nextInt();
        int timesToTakeBus = 0;
        for (int i = 0; i < daysToCourse; i++) {
            String someDayCourse = sc.next();
            for (int j = 0; j < someDayCourse.length(); j++) {
                if (j == 0) timesToTakeBus += someDayCourse.charAt(j) == 49 ? 1 : 0;
                if (j == someDayCourse.length()-1)    timesToTakeBus += someDayCourse.charAt(j) == 49 ? 1 : 0;
                else    timesToTakeBus += Math.abs(someDayCourse.charAt(j) - someDayCourse.charAt(j+1)) == 1 ? 1 : 0;
            }
        }
        sc.close();
        System.out.print(timesToTakeBus);
    }
}