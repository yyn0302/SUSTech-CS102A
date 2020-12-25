import java.util.*;

public class Ex6_3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Please enter the number of subjects:");
        int subjects = sc.nextInt();
        System.out.print("Please enter the number of students:");
        int students = sc.nextInt();
        int[][] SubStu = new int[subjects][students];
        for (int i = 0; i < subjects; i++) {
            for (int j = 0; j < students; j++) {
                SubStu[i][j] = sc.nextInt();
            }
        }
        sc.close();

        System.out.print("        ");
        for (int i = 1; i <= subjects; i++) {
            System.out.printf("    Course%d", i);
        }
        System.out.println("    Average");
        
        for (int i = 1; i <= students; i++) {
            System.out.printf("Student%d", i);
            float sum = 0f;
            for (int j = 0; j < subjects; j++) {
                System.out.printf("%10d", SubStu[j][i - 1]);
                sum += SubStu[j][i - 1];
            }
            System.out.printf("%8.2f\n", sum/subjects);
        }
        
        System.out.print("Average");
        for (int i = 0; i < subjects; i++) {
            float subSum = 0;
            for (int j : SubStu[i]) {
                subSum += j;
            }
            System.out.printf("%12.2f", subSum/students);
        }
    }
}