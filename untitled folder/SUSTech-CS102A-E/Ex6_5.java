import java.util.*;

public class Ex6_5 {
    public static boolean examineRows(int[][] toExam) {
        int[][] copyExam = new int[9][9];
        int[] trueSudo = {1,2,3,4,5,6,7,8,9};
        for (int i = 0; i < 9; i++) {
            System.arraycopy(toExam[i], 0, copyExam[i], 0, 9);
            Arrays.sort(copyExam[i]);
            if (!Arrays.equals(copyExam[i], trueSudo)) return false;
        }
        return true;
    }
    
    public static boolean examineCols(int[][] toExam) {
        int[] temp = new int[9];
        int[] trueSudo = {1,2,3,4,5,6,7,8,9};
        for (int col = 0; col < 9; col++) {
            System.arraycopy(toExam[col], 0, temp, 0, 9);
            Arrays.sort(temp);
            if (!Arrays.equals(temp, trueSudo)) return false;
        }
        return true;
    }

    public static boolean splitExam(int[][] toExam) {
        int[] temp = new int[9];
        int[] trueSudo = {1,2,3,4,5,6,7,8,9};
        for (int sepStatus = 0; sepStatus <= 6; sepStatus += 3) {
            for (int rowPlus = 0; rowPlus < 3; rowPlus++) {
                System.arraycopy(toExam[sepStatus + rowPlus], sepStatus, temp, rowPlus * 3, 3);
            }
            Arrays.sort(temp);
            if (!Arrays.equals(temp, trueSudo)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // get a 9*9 2d-array
        int[][] maybeSudoku = new int[9][9];
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                maybeSudoku[row][col] = sc.nextInt();
            }
        }
        sc.close();

        if (examineCols(maybeSudoku) && examineRows(maybeSudoku) && splitExam(maybeSudoku)) System.out.println("Yes");
        else System.out.println("No");
    }
}
