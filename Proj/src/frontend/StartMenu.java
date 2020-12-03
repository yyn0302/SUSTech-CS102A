package frontend;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenu extends JFrame{
    private JButton 设置Button;
    private JPanel panel1;
    private JButton 继续Button;
    private JButton 开始Button;

    public StartMenu() {
        设置Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame jf1=new JFrame("设置");
                jf1.setBounds(100,50,800,600);
                JPanel jp1=new JPanel();
                JButton jb1=new JButton("主题");
                jb1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFrame jf2=new JFrame("主题");
                        JPanel jp2=new JPanel();
                        JButton jb2=new JButton("海王");
                        JButton jb3=new JButton("灵笼");
                        jb2.add(jb3,jb2);
                        jf2.add(jp2);
                        jf2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                        jf1.setVisible(false);
                        jf2.setVisible(true);
                    }
                });
                jp1.add(jb1);
                jf1.add(jp1);
                jf1.setDefaultCloseOperation(jf1.EXIT_ON_CLOSE);
                jf1.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("StartMenu");
        frame.setContentPane(new StartMenu().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
