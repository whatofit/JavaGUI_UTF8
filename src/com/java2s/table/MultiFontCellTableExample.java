//package com.java2s.table;
//
////Example from http://www.crionics.com/products/opensource/faq/swing_ex/SwingExamples.html
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Component;
//import java.awt.Container;
//import java.awt.Dimension;
//import java.awt.FlowLayout;
//import java.awt.Font;
//import java.awt.GridLayout;
//import java.awt.Toolkit;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
//import java.util.Enumeration;
//import java.util.Vector;
//
//import javax.swing.Box;
//import javax.swing.BoxLayout;
//import javax.swing.JButton;
//import javax.swing.JColorChooser;
//import javax.swing.JComboBox;
//import javax.swing.JDialog;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JSeparator;
//import javax.swing.JTable;
//import javax.swing.ListSelectionModel;
//import javax.swing.SwingConstants;
//import javax.swing.UIManager;
//import javax.swing.border.Border;
//import javax.swing.border.EmptyBorder;
//import javax.swing.event.TableModelEvent;
//import javax.swing.table.DefaultTableModel;
//import javax.swing.table.TableCellRenderer;
//import javax.swing.table.TableModel;
//
///**
// * @version 1.0 11/22/98
// */
//public class MultiFontCellTableExample extends JFrame {
//
//	public MultiFontCellTableExample() {
//		super("Multi-Font Cell Example");
//
//		AttributiveCellTableModel ml = new AttributiveCellTableModel(8, 3);
//		CellFont cellAtt = (CellFont) ml.getCellAttribute();
//		JTable table = new JTable(ml);
//		table.setRowHeight(26);
//		table.setCellSelectionEnabled(true);
//		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
//		table.setDefaultRenderer(Object.class, new AttributiveCellRenderer());
//		JScrollPane scroll = new JScrollPane(table);
//
//		FontPanel fontPanel = new FontPanel(table, cellAtt);
//
//		Box box = new Box(BoxLayout.X_AXIS);
//		box.add(scroll);
//		box.add(new JSeparator(SwingConstants.HORIZONTAL));
//		box.add(fontPanel);
//		getContentPane().add(box);
//		setSize(400, 200);
//		setVisible(true);
//	}
//
//	class FontPanel extends JPanel {
//		String[] str_size = { "10", "12", "14", "16", "20", "24" };
//
//		String[] str_style = { "PLAIN", "BOLD", "ITALIC" };
//
//		JComboBox name, style, size;
//
//		FontPanel(final JTable table, final CellFont cellAtt) {
//			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//			Box box = new Box(BoxLayout.X_AXIS);
//			JPanel p2 = new JPanel(new GridLayout(3, 1));
//			JPanel p3 = new JPanel(new GridLayout(3, 1));
//			JPanel p4 = new JPanel(new FlowLayout());
//			p2.add(new JLabel("Name:"));
//			p2.add(new JLabel("Style:"));
//			p2.add(new JLabel("Size:"));
//			Toolkit toolkit = Toolkit.getDefaultToolkit();
//			name = new JComboBox(toolkit.getFontList());
//			style = new JComboBox(str_style);
//			size = new JComboBox(str_size);
//			size.setEditable(true);
//			JButton b_apply = new JButton("Apply");
//			b_apply.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent e) {
//					int[] columns = table.getSelectedColumns();
//					int[] rows = table.getSelectedRows();
//					if ((rows == null) || (columns == null))
//						return;
//					if ((rows.length < 1) || (columns.length < 1))
//						return;
//					Font font = new Font((String) name.getSelectedItem(), style
//							.getSelectedIndex(), Integer.parseInt((String) size
//							.getSelectedItem()));
//					cellAtt.setFont(font, rows, columns);
//					table.clearSelection();
//					table.revalidate();
//					table.repaint();
//				}
//			});
//			p3.add(name);
//			p3.add(style);
//			p3.add(size);
//			p4.add(b_apply);
//			box.add(p2);
//			box.add(p3);
//			add(box);
//			add(p4);
//		}
//	}
//
//	public static void main(String[] args) {
//		MultiFontCellTableExample frame = new MultiFontCellTableExample();
//		frame.addWindowListener(new WindowAdapter() {
//			public void windowClosing(WindowEvent e) {
//				System.exit(0);
//			}
//		});
//	}
//}
//
///**
// * @version 1.0 11/22/98
// */
//
//class AttributiveCellTableModel extends DefaultTableModel {
//
//	protected CellAttribute cellAtt;
//
//	public AttributiveCellTableModel() {
//		this((Vector) null, 0);
//	}
//
//	public AttributiveCellTableModel(int numRows, int numColumns) {
//		Vector names = new Vector(numColumns);
//		names.setSize(numColumns);
//		setColumnIdentifiers(names);
//		dataVector = new Vector();
//		setNumRows(numRows);
//		cellAtt = new DefaultCellAttribute(numRows, numColumns);
//	}
//
//	public AttributiveCellTableModel(Vector columnNames, int numRows) {
//		setColumnIdentifiers(columnNames);
//		dataVector = new Vector();
//		setNumRows(numRows);
//		cellAtt = new DefaultCellAttribute(numRows, columnNames.size());
//	}
//
//	public AttributiveCellTableModel(Object[] columnNames, int numRows) {
//		this(convertToVector(columnNames), numRows);
//	}
//
//	public AttributiveCellTableModel(Vector data, Vector columnNames) {
//		setDataVector(data, columnNames);
//	}
//
//	public AttributiveCellTableModel(Object[][] data, Object[] columnNames) {
//		setDataVector(data, columnNames);
//	}
//
//	public void setDataVector(Vector newData, Vector columnNames) {
//		if (newData == null)
//			throw new IllegalArgumentException(
//					"setDataVector() - Null parameter");
//		dataVector = new Vector(0);
//		setColumnIdentifiers(columnNames);
//		dataVector = newData;
//
//		//
//		cellAtt = new DefaultCellAttribute(dataVector.size(),
//				columnIdentifiers.size());
//
//		newRowsAdded(new TableModelEvent(this, 0, getRowCount() - 1,
//				TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT));
//	}
//
//	public void addColumn(Object columnName, Vector columnData) {
//		if (columnName == null)
//			throw new IllegalArgumentException("addColumn() - null parameter");
//		columnIdentifiers.addElement(columnName);
//		int index = 0;
//		Enumeration eeration = dataVector.elements();
//		while (eeration.hasMoreElements()) {
//			Object value;
//			if ((columnData != null) && (index < columnData.size()))
//				value = columnData.elementAt(index);
//			else
//				value = null;
//			((Vector) eeration.nextElement()).addElement(value);
//			index++;
//		}
//
//		//
//		cellAtt.addColumn();
//
//		fireTableStructureChanged();
//	}
//
//	public void addRow(Vector rowData) {
//		Vector newData = null;
//		if (rowData == null) {
//			newData = new Vector(getColumnCount());
//		} else {
//			rowData.setSize(getColumnCount());
//		}
//		dataVector.addElement(newData);
//
//		//
//		cellAtt.addRow();
//
//		newRowsAdded(new TableModelEvent(this, getRowCount() - 1,
//				getRowCount() - 1, TableModelEvent.ALL_COLUMNS,
//				TableModelEvent.INSERT));
//	}
//
//	public void insertRow(int row, Vector rowData) {
//		if (rowData == null) {
//			rowData = new Vector(getColumnCount());
//		} else {
//			rowData.setSize(getColumnCount());
//		}
//
//		dataVector.insertElementAt(rowData, row);
//
//		//
//		cellAtt.insertRow(row);
//
//		newRowsAdded(new TableModelEvent(this, row, row,
//				TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT));
//	}
//
//	public CellAttribute getCellAttribute() {
//		return cellAtt;
//	}
//
//	public void setCellAttribute(CellAttribute newCellAtt) {
//		int numColumns = getColumnCount();
//		int numRows = getRowCount();
//		if ((newCellAtt.getSize().width != numColumns)
//				|| (newCellAtt.getSize().height != numRows)) {
//			newCellAtt.setSize(new Dimension(numRows, numColumns));
//		}
//		cellAtt = newCellAtt;
//		fireTableDataChanged();
//	}
//
//	/*
//	 * public void changeCellAttribute(int row, int column, Object command) {
//	 * cellAtt.changeAttribute(row, column, command); }
//	 * 
//	 * public void changeCellAttribute(int[] rows, int[] columns, Object
//	 * command) { cellAtt.changeAttribute(rows, columns, command); }
//	 */
//
//}
//
///**
// * @version 1.0 11/22/98
// */
//
//class DefaultCellAttribute
//// implements CellAttribute ,CellSpan {
//		implements CellAttribute, CellSpan, ColoredCell, CellFont {
//
//	//
//	// !!!! CAUTION !!!!!
//	// these values must be synchronized to Table data
//	//
//	protected int rowSize;
//
//	protected int columnSize;
//
//	protected int[][][] span; // CellSpan
//
//	protected Color[][] foreground; // ColoredCell
//
//	protected Color[][] background; //
//
//	protected Font[][] font; // CellFont
//
//	public DefaultCellAttribute() {
//		this(1, 1);
//	}
//
//	public DefaultCellAttribute(int numRows, int numColumns) {
//		setSize(new Dimension(numColumns, numRows));
//	}
//
//	protected void initValue() {
//		for (int i = 0; i < span.length; i++) {
//			for (int j = 0; j < span[i].length; j++) {
//				span[i][j][CellSpan.COLUMN] = 1;
//				span[i][j][CellSpan.ROW] = 1;
//			}
//		}
//	}
//
//	//
//	// CellSpan
//	//
//	public int[] getSpan(int row, int column) {
//		if (isOutOfBounds(row, column)) {
//			int[] ret_code = { 1, 1 };
//			return ret_code;
//		}
//		return span[row][column];
//	}
//
//	public void setSpan(int[] span, int row, int column) {
//		if (isOutOfBounds(row, column))
//			return;
//		this.span[row][column] = span;
//	}
//
//	public boolean isVisible(int row, int column) {
//		if (isOutOfBounds(row, column))
//			return false;
//		if ((span[row][column][CellSpan.COLUMN] < 1)
//				|| (span[row][column][CellSpan.ROW] < 1))
//			return false;
//		return true;
//	}
//
//	public void combine(int[] rows, int[] columns) {
//		if (isOutOfBounds(rows, columns))
//			return;
//		int rowSpan = rows.length;
//		int columnSpan = columns.length;
//		int startRow = rows[0];
//		int startColumn = columns[0];
//		for (int i = 0; i < rowSpan; i++) {
//			for (int j = 0; j < columnSpan; j++) {
//				if ((span[startRow + i][startColumn + j][CellSpan.COLUMN] != 1)
//						|| (span[startRow + i][startColumn + j][CellSpan.ROW] != 1)) {
//					// System.out.println("can't combine");
//					return;
//				}
//			}
//		}
//		for (int i = 0, ii = 0; i < rowSpan; i++, ii--) {
//			for (int j = 0, jj = 0; j < columnSpan; j++, jj--) {
//				span[startRow + i][startColumn + j][CellSpan.COLUMN] = jj;
//				span[startRow + i][startColumn + j][CellSpan.ROW] = ii;
//				// System.out.println("r " +ii +" c " +jj);
//			}
//		}
//		span[startRow][startColumn][CellSpan.COLUMN] = columnSpan;
//		span[startRow][startColumn][CellSpan.ROW] = rowSpan;
//
//	}
//
//	public void split(int row, int column) {
//		if (isOutOfBounds(row, column))
//			return;
//		int columnSpan = span[row][column][CellSpan.COLUMN];
//		int rowSpan = span[row][column][CellSpan.ROW];
//		for (int i = 0; i < rowSpan; i++) {
//			for (int j = 0; j < columnSpan; j++) {
//				span[row + i][column + j][CellSpan.COLUMN] = 1;
//				span[row + i][column + j][CellSpan.ROW] = 1;
//			}
//		}
//	}
//
//	//
//	// ColoredCell
//	//
//	public Color getForeground(int row, int column) {
//		if (isOutOfBounds(row, column))
//			return null;
//		return foreground[row][column];
//	}
//
//	public void setForeground(Color color, int row, int column) {
//		if (isOutOfBounds(row, column))
//			return;
//		foreground[row][column] = color;
//	}
//
//	public void setForeground(Color color, int[] rows, int[] columns) {
//		if (isOutOfBounds(rows, columns))
//			return;
//		setValues(foreground, color, rows, columns);
//	}
//
//	public Color getBackground(int row, int column) {
//		if (isOutOfBounds(row, column))
//			return null;
//		return background[row][column];
//	}
//
//	public void setBackground(Color color, int row, int column) {
//		if (isOutOfBounds(row, column))
//			return;
//		background[row][column] = color;
//	}
//
//	public void setBackground(Color color, int[] rows, int[] columns) {
//		if (isOutOfBounds(rows, columns))
//			return;
//		setValues(background, color, rows, columns);
//	}
//
//	//
//
//	//
//	// CellFont
//	//
//	public Font getFont(int row, int column) {
//		if (isOutOfBounds(row, column))
//			return null;
//		return font[row][column];
//	}
//
//	public void setFont(Font font, int row, int column) {
//		if (isOutOfBounds(row, column))
//			return;
//		this.font[row][column] = font;
//	}
//
//	public void setFont(Font font, int[] rows, int[] columns) {
//		if (isOutOfBounds(rows, columns))
//			return;
//		setValues(this.font, font, rows, columns);
//	}
//
//	//
//
//	//
//	// CellAttribute
//	//
//	public void addColumn() {
//		int[][][] oldSpan = span;
//		int numRows = oldSpan.length;
//		int numColumns = oldSpan[0].length;
//		span = new int[numRows][numColumns + 1][2];
//		System.arraycopy(oldSpan, 0, span, 0, numRows);
//		for (int i = 0; i < numRows; i++) {
//			span[i][numColumns][CellSpan.COLUMN] = 1;
//			span[i][numColumns][CellSpan.ROW] = 1;
//		}
//	}
//
//	public void addRow() {
//		int[][][] oldSpan = span;
//		int numRows = oldSpan.length;
//		int numColumns = oldSpan[0].length;
//		span = new int[numRows + 1][numColumns][2];
//		System.arraycopy(oldSpan, 0, span, 0, numRows);
//		for (int i = 0; i < numColumns; i++) {
//			span[numRows][i][CellSpan.COLUMN] = 1;
//			span[numRows][i][CellSpan.ROW] = 1;
//		}
//	}
//
//	public void insertRow(int row) {
//		int[][][] oldSpan = span;
//		int numRows = oldSpan.length;
//		int numColumns = oldSpan[0].length;
//		span = new int[numRows + 1][numColumns][2];
//		if (0 < row) {
//			System.arraycopy(oldSpan, 0, span, 0, row - 1);
//		}
//		System.arraycopy(oldSpan, 0, span, row, numRows - row);
//		for (int i = 0; i < numColumns; i++) {
//			span[row][i][CellSpan.COLUMN] = 1;
//			span[row][i][CellSpan.ROW] = 1;
//		}
//	}
//
//	public Dimension getSize() {
//		return new Dimension(rowSize, columnSize);
//	}
//
//	public void setSize(Dimension size) {
//		columnSize = size.width;
//		rowSize = size.height;
//		span = new int[rowSize][columnSize][2]; // 2: COLUMN,ROW
//		foreground = new Color[rowSize][columnSize];
//		background = new Color[rowSize][columnSize];
//		font = new Font[rowSize][columnSize];
//		initValue();
//	}
//
//	/*
//	 * public void changeAttribute(int row, int column, Object command) { }
//	 * 
//	 * public void changeAttribute(int[] rows, int[] columns, Object command) {
//	 * }
//	 */
//
//	protected boolean isOutOfBounds(int row, int column) {
//		if ((row < 0) || (rowSize <= row) || (column < 0)
//				|| (columnSize <= column)) {
//			return true;
//		}
//		return false;
//	}
//
//	protected boolean isOutOfBounds(int[] rows, int[] columns) {
//		for (int i = 0; i < rows.length; i++) {
//			if ((rows[i] < 0) || (rowSize <= rows[i]))
//				return true;
//		}
//		for (int i = 0; i < columns.length; i++) {
//			if ((columns[i] < 0) || (columnSize <= columns[i]))
//				return true;
//		}
//		return false;
//	}
//
//	protected void setValues(Object[][] target, Object value, int[] rows,
//			int[] columns) {
//		for (int i = 0; i < rows.length; i++) {
//			int row = rows[i];
//			for (int j = 0; j < columns.length; j++) {
//				int column = columns[j];
//				target[row][column] = value;
//			}
//		}
//	}
//}
//
///**
// * @version 1.0 11/22/98
// */
//
///*
// * (swing1.1beta3)
// */
///**
// * @version 1.0 11/22/98
// */
//
//interface CellAttribute {
//
//	public void addColumn();
//
//	public void addRow();
//
//	public void insertRow(int row);
//
//	public Dimension getSize();
//
//	public void setSize(Dimension size);
//
//}
//
//interface ColoredCell {
//
//	public Color getForeground(int row, int column);
//
//	public void setForeground(Color color, int row, int column);
//
//	public void setForeground(Color color, int[] rows, int[] columns);
//
//	public Color getBackground(int row, int column);
//
//	public void setBackground(Color color, int row, int column);
//
//	public void setBackground(Color color, int[] rows, int[] columns);
//
//}
//
///**
// * @version 1.0 11/22/98
// */
//
//class AttributiveCellRenderer extends JLabel implements TableCellRenderer {
//	protected static Border noFocusBorder;
//
//	public AttributiveCellRenderer() {
//		noFocusBorder = new EmptyBorder(1, 2, 1, 2);
//		setOpaque(true);
//		setBorder(noFocusBorder);
//	}
//
//	public Component getTableCellRendererComponent(JTable table, Object value,
//			boolean isSelected, boolean hasFocus, int row, int column) {
//		Color foreground = null;
//		Color background = null;
//		Font font = null;
//		TableModel model = table.getModel();
//		if (model instanceof AttributiveCellTableModel) {
//			CellAttribute cellAtt = ((AttributiveCellTableModel) model)
//					.getCellAttribute();
//			if (cellAtt instanceof ColoredCell) {
//				foreground = ((ColoredCell) cellAtt).getForeground(row, column);
//				background = ((ColoredCell) cellAtt).getBackground(row, column);
//			}
//			if (cellAtt instanceof CellFont) {
//				font = ((CellFont) cellAtt).getFont(row, column);
//			}
//		}
//		if (isSelected) {
//			setForeground((foreground != null) ? foreground : table
//					.getSelectionForeground());
//			setBackground(table.getSelectionBackground());
//		} else {
//			setForeground((foreground != null) ? foreground : table
//					.getForeground());
//			setBackground((background != null) ? background : table
//					.getBackground());
//		}
//		setFont((font != null) ? font : table.getFont());
//
//		if (hasFocus) {
//			setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
//			if (table.isCellEditable(row, column)) {
//				setForeground((foreground != null) ? foreground : UIManager
//						.getColor("Table.focusCellForeground"));
//				setBackground(UIManager.getColor("Table.focusCellBackground"));
//			}
//		} else {
//			setBorder(noFocusBorder);
//		}
//		setValue(value);
//		return this;
//	}
//
//	protected void setValue(Object value) {
//		setText((value == null) ? "" : value.toString());
//	}
//}
//
///**
// * @version 1.0 11/22/98
// */
//
//class TextPreviewLabel extends JLabel {
//	private String sampleText = "  Sample Text  Sample Text  ";
//
//	boolean isForgroundSelection;
//
//	public TextPreviewLabel() {
//		this(Color.black, Color.white, true);
//	}
//
//	public TextPreviewLabel(Color fore, Color back, boolean isForgroundSelection) {
//		setOpaque(true);
//		setForeground(fore);
//		setBackground(back);
//		this.isForgroundSelection = isForgroundSelection;
//		setText(sampleText);
//	}
//
//	public void setForeground(Color col) {
//		if (isForgroundSelection) {
//			super.setForeground(col);
//		} else {
//			super.setBackground(col);
//		}
//	}
//
//}
//
//class ColorChooserDialog extends JDialog {
//	private Color initialColor;
//
//	private Color retColor;
//
//	private JColorChooser chooserPane;
//
//	public ColorChooserDialog(Component c, String title,
//			final JColorChooser chooserPane) {
//		super(JOptionPane.getFrameForComponent(c), title, true);
//		setResizable(false);
//
//		this.chooserPane = chooserPane;
//
//		String okString = UIManager.getString("ColorChooser.okText");
//		String cancelString = UIManager.getString("ColorChooser.cancelText");
//		String resetString = UIManager.getString("ColorChooser.resetText");
//
//		Container contentPane = getContentPane();
//		contentPane.setLayout(new BorderLayout());
//		contentPane.add(chooserPane, BorderLayout.CENTER);
//
//		JPanel buttonPane = new JPanel();
//		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
//		JButton okButton = new JButton(okString);
//		getRootPane().setDefaultButton(okButton);
//		okButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				retColor = chooserPane.getColor();
//				setVisible(false);
//			}
//		});
//		buttonPane.add(okButton);
//
//		JButton cancelButton = new JButton(cancelString);
//		cancelButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				retColor = null;
//				setVisible(false);
//			}
//		});
//		buttonPane.add(cancelButton);
//
//		JButton resetButton = new JButton(resetString);
//		resetButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				chooserPane.setColor(initialColor);
//			}
//		});
//		buttonPane.add(resetButton);
//		contentPane.add(buttonPane, BorderLayout.SOUTH);
//
//		pack();
//		setLocationRelativeTo(c);
//		addWindowListener(new WindowAdapter() {
//			public void windowClosing(WindowEvent e) {
//				setVisible(false);
//			}
//		});
//	}
//
//	public Color getColor() {
//		return retColor;
//	}
//
//}
//
//class TextColorChooser extends JColorChooser {
//
//	public TextColorChooser(Color target, Color reference,
//			boolean isForgroundSelection) {
//		super(target);
//		if (isForgroundSelection) {
//			setPreviewPanel(new TextPreviewLabel(target, reference,
//					isForgroundSelection));
//		} else {
//			setPreviewPanel(new TextPreviewLabel(reference, target,
//					isForgroundSelection));
//		}
//		updateUI();
//	}
//
//	public Color showDialog(Component component, String title) {
//		ColorChooserDialog dialog = new ColorChooserDialog(component, title,
//				this);
//		dialog.show();
//		Color col = dialog.getColor();
//		dialog.dispose();
//		return col;
//	}
//
//}
//
//interface CellFont {
//
//	public Font getFont(int row, int column);
//
//	public void setFont(Font font, int row, int column);
//
//	public void setFont(Font font, int[] rows, int[] columns);
//
//}
//
//interface CellSpan {
//	public final int ROW = 0;
//
//	public final int COLUMN = 1;
//
//	public int[] getSpan(int row, int column);
//
//	public void setSpan(int[] span, int row, int column);
//
//	public boolean isVisible(int row, int column);
//
//	public void combine(int[] rows, int[] columns);
//
//	public void split(int row, int column);
//
//}
