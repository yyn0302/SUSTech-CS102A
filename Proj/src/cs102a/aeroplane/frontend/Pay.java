package cs102a.aeroplane.frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//大佬请把这个填完
public class Pay extends JFrame {
    public static Pay pay = new Pay("付款");

    public Pay(String title) {
        this.setTitle(title);

        ImageIcon pay = new ImageIcon("src\\付款.png");
        JLabel paJLabel = new JLabel(pay);
        JLabel paylabel = new JLabel("扫码付款");
        JButton bomb = new JButton("炸弹");
        JButton boyin = new JButton("波音");
        JButton VIP = new JButton("VIP");
        bomb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //加一个炸弹
            }
        });
        boyin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //加一个波音
            }
        });
        VIP.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //加一个VIP
            }
        });
        this.setSize(1800, 1000);
        GridBagLayout gridBagLayout = new GridBagLayout();
        this.setLayout(gridBagLayout);
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 4;
        gridBagLayout.setConstraints(paJLabel, gridBagConstraints);

        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagLayout.setConstraints(paylabel, gridBagConstraints);


        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 1;
        gridBagLayout.setConstraints(bomb, gridBagConstraints);

        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 1;
        gridBagLayout.setConstraints(boyin, gridBagConstraints);


        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 1;
        gridBagLayout.setConstraints(VIP, gridBagConstraints);


        this.add(paJLabel);
        this.add(paylabel);
        this.add(bomb);
        this.add(boyin);
        this.add(VIP);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        pay.setVisible(true);
    }

    public static void popPay() {
        pay.setVisible(true);
    }
}
