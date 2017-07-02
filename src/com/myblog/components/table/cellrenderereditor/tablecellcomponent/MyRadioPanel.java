package com.myblog.components.table.cellrenderereditor.tablecellcomponent;

import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * create the pane that some radio pane in it.
**/
public class MyRadioPanel extends JPanel {
	//它只有一个属性,根据给定数组长度构建Radio数组,
    /** radio button group. */
    private JRadioButton[] buttons = null;
    private int curSelectedIndex = -1;

    public MyRadioPanel() {
    }
    
    //再看它的构造函数:
    public MyRadioPanel(String[] strButtonText) {
    	System.out.println("strButtonText.length=" + strButtonText.length);
    	buttons = new JRadioButton[strButtonText.length];
    	for (int i = 0; i < strButtonText.length;i++) {
	    	buttons[i] = new JRadioButton(strButtonText[i]);	//构造JRadioButton：
	    	//buttons[i] = new JRadioButton("Plain", true);
	    	add(buttons[i]);	    							//加入到面板:
    	}
    }

    //再添加取得和设置JRadioButton选择的方法:
    /**
     * get button groups.
     */
    public JRadioButton[] getButtons() {
       return buttons;
    }

    /**
     * set which index select.
     */
    public void setSelectedIndex(int index) {
    	curSelectedIndex = index;
       for (int i = 0; i < buttons.length; i++) {
           buttons[i].setSelected(i == index);
       }
    }
    
	public int getSelectedIndex() {
		return curSelectedIndex;
	}
}