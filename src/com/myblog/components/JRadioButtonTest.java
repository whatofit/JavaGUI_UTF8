package com.myblog.components;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JRadioButtonTest extends JPanel // implements ActionListener
{
	private ButtonGroup bg;
	private JRadioButton b1, b2, b3, b4;
	private JComboBox c1;
	private JTextField t1;
	private ActionHandler h1;

	public void init() {

		setLayout(new FlowLayout());
		h1 = new ActionHandler(this);

		String[] s = { "Serif", "SansSerif", "Monospaced", "Dialog" };
		c1 = new JComboBox(s);
		c1.addActionListener(h1);
		add(c1);

		Font font = new Font(c1.getItemAt(0).toString(), Font.PLAIN, 14);
		t1 = new JTextField("Test string", 30);
		t1.setEditable(false);
		t1.setFont(font);
		add(t1);

		b1 = new JRadioButton("Plain", true);
		b1.addActionListener(h1);
		add(b1);
		b2 = new JRadioButton("Bold", false);
		b2.addActionListener(h1);
		add(b2);
		b3 = new JRadioButton("Italic", false);
		b3.addActionListener(h1);
		add(b3);
		b4 = new JRadioButton("Bold Italic", false);
		b4.addActionListener(h1);
		add(b4);

		bg = new ButtonGroup();
		bg.add(b1);
		bg.add(b2);
		bg.add(b3);
		bg.add(b4);

	}

	public void updateFont() {
		int valBold, valItatic;
		int fontStyle = t1.getFont().getStyle();
		int fontSize = t1.getFont().getSize();
		String fontName = (String) c1.getSelectedItem();
		if (b1.isSelected())
			fontStyle = Font.PLAIN;
		else if (b2.isSelected())
			fontStyle = Font.BOLD;
		else if (b3.isSelected())
			fontStyle = Font.ITALIC;
		else if (b4.isSelected())
			fontStyle = Font.BOLD + Font.ITALIC;

		t1.setFont(new Font(fontName, fontStyle, fontSize));
		t1.repaint();
	}

	public static void main(String args[]) {
		JFrame fr = new JFrame("Test...");
		fr.setSize(400, 200);
		fr.setLocation(400, 400);
		JRadioButtonTest ob = new JRadioButtonTest();
		ob.init();
		fr.add(ob, BorderLayout.CENTER);
		fr.setVisible(true);
	}
}

class ActionHandler implements ActionListener {
	private JRadioButtonTest ttf;

	public ActionHandler(JRadioButtonTest t) {
		ttf = t;
	}

	public void actionPerformed(ActionEvent e) {

		ttf.updateFont();
	}
}