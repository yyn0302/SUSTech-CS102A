package cs102a.aeroplane.frontend.model;

import cs102a.aeroplane.GameInfo;
import cs102a.aeroplane.model.ChessBoard;
import cs102a.aeroplane.util.SystemSelect;

import javax.swing.*;
import java.awt.*;


// 玩家面板,游戏窗体的右上角
// PlayerInfoPanel pip = new PlayerInfoPanel(this)
// pip.refresh();
public class PlayerInfoPanel extends JPanel {

    private static final String path = SystemSelect.getImagePath();
    private static final ImageIcon pic1 = new ImageIcon(path + "玩家1.jpg");
    private static final ImageIcon pic2 = new ImageIcon(path + "玩家2.jpg");
    private static final ImageIcon pic3 = new ImageIcon(path + "玩家3.jpg");
    private static final ImageIcon pic4 = new ImageIcon(path + "玩家4.jpg");

    private final JLabel playerLabel;
    private final JLabel nameLabel;
    private final ChessBoard chessBoard;

    public PlayerInfoPanel(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
        this.setLayout(new GridLayout(1, 2));
        this.setPreferredSize(new Dimension(100, 100));
        // TODO: 2020/12/13 大小有待调整

        ImageIcon pic;
        switch (chessBoard.getNowPlayer()) {
            case 1:
                pic = pic2;
                break;
            case 2:
                pic = pic3;
                break;
            case 3:
                pic = pic4;
                break;
            default:
                pic = pic1;
                break;
        }
        playerLabel = new JLabel(pic);

        String color;
        switch (chessBoard.getNowPlayer()) {
            case 2:
                color = "（绿子）";
                break;
            case 3:
                color = "（红子）";
                break;
            case 4:
                color = "（黄子）";
                break;
            default:
                color = "（蓝子）";
                break;
        }
        nameLabel = new JLabel("当前玩家：" + GameInfo.getPlayerName()[chessBoard.getNowPlayer()] + color);

        this.add(playerLabel);
        this.add(nameLabel);
    }

    public void refresh() {
        ImageIcon pic;
        switch (chessBoard.getNowPlayer()) {
            case 1:
                pic = pic2;
                break;
            case 2:
                pic = pic3;
                break;
            case 3:
                pic = pic4;
                break;
            default:
                pic = pic1;
                break;
        }
        playerLabel.setIcon(pic);

        String color;
        switch (chessBoard.getNowPlayer()) {
            case 2:
                color = "（绿子）";
                break;
            case 3:
                color = "（红子）";
                break;
            case 4:
                color = "（黄子）";
                break;
            default:
                color = "（蓝子）";
                break;
        }
        nameLabel.setText("当前玩家：" + GameInfo.getPlayerName()[chessBoard.getNowPlayer()] + color);
    }
}
