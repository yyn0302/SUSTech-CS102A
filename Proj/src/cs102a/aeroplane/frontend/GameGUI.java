package cs102a.aeroplane.frontend;

import cs102a.aeroplane.frontend.model.BackgroundPanel;
import cs102a.aeroplane.frontend.model.PlayerInfoPanel;
import cs102a.aeroplane.model.ChessBoard;
import cs102a.aeroplane.util.SystemSelect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameGUI extends JFrame {
    public static GameGUI window = new GameGUI("飞行棋[当前 " + " 步]");
    private final ChessBoard chessBoard = new ChessBoard();
    private final PlayerInfoPanel playerInfoPanel;

    public GameGUI(String title) {
        this.setTitle(title);
        String path = SystemSelect.getImagePath();
        JPanel chessBoardPanel = new BackgroundPanel((new ImageIcon(path + "开始图片.jpg").getImage()));

        JPanel rightSidePanel = new JPanel();
        this.setLayout(new GridLayout(1, 2, 50, 50));//大小有待后续调整
        rightSidePanel.setLayout(new GridLayout(2, 1, 20, 20));
        //玩家面板
        playerInfoPanel = new PlayerInfoPanel(chessBoard);

        //保存面板
        JPanel savePanel = new JPanel();
        savePanel.setLayout(new GridLayout(3, 1, 10, 10));//大小有待调整
        JButton resetButton = new JButton("重置");
        JButton saveButton = new JButton("保存");
        JButton returnButton = new JButton("返回");

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //重置游戏的后台
            }
        });

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //保存游戏的后台
            }
        });

        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                window.dispose();
                //打开startMenu

            }
        });
        savePanel.add(resetButton);
        savePanel.add(saveButton);
        savePanel.add(returnButton);
        savePanel.setPreferredSize(new Dimension(100, 100));//大小有待后续调整
        //窗口初始化
        rightSidePanel.add(playerInfoPanel);
        rightSidePanel.add(savePanel);
        this.add(chessBoardPanel);
        this.add(rightSidePanel);
        this.setVisible(true);
        this.setSize(800, 600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void popGameGUI() {
        window.setVisible(true);
    }

    public PlayerInfoPanel getPlayerInfoPanel() {
        return playerInfoPanel;
    }

    public void refresh() {

    }
}