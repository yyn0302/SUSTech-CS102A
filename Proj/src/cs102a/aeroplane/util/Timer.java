package cs102a.aeroplane.util;

import java.awt.*;

public class Timer {

    /**
     * @description: 利用Robot类实现自动操作
     * @param {int} ms
     * @return {*}
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
