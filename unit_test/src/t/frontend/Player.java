package t.frontend;

import java.util.ArrayList;

public class Player {
    private static int ans;

//    public static int getAns() {
//        return ans;
//    }
//
//    public static void setAns(int ans) {
//        Player.ans = ans;
//    }

//    private static int ans;

//    public static int getAns() {
//        return ans;
//    }

    public static void setAns(int ans) {
        Player.ans = ans;
    }

    public static int askPlayerStep(int[] rollResult) {
        if (GameInfo.isIsCheatMode()) {
//            // TODO: 2020/12/4 传入popup中用户输入的值，原始的String即可
//            // FIXME: 2020/12/4 删除调试用例
//            String cheat = "";
//            while (true) {
//                boolean hasError = false;
//                try {
//                    ans = Integer.parseInt(cheat);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    hasError = true;
//                    continue;
//                    // TODO: 2020/12/4 JPopupMenu 应该捕获这个异常并且提示用户重新输入
//                }
//                if (ans >= 1 && ans <= 12) return ans;
//                else {
//                    hasError = true;
//                    // TODO: 2020/12/4 JPopupMenu 应该捕获这个异常并且提示用户重新输入
//                }
//            }
            CustomerChoice.main(null);
        } else {
//            获取这个Arraylist

            ArrayList<Integer> possibleChoice = new ArrayList<>();
            if (rollResult[0] + rollResult[1] <= 12)
                possibleChoice.add(rollResult[0] + rollResult[1]);
            if (rollResult[0] - rollResult[1] > 0)
                possibleChoice.add(rollResult[0] - rollResult[1]);
            if (rollResult[1] - rollResult[0] > 0)
                possibleChoice.add(rollResult[1] - rollResult[0]);
            if (rollResult[0] * rollResult[1] <= 12)
                possibleChoice.add(rollResult[1] * rollResult[0]);
            if ((rollResult[0] / (float) rollResult[1]) % 1f == 0f)
                possibleChoice.add(rollResult[0] / rollResult[1]);
            if ((rollResult[1] / (float) rollResult[0]) % 1f == 0f)
                possibleChoice.add(rollResult[1] / rollResult[0]);
            // TODO: 2020/12/4 把这个list传给前端，选择一个并把值传回来
            // 前端对应选项传回的int
            CustomerChoice.main(possibleChoice);
        }
            return ans;
    }
}