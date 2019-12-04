package com.test_swing.main;

import com.test_swing.table.JTablePanel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
//    addWindowListener(new WindowAdapter() {
//
//
//        public void windowClosing(WindowEvent e) {
//            super.windowClosing(e);
//        }
//
//    });

    public MainFrame() {
        try {
            setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            init();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void init() {

        JPanel panel = new JPanel(new BorderLayout());

//        JPanel panelT = new JPanel();
        JPanel panelL = new JPanel();
        JPanel panelR = new JPanel();
        JButton button0 = new JButton("test");

        panelL.setPreferredSize(new Dimension(100, getHeight()));//关键代码,设置JPanel的大小
        panelL.setBorder(BorderFactory.createTitledBorder("左菜单"));  // 标题边框

//        JButton button1 = new JButton("test");
        JButton button2 = new JButton("test");
        panelL.add(button2);
        // 把 5 个按钮添加到容器中的 5 个方位
        panel.add(panelL, BorderLayout.WEST);
        panel.add(panelR, BorderLayout.CENTER);

        // 表头（列名）
        Object[] columnNames = {"姓名", "语文", "数学", "英语", "总分"};

        // 表格所有行数据
        Object[][] rowData = {
                {"张三", 80, 80, 80, 240},
                {"John", 70, 80, 90, 240},
                {"Sue", 70, 70, 70, 210},
                {"Jane", 80, 70, 60, 210},
                {"Joe", 80, 70, 60, 210}
        };

        // 创建一个表格，指定 所有行数据 和 表头
//        JTable table = new JTable(rowData, columnNames);
//        JScrollPane jScrollPane = new JScrollPane();
//        jScrollPane.setViewportView(table);
        panelR.setLayout( new BorderLayout());
        // 把 表头 添加到容器顶部（使用普通的中间容器添加表格时，表头 和 内容 需要分开添加）
        panelR.add(new JTablePanel());



        try {
//            System.out.println(getClass().getResource("/img/ic_diy.png"));
//            System.out.println(getClass().getResource("/"));
            setIconImage(new ImageIcon(getClass().getResource("/img/ic_diy.png")).getImage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setSize(800, 600);
        setPreferredSize(new Dimension(800, 600));
        setContentPane(panel);

        setJMenuBar(getMenuBarView());
    }




    private JMenuBar getMenuBarView() {
        /*
         * 创建一个菜单栏
         */
        JMenuBar menuBar = new JMenuBar();

        /*
         * 创建一级菜单
         */
        JMenu fileMenu = new JMenu("文件");
        JMenu editMenu = new JMenu("编辑");
        JMenu viewMenu = new JMenu("视图");
        JMenu aboutMenu = new JMenu("关于");
        // 一级菜单添加到菜单栏
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);
        menuBar.add(aboutMenu);

        /*
         * 创建 "文件" 一级菜单的子菜单
         */
        JMenuItem newMenuItem = new JMenuItem("新建");
        JMenuItem openMenuItem = new JMenuItem("打开");
        JMenuItem exitMenuItem = new JMenuItem("退出");
        // 子菜单添加到一级菜单
        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.addSeparator();       // 添加一条分割线
        fileMenu.add(exitMenuItem);

        /*
         * 创建 "编辑" 一级菜单的子菜单
         */
        JMenuItem copyMenuItem = new JMenuItem("复制");
        JMenuItem pasteMenuItem = new JMenuItem("粘贴");
        // 子菜单添加到一级菜单
        editMenu.add(copyMenuItem);
        editMenu.add(pasteMenuItem);

        /*
         * 创建 "视图" 一级菜单的子菜单
         */
        final JCheckBoxMenuItem checkBoxMenuItem = new JCheckBoxMenuItem("复选框子菜单");
        final JRadioButtonMenuItem radioButtonMenuItem01 = new JRadioButtonMenuItem("单选按钮子菜单01");
        final JRadioButtonMenuItem radioButtonMenuItem02 = new JRadioButtonMenuItem("单选按钮子菜单02");
        // 子菜单添加到一级菜单
        viewMenu.add(checkBoxMenuItem);
        viewMenu.addSeparator();                // 添加一个分割线
        viewMenu.add(radioButtonMenuItem01);
        viewMenu.add(radioButtonMenuItem02);

        // 其中两个 单选按钮子菜单，要实现单选按钮的效果，需要将它们放到一个按钮组中
        ButtonGroup btnGroup = new ButtonGroup();
        btnGroup.add(radioButtonMenuItem01);
        btnGroup.add(radioButtonMenuItem02);

        // 默认第一个单选按钮子菜单选中
        radioButtonMenuItem01.setSelected(true);

        /*
         * 菜单项的点击/状态改变事件监听，事件监听可以直接设置在具体的子菜单上（这里只设置其中几个）
         */
        // 设置 "新建" 子菜单被点击的监听器
        newMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("新建  被点击");
            }
        });
        // 设置 "打开" 子菜单被点击的监听器
        openMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("打开  被点击");
            }
        });
        // 设置 "退出" 子菜单被点击的监听器
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("退出  被点击");
            }
        });

        // 设置 复选框子菜单 状态改变 监听器
        checkBoxMenuItem.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                System.out.println("复选框是否被选中: " + checkBoxMenuItem.isSelected());
            }
        });

        // 设置 单选按钮子菜单 状态改变 监听器
        radioButtonMenuItem01.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                System.out.println("单选按钮01 是否被选中: " + radioButtonMenuItem01.isSelected());
            }
        });

        return menuBar;
    }

}
