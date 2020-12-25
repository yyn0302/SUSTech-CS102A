import java.util.*;

public class Ex6_4 {
    public static int[][] powMatrix(int[][] Mat1, int[][] Mat2) {
        int[][] poweredMatrix = new int[Mat1.length][Mat2[0].length];
        for (int lnIndex = 0; lnIndex < poweredMatrix.length; lnIndex++) {
            for (int colIndex = 0; colIndex < poweredMatrix[0].length; colIndex++) {
                for (int k = 0; k < Mat2.length; k++) {
                    poweredMatrix[lnIndex][colIndex] += Mat1[lnIndex][k] * Mat2[k][colIndex];
                }
            }
        }
        return poweredMatrix;
    }

    public static void printMatrix(int[][] matrix) {
        System.out.println("The results:");
        for (int i = 0; i < matrix.length; i++) {
            for (int j : matrix[i]) {
                
                System.out.printf("%8d", j);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter the number of matrices:");
        int numOfMatrices = sc.nextInt();
        
        int[][][] Matrices = new int[numOfMatrices][][];

        for (int i = 0; i < numOfMatrices; i++) {
            System.out.printf("Enter the number of row and column of matrix %d: ", i + 1);
            int matRows = sc.nextInt();
            int matCols = sc.nextInt();
            Matrices[i] = new int[matRows][matCols];
            System.out.println("Enter the elements of the matrix:");
            for (int row = 0; row < matRows; row++) {
                for (int column = 0; column < matCols; column++) {
                    Matrices[i][row][column] = sc.nextInt();
                }
            }
        }

        sc.close();

        int[][][] temp = new int[numOfMatrices - 1][][];
        temp[0] = (int[][])powMatrix(Matrices[0], Matrices[1]).clone();
        for (int i = 2; i <= numOfMatrices - 1; i++) {
            temp[i - 1] = powMatrix(temp[i - 2], Matrices[i]);
        }

        printMatrix(temp[numOfMatrices - 2]);
    }
}