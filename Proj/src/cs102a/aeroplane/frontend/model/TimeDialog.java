package cs102a.aeroplane.frontend.model;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimeDialog {

    private final JLabel label = new JLabel();
    private JDialog dialog;

    private int seconds;

    /**
     * @param jFrameOfButton 程序主窗口（按钮所在）
     * @param message        对话框主体消息
     * @param closeInSec     以秒记的自动关闭时间，可以提前按按钮关闭
     */
    public void showDialog(JFrame jFrameOfButton, String message, int closeInSec) {
        seconds = closeInSec;
        label.setText(message);
        label.setBounds(80, 10, 200, 20);

        ScheduledExecutorService s = Executors.newSingleThreadScheduledExecutor();

        JButton confirm = new JButton("Ok ok...");
        confirm.setBounds(100, 40, 60, 20);
        confirm.addActionListener(e -> TimeDialog.this.dialog.dispose());

        dialog = new JDialog(jFrameOfButton, true);
        dialog.setTitle("给你" + seconds + "秒，看好啦");
        dialog.setLayout(null);
        dialog.add(label);
        dialog.add(confirm);

        s.scheduleAtFixedRate(() -> {
            TimeDialog.this.seconds--;
            if (TimeDialog.this.seconds == 0) {
                TimeDialog.this.dialog.dispose();
                System.gc();
            } else {
                dialog.setTitle("给你" + seconds + "秒，看好啦");
            }
        }, 1, 1, TimeUnit.SECONDS);

        dialog.pack();
        dialog.setSize(new Dimension(350, 100));
        dialog.setLocationRelativeTo(jFrameOfButton);
        dialog.setVisible(true);
    }
}
