package cs102a.aeroplane.model;

import cs102a.aeroplane.GameInfo;
import cs102a.aeroplane.presets.BoardCoordinate;
import cs102a.aeroplane.presets.PlaneState;
import cs102a.aeroplane.util.SystemSelect;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PlaneView extends JButton {
    private final int color;
    private final int number;
    private final int itsHangar;
    // TODO: 2020/12/14 放好棋盘GUI后把棋盘图片左上角重定位(0,0)
    private final int xOffSet;
    private final int yOffSet;
    private final ChessBoard chessboard;    // 引用
    private int state;
    private final Aeroplane aeroplane;
    private int numOfStackedPlanes;
    MouseListener ableToMoveTipListener = new MouseListener() {
        @Override
        // 选择此飞机，禁止其他点击，飞到相应位置
        public void mousePressed(MouseEvent e) {
            for (Aeroplane p : chessboard.getPlanes()) {
                if (p.getNumber() != number) p.getPlaneView().setEnabled(false);
                aeroplane.tryMovingFront(chessboard.getNowMove());
            }
        }

        // 做了个移入移出的可选择的提示
        @Override
        public void mouseEntered(MouseEvent e) {
            StringBuilder themeSelectedIconPath = new StringBuilder();
            themeSelectedIconPath.append(SystemSelect.getImagePath());
            themeSelectedIconPath.append("pl_fin_");
            switch (color) {
                case PlaneState.BLUE:
                    themeSelectedIconPath.append("bl.png");
                    break;
                case PlaneState.GREEN:
                    themeSelectedIconPath.append("gr.png");
                    break;
                case PlaneState.RED:
                    themeSelectedIconPath.append("re.png");
                    break;
                case PlaneState.YELLOW:
                    themeSelectedIconPath.append("ye.png");
                    break;
            }
            setIcon(new ImageIcon(themeSelectedIconPath.toString()));
        }

        // 做了个移入移出的可选择的提示
        @Override
        public void mouseExited(MouseEvent e) {
            setIconAsPlaneNum(numOfStackedPlanes);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }
    };

    public PlaneView(ChessBoard chessboard, int number, int color, int itsHangar, int xOffSet, int yOffSet, Aeroplane aeroplane) {
        this.color = color;
        this.xOffSet = xOffSet;
        this.yOffSet = yOffSet;
        this.chessboard = chessboard;
        this.number = number;
        this.itsHangar = itsHangar;
        this.aeroplane = aeroplane;

        this.numOfStackedPlanes = 1;
        this.state = PlaneState.IN_HANGAR;
        this.setIconAsPlaneNum(1);  // 设置单个飞机图片
        this.moveTo(itsHangar);     // 放上棋盘
    }

    // 输入飞机叠子数量，将此飞机设置为对应的图片
    // 其他被叠飞机另外设置invisible
    public void setIconAsPlaneNum(int numOfStackedPlanes) {
        this.numOfStackedPlanes = numOfStackedPlanes;
        StringBuilder themeSelectedIconPath = new StringBuilder();
        themeSelectedIconPath.append(SystemSelect.getImagePath());
        themeSelectedIconPath.append(GameInfo.getTheme() == 1 ? "t1_p" : "t2_p");
        themeSelectedIconPath.append(numOfStackedPlanes).append("_");
        switch (color) {
            case PlaneState.BLUE:
                themeSelectedIconPath.append("bl.png");
                break;
            case PlaneState.GREEN:
                themeSelectedIconPath.append("gr.png");
                break;
            case PlaneState.RED:
                themeSelectedIconPath.append("re.png");
                break;
            case PlaneState.YELLOW:
                themeSelectedIconPath.append("ye.png");
                break;
        }
        this.setIcon(new ImageIcon(themeSelectedIconPath.toString()));
    }

    public void moveTo(int generalIndex) {
        this.setBounds(xOffSet + BoardCoordinate.GRID_CENTER_OFFSET[generalIndex][0] - BoardCoordinate.GRID_SIZE / 2,
                yOffSet + BoardCoordinate.GRID_CENTER_OFFSET[generalIndex][1] - BoardCoordinate.GRID_SIZE / 2,
                BoardCoordinate.GRID_SIZE, BoardCoordinate.GRID_SIZE);

        // FIXME: 2020/12/17 旋转
//        this.setRotation(BoardCoordinate.REVOLVE_ANGLE[generalGridIndex]);
//        try {
//            BufferedImage origin;
//            BufferedImage rotated90 = rotate(original, 90.0d);
//            BufferedImage rotatedMinus90 = rotate(original, -90.0d);
//
//            JPanel panel = new JPanel();
//            panel.add(new JLabel(new ImageIcon(original)));
//            panel.add(new JLabel(new ImageIcon(rotated90)));
//            panel.add(new JLabel(new ImageIcon(rotatedMinus90)));
//
//            JOptionPane.showMessageDialog(null, panel, null, JOptionPane.PLAIN_MESSAGE, null);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
    }

//    /**
//     * @param image   BufferedImage original = ImageIO.read(img);
//     *                BufferedImage rotated90 = rotate(original, 90.0d);
//     * @param degrees in degrees
//     * @return a rotated BufferedImage can be given to new ImageIcon()
//     */
//    private BufferedImage rotate(BufferedImage image, Double degrees) {
//        double radians = Math.toRadians(degrees);
//        double sin = Math.abs(Math.sin(radians));
//        double cos = Math.abs(Math.cos(radians));
//        int newWidth = (int) Math.round(image.getWidth() * cos + image.getHeight() * sin);
//        int newHeight = (int) Math.round(image.getWidth() * sin + image.getHeight() * cos);
//
//        BufferedImage rotate = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
//        Graphics2D g2d = rotate.createGraphics();
//        int x = (newWidth - image.getWidth()) / 2;
//        int y = (newHeight - image.getHeight()) / 2;
//        AffineTransform at = new AffineTransform();
//        at.setToRotation(radians, x + (image.getWidth() / 2), y + (image.getHeight() / 2));
//        at.translate(x, y);
//        g2d.setTransform(at);
//        g2d.drawImage(image, 0, 0, null);
//        g2d.dispose();
//        return rotate;
//    }

    public void readyToBeSelected() {
        for (ActionListener actionListener : this.getActionListeners()) {
            this.removeActionListener(actionListener);
        }
        this.addMouseListener(ableToMoveTipListener);
        this.setEnabled(true);
    }

    public void finish() {
        moveTo(itsHangar);
        StringBuilder themeSelectedIconPath = new StringBuilder();
        themeSelectedIconPath.append(SystemSelect.getImagePath());
        themeSelectedIconPath.append("pl_fin_");
        switch (color) {
            case PlaneState.BLUE:
                themeSelectedIconPath.append("bl.png");
                break;
            case PlaneState.GREEN:
                themeSelectedIconPath.append("gr.png");
                break;
            case PlaneState.RED:
                themeSelectedIconPath.append("re.png");
                break;
            case PlaneState.YELLOW:
                themeSelectedIconPath.append("ye.png");
                break;
        }
        setIcon(new ImageIcon(themeSelectedIconPath.toString()));
        this.state = PlaneState.FINISH;
        this.setEnabled(false);
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
