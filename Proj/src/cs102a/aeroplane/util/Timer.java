package cs102a.aeroplane.util;

import java.awt.*;

public class Timer {

    /**
     * @param {int} ms
     * @return {*}
     * @description: 利用Robot类实现自动操作
     */
    public static void delay(int ms) {
        try {
            Robot robot = new Robot();
            robot.delay(ms);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
