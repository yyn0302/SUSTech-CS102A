package cs102a.aeroplane.frontend;

import cs102a.aeroplane.frontend.model.BackgroundPanel;
import cs102a.aeroplane.frontend.model.TimeDialog;
import cs102a.aeroplane.savegame.GameLoader;
import cs102a.aeroplane.util.SystemSelect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.io.File;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class LoadHistory extends JFrame {

    private ArrayList<String> nameList = new ArrayList<>();
    private String historySelected;

    public LoadHistory(String title) {
        this.setTitle(title);

        JPanel panel = new BackgroundPanel(new ImageIcon(SystemSelect.getImagePath() + "读档图片.jpg").getImage());
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 1, 20, 150));
        mainPanel.setPreferredSize(new Dimension(600, 600));

        JLabel chooseLabel = new JLabel("选择一个存档");
        chooseLabel.setFont(new java.awt.Font("微软雅黑", Font.BOLD, 30));

        getFiles();
        JComboBox<String> chooseList = new JComboBox<>();
        for (String name : nameList) chooseList.addItem(name);

        chooseList.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                try {
                    String[] selection = Objects.requireNonNull(chooseList.getSelectedItem()).toString().split("@");
                    historySelected = selection[0];
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        JButton confirmButton = new JButton("确定");
        confirmButton.addActionListener(e -> {
            if (!historySelected.equals("没有游戏档案哦"))
                GameLoader.setFileName(historySelected);
            else {
                TimeDialog td = new TimeDialog();
                td.showDialog(this, "不能读档哦", 3);
            }
            dispose();
        });

        mainPanel.add(chooseLabel);
        mainPanel.add(chooseList);
        mainPanel.add(confirmButton);
        mainPanel.setOpaque(false);
        panel.add(mainPanel);
        this.add(panel);
        this.setSize(800, 600);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    public void getFiles() {
        File file = new File(SystemSelect.getHistoryPath());
        File[] list = file.listFiles(File::isDirectory);

        Format simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            for (File value : list) {
                String[] name = value.getName().split(".a");    // 把后缀名切开
                Date d = new Date(value.lastModified());
                String dateString = simpleFormat.format(d);
                nameList.add(name[0] + "@保存时间:" + dateString);
            }
        } catch (NullPointerException np) {
            nameList.add("没有游戏档案哦");
        }
    }
}

