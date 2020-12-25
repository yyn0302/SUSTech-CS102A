import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class A2Q6 {
    private static class Reader {
        BufferedReader in;
        StringTokenizer tokenizer;

        public Reader(InputStream inputStream) {
            in = new BufferedReader(new InputStreamReader(inputStream));
        }

        private String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(in.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void MatPow2(long[][] Mat) {
        Mat = powMatrix(Mat, Mat);
    }

    public static long[][] quickPower(long[][] mat, int k) {
        long[][] ans = new long[mat.length][mat[0].length];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                ans[i][j] = mat[i][j];
            }
        }
        ArrayList<Integer> a = new ArrayList<>();
        while (k != 0) {
            a.add(k & 1);
            k = k >> 1;
        }
        for (int p = 0; p < a.size(); p++) {
            if (a.get(p) == 1) {
                ans = powMatrix(ans, mat);
            }
            mat = powMatrix(mat, mat);
        }
        return ans;
    }


    public static long[][] powMatrix(long[][] Mat1, long[][] Mat2) {
        long[][] poweredMatrix = new long[Mat1.length][Mat2[0].length];
        for (int lnIndex = 0; lnIndex < poweredMatrix.length; lnIndex++) {
            for (int colIndex = 0; colIndex < poweredMatrix[0].length; colIndex++) {
                for (int k = 0; k < Mat2.length; k++) {
                    poweredMatrix[lnIndex][colIndex] += (Mat1[lnIndex][k] * Mat2[k][colIndex]) % 1000000007;
                    poweredMatrix[lnIndex][colIndex] %= 1000000007;
                }
            }
        }
        return poweredMatrix;
    }


    public static void printMatrix(long[][] matrix) {
        for (long[] line : matrix) {
            for (long j : line) {
                System.out.printf("%d ", j);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Reader rd = new Reader(System.in);
        { // get all original matrices
            int numOfMatrices = rd.nextInt();
            int k = rd.nextInt();
            long[][][] Matrices = new long[numOfMatrices][][];
            for (int i = 0; i < numOfMatrices; i++) {
                int matRows = rd.nextInt();
                int matCols = rd.nextInt();
                Matrices[i] = new long[matRows][matCols];
                for (int row = 0; row < matRows; row++) {
                    for (int column = 0; column < matCols; column++) {
                        Matrices[i][row][column] = rd.nextInt();
                    }
                }
            }
            long[][][] temp = new long[numOfMatrices][][];
            temp[0] = Matrices[0].clone();
            for (int i = 1; i < numOfMatrices; i++) {
                temp[i] = powMatrix(temp[i - 1], Matrices[i]);
            }
            printMatrix(quickPower(temp[numOfMatrices - 1], k-1));

            long endTime = System.currentTimeMillis();
            System.out.println("\n\nusing " + (endTime - startTime) + " ms");
        }
    }
}