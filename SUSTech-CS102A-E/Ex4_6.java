import java.util.*;

public class Ex4_6 {
    public static void getAvgAndPairs() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter how many numbers: ");
        int size = sc.nextInt();
        int[] numbers = new int[size];
        int total = 0;
        System.out.printf("Enter %d numbers:\n", size);
        for (int i = 0; i < size; i++) {
            numbers[i] = sc.nextInt();
            total += numbers[i];
        }
        sc.close();
        System.out.printf("average=%.1f\n", (float)total/size);
        // 随便找一个数x，要让另一个数大于 2avg - x
        // 用bs找到[2avg-x]
        // 可能的组合数对于该x，是size-index
        //for 让 indexX 从0到size-2

        // TODO 以下可能有bug
        int greater;
        int pairs = 0;
        for (int indexX = 0; indexX < size - 2; indexX++) {
            greater = (int)Math.ceil(2*(float)total/size - numbers[indexX]);
            // 这里改进过了，看bs
            if (numbers[size - 1] >= greater) {
                int fill = size - 1;
                for (; fill > indexX; fill--) {
                    if (numbers[fill] < greater) break;
                }
                pairs += size - fill - 1;
            }
        }
        System.out.printf("The number of pairs of integer is %d\n", pairs + 1);
        return;
    }
    public static void main(String[] args) {
        long current1=System.currentTimeMillis();
        getAvgAndPairs();
        long current2=System.currentTimeMillis();
        System.out.printf("your program using %.3f second\n",(current2 - current1)/1000000.0d);
        // System.out.println(current1);
        // System.out.println(current2);
    }
}
