package com.test_swing;

import com.test_swing.main.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main {
    boolean packFrame = false;
    MainFrame frame;
    public Main() {
        frame = new MainFrame();
        // Validate frames that have preset sizes
        // Pack frames that have useful preferred size info, e.g. from their layout
//        if (packFrame) {
//            frame.pack();
//        } else {
//            frame.validate();
//        }
        frame.pack();
        frame.setLocationRelativeTo(null);//在屏幕中居
        frame.setVisible(true);
        initSystemTray();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            UIManager.setLookAndFeel(UIManager.
                                    getSystemLookAndFeelClassName());
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                        new Main();
                    }
                }
        );

    }

    private void initSystemTray() {
        /*
         * 添加系统托盘
         */
        if (SystemTray.isSupported()) {
            // 获取当前平台的系统托盘
            SystemTray tray = SystemTray.getSystemTray();

            // 加载一个图片用于托盘图标的显示
            Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/ic_diy.png"));

            // 创建点击图标时的弹出菜单
            PopupMenu popupMenu = new PopupMenu();

            MenuItem openItem = new MenuItem("open");
            MenuItem exitItem = new MenuItem("exit");

            openItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // 点击打开菜单时显示窗口
                    if (!frame.isShowing()) {
                        frame.setVisible(true);
                    }
                }
            });
            exitItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // 点击退出菜单时退出程序
                    System.exit(0);
                }
            });

            popupMenu.add(openItem);
            popupMenu.add(exitItem);

            // 创建一个托盘图标
            TrayIcon trayIcon = new TrayIcon(image, "这是一个托盘图标", popupMenu);

            // 托盘图标自适应尺寸
            trayIcon.setImageAutoSize(true);

            trayIcon.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("托盘图标被右键点击");
                    // 点击打开菜单时显示窗口


                }
            });
            trayIcon.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    switch (e.getButton()) {
                        case MouseEvent.BUTTON1: {
                            System.out.println("托盘图标被鼠标左键被点击");
                            frame.setVisible(!frame.isShowing());
                            break;
                        }
                        case MouseEvent.BUTTON2: {
                            System.out.println("托盘图标被鼠标中键被点击");
                            break;
                        }
                        case MouseEvent.BUTTON3: {
                            System.out.println("托盘图标被鼠标右键被点击");
                            break;
                        }
                        default: {
                            break;
                        }
                    }
                }
            });

            // 添加托盘图标到系统托盘
            try {
                tray.add(trayIcon);
            } catch (AWTException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("当前系统不支持系统托盘");
        }

    }


}
