import java.util.*;
// import java.lang.*;


public class Ex4_0 {
    public static void main(String[] args) {
        int[] demoArr = {1,2,4,5,5};
        // System.out.println(demoArr[1][2]);
        // Arrays.fill(demoArr, 3);
        System.out.print("");
        System.out.print("");
        int[] demoCopy = new int[demoArr.length + 1];
        System.arraycopy(demoArr, 0, demoCopy, 1, demoArr.length);
        for (int i : demoCopy)
            System.out.println(i);
            
            // boolean a = Arrays.equals(demoArr, demoCopy);
            // if (a)
            System.out.println(Arrays.binarySearch(demoArr, 8));
            
        int[] arr = null;
        System.out.println(arr);
        arr = demoArr;
        System.out.println(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(demoArr));

        char[] Array4 = {'a', 'b', 'c'};
        System.out.println(Array4);
    }
}

//import java.util.*;
//
//public class Ex4_1 {
//    public static void main(String[] args) {
//        double[] arr1 = {4.3, 5.2, 6.3, 1.4};
//        // 这里的测试数据随便换
//
//        double[] arr2 = new double[arr1.length];
//        System.arraycopy(arr1, 0, arr2, 0, arr1.length);
//        // 把arr1全复制到arr2
//        Arrays.sort(arr2);
//
//        for (double i : arr1) {
//            int bsIndex = Arrays.binarySearch(arr2, i);
//            System.out.println("\n\n" + bsIndex);
//            System.out.print(i);
//            // binarySearch要先有序
//        }
//    }
//}
//
