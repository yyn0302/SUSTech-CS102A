import javax.swing.*;
import java.awt.*;

public class Ex11_1 extends JFrame {
    private JLabel label;

    public Ex11_1() {
        super("Ex11_1");
        setLayout(new FlowLayout());
        label = new JLabel("GUI demo");
        label.setFont(new Font("JetBrains Mono", Font.PLAIN, 30));
        add(label);
    }

    public static void main(String[] args) {
        Ex11_1 gui = new Ex11_1();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(800, 600);
        gui.setVisible(true);

        String input1 = JOptionPane.showInputDialog("Input num 1: ");
        String input2 = JOptionPane.showInputDialog("Input num 2: ");
        JOptionPane.showMessageDialog(null, input1 + " + " + input2 + " = " +
                (int)(Integer.parseInt(input1) + Integer.parseInt(input2)));
    }
}