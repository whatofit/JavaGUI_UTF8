package com.myblog.toplevelcontainers.frame;


import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class TestJFrame {

	/**
	 * @param args
	 */
    public static void main(String[] args){  
        JFrame frame = new JFrame();  
        JPanel panel = new JPanel();  
        JTextArea textArea = new JTextArea();  
          
        panel.setLayout(new GridLayout());  
        textArea.setText("test");  
        //当TextArea里的内容过长时生成滚动条  
        panel.add(new JScrollPane(textArea));  
        frame.add(panel);  
          
        frame.setSize(200,200);  
        frame.setVisible(true);  
    }  
}
