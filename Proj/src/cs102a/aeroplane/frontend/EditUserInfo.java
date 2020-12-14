package cs102a.aeroplane.frontend;

import cs102a.aeroplane.GameInfo;
import cs102a.aeroplane.frontend.model.DecimalLimitedDocument;
import cs102a.aeroplane.frontend.model.InputLimiter;
import cs102a.aeroplane.frontend.model.TimeDialog;
import cs102a.aeroplane.gamemall.Wallet;

import javax.swing.*;
import java.awt.*;

public class EditUserInfo extends JFrame {
    public static EditUserInfo window = new EditUserInfo();

    public EditUserInfo() {
        this.setTitle("修改账户信息");
        this.setSize(300, 200);
        this.setLocationRelativeTo(null);

        JLabel nameLabel = new JLabel("玩家名：");
        JTextField nameTextField = new JTextField(GameInfo.getPlayerName()[GameMall.getAsPlayer()]);
        JPanel jp1 = new JPanel(new GridLayout(1, 2));
        jp1.add(nameLabel);
        jp1.add(nameTextField);

        JLabel balanceLabel = new JLabel("余额(金币)：");
        JTextField balanceTextField = new JTextField(Wallet.getBalance(GameMall.getAsPlayer()) + "");
        balanceTextField.setDocument(new DecimalLimitedDocument());
        JPanel jp2 = new JPanel(new GridLayout(1, 2));
        jp2.add(balanceLabel);
        jp2.add(balanceTextField);

        JLabel discountLabel = new JLabel("优惠方案(%)：");
        JTextField discountTextField = new JTextField(Wallet.getDiscountAsPercent(GameMall.getAsPlayer()) * 100 + "");
        discountTextField.addKeyListener(new InputLimiter());
        JPanel jp3 = new JPanel(new GridLayout(1, 2));
        jp3.add(discountLabel);
        jp3.add(discountTextField);

        JButton confirmButton = new JButton("保存");
        confirmButton.addActionListener(e -> {

            try {
                Wallet.editBalance(GameMall.getAsPlayer(), balanceTextField.getText());
            } catch (AssertionError ex) {
                TimeDialog timeDialog = new TimeDialog();
                timeDialog.showDialog(window, ex.getMessage(), 3);
            }

            try {
                Wallet.setDiscountAsPercent(GameMall.getAsPlayer(), discountTextField.getText());
            } catch (AssertionError ex) {
                TimeDialog timeDialog = new TimeDialog();
                timeDialog.showDialog(window, ex.getMessage(), 3);
            }

            GameInfo.getPlayerName()[GameMall.getAsPlayer()] = nameTextField.getText();
        });

        JPanel base = new JPanel(new GridLayout(1, 4));
        base.add(jp1);
        base.add(jp2);
        base.add(jp3);
        base.add(confirmButton);

        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.add(base);
    }
}
