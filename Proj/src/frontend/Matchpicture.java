package frontend;

import cs102a.aeroplane.savegame.SystemSelect;

import javax.swing.*;

public class Matchpicture {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.add(panel);
        frame.setVisible(true);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    //根据骰子点数选择图片
    public static ImageIcon chooseImage(int points) {
        return thepicture(points);
    }

    public static ImageIcon thepicture(int point) {

        String path = SystemSelect.isMacOS() ? SystemSelect.getMacImagePath() : SystemSelect.getWindowsImagePath();

        ImageIcon pic1 = new ImageIcon(path + "1.jpg");
        ImageIcon pic2 = new ImageIcon(path + "2.jpg");
        ImageIcon pic3 = new ImageIcon(path + "3.jpg");
        ImageIcon pic4 = new ImageIcon(path + "4.jpg");
        ImageIcon pic5 = new ImageIcon(path + "5.jpg");
        ImageIcon pic6 = new ImageIcon(path + "6.jpg");

        switch (point) {
            case 2:
                return pic2;
            case 3:
                return pic3;
            case 4:
                return pic4;
            case 5:
                return pic5;
            case 6:
                return pic6;
            default:
                return pic1;
        }
    }
}

