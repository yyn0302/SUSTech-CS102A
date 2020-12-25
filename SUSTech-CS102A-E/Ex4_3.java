// > java Lab5P3
// Enter the length of array:4
// Enter the 1st integer array of size 4:1 2 3 4
// Enter the 2nd integer array of size 4:1 2 3 4
// The two arrays are equal.
// > java Lab5P3
// Enter the length of array:3
// Enter the 1st integer array of size 4:1 2 3
// Enter the 2nd integer array of size 4:3 2 1
// The two arrays are not equal.

import java.util.Scanner;
import java.util.Arrays;

public class Ex4_3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the length of array:");
        int lengthOfArray = sc.nextInt();
        int[] arr1 = new int[lengthOfArray];
        int[] arr2 = new int[lengthOfArray];
        
        System.out.printf("Enter the 1st integer array of size %d:", lengthOfArray);
        for (int i = 0; i < lengthOfArray; i++) {
            arr1[i] = sc.nextInt();
        }
        System.out.printf("Enter the 2nd integer array of size %d:", lengthOfArray);
        for (int i = 0; i < lengthOfArray; i++) {
            arr2[i] = sc.nextInt();
        }
        sc.close();
        System.out.println(Arrays.equals(arr1, arr2) ? "The two arrays are equal." : "The two arrays are not equal.");
    }
}
