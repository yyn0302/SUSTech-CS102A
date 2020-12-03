package frontend;

import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {
    Image im;

    public BackgroundPanel(Image im) {
        this.im = im;
        this.setOpaque(true);
    }

    //Draw the back ground.
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.drawImage(im, 0, 0, this.getWidth(), this.getHeight(), this);

    }
}