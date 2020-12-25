import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ex11_2 extends JFrame {
    private JButton btnCnt;
    private JTextField tfCnt;
    private int count = 0;

    public Ex11_2() {
        setLayout(new FlowLayout(FlowLayout.LEFT, 50, 0));
        add(new JLabel("Counter"));

        tfCnt = new JTextField("0");
        tfCnt.setEditable(false);
        btnCnt = new JButton("Count");
        btnCnt.addActionListener(new eventListener());
        add(tfCnt);
        add(btnCnt);
    }

    public static void main(String[] args) {
        Ex11_2 gui = new Ex11_2();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(800, 600);
        gui.setVisible(true);

    }

    class eventListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ++count; tfCnt.setText(count + "");
        }
    }
}