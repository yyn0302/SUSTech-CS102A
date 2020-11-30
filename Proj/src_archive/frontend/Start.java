package frontend;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Start {
    private JButton 开始游戏Button;
    private JButton 继续游戏Button;
    private JButton 设置Button;
    private JPanel panel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Start");
        frame.setContentPane(new Start().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public Start() {
        设置Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    String filepath = "src_archive/Incarnation.wav";
                    musicStuff musicObject = new musicStuff();
                    musicObject.playMusic(filepath);
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
