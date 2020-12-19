package cs102a.aeroplane.model;

import cs102a.aeroplane.GameInfo;
import cs102a.aeroplane.util.Timer;

import java.util.ArrayList;

public class ComputerAgent {

//    public static int getBestStep(int[] rollResult, int tryingPlane, ChessBoard chessBoard) {
//        ArrayList<Integer> possibleStep = new ArrayList<>();
//        if (GameInfo.isIsCheatMode()) {
//            for (int i = 1; i <= 12; i++) {
//                possibleStep.add(i);
//            }
//        } else {
//            int a = rollResult[0] + rollResult[1];
//            int b = rollResult[0] - rollResult[1];
//            int c = rollResult[1] - rollResult[0];
//            int e = rollResult[0] * rollResult[1];
//            float f = rollResult[0] / (float) rollResult[1];
//            float g = rollResult[1] / (float) rollResult[0];
//            if (a <= 12) possibleStep.add(a);
//            if (b > 0 && b != a) possibleStep.add(b);
//            if (c > 0 && c != a) possibleStep.add(c);
//            if (e <= 12 && e != a && e != b && e != c) possibleStep.add(e);
//            if (f % 1f == 0f && f != a && f != b && f != c && f != e) possibleStep.add((int) f);
//            if (g % 1f == 0f && g != a && g != b && g != c && g != e && g != f) possibleStep.add((int) g);
//        }
//        int nowGridIndex = chessBoard.getPlanes()[tryingPlane].getSelfPathIndexFromGeneralIndex
//                (chessBoard.getPlanes()[tryingPlane].getGeneralGridIndex());
//        for (int step : possibleStep) {
//            if (chessBoard.getPlanes()[tryingPlane].isJetGrid(nowGridIndex + step) != -1)
//                return step;
//        }
//
//    }

//    public static int getBestPlaneToMove() {
//
//    }

    public static void selectAndClick() {
        Timer.delay(300);

    }
}
