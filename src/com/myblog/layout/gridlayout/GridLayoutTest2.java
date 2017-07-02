package com.myblog.layout.gridlayout;

//这段代码主要是为读者展示如何使用GridBagLayout布局管理器布局方式来排列内容面板中的组件

import javax.swing.*;

import java.awt.*;

import java.awt.event.*;

class login extends JPanel// 此处类继承了JPanel类

{

    static final int WIDTH = 300;

    static final int HEIGHT = 150;

    JFrame loginframe;

    public void add(Component c, GridBagConstraints constraints, int x, int y,
            int w, int h)

    { // 此方法用来添加控件到容器中

        constraints.gridx = x;

        constraints.gridy = y;

        constraints.gridwidth = w;

        constraints.gridheight = h;

        add(c, constraints);

    }

    login()

    {

        loginframe = new JFrame("信息管理系统"); // 设置顶层容器

        loginframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 设置其顶层容器的关闭性

        GridBagLayout lay = new GridBagLayout();// 创建网格组布局方式对象

        setLayout(lay);

        loginframe.add(this, BorderLayout.WEST);

        // 这个相当于是在contentpane上添加panel组件

        loginframe.setSize(WIDTH, HEIGHT);

        Toolkit kit = Toolkit.getDefaultToolkit();// 设置顶层容器框架为居中

        Dimension screenSize = kit.getScreenSize();

        int width = screenSize.width;

        int height = screenSize.height;

        int x = (width - WIDTH) / 2;

        int y = (height - HEIGHT) / 2;

        loginframe.setLocation(x, y);

        JButton ok = new JButton("确认");

        JButton cancel = new JButton("放弃");

        JButton title = new JButton("布局管理器测试窗口");

        JButton name = new JButton("用户名 ");

        JButton password = new JButton("密     码");

        final JTextField nameinput = new JTextField(15);

        final JTextField passwordinput = new JTextField(15);

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.NONE;

        // constraints.anchor=GridBagConstraints.CENTER; /组件的对齐方式 选择

        constraints.weightx = 3;

        constraints.weighty = 4;

        // x,y方向的增量 比例

        add(title, constraints, 0, 0, 5, 1); // 使用网格组布局添加控件

        add(name, constraints, 0, 1, 1, 1);

        add(password, constraints, 0, 2, 1, 1);

        add(nameinput, constraints, 2, 1, 2, 1);

        add(passwordinput, constraints, 2, 2, 2, 1);

        add(ok, constraints, 0, 3, 1, 1);

        add(cancel, constraints, 2, 3, 1, 1);

        // loginframe.setResizable(false);

        loginframe.setVisible(true);

    }

}

public class GridLayoutTest2
{
    public static void main(String[] args)
    {
        login log = new login();
    }
}