package cs102a.aeroplane.frontend;

import cs102a.aeroplane.GameInfo;
import cs102a.aeroplane.frontend.model.BackgroundPanel;
import cs102a.aeroplane.frontend.model.PlayerInfoPanel;
import cs102a.aeroplane.frontend.model.TimeDialog;
import cs102a.aeroplane.model.ChessBoard;
import cs102a.aeroplane.savegame.GameSaver;
import cs102a.aeroplane.util.SystemSelect;

import javax.swing.*;

public class GameGUI extends JFrame {
    public static GameGUI window = new GameGUI();
    public static ImageIcon background;
    // TODO: 2020/12/18 当棋子出现偏移时修改xy方向偏置
    public ChessBoard chessBoard = new ChessBoard(this, 0, 0);
    private PlayerInfoPanel playerInfoPanel;

    public GameGUI() {
        this.add(new ChessBoard(GameGUI.window, 0, 0));

        this.setSize(900, 800);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);       // 以启用绝对布局

        this.setTitle("飞行棋");
        String path = SystemSelect.getImagePath();

        // 背景图片
        background = new ImageIcon(path + (GameInfo.getTheme() == 1 ? "海王主题.png" : "灵笼主题.jpg"));
        JPanel backgroundPanel = new BackgroundPanel(background.getImage());
        backgroundPanel.setLayout(null);
        backgroundPanel.setBounds(0, 0, 900, 800);
//        backgroundPanel.setOpaque(false);


        ImageIcon boardImg = new ImageIcon(path + "blankBoard.png");
        JPanel boardImgPanel = new BackgroundPanel(boardImg.getImage());
        boardImgPanel.setOpaque(false);
        boardImgPanel.setLayout(null);
        boardImgPanel.setBounds(0, 0, 800, 800);

        chessBoard.setBounds(0, 0, 800, 800);
        chessBoard.setOpaque(false);
        boardImgPanel.add(chessBoard);

        backgroundPanel.add(boardImgPanel);

        //玩家面板
        playerInfoPanel = new PlayerInfoPanel(chessBoard);
        playerInfoPanel.setBounds(820, 30, 60, 130);
        playerInfoPanel.setOpaque(false);
        backgroundPanel.add(playerInfoPanel);

        //util面板
        JButton resetButton = new JButton("重置");
        JButton saveButton = new JButton("保存");
        JButton returnButton = new JButton("返回");
        resetButton.setBounds(818, 560, 60, 60);
        saveButton.setBounds(818, 630, 60, 60);
        returnButton.setBounds(818, 700, 60, 60);
        resetButton.setOpaque(false);
        saveButton.setOpaque(false);
        returnButton.setOpaque(false);

        // FIXME: 2020/12/18 不知道对不对
        resetButton.addActionListener(e -> {
            chessBoard = null;
            System.gc();
            chessBoard = new ChessBoard(window, 0, 0);
        });
        saveButton.addActionListener(e -> {
            GameSaver.save(chessBoard);
            new TimeDialog().showDialog(window, "成功保存", 3);
        });
        returnButton.addActionListener(e -> {
            window.setVisible(false);
            //打开startMenu
            Start.popStart();
        });

        backgroundPanel.add(resetButton);
        backgroundPanel.add(saveButton);
        backgroundPanel.add(returnButton);
        backgroundPanel.setOpaque(false);


        this.add(backgroundPanel);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);

        chessBoard.startGame();
    }


    public void refresh() {
//         FIXME: 2020/12/18 增加效果按键，刷新剩余个数
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public void setChessBoard(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

   /* public static void main(String[] args) {
        window.setVisible(true);
    }*/
}