import java.util.Scanner;

public class OJ_13 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int lines = sc.nextInt(), cols = sc.nextInt();
        char[][] map = new char[lines][cols];
        int[][] flagged = new int[lines][cols];
        char[][] ans = new char[lines][cols];
        String tempLine;
        for (int i = 0; i < lines; i++) {
            tempLine = sc.next();
            map[i] = tempLine.toCharArray();
        }   // get the full map
        sc.close();

        for (int tempLn = 0; tempLn < lines; tempLn++) {
            for (int tempCol = 0; tempCol < cols; tempCol++) {
                try {
                    flagged[tempLn][tempCol] += map[tempLn - 1][tempCol - 1] == '*' ? 1 : 0;
                } catch (ArrayIndexOutOfBoundsException e) {}
                try {
                    flagged[tempLn][tempCol] += map[tempLn - 1][tempCol] == '*' ? 1 : 0;
                } catch (ArrayIndexOutOfBoundsException e) {}
                try {
                    flagged[tempLn][tempCol] += map[tempLn - 1][tempCol + 1] == '*' ? 1 : 0;
                } catch (ArrayIndexOutOfBoundsException e) {}
                try {
                    flagged[tempLn][tempCol] += map[tempLn][tempCol - 1] == '*' ? 1 : 0;
                } catch (ArrayIndexOutOfBoundsException e) {}
                try {
                    flagged[tempLn][tempCol] += map[tempLn][tempCol + 1] == '*' ? 1 : 0;
                } catch (ArrayIndexOutOfBoundsException e) {}
                try {
                    flagged[tempLn][tempCol] += map[tempLn + 1][tempCol - 1] == '*' ? 1 : 0;
                } catch (ArrayIndexOutOfBoundsException e) {}
                try {
                    flagged[tempLn][tempCol] += map[tempLn + 1][tempCol] == '*' ? 1 : 0;
                } catch (ArrayIndexOutOfBoundsException e) {}
                try {
                    flagged[tempLn][tempCol] += map[tempLn + 1][tempCol + 1] == '*' ? 1 : 0;
                } catch (ArrayIndexOutOfBoundsException e) {}
            }
        }   // get the num of bomb nearby

        for (int tempLn = 0; tempLn < lines; tempLn++) {
            for (int tempCol = 0; tempCol < cols; tempCol++) {
                if (map[tempLn][tempCol] == '*') {
                    ans[tempLn][tempCol] = 'F';
                } else {
                    ans[tempLn][tempCol] = flagged[tempLn][tempCol] == 0 ? '-' : (char) (flagged[tempLn][tempCol] + 48);
                }
            }
        }
        for (int tempLn = 0; tempLn < lines; tempLn++) {
            tempLine = new String(ans[tempLn]);
            System.out.println(tempLine);
        }
    }
}
