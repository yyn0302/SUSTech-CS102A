package cs102a.aeroplane.frontend;

import cs102a.aeroplane.GameInfo;
import cs102a.aeroplane.gamemall.GoodsList;
import cs102a.aeroplane.gamemall.Wallet;
import cs102a.aeroplane.util.SystemSelect;

import javax.swing.*;
import java.awt.*;

public class GameMall extends JFrame {

    public static GameMall window = new GameMall("道具商店");


    private static int asPlayer = 0;
    public JPanel userInfoPanel;
    JLabel userBalanceLabel = new JLabel();
    JLabel userDiscountLabel = new JLabel();
    JButton editWallet = new JButton("修改账户信息");
    JRadioButton player1 = new JRadioButton("玩家1：" + GameInfo.getPlayerName()[0], true);
    JRadioButton player2 = new JRadioButton("玩家2：" + GameInfo.getPlayerName()[1]);
    JRadioButton player3 = new JRadioButton("玩家3：" + GameInfo.getPlayerName()[2]);
    JRadioButton player4 = new JRadioButton("玩家4：" + GameInfo.getPlayerName()[3]);

    public GameMall(String title) {

        this.setTitle(title);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);

        JLabel playerLabel = new JLabel("当前身份：");
        player1.addActionListener(e -> {
            if (player1.isSelected()) {
                asPlayer = 0;
                refreshInfo();
            }
        });
        player2.addActionListener(e -> {
            if (player2.isSelected()) {
                asPlayer = 1;
                refreshInfo();
            }
        });
        player3.addActionListener(e -> {
            if (player3.isSelected()) {
                asPlayer = 2;
                refreshInfo();
            }
        });
        player4.addActionListener(e -> {
            if (player4.isSelected()) {
                asPlayer = 3;
                refreshInfo();
            }
        });

        ButtonGroup playerSelect = new ButtonGroup();
        playerSelect.add(player1);
        playerSelect.add(player2);
        playerSelect.add(player3);
        playerSelect.add(player4);

        JPanel userSelectPanel = new JPanel(new GridLayout(1, 6));
        userSelectPanel.add(playerLabel);
        userSelectPanel.add(new JLabel());
        userSelectPanel.add(player1);
        userSelectPanel.add(player2);
        userSelectPanel.add(player3);
        userSelectPanel.add(player4);


        this.userBalanceLabel.setText("账户余额：" + Wallet.getBalance(asPlayer) + "金币");
        this.userDiscountLabel.setText("优惠方案：" + Wallet.getDiscountAsPercent(asPlayer) * 100 + "折");
        this.editWallet.addActionListener(e -> EditUserInfo.window.setVisible(true));
        this.editWallet.setEnabled(GameInfo.isSuperUser());

        userInfoPanel = new JPanel(new GridLayout(1, 3));
        userInfoPanel.add(userBalanceLabel);
        userInfoPanel.add(userDiscountLabel);
        userInfoPanel.add(editWallet);


        String path = SystemSelect.getImagePath();
        ImageIcon bomb = new ImageIcon(path + "炸弹.jpg");
        ImageIcon boeing = new ImageIcon(path + "波音.jpg");
        ImageIcon VIP = new ImageIcon(path + "VIP.jpg");

        JButton bombButton = new JButton(bomb);
        JButton boeingButton = new JButton(boeing);
        JButton vipButton = new JButton(VIP);
        bombButton.addActionListener(e -> {
            GoodsList.bomb.setAsPlayer(asPlayer);
            GoodsList.bomb.itemDetail.setVisible(true);
        });
        boeingButton.addActionListener(e -> {
            GoodsList.takeOffAnyway.setAsPlayer(asPlayer);
            GoodsList.takeOffAnyway.itemDetail.setVisible(true);
        });
        vipButton.addActionListener(e -> {
            GoodsList.makeMeWin.setAsPlayer(asPlayer);
            GoodsList.makeMeWin.itemDetail.setVisible(true);
        });

        JPanel goodsPanel = new JPanel(new GridLayout(1, 3));
        goodsPanel.add(bombButton);
        goodsPanel.add(boeingButton);
        goodsPanel.add(vipButton);

        JPanel base = new JPanel();
        base.setLayout(new GridLayout(4, 2, 20, 30));
        base.setPreferredSize(new Dimension(600, 600));
        base.add(userSelectPanel);
        base.add(userInfoPanel);
        base.add(goodsPanel);


        this.add(base);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    public static int getAsPlayer() {
        return asPlayer;
    }

    public final void refreshInfo() {
        this.userBalanceLabel.setText("账户余额：" + Wallet.getBalance(asPlayer) + "金币");
        this.userDiscountLabel.setText("优惠方案：" + Wallet.getDiscountAsPercent(asPlayer) * 100 + "折");
        this.player1.setText("玩家1：" + GameInfo.getPlayerName()[0]);
        this.player2.setText("玩家2：" + GameInfo.getPlayerName()[1]);
        this.player3.setText("玩家3：" + GameInfo.getPlayerName()[2]);
        this.player4.setText("玩家4：" + GameInfo.getPlayerName()[3]);

        this.editWallet.setEnabled(GameInfo.isSuperUser());
    }
}
