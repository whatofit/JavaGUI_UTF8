//package com.java2s.table;
//
////Example from http://www.crionics.com/products/opensource/faq/swing_ex/SwingExamples.html
///*
// * (swing1.1beta3)
// */
//
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Component;
//import java.awt.Container;
//import java.awt.Dimension;
//import java.awt.FlowLayout;
//import java.awt.Font;
//import java.awt.Graphics;
//import java.awt.GridLayout;
//import java.awt.Point;
//import java.awt.Rectangle;
//import java.awt.Toolkit;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
//import java.util.Enumeration;
//import java.util.Vector;
//
//import javax.swing.BorderFactory;
//import javax.swing.Box;
//import javax.swing.BoxLayout;
//import javax.swing.JButton;
//import javax.swing.JColorChooser;
//import javax.swing.JComboBox;
//import javax.swing.JComponent;
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
//import javax.swing.event.ListSelectionEvent;
//import javax.swing.event.TableModelEvent;
//import javax.swing.plaf.basic.BasicTableUI;
//import javax.swing.table.DefaultTableModel;
//import javax.swing.table.TableCellRenderer;
//import javax.swing.table.TableColumn;
//import javax.swing.table.TableModel;
//
///**
// * @version 1.0 11/22/98
// */
//public class MixedExample extends JFrame {
//
//	public MixedExample() {
//		super("Mixed Example");
//
//		AttributiveCellTableModel ml = new AttributiveCellTableModel(20, 5) {
//			public Object getValueAt(int row, int col) {
//				return "" + row + "," + col;
//			}
//		};
//		CellAttribute cellAtt = ml.getCellAttribute();
//		MultiSpanCellTable table = new MultiSpanCellTable(ml);
//		table.setCellSelectionEnabled(true);
//		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
//		table.setDefaultRenderer(Object.class, new AttributiveCellRenderer());
//		JScrollPane scroll = new JScrollPane(table);
//
//		ColorPanel colorPanel = new ColorPanel(table, (ColoredCell) cellAtt);
//		FontPanel fontPanel = new FontPanel(table, (CellFont) cellAtt);
//		SpanPanel spanPanel = new SpanPanel(table, (CellSpan) cellAtt);
//		Box boxAtt = new Box(BoxLayout.Y_AXIS);
//		boxAtt.add(colorPanel);
//		boxAtt.add(fontPanel);
//		boxAtt.add(spanPanel);
//
//		Box box = new Box(BoxLayout.X_AXIS);
//		box.add(scroll);
//		box.add(new JSeparator(SwingConstants.HORIZONTAL));
//		box.add(boxAtt);
//		getContentPane().add(box);
//		setSize(400, 300);
//		setVisible(true);
//	}
//
//	class ColorPanel extends JPanel {
//		JTable table;
//
//		ColoredCell cellAtt;
//
//		ColorPanel(final JTable table, final ColoredCell cellAtt) {
//			this.table = table;
//			this.cellAtt = cellAtt;
//			setLayout(new GridLayout(2, 1));
//			setBorder(BorderFactory.createTitledBorder("Color"));
//			JButton b_fore = new JButton("Foreground");
//			b_fore.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent e) {
//					changeColor(true);
//				}
//			});
//			JButton b_back = new JButton("Background");
//			b_back.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent e) {
//					changeColor(false);
//				}
//			});
//			JPanel p_buttons = new JPanel();
//			add(b_fore);
//			add(b_back);
//		}
//
//		private final void changeColor(boolean isForeground) {
//			int[] columns = table.getSelectedColumns();
//			int[] rows = table.getSelectedRows();
//			if ((rows == null) || (columns == null))
//				return;
//			if ((rows.length < 1) || (columns.length < 1))
//				return;
//			Color target = cellAtt.getForeground(rows[0], columns[0]);
//			Color reference = cellAtt.getBackground(rows[0], columns[0]);
//			for (int i = 0; i < rows.length; i++) {
//				int row = rows[i];
//				for (int j = 0; j < columns.length; j++) {
//					int column = columns[j];
//					target = (target != cellAtt.getForeground(row, column)) ? null
//							: target;
//					reference = (reference != cellAtt
//							.getBackground(row, column)) ? null : reference;
//				}
//			}
//			String title;
//			if (isForeground) {
//				target = (target != null) ? target : table.getForeground();
//				reference = (reference != null) ? reference : table
//						.getBackground();
//				title = "Foreground Color";
//			} else {
//				target = (reference != null) ? reference : table
//						.getBackground();
//				reference = (target != null) ? target : table.getForeground();
//				title = "Foreground Color";
//			}
//			TextColorChooser chooser = new TextColorChooser(target, reference,
//					isForeground);
//			Color color = chooser.showDialog(MixedExample.this, title);
//			if (color != null) {
//				if (isForeground) {
//					cellAtt.setForeground(color, rows, columns);
//				} else {
//					cellAtt.setBackground(color, rows, columns);
//				}
//				table.clearSelection();
//				table.revalidate();
//				table.repaint();
//			}
//		}
//	}
//
//	class FontPanel extends JPanel {
//		String[] str_size = { "10", "12", "14", "16", "20" };
//
//		String[] str_style = { "PLAIN", "BOLD", "ITALIC" };
//
//		JComboBox name, style, size;
//
//		FontPanel(final JTable table, final CellFont cellAtt) {
//			setLayout(new BorderLayout());
//			setBorder(BorderFactory.createTitledBorder("Font"));
//			Box box = new Box(BoxLayout.X_AXIS);
//			JPanel p2 = new JPanel(new GridLayout(3, 1));
//			JPanel p3 = new JPanel(new GridLayout(3, 1));
//			JPanel p4 = new JPanel(new BorderLayout());
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
//			p4.add(BorderLayout.CENTER, b_apply);
//			box.add(p2);
//			box.add(p3);
//			add(BorderLayout.CENTER, box);
//			add(BorderLayout.SOUTH, p4);
//		}
//	}
//
//	class SpanPanel extends JPanel {
//		JTable table;
//
//		CellSpan cellAtt;
//
//		SpanPanel(final JTable table, final CellSpan cellAtt) {
//			this.table = table;
//			this.cellAtt = cellAtt;
//			setLayout(new GridLayout(2, 1));
//			setBorder(BorderFactory.createTitledBorder("Span"));
//			JButton b_one = new JButton("Combine");
//			b_one.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent e) {
//					int[] columns = table.getSelectedColumns();
//					int[] rows = table.getSelectedRows();
//					cellAtt.combine(rows, columns);
//					table.clearSelection();
//					table.revalidate();
//					table.repaint();
//				}
//			});
//			JButton b_split = new JButton("Split");
//			b_split.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent e) {
//					int column = table.getSelectedColumn();
//					int row = table.getSelectedRow();
//					cellAtt.split(row, column);
//					table.clearSelection();
//					table.revalidate();
//					table.repaint();
//				}
//			});
//			add(b_one);
//			add(b_split);
//		}
//	}
//
//	public static void main(String[] args) {
//		MixedExample frame = new MixedExample();
//		frame.addWindowListener(new WindowAdapter() {
//			public void windowClosing(WindowEvent e) {
//				System.exit(0);
//			}
//		});
//	}
//}
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
//		System.out.println("numRows, numColumn="+numRows +","+ numColumns);
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
//		dataVector = new Vector();
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
//		Enumeration enumeration = dataVector.elements();
//		while (enumeration.hasMoreElements()) {
//			Object value;
//			if ((columnData != null) && (index < columnData.size()))
//				value = columnData.elementAt(index);
//			else
//				value = null;
//			((Vector) enumeration.nextElement()).addElement(value);
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
//
//class MultiSpanCellTable extends JTable {
//
//	public MultiSpanCellTable(TableModel model) {
//		super(model);
//		setUI(new MultiSpanCellTableUI());
//		getTableHeader().setReorderingAllowed(false);
//		setCellSelectionEnabled(true);
//		setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
//	}
//
//	public Rectangle getCellRect(int row, int column, boolean includeSpacing) {
//		Rectangle sRect = super.getCellRect(row, column, includeSpacing);
//		if ((row < 0) || (column < 0) || (getRowCount() <= row)
//				|| (getColumnCount() <= column)) {
//			return sRect;
//		}
//		CellSpan cellAtt = (CellSpan) ((AttributiveCellTableModel) getModel())
//				.getCellAttribute();
//		if (!cellAtt.isVisible(row, column)) {
//			int temp_row = row;
//			int temp_column = column;
//			row += cellAtt.getSpan(temp_row, temp_column)[CellSpan.ROW];
//			column += cellAtt.getSpan(temp_row, temp_column)[CellSpan.COLUMN];
//		}
//		int[] n = cellAtt.getSpan(row, column);
//
//		int index = 0;
//		int columnMargin = getColumnModel().getColumnMargin();
//		Rectangle cellFrame = new Rectangle();
//		int aCellHeight = rowHeight + rowMargin;
//		cellFrame.y = row * aCellHeight;
//		cellFrame.height = n[CellSpan.ROW] * aCellHeight;
//
//		Enumeration enumeration = getColumnModel().getColumns();
//		while (enumeration.hasMoreElements()) {
//			TableColumn aColumn = (TableColumn) enumeration.nextElement();
//			cellFrame.width = aColumn.getWidth() + columnMargin;
//			if (index == column)
//				break;
//			cellFrame.x += cellFrame.width;
//			index++;
//		}
//		for (int i = 0; i < n[CellSpan.COLUMN] - 1; i++) {
//			TableColumn aColumn = (TableColumn) enumeration.nextElement();
//			cellFrame.width += aColumn.getWidth() + columnMargin;
//		}
//
//		if (!includeSpacing) {
//			Dimension spacing = getIntercellSpacing();
//			cellFrame.setBounds(cellFrame.x + spacing.width / 2, cellFrame.y
//					+ spacing.height / 2, cellFrame.width - spacing.width,
//					cellFrame.height - spacing.height);
//		}
//		return cellFrame;
//	}
//
//	private int[] rowColumnAtPoint(Point point) {
//		int[] retValue = { -1, -1 };
//		int row = point.y / (rowHeight + rowMargin);
//		if ((row < 0) || (getRowCount() <= row))
//			return retValue;
//		int column = getColumnModel().getColumnIndexAtX(point.x);
//
//		CellSpan cellAtt = (CellSpan) ((AttributiveCellTableModel) getModel())
//				.getCellAttribute();
//
//		if (cellAtt.isVisible(row, column)) {
//			retValue[CellSpan.COLUMN] = column;
//			retValue[CellSpan.ROW] = row;
//			return retValue;
//		}
//		retValue[CellSpan.COLUMN] = column
//				+ cellAtt.getSpan(row, column)[CellSpan.COLUMN];
//		retValue[CellSpan.ROW] = row
//				+ cellAtt.getSpan(row, column)[CellSpan.ROW];
//		return retValue;
//	}
//
//	public int rowAtPoint(Point point) {
//		return rowColumnAtPoint(point)[CellSpan.ROW];
//	}
//
//	public int columnAtPoint(Point point) {
//		return rowColumnAtPoint(point)[CellSpan.COLUMN];
//	}
//
//	public void columnSelectionChanged(ListSelectionEvent e) {
//		repaint();
//	}
//
//	public void valueChanged(ListSelectionEvent e) {
//		int firstIndex = e.getFirstIndex();
//		int lastIndex = e.getLastIndex();
//		if (firstIndex == -1 && lastIndex == -1) { // Selection cleared.
//			repaint();
//		}
//		Rectangle dirtyRegion = getCellRect(firstIndex, 0, false);
//		int numCoumns = getColumnCount();
//		int index = firstIndex;
//		for (int i = 0; i < numCoumns; i++) {
//			dirtyRegion.add(getCellRect(index, i, false));
//		}
//		index = lastIndex;
//		for (int i = 0; i < numCoumns; i++) {
//			dirtyRegion.add(getCellRect(index, i, false));
//		}
//		repaint(dirtyRegion.x, dirtyRegion.y, dirtyRegion.width,
//				dirtyRegion.height);
//	}
//
//}
//
//class MultiSpanCellTableUI extends BasicTableUI {
//
//	public void paint(Graphics g, JComponent c) {
//		Rectangle oldClipBounds = g.getClipBounds();
//		Rectangle clipBounds = new Rectangle(oldClipBounds);
//		int tableWidth = table.getColumnModel().getTotalColumnWidth();
//		clipBounds.width = Math.min(clipBounds.width, tableWidth);
//		g.setClip(clipBounds);
//
//		int firstIndex = table.rowAtPoint(new Point(0, clipBounds.y));
//		int lastIndex = table.getRowCount() - 1;
//
//		Rectangle rowRect = new Rectangle(0, 0, tableWidth,
//				table.getRowHeight() + table.getRowMargin());
//		rowRect.y = firstIndex * rowRect.height;
//
//		for (int index = firstIndex; index <= lastIndex; index++) {
//			if (rowRect.intersects(clipBounds)) {
//				// System.out.println(); // debug
//				// System.out.print("" + index +": "); // row
//				paintRow(g, index);
//			}
//			rowRect.y += rowRect.height;
//		}
//		g.setClip(oldClipBounds);
//	}
//
//	private void paintRow(Graphics g, int row) {
//		Rectangle rect = g.getClipBounds();
//		boolean drawn = false;
//
//		AttributiveCellTableModel tableModel = (AttributiveCellTableModel) table
//				.getModel();
//		CellSpan cellAtt = (CellSpan) tableModel.getCellAttribute();
//		int numColumns = table.getColumnCount();
//
//		for (int column = 0; column < numColumns; column++) {
//			Rectangle cellRect = table.getCellRect(row, column, true);
//			int cellRow, cellColumn;
//			if (cellAtt.isVisible(row, column)) {
//				cellRow = row;
//				cellColumn = column;
//				// System.out.print(" "+column+" "); // debug
//			} else {
//				cellRow = row + cellAtt.getSpan(row, column)[CellSpan.ROW];
//				cellColumn = column
//						+ cellAtt.getSpan(row, column)[CellSpan.COLUMN];
//				// System.out.print(" ("+column+")"); // debug
//			}
//			if (cellRect.intersects(rect)) {
//				drawn = true;
//				paintCell(g, cellRect, cellRow, cellColumn);
//			} else {
//				if (drawn)
//					break;
//			}
//		}
//
//	}
//
//	private void paintCell(Graphics g, Rectangle cellRect, int row, int column) {
//		int spacingHeight = table.getRowMargin();
//		int spacingWidth = table.getColumnModel().getColumnMargin();
//
//		Color c = g.getColor();
//		g.setColor(table.getGridColor());
//		g.drawRect(cellRect.x, cellRect.y, cellRect.width - 1,
//				cellRect.height - 1);
//		g.setColor(c);
//
//		cellRect.setBounds(cellRect.x + spacingWidth / 2, cellRect.y
//				+ spacingHeight / 2, cellRect.width - spacingWidth,
//				cellRect.height - spacingHeight);
//
//		if (table.isEditing() && table.getEditingRow() == row
//				&& table.getEditingColumn() == column) {
//			Component component = table.getEditorComponent();
//			component.setBounds(cellRect);
//			component.validate();
//		} else {
//			TableCellRenderer renderer = table.getCellRenderer(row, column);
//			Component component = table.prepareRenderer(renderer, row, column);
//
//			if (component.getParent() == null) {
//				rendererPane.add(component);
//			}
//			rendererPane.paintComponent(g, component, table, cellRect.x,
//					cellRect.y, cellRect.width, cellRect.height, true);
//		}
//	}
//}
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
//
//}
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