package com.myblog.layout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NullLayoutTest1 extends JFrame {
    public NullLayoutTest1(){
        //在 “initUI”中我们将使用“空布局”，向窗口容器中添加组件！
        initUI();
    }
    
    public static void main(String args[]) {
        NullLayoutTest1 window = new NullLayoutTest1();
        window.setTitle("使用空布局的Swing程序");
        window.setSize(600, 400);
//        window.setResizable(Boolean.FALSE);
        window.setLocationRelativeTo(null); // 让窗体居中显示
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(Boolean.TRUE);
        // 该代码依据放置的组件设定窗口的大小使之正好能容纳你放置的所有组件
        //window.pack();
    }
    
    private void initUI() {
        //javax.swing.JPanel 代表一张画布（标准称呼是容器）
        //其代表一个空间，用户放置其他可视性的控件 比如按钮，文本等
        JPanel contentPanel = new JPanel();
        //设置这个容器的布局方式是空布局
        contentPanel.setLayout(null);
        //初始化所需组件
        JLabel nameLabel = new JLabel("用户名 :");
        JTextField nameTxt = new JTextField();
        JLabel pwLabel = new JLabel("密码 :");
        JTextField pwTxt = new JTextField();
        JButton forgetPwBtn = new JButton("忘记密码");
        JButton loginBtn = new JButton("登陆");
        //将组件全部添加到容器中
        contentPanel.add(nameLabel);
        contentPanel.add(nameTxt);
        contentPanel.add(pwLabel);
        contentPanel.add(pwTxt);
        contentPanel.add(forgetPwBtn);
        contentPanel.add(loginBtn);
        //调用 setBounds(x,y,width,height) 设置组件的特定位置
        //对应四个参数分别为 : 横坐标，纵坐标，组件宽度，组件高度
        nameLabel.setBounds(140, 100, 60, 35);
        nameTxt.setBounds(210, 100, 250, 35);
        pwLabel.setBounds(140, 160, 60, 35);
        pwTxt.setBounds(210, 160, 250, 35);
        forgetPwBtn.setBounds(140, 220, 100, 35);
        loginBtn.setBounds(350, 220, 100, 35);
        //最后将这张画布添加到窗口中显示
        getContentPane().add(contentPanel);
      }
}


