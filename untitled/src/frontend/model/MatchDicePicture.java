package frontend.model;

import cs102a.aeroplane.util.SystemSelect;

import javax.swing.*;

//根据骰子点数选择图片
public class MatchDicePicture {

    public static ImageIcon getImage(int point) {
        String path = SystemSelect.isMacOS() ? SystemSelect.getMacImagePath() : SystemSelect.getWindowsImagePath();
        ImageIcon pic1 = new ImageIcon(path + "1.jpg");
        ImageIcon pic2 = new ImageIcon(path + "2.jpg");
        ImageIcon pic3 = new ImageIcon(path + "3.jpg");
        ImageIcon pic4 = new ImageIcon(path + "4.jpg");
        ImageIcon pic5 = new ImageIcon(path + "5.jpg");
        ImageIcon pic6 = new ImageIcon(path + "6.jpg");

        switch (point) {
            case 1:
                return pic1;
            case 2:
                return pic2;
            case 3:
                return pic3;
            case 4:
                return pic4;
            case 5:
                return pic5;
            default:
                return pic6;
        }
    }
}

