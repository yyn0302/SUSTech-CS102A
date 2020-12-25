import java.util.ArrayList;
import java.io.*;
import java.util.StringTokenizer;
// import java.util.Scanner;

class standardCase {
    public static int lengthOfSet;
    public static int luckyDivisor;
    public static int[]
            standardList;
    public static int luckyNum;

    public standardCase(int lS, int lD, int[] sL) {
        lengthOfSet = lS;
        luckyDivisor = lD;
        standardList = sL;
        int temp = 0;
        for (int i : sL) {
            temp += i;
        }
        luckyNum = temp % luckyDivisor;
    }
}

public class A2Q5 {
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


    static int setLength;

    public static int getScore(int[] testList) {
        int score = 0;
        for (int i = 0; i < testList.length; i++) {
            if (testList[i] == standardCase.standardList[i]) score += 2;
            else if (Math.abs(testList[i] - standardCase.standardList[i]) < 3) score++;
        }
        int temp = 0;
        int testLuckyNum;
        for (int i : testList) {
            temp += i;
        }
        testLuckyNum = temp % standardCase.luckyDivisor;
        if (testLuckyNum == standardCase.luckyNum) score += 2;
        else if (Math.abs(testLuckyNum - standardCase.luckyNum) < 3) score++;
        return score;
    }

    public static void main(String[] args) {
        // Scanner sc = new Scanner(System.in);
        Reader sc = new Reader(System.in);
        int numOfTestCases = sc.nextInt();
        setLength = sc.nextInt();
        int luckyDivisor = sc.nextInt();
        int[] temp1 = new int[A2Q5.setLength];
        for (int j = 0; j < A2Q5.setLength; j++) {
            temp1[j] = sc.nextInt();
        }
        standardCase stCase = new standardCase(setLength, luckyDivisor, temp1);
        ArrayList<Integer> testCaseScores = new ArrayList<>();

        for (int i = 0; i < numOfTestCases; i++) {
            int[] temp = new int[A2Q5.setLength];
            for (int j = 0; j < A2Q5.setLength; j++) {
                temp[j] = sc.nextInt();
            }
            testCaseScores.add(getScore(temp));
        }
        for (int i = 0; i < numOfTestCases; i++) {
            System.out.println(testCaseScores.get(i));
        }
    }
}
