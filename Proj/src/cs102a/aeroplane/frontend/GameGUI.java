package cs102a.aeroplane.frontend;

import cs102a.aeroplane.GameInfo;
import cs102a.aeroplane.frontend.model.BackgroundPanel;
import cs102a.aeroplane.frontend.model.PlayerInfoPanel;
import cs102a.aeroplane.frontend.model.TimeDialog;
import cs102a.aeroplane.model.Aeroplane;
import cs102a.aeroplane.model.ChessBoard;
import cs102a.aeroplane.savegame.GameSaver;
import cs102a.aeroplane.util.SystemSelect;

import javax.swing.*;
import java.awt.*;

public class GameGUI extends JFrame {
    public static GameGUI window = new GameGUI();
    public static ImageIcon background;
    // TODO: 2020/12/18 当棋子出现偏移时修改xy方向偏置
    public ChessBoard chessBoard = new ChessBoard(null, 0, 0);
    private PlayerInfoPanel playerInfoPanel;

    public GameGUI() {
        this.add(new ChessBoard(GameGUI.window,0,0));

        this.setSize(900, 800);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);       // 以启用绝对布局

        this.setTitle("飞行棋");
        String path = SystemSelect.getImagePath();

        // 背景图片
        background = new ImageIcon(path + (GameInfo.getTheme() == 1 ? "海王主题.jpg" : "灵笼主题.jpg"));
        JPanel backgroundPanel = new BackgroundPanel(background.getImage());
        backgroundPanel.setBounds(0, 0, 900, 800);
        backgroundPanel.setOpaque(false);
        backgroundPanel.setVisible(true);
        this.add(backgroundPanel);

        JPanel rightSidePanel = new JPanel();
        this.setLayout(new GridLayout(1, 2, 50, 50));//大小有待后续调整
        rightSidePanel.setLayout(new GridLayout(2, 1, 20, 20));

        //玩家面板
        playerInfoPanel = new PlayerInfoPanel(chessBoard);

        //保存面板
        JPanel savePanel = new JPanel();
        savePanel.setLayout(new GridLayout(3, 1, 10, 10));//大小有待调整
        JButton resetButton = new JButton(new ImageIcon(path+"reset.jpg"));
        JButton saveButton = new JButton(new ImageIcon(path+"save.png"));
        JButton returnButton = new JButton(new ImageIcon(path+"back.png"));

        // FIXME: 2020/12/18 不知道对不对
        resetButton.addActionListener(e -> {
            chessBoard=null;
            System.gc();
            chessBoard=new ChessBoard(window,0,0);
        });

        saveButton.addActionListener(e -> {
            GameSaver.save(chessBoard);
            new TimeDialog().showDialog(window,"成功保存",3);
        });

        returnButton.addActionListener(e -> {
            window.setVisible(false);
            //打开startMenu
            Start.popStart();
        });

        savePanel.add(resetButton);
        savePanel.add(saveButton);
        savePanel.add(returnButton);
        savePanel.setPreferredSize(new Dimension(100, 100));//大小有待后续调整

        //窗口初始化
//        rightSidePanel.add(playerInfoPanel);
//        rightSidePanel.add(savePanel);

        chessBoard.setBounds(0,0,800,800);
        this.add(chessBoard);

//        rightSidePanel.setBounds(900,0,200,800);
//        this.add(rightSidePanel);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);

        chessBoard.startGame();
        for(Aeroplane aeroplane:chessBoard.getPlanes()){
            this.add(aeroplane.getPlaneView());
        }
    }

    public PlayerInfoPanel getPlayerInfoPanel() {
        return playerInfoPanel;
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

    public static void main(String[] args) {
        window.setVisible(true);
    }
}