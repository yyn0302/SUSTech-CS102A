package frontend.model;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


/**
 * JTextField num1 = new JTextField(10);
 * num1.addKeyListener(new InputLimiter());
 */
public class InputLimiter extends KeyAdapter {
// 只能输入数字

    @Override
    public void keyTyped(KeyEvent e) {
        String key = "0123456789" + (char) 8;
        if (key.indexOf(e.getKeyChar()) < 0) {
            e.consume();       //如果不是数字则取消
        }
    }
}
