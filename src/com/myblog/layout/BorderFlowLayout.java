package com.myblog.layout;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/*
 *frame是窗体，使用默认的borderLayout布局
 *
 *panel_up 以及panel_bottom将窗体分割成上下两个部分 分别占领CENTER区域以及 SOUTH区域
 **panel_up 使用GridLayout布局 2*2 右边再嵌入两个小面板 small_panel_1 以及 small_panel_2
 **small_panel_1以及 small_panel_2使用flowLayout布局使得输入框不会被撑开
 *
 *panel_buttom 使用FlowLayout 布局直接收纳三个button控件
 */
public class BorderFlowLayout {
    public static void main(String args[]) {
        JFrame frame = new JFrame();
        frame.setSize(300, 150);
        JPanel panel_up = new JPanel();
        JPanel panel_bottom = new JPanel();

        frame.setLayout(new BorderLayout());
        frame.add(panel_up, BorderLayout.CENTER);
        frame.add(panel_bottom, BorderLayout.SOUTH);

        /*
         * 直接用GridLayout会以所有控件中最宽的空间宽为宽， 最高的控件高为高，输入框会被撑开
         */

        panel_up.setLayout(new GridLayout(2, 2));
        JLabel lbl_username = new JLabel("User Name:");
        JLabel lbl_passsword = new JLabel("Password:");

        /*
         * 小面板1 2 别分用来存放 用户名的输入框以及密码的输入框 小面板1 2 内部的布局使用FlowLayout布局
         */
        JPanel panel_small_1 = new JPanel();
        JPanel panel_small_2 = new JPanel();

        panel_up.add(lbl_username);
        panel_up.add(panel_small_1);
        panel_up.add(lbl_passsword);
        panel_up.add(panel_small_2);

        JTextField txtfd_username = new JTextField(10);
        JPasswordField pswfd_password = new JPasswordField(10);

        panel_small_1.setLayout(new FlowLayout());
        panel_small_1.add(txtfd_username);
        panel_small_2.setLayout(new FlowLayout());
        panel_small_2.add(pswfd_password);

        panel_bottom.setLayout(new FlowLayout());
        JButton btn_login = new JButton("Login");
        JButton btn_register = new JButton("register");
        JButton btn_cancle = new JButton("cancle");
        // Does cancle mean "clear the textfile" or "close the frame"?
        panel_bottom.add(btn_login);
        panel_bottom.add(btn_register);
        panel_bottom.add(btn_cancle);

        frame.setVisible(true);
    }
}