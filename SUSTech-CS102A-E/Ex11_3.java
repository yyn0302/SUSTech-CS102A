import javax.swing.*;
import java.awt.*;

public class Ex11_3 extends JFrame {
    private JButton btn1, btn2, btn3, btn4, btn5, btn6;

    public Ex11_3() {
        super("Grid Layout");
        setLayout(new GridLayout(3, 2, 3, 3));
        btn1 = new JButton("Button 1");
        add(btn1);
        btn2 = new JButton("This is Button 2");
        add(btn2);
        btn3 = new JButton("3");
        add(btn3);
        btn4 = new JButton("Another Button 4");
        add(btn4);
        btn5 = new JButton("Button 5");
        add(btn5);
        btn6 = new JButton("One More Button 6");
        add(btn6);
    }

    public static void main(String[] args) {
        Ex11_3 gui = new Ex11_3();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(800, 600);
        gui.setVisible(true);
    }
}
