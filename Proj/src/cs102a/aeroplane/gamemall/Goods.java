package cs102a.aeroplane.gamemall;

import cs102a.aeroplane.GameInfo;
import cs102a.aeroplane.frontend.GameMall;
import cs102a.aeroplane.model.ChessBoard;

import javax.swing.*;
import java.awt.*;

public abstract class Goods {

    private final float price;
    private int asPlayer;       // 记录本页的购买等信息/活动与此用户相关
    private int[] storeCnt = {0, 0, 0, 0};

    public JFrame itemDetail;
    JLabel possessionLabel = new JLabel("拥有件数" + storeCnt[asPlayer]);


    // 实例化一个介绍页面，默认invisible
    public Goods(float price, String itemName, String intro) {
        this.price = price;
        this.itemDetail.setTitle("道具：" + itemName);
        this.itemDetail.setSize(400, 300);
        this.itemDetail.setLocationRelativeTo(null);

        itemDetail = new JFrame(itemName + " 详情");
        itemDetail.setSize(600, 400);
        itemDetail.setLocationRelativeTo(null);

        JLabel nameLabel = new JLabel(itemName);
        JLabel priceLabel = new JLabel(price + "金币");

        JLabel introLabel = new JLabel(intro);

        JButton buyButton = new JButton(Wallet.getDiscountAsPercent(asPlayer) == 1.00f ?
                "花费" + this.price + "金币购买一件" : "优惠价" + this.price * Wallet.getDiscountAsPercent(asPlayer) + "金币");
buyButton.addActionListener(e->{
    try{
    this.purchase(asPlayer);}catch (Exception e){aa}
});
        JPanel rowPanel1 = new JPanel();
        rowPanel1.setPreferredSize(new Dimension(30, 60));
        rowPanel1.setLayout(new GridLayout(1, 4, 10, 10));
        rowPanel1.add(nameLabel);

        JPanel rowPanel2 = new JPanel();
        rowPanel2.setPreferredSize(new Dimension(30, 60));
        rowPanel2.setLayout(new GridLayout(1, 3, 10, 10));
        rowPanel2.add(priceLabel);
        rowPanel2.add(possessionLabel);

        JPanel rowPanel3 = new JPanel();
        rowPanel3.setPreferredSize(new Dimension(30, 60));
        rowPanel3.setLayout(new GridLayout(1, 1, 10, 10));
        rowPanel3.add(introLabel);

        JPanel rowPanel4 = new JPanel();
        rowPanel4.setPreferredSize(new Dimension(30, 60));
        rowPanel4.setLayout(new GridLayout(3, 1, 10, 10));
        rowPanel4.add(buyButton);

        JPanel base = new JPanel(new GridLayout(1, 4));
        base.setPreferredSize(new Dimension(150, 150));
        base.add(rowPanel1);
        base.add(rowPanel2);
        base.add(rowPanel3);
        base.add(rowPanel4);

        itemDetail.add(base);
        itemDetail.setDefaultCloseOperation(itemDetail.HIDE_ON_CLOSE);
    }


    public void purchase(int purchaser) throws Exception {
        if (price * Wallet.getDiscountAsPercent(purchaser) <= Wallet.getBalance(purchaser)) {
            Wallet.spendBalance(purchaser, price * Wallet.getDiscountAsPercent(purchaser));
            storeCnt[purchaser] += 1;
            possessionLabel.setText("拥有件数" + storeCnt[asPlayer]);       // 刷新内容
            GameMall.gameMall.refreshInfo();
        } else throw new Exception("啊哦，余额不足啦");
    }


    public int[] getStoreCnt() {
        return storeCnt;
    }


    protected void setStoreCnt(int storeCnt, int nowPlayer) {
        if (GameInfo.isSuperUser()) this.storeCnt[nowPlayer] = storeCnt;
    }

    public void setAsPlayer(int asPlayer) {
        this.asPlayer = asPlayer;
    }

    public abstract void use(ChessBoard chessBoard);
}
