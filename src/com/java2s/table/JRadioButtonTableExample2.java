package com.java2s.table;

//Example from http://www.crionics.com/products/opensource/faq/swing_ex/SwingExamples.html
/* (swing1.1.1) */

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 * @version 1.2 08/13/99
 */
public class JRadioButtonTableExample2 extends JFrame {

	public JRadioButtonTableExample2() {
		super("JRadioButtonTable Example");

		DefaultTableModel dm = new DefaultTableModel();
		dm.setDataVector(new Object[][] { { "1", new Integer(-1) },
				{ "2", new Integer(-1) }, { "3", new Integer(0) },
				{ "4", new Integer(1) }, { "5", new Integer(2) } },
				new Object[] { "Question", "Answer" });

		JTable table = new JTable(dm);
		String[] answer = { "A", "B", "C" };

		table.getColumn("Answer").setCellRenderer(
				new RadioButtonRenderer(answer));
		table.getColumn("Answer").setCellEditor(
				new RadioButtonEditor(new JCheckBox(), new RadioButtonPanel(
						answer)));
		JScrollPane scroll = new JScrollPane(table);
		getContentPane().add(scroll);
	}

	// Cell base
	class RadioButtonPanel extends JPanel {
		JRadioButton[] buttons;

		RadioButtonPanel(String[] str) {
			setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
			buttons = new JRadioButton[str.length];
			for (int i = 0; i < buttons.length; i++) {
				buttons[i] = new JRadioButton(str[i]);
				buttons[i].setFocusPainted(false);
				add(buttons[i]);
			}
		}

		public void setSelectedIndex(int index) {
			for (int i = 0; i < buttons.length; i++) {
				buttons[i].setSelected(i == index);
			}
		}

		public int getSelectedIndex() {
			for (int i = 0; i < buttons.length; i++) {
				if (buttons[i].isSelected()) {
					return i;
				}
			}
			return -1;
		}

		public JRadioButton[] getButtons() {
			return buttons;
		}
	}

	class RadioButtonRenderer extends RadioButtonPanel implements
			TableCellRenderer {
		RadioButtonRenderer(String[] strs) {
			super(strs);
		}

		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			if (value instanceof Integer) {
				setSelectedIndex(((Integer) value).intValue());
			}
			return this;
		}
	}

	class RadioButtonEditor extends DefaultCellEditor implements ItemListener {
		RadioButtonPanel panel;

		public RadioButtonEditor(JCheckBox checkBox, RadioButtonPanel panel) {
			super(checkBox);
			this.panel = panel;
			ButtonGroup buttonGroup = new ButtonGroup();
			JRadioButton[] buttons = panel.getButtons();
			for (int i = 0; i < buttons.length; i++) {
				buttonGroup.add(buttons[i]);
				buttons[i].addItemListener(this);
			}
		}

		public Component getTableCellEditorComponent(JTable table,
				Object value, boolean isSelected, int row, int column) {
			if (value instanceof Integer) {
				panel.setSelectedIndex(((Integer) value).intValue());
			}
			return panel;
		}

		public Object getCellEditorValue() {
			return new Integer(panel.getSelectedIndex());
		}

		public void itemStateChanged(ItemEvent e) {
			super.fireEditingStopped();
		}
	}

	public static void main(String[] args) {
		JRadioButtonTableExample2 frame = new JRadioButtonTableExample2();
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		frame.setSize(230, 140);
		frame.setVisible(true);
	}
}