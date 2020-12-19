package cs102a.aeroplane.frontend;

import cs102a.aeroplane.frontend.model.BackgroundPanel;
import cs102a.aeroplane.frontend.model.TimeDialog;
import cs102a.aeroplane.savegame.GameLoader;
import cs102a.aeroplane.util.SystemSelect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class LoadHistory {

    public static JFrame window = new JFrame();
    private ArrayList<String> nameList = new ArrayList<>();
    private String historySelected;

    public LoadHistory(String title) {
        window.setTitle(title);

        JPanel panel = new BackgroundPanel(new ImageIcon(SystemSelect.getImagePath() + "loadFile.jpg").getImage());
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 1, 20, 20));
        mainPanel.setPreferredSize(new Dimension(400, 240));

        JLabel chooseLabel = new JLabel("选择一个存档", JLabel.CENTER);
        chooseLabel.setFont(new java.awt.Font("微软雅黑", Font.BOLD, 40));

        getFiles();
        JComboBox<String> chooseList = new JComboBox<>();
        for (String name : nameList) chooseList.addItem(name);

        historySelected = Objects.requireNonNull(chooseList.getSelectedItem()).toString();
        chooseList.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                try {
                    historySelected = Objects.requireNonNull(chooseList.getSelectedItem()).toString();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        JButton confirmButton = new JButton("确定");
        confirmButton.setOpaque(false);
        confirmButton.setBorder(null);
        confirmButton.setForeground(Color.yellow);
        confirmButton.setFont(new java.awt.Font("微软雅黑", Font.BOLD, 20));
        confirmButton.addActionListener(e -> {
            if (historySelected != null && !historySelected.equals("没有游戏档案哦")) {
                GameLoader.setFileName(historySelected);
                window.setVisible(false);
                GameGUI.window.setVisible(true);
            } else {
                TimeDialog td = new TimeDialog();
                td.showDialog(window, "不能读档哦", 2);
                window.setVisible(false);
                Start.popStart();
            }
            confirmButton.setOpaque(false);
            confirmButton.setBorder(null);
            confirmButton.setForeground(Color.yellow);
            confirmButton.setFont(new java.awt.Font("微软雅黑", Font.BOLD, 20));
        });

        mainPanel.add(chooseLabel);
        mainPanel.add(chooseList);
        mainPanel.add(confirmButton);
        mainPanel.setOpaque(false);
        panel.add(mainPanel);
        window.add(panel);
        window.setSize(600, 300);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }

    public void getFiles() {
        File dir = new File(SystemSelect.getHistoryPath());
        String[] list = dir.list();

        try {
            nameList.addAll(Arrays.asList(list));
            if (list.length == 0) nameList.add("没有游戏档案哦");
        } catch (NullPointerException np) {
            nameList.add("没有游戏档案哦");
        }
    }
}

