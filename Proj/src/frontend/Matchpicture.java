package frontend;

import javax.swing.*;

public class Matchpicture {

//根据骰子点数选择图片
public static ImageIcon chooseImage(int points){
      return thepicture(points);
}
public static ImageIcon thepicture(int point) {
    ImageIcon pic1 = new ImageIcon("src\\1.jpg");
    ImageIcon pic2 = new ImageIcon("src\\2.jpg");
    ImageIcon pic3 = new ImageIcon("src\\3.jpg");
    ImageIcon pic4 = new ImageIcon("src\\4.jpg");
    ImageIcon pic5 = new ImageIcon("src\\5.jpg");
    ImageIcon pic6 = new ImageIcon("src\\6.jpg");

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

        case 6:
            return pic6;
        default:
            return pic1;
    }
}
}

